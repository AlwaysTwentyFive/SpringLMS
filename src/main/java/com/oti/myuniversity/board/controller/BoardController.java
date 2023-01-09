package com.oti.myuniversity.board.controller;

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

import com.oti.myuniversity.board.model.Board;
import com.oti.myuniversity.board.model.BoardFile;
import com.oti.myuniversity.board.service.IBoardService;
import com.oti.myuniversity.common.Pager;
import com.oti.myuniversity.member.model.Member;

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
		List<Board> libraryList = boardService.selectArticleListByCatoryType(categoryType, pageNo);
		model.addAttribute("libraryList", libraryList);
		
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
	public String getBoardDetails(@PathVariable int pageNo,@PathVariable int boardId, Model model) {
		Board board = boardService.selectArticle(boardId);
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
		logger.info("getBoardDetails: " + board.toString());
		
		if(board.getBoardCategory()==1) {
			return "board/library/viewdetail";
		} else {
			return "board/report/viewdetail";
		}
	}
	
	@RequestMapping("/board/{boardId}")
	public String  getBoardDetails(@PathVariable int boardId, Model model) {
		return getBoardDetails(1, boardId, model);
	}
	
	
	//글 작성하기
	@RequestMapping(value= "/board/{categoryType}/write", method=RequestMethod.GET)
	public String writeBoard(@PathVariable int categoryType, Model model) {
		logger.info("/board/write: get");

		model.addAttribute("categoryType", categoryType);
		
		if(categoryType == 1) {
			return "board/library/write";
		} else {
			return "board/report/write";
		}
	}
	
	@RequestMapping(value= "/board/write", method=RequestMethod.POST)
	public String writeBoard(String boardTitle, String boardContent, @RequestParam("fileList") MultipartFile[] files, HttpSession session, RedirectAttributes redirectAttrs) {
		
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		Member member = (Member)session.getAttribute("member");
		
		board.setMemberId(member.getMemberId());
		System.out.println(board.toString());
		System.out.println(files);
		
		
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
