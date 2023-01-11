package com.oti.myuniversity.domain.board.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.board.model.Board;
import com.oti.myuniversity.domain.board.model.BoardFile;
import com.oti.myuniversity.domain.board.service.IBoardFileService;
import com.oti.myuniversity.domain.board.service.IBoardService;
import com.oti.myuniversity.domain.member.model.Member;

@Controller
public class BoardController {
	static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	IBoardService boardService;
	
	@Autowired
	IBoardFileService boardFileService;
	
	@Autowired
	Pager pager;
	
	//글 목록 조회
	@RequestMapping("/board/cat/{categoryType}/{pageNo}")
	public String getListByCategoryType(@PathVariable int categoryType, @PathVariable int pageNo, HttpSession session, Model model) {
		
		model.addAttribute("categoryType", categoryType);
		List<Board> boardList = boardService.selectArticleListByCatoryType(categoryType, pageNo);
		model.addAttribute("boardList", boardList);
		
		//paging start
		int totalRows = boardService.selectTotalArticleCount(categoryType);
		 pager.init(10, 5, totalRows, pageNo);
		model.addAttribute("pager", pager);
		
		if(categoryType == 1) {
			return "board/library/list";
		} else {
			return "board/report/list";
		}
	}
	
	@RequestMapping("/board/cat/{categoryType}")
	public String getListByCategoryType(@PathVariable int categoryType, HttpSession session, Model model) {
		
		return getListByCategoryType(categoryType, 1, session, model);
	}
	
	//글 상세 보기
	@RequestMapping("/board/{boardId}/{pageNo}")
	public String getBoardDetails(@PathVariable int pageNo,@PathVariable int boardId,HttpSession session, Model model) {
		Board board = boardService.selectArticle(boardId);
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
		logger.info("getBoardDetails: " + board.toString());
		
		
		if(board.getBoardCategory()==1) {
			return "board/library/viewdetail";

		} else {
			String memberId = ((Member)session.getAttribute("member")).getMemberId();

			if(memberId.equals("admin")) {
				//관리자
				//과제를 제출한 학생들의 board객체..
				List<Board> studentsBoard = boardService.selectStudentsReport(boardId);
				for(Board student:studentsBoard) {
					System.out.println("student 이름 출력 시도: "+student.getMemberName());
				}
				model.addAttribute("studentsBoard", studentsBoard);
				return "board/report/admindetail";
			} else {
				//학생
				//관리자가 쓴 board 객체 , 학생이 쓴 board객체 두개 전달
				Board reportBoard = boardService.selectReport(boardId, memberId);
				model.addAttribute("reportBoard", reportBoard);
				
				return "board/report/studentdetail";
			}
		}
	}
	
	@RequestMapping("/board/{boardId}")
	public String  getBoardDetails(@PathVariable int boardId,HttpSession session, Model model) {
		return getBoardDetails(1, boardId, session, model);
	}
	
	
	//글 작성하기
	@RequestMapping(value= "/board/{categoryType}/write", method=RequestMethod.GET)
	public String writeBoard(@PathVariable int categoryType, Model model) {

		model.addAttribute("categoryType", categoryType);
		
		if(categoryType == 1) {
			return "board/library/write";
		} else {
			return "board/report/write";
		}
	}
	
