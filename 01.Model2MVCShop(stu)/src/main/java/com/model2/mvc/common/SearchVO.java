package com.model2.mvc.common;

//�˻��� ����¡ ����� ���� ������ �����ϰ� �ִ� 
//Value Object(VO) Ŭ����
public class SearchVO {
	
	//1. ���� ��������ȣ ����
	private int page;
	//2. �˻����� ����
	String searchCondition;
	//3. ���� �˻� Ű���� ����
	String searchKeyword;
	//4. �� �������� ǥ�� �� �׸��� �� ����
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