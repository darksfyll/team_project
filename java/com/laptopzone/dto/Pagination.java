package com.laptopzone.dto;

public class Pagination {

	private String display;
	private int pageNum;
	private boolean curPage;
	
	public Pagination(String display, int pageNum, boolean curPage) {
		this.display = display;
		this.pageNum = pageNum;
		this.curPage = curPage;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public boolean isCurPage() {
		return curPage;
	}
	public void setCurPage(boolean curPage) {
		this.curPage = curPage;
	}
	
	
}