	@RequestMapping(value= "/board/report/write", method=RequestMethod.POST)
	public String writeBoard(Board board, @RequestParam String time , @RequestParam MultipartFile[] files,
			HttpSession session, RedirectAttributes redirectAttrs) {
		logger.info("/board/write: " + board.toString());
		System.out.println("getBoardDate 함: "+ board.getReportDeadline());
		//string -> Timestamp
		if(time!=null && !time.equals("")) {
			String timeStamptool = board.getReportDeadline() + " " + time + ":00.0";
			board.setReportDeadlineTime(Timestamp.valueOf(timeStamptool));
			System.out.println("getReportDeadLineTime함" + board.getReportDeadlineTime());
		}
		try {
			board.setBoardTitle(Jsoup.clean(board.getBoardTitle(), Whitelist.basic()));
			board.setBoardContent(Jsoup.clean(board.getBoardContent(), Whitelist.basic()));
			
			
			
			ArrayList<BoardFile> fileList = new ArrayList<BoardFile>();
			for(int i = 0; i<files.length; i++) {
				logger.info("/board/write: " + files[i].getOriginalFilename());
				BoardFile file = new BoardFile();
				file.setBoardFileName(files[i].getOriginalFilename());
				file.setBoardFileSize(files[i].getSize());
				file.setBoardFileContentType(files[i].getContentType());
				file.setBoardFileData(files[i].getBytes());
				fileList.add(file);
				logger.info("/board/write : " + file.toString());
			}
		
			if(fileList!=null && !fileList.isEmpty()) {
				boardService.insertNoticeReport(board, fileList);
			} else {
				boardService.insertNoticeReport(board);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		
		//해당 게시글의 1페이지 목록으로 이동
			return "redirect:/board/cat/" + board.getBoardCategory(); 		
	}
	
	//aop쓸 수 있을까??
	@RequestMapping(value= "/board/libary/write", method=RequestMethod.POST)
	public String writeBoard(Board board,  @RequestParam MultipartFile[] files, HttpSession session, RedirectAttributes redirectAttrs) {
		logger.info("/board/write: " + board.toString());

		try {
			board.setBoardTitle(Jsoup.clean(board.getBoardTitle(), Whitelist.basic()));
			board.setBoardContent(Jsoup.clean(board.getBoardContent(), Whitelist.basic()));
			
			if(files!=null) {
				boardService.insertLibrary(board, files);
			}else {
				boardService.insertLibrary(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		
		//해당 게시글의 1페이지 목록으로 이동
			return "redirect:/board/cat/" + board.getBoardCategory(); 		
	}
	
	//학생의 과제 제출 
	@RequestMapping(value="/board/report/submit", method=RequestMethod.POST)
	public String submitReport(Board board,@RequestParam int pageNo ,@RequestParam MultipartFile[] files, HttpSession session, RedirectAttributes redirectAttrs ) {
		
		try {
			board.setBoardTitle(Jsoup.clean(board.getBoardTitle(), Whitelist.basic()));
			board.setBoardContent(Jsoup.clean(board.getBoardContent(), Whitelist.basic()));
			
			ArrayList<BoardFile> fileList = new ArrayList<BoardFile>();
			System.out.println("멀티파드파일[] files 길이 :" + files.length);

			for(int i = 0; i<files.length; i++) {
				logger.info("/board/write: " + files[i].getOriginalFilename());
				BoardFile file = new BoardFile();
				file.setBoardFileName(files[i].getOriginalFilename());
				file.setBoardFileSize(files[i].getSize());
				file.setBoardFileContentType(files[i].getContentType());
				file.setBoardFileData(files[i].getBytes());
				System.out.println("file"+ i + "번째 파일의 fileName:" +  files[i].getOriginalFilename());
				fileList.add(file);
				logger.info("/board/write : " + file.toString());
			}
			System.out.println("fileList 개수: " + fileList.size());
			if(fileList!=null && !fileList.isEmpty() ) {
				boardService.insertReport(board, fileList);
			} else {
				boardService.insertReport(board);
			}
		
		}catch(Exception e){
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		
		return "redirect:/board/"+ board.getReportNoticeId() + "/" + pageNo;
	}
	
	//자료실 글 수정하기
	@RequestMapping(value="/board/update/{boardId}", method=RequestMethod.GET)
	public String updateLibary(@PathVariable int boardId, @RequestParam int pageNo, Model model) {
		Board board = boardService.selectArticle(boardId);
		int categoryType = board.getBoardCategory();
		
		model.addAttribute("categoryType", categoryType);
		model.addAttribute("board", board);
		
		if(categoryType ==1) {
			return "board/libarary/update";
		} else {
			return "board/report/update";
		}
	}
	
	@RequestMapping(value="board/library/update", method=RequestMethod.POST)
	public String updateLibrary(Board newBoard, @RequestParam int pageNo, @RequestParam MultipartFile[] files,
			HttpSession session, RedirectAttributes redirectAttrs) {
		logger.info("/board/library/update " + newBoard.toString());
		try {
			newBoard.setBoardTitle(Jsoup.clean(newBoard.getBoardTitle(), Whitelist.basic()));
			newBoard.setBoardContent(Jsoup.clean(newBoard.getBoardContent(), Whitelist.basic()));
			
			//기존 boardId의 데이터 가져오기
			Board existedBoard = boardService.selectArticle(newBoard.getBoardId());
			List<BoardFile> existedFiles = existedBoard.getFileList();
			int existedBoardId = newBoard.getBoardId();
			
			// <case-1> 기존 파일 O, 새로운 파일 O
			if(existedFiles != null && files !=null) {
				boardService.updateExistToNew(newBoard, files);
			}
			// <case-2> 기존 파일 O, 새로운 파일 x
			else if(existedFiles != null && files == null) {
				boardService.updateLibrary(newBoard);
			}
			// <case-3> 기존 파일 X, 새로운 파일 O
			else if(existedFiles == null && files != null) {
				boardService.updateNothingtoNew(newBoard, files);
			} else{
				boardService.updateLibrary(newBoard);
			}
		} catch(Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		
		return "redirect:/board/"+ newBoard.getBoardId() + "/" + pageNo;
	}
	
	
	@RequestMapping("/boardy/delete")
	public String deleteBoard(Board board,@RequestParam int pageNo, HttpSession session, Model model) {
		try {
			
			//유저가 일치하는지 확인
			Member user = (Member)session.getAttribute("member");
			String userId = user.getMemberId();
			if(userId.equals(board.getMemberId())) {
				boardService.deleteArticleByBoardId(board.getBoardId());
				return "redirect:/board/cat/" + board.getBoardCategory() + "/" + pageNo;
			} else {
				model.addAttribute("message", "해당 유저의 게시물이 아닙니다.");
				return "error/runtime";
			}
		} catch(Exception e) {
			model.addAttribute("message", e.getMessage());
			return "error/runtime";
		}
	}
	
	
}
