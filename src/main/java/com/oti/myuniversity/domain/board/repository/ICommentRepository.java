package com.oti.myuniversity.domain.board.repository;

import java.util.List;

import com.oti.myuniversity.domain.board.model.Comment;

public interface ICommentRepository {

	int selectMaxCommentId();

	void insertComment(Comment comment);

	Comment selectComment(int commentId);

	List<Comment> selectCommentList(int boardId);

	void updateComment(Comment comment);

	void deleteComment(int commentId);


}
