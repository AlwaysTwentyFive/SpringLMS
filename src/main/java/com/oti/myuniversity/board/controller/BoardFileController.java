package com.oti.myuniversity.board.controller;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oti.myuniversity.board.model.BoardFile;
import com.oti.myuniversity.board.service.IBoardFileService;

@Controller
public class BoardFileController {
	
	@Autowired 
	IBoardFileService boardFileService;
	
	@RequestMapping("/boardfile/{fileId}")
	public ResponseEntity<byte[]> getFile(@PathVariable int fileId){
		BoardFile file = boardFileService.getFile(fileId);
		final HttpHeaders headers = new HttpHeaders();
		String[] mtypes = file.getBoardFileContentType().split("/");
		headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
		headers.setContentLength(file.getBoardFileSize());
		headers.setContentDispositionFormData("attachment",
				file.getBoardFileName(), Charset.forName("UTF-8"));
		return new ResponseEntity<byte[]>(file.getBoardFileData(), headers, HttpStatus.OK);
	}
	
}
