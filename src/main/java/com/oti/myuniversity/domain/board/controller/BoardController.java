package com.oti.myuniversity.domain.board.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.board.model.Board;
import com.oti.myuniversity.domain.board.model.BoardFile;
import com.oti.myuniversity.domain.board.service.IBoardService;
import com.oti.myuniversity.domain.member.model.Member;

@Controller
public class BoardController {
	static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	IBoardService boardService;
	
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
				return "board/report/admindetail";
			} else {
				//학생
				//관리자가 쓴 board 객체 , 학생이 쓴 board객체 두개 전달 해야 함. 
				Board reportBoard = boardService.selectStudentReport(boardId,memberId);
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
	
	@RequestMapping(value= "/board/write", method=RequestMethod.POST)
	public String writeBoard(Board board, @RequestParam MultipartFile[] files, HttpSession session, RedirectAttributes redirectAttrs) {
		
		logger.info("/board/write: " + board.toString());
		
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
			
			if(fileList!=null && !fileList.isEmpty() ) {
				boardService.insertArticle(board, fileList);
				
			}else {
				boardService.insertArticle(board);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		
		//해당 게시글의 1페이지 목록으로 이동
		return "redirect:/board/cat/" + board.getBoardCategory(); 		
		
	}
	
	
}
