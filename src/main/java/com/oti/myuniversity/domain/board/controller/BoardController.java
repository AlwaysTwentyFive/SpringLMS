package com.oti.myuniversity.domain.board.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.oti.myuniversity.domain.board.model.Comment;
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
		Member member = (Member) session.getAttribute("member");
		model.addAttribute("categoryType", categoryType);
		
		//paging start
		int totalRows = boardService.selectTotalArticleCount(categoryType);
		pager.init(10, 5, totalRows, pageNo);
		model.addAttribute("pager", pager);
		
		List<Board> tempBoardList = boardService.selectArticleListByCategoryType(categoryType, pager);
		List<Board> boardList = new ArrayList<Board>();
		if(member.getMemberType().equals("admin")) {
			boardList = tempBoardList;
			model.addAttribute("boardList", boardList);
		
			
			if(categoryType == 1) {
				return "board/library/admin/list";
			} else {
				return "board/report/admin/list";
			}
			
		} else {
			for(Board board : tempBoardList) {
				Board myBoard = boardService.selectScoreNContent(member.getMemberId(), board.getBoardId());
				if(myBoard != null) {
					board.setIsSubmit("제출");
					board.setSubmissionScore(myBoard.getSubmissionScore());
				} else {
					board.setIsSubmit("미제출");
				}
				boardList.add(board);		
			}	
			
			model.addAttribute("boardList", boardList);
			
			if(categoryType == 1) {
				return "board/library/list";
			} else {
				return "board/report/list";
			}
		}
	
	}
	
	@RequestMapping("/board/cat/{categoryType}")
	public String getListByCategoryType(@PathVariable int categoryType, HttpSession session, Model model) {
		
		return getListByCategoryType(categoryType, 1, session, model);
	}
	
	//글 상세 보기
	@RequestMapping("/board/{boardId}/{pageNo}")
	public String getBoardDetails(@PathVariable int pageNo, @PathVariable int boardId, HttpSession session, Model model) {
		Board board = boardService.selectArticle(boardId);
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
		logger.info("getBoardDetails: " + board.toString());
		
		
		if(board.getBoardCategory()==1) {
			//댓글 조회
			List<Comment> commentList = boardService.selectCommentList(boardId);
			model.addAttribute("commentList", commentList);
			
			return "board/library/viewdetail";

		} else {
			String memberId = ((Member)session.getAttribute("member")).getMemberId();

			if(memberId.equals("admin")) {
				//관리자
				//과제를 제출한 학생들의 board객체..
				List<Board> studentsBoard = boardService.selectStudentsReport(boardId);
				model.addAttribute("studentsBoard", studentsBoard);
				return "board/report/admindetail";
			} else {
				//학생
				//관리자가 쓴 board 객체 , 학생이 쓴 board객체 두개 전달
				Board reportBoard = boardService.selectReport(boardId, memberId);
				Boolean isUpdate = (Boolean) session.getAttribute("update");
				if(isUpdate != null && isUpdate == true) {
					reportBoard.setSubmissionSubmitDate(null);
					session.removeAttribute("update");
				}
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
	public String writeBoard(Board board, @RequestParam String time , @RequestParam MultipartFile[] files, RedirectAttributes redirectAttrs) {
		logger.info("/board/write: " + board.toString());
		System.out.println(files.length);
		//string -> Timestamp
		if(time!=null && !time.equals("")) {
			String timeStamptool = board.getReportDeadline() + " " + time + ":00.0";
			board.setReportDeadlineTime(Timestamp.valueOf(timeStamptool));
		} else {
			String timeStamptool = board.getReportDeadline() + " " + "00:00:00.0";
			board.setReportDeadlineTime(Timestamp.valueOf(timeStamptool));
		}
		try {
			board.setBoardTitle(Jsoup.clean(board.getBoardTitle(), Whitelist.basic()));
			board.setBoardContent(Jsoup.clean(board.getBoardContent(), Whitelist.basic()));
			
			if(files!=null) {
				boardService.insertNoticeReport(board, files);
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
	
	
	@RequestMapping(value= "/board/libary/write", method=RequestMethod.POST)
	public String writeBoard(Board board,  @RequestParam MultipartFile[] files, RedirectAttributes redirectAttrs) {
		logger.info("/board/write: " + board.toString());
		System.out.println(board.getReportNoticeId());
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
	
	//학생의 과제 제출 및 업데이트
	@RequestMapping(value="/board/report/submit", method=RequestMethod.POST)
	public String submitReport(Board board,@RequestParam int pageNo ,@RequestParam MultipartFile[] files, HttpSession session, RedirectAttributes redirectAttrs ) {
		
		System.out.println("멀티파드파일[] files 길이 :" + files.length);
		try {
			board.setBoardTitle(Jsoup.clean(board.getBoardTitle(), Whitelist.basic()));
			board.setBoardContent(Jsoup.clean(board.getBoardContent(), Whitelist.basic()));
			

			if(files!=null) {
				boardService.insertReport(board, files);
			} else {
				boardService.insertReport(board);
			}
		
		}catch(Exception e){
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		
		return "redirect:/board/"+ board.getReportNoticeId() + "/" + pageNo;
	}
	
	//과제 업데이트 화면
	@RequestMapping(value="/board/report/updatereport")
	public String updateSubmittedReport(int pageNo, int reportNoticeId, HttpSession session) {
		session.setAttribute("update", new Boolean(true));
		return "redirect:/board/" + reportNoticeId + "/" + pageNo;
	}
	
	//자료실 글 수정하기
	@RequestMapping(value="/board/update/{boardId}", method=RequestMethod.GET)
	public String updateLibary(@PathVariable int boardId, @RequestParam int pageNo, Model model) {
		Board board = boardService.selectArticle(boardId);
		int categoryType = board.getBoardCategory();
		
		model.addAttribute("categoryType", categoryType);
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
		if(categoryType ==1) {
			return "board/library/update";
		} else {
			return "board/report/update";
		}
	}
	
	//과제 평가
	@RequestMapping(value="/board/update/{boardId}", method=RequestMethod.POST)
	public String evaluateSubmittedReport(int reportNoticeId, @PathVariable int boardId, int submissionScore, int pageNo, Model model) {
		boardService.evaluateSubmittedReport(boardId, submissionScore);
		return "redirect:/board/" + reportNoticeId + "/" + pageNo;
	}
	
	@RequestMapping(value="/board/library/update", method=RequestMethod.POST)
	public String updateLibrary(Board board, @RequestParam int pageNo, @RequestParam MultipartFile[] files,
			HttpSession session, RedirectAttributes redirectAttrs) {
		logger.info("/board/library/update " + board.toString());
		try {
			board.setBoardTitle(Jsoup.clean(board.getBoardTitle(), Whitelist.basic()));
			board.setBoardContent(Jsoup.clean(board.getBoardContent(), Whitelist.basic()));
			
			//기존 boardId의 데이터 가져오기
			Board existedBoard = boardService.selectArticle(board.getBoardId());
			List<BoardFile> existedFiles = existedBoard.getFileList();
			
			// <case-1> 기존 파일 O, 새로운 파일 O
			if(existedFiles != null && files !=null) {
				boardService.updateExistToNew(board, files);
			}
			// <case-2> 기존 파일 O, 새로운 파일 x
			else if(existedFiles != null && files == null) {
				boardService.updateLibrary(board);
			}
			// <case-3> 기존 파일 X, 새로운 파일 O
			else if(existedFiles == null && files != null) {
				boardService.updateNothingtoNew(board, files);
			} else{
				boardService.updateLibrary(board);
			}
		} catch(Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		
		return "redirect:/board/"+ board.getBoardId() + "/" + pageNo;
	}
	
	@RequestMapping(value="/board/report/update", method=RequestMethod.POST)
	public String updateReport(Board board, @RequestParam int pageNo, @RequestParam String time , @RequestParam MultipartFile[] files,
			HttpSession session, RedirectAttributes redirectAttrs) {
		
		//string -> Timestamp
		if(time!=null && !time.equals("")) {
			String timeStamptool = board.getReportDeadline() + " " + time + ":00.0";
			board.setReportDeadlineTime(Timestamp.valueOf(timeStamptool));
		} else {
			String timeStamptool = board.getReportDeadline() + " " + "00:00:00.0";
			board.setReportDeadlineTime(Timestamp.valueOf(timeStamptool));
			}
	try {
		board.setBoardTitle(Jsoup.clean(board.getBoardTitle(), Whitelist.basic()));
		board.setBoardContent(Jsoup.clean(board.getBoardContent(), Whitelist.basic()));
		
		//기존 boardId의 데이터 가져오기
		Board existedBoard = boardService.selectArticle(board.getBoardId());
		List<BoardFile> existedFiles = existedBoard.getFileList();
		
		// <case-1> 기존 파일 O, 새로운 파일 O
		if(existedFiles != null && files !=null) {
			boardService.updateExistToNew(board, files);
		}
		// <case-2> 기존 파일 O, 새로운 파일 x
		else if(existedFiles != null && files == null) {
			boardService.updateReport(board);
		}
		// <case-3> 기존 파일 X, 새로운 파일 O
		else if(existedFiles == null && files != null) {
			boardService.updateNothingtoNew(board, files);
		} else{
			boardService.updateReport(board);
		}
	} catch(Exception e) {
		e.printStackTrace();
		redirectAttrs.addFlashAttribute("message", e.getMessage());
	}
	
	return "redirect:/board/"+ board.getBoardId() + "/" + pageNo;
	}
	
	
	@RequestMapping("/board/delete")
	public String deleteBoard(Board board, @RequestParam int pageNo, HttpSession session, Model model) {
		try {
			
			//유저가 일치하는지 확인
			Member user = (Member)session.getAttribute("member");
			String userId = user.getMemberId();
			if(userId.equals(board.getMemberId())) {
				boardService.deleteArticleByBoardId(board.getBoardId());
				if(board.getBoardCategory()==1) {
					//자료실 목록으로 리턴
					return "redirect:/board/cat/" + board.getBoardCategory() + "/" + pageNo;
				} else {
					if(userId.equals("admin")) {
						//관리자일 경우
						return "redirect:/board/cat/" + board.getBoardCategory() + "/" + pageNo;
					}else {
						//학생일 경우
						return "board/report/studentdetail";
					}
				}
				
			} else {
				model.addAttribute("message", "해당 유저의 게시물이 아닙니다.");
				return "error/runtime";
			}
		} catch(Exception e) {
			model.addAttribute("message", e.getMessage());
			return "error/runtime";
		}
	}
	//댓글 작성
	@RequestMapping(value="/board/reply", method=RequestMethod.POST)
	public String replyBoard(Comment comment, HttpSession session, Model model, RedirectAttributes redirectAttrs) {
		try {
			//댓글 입력
			comment.setCommentContent(Jsoup.clean(comment.getCommentContent(), Whitelist.basic()));
			
			Comment acomment = boardService.insertComment(comment);
			
			model.addAttribute("acomment", acomment);
		} catch(Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		
		return "board/library/reply";
	}
	
	//댓글 수정
	@RequestMapping(value="/reply/update", method=RequestMethod.POST)
	public String updateReply(Comment comment, HttpSession session, Model model, RedirectAttributes redirectAttrs) {
		try {
			//댓글 입력
			Member user = (Member)session.getAttribute("member");
			String userId = user.getMemberId();
			comment.setMemberId(userId);
			
			comment.setCommentContent(comment.getCommentContent());
			
			List<Comment> commentList = boardService.updateComment(comment);
				
			model.addAttribute("commentList", commentList);
			
		} catch(Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		
		return "board/library/replydelete";
	}
	//
	
	//삭제
	@RequestMapping("/reply/delete")
	public String deleteReply(Comment comment, HttpSession session, Model model) {
		//유저가 일치하는지 확인
		Member user = (Member)session.getAttribute("member");
		String userId = user.getMemberId();
		if(userId.equals(comment.getMemberId())) {
			List<Comment> commentList = boardService.deleteComment(comment);
			model.addAttribute("commentList", commentList);
			System.out.println("commentList: " + commentList);
			
			
			return "board/library/replydelete";
		} else {
			return "board/library/viewdetail";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
