package com.uwang.pagedemo.entity;

import java.util.List;

/**
 *  分页模型
 *  用来专门处理分页信息
 *
 */
public class PageDemo<T> {

	
	// 总页数
	private int totalPage;
	
	// 一页显示数据库条数
	private int pageSize=10;
	
	// 总条数
	private int totalNum;
	
	// 当前页
	private int thisPage=1;
	
	// 装载数据
	private List<T> pageList; 

	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getThisPage() {
		return thisPage;
	}

	public void setThisPage(int thisPage) {
		this.thisPage = thisPage;
	}

	@Override
	public String toString() {
		return "PageDemo [totalPage=" + totalPage + ", pageSize=" + pageSize + ", totalNum=" + totalNum + ", thisPage="
				+ thisPage + ", pageList=" + pageList + "]";
	}
	
	
	
}
