package com.model2.mvc.common;

//검색과 페이징 기능을 위한 정보를 포장하고 있는 
//Value Object(VO) 클래스
public class SearchVO {
	
	//1. 현재 페이지번호 저장
	private int page;
	//2. 검색조건 저장
	String searchCondition;
	//3. 실제 검색 키워드 저장
	String searchKeyword;
	//4. 한 페이지에 표시 될 항목의 수 저장
	int pageUnit;
	
	public SearchVO(){
	}
	
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
}