package com.oti.myuniversity.board.model;

import java.sql.Date;

public class Board {
	private int boardId;
	private String boardTitle;
	private String boardContent;
	private Date boardDate;
	private String boardCategory;
	private Date reportDeadline;
	private Date submissionSubmitDate;
	private int submissionScore;
	private String memberId;
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardCategory() {
		return boardCategory;
	}
	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}
	public Date getReportDeadline() {
		return reportDeadline;
	}
	public void setReportDeadline(Date reportDeadline) {
		this.reportDeadline = reportDeadline;
	}
	public Date getSubmissionSubmitDate() {
		return submissionSubmitDate;
	}
	public void setSubmissionSubmitDate(Date submissionSubmitDate) {
		this.submissionSubmitDate = submissionSubmitDate;
	}
	public int getSubmissionScore() {
		return submissionScore;
	}
	public void setSubmissionScore(int submissionScore) {
		this.submissionScore = submissionScore;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardDate=" + boardDate + ", boardCategory=" + boardCategory + ", reportDeadline=" + reportDeadline
				+ ", submissionSubmitDate=" + submissionSubmitDate + ", submissionScore=" + submissionScore
				+ ", memberId=" + memberId + "]";
	}
	
}