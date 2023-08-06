package com.model2.mvc.view.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class ListUserAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		//1. 페이지, 검색기능을 사용하기위해 SearchVO 인스턴스 생성
		SearchVO searchVO=new SearchVO();
		
		//2. 로컬변수로 페이지 수 설정
		int page=1;
		
		//3.listUser.jsp에서 할당한 page의 값을 가져와서 로컬변수에 할당
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		//4.현재페이지를 새로만든 searchVO에 저장 없으면 1
		searchVO.setPage(page);
		//5.검색조건을 가져와서 새로만든 searchVO에 저장
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		//6. 검색키워드를 새로만든 searchVO에 저장
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		//7. 보여질 사이즈를 현재 서버에 저장된 context로부터 읽어들임
		String pageUnit=getServletContext().getInitParameter("pageSize");
		//8. 새로만든 searchVO의 페이지 수 설정
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		//9.새롭게 설정된 searchVO를 getUserList에 전달
		UserService service=new UserServiceImpl();
		HashMap<String,Object> map=service.getUserList(searchVO);
		
		//10. map이라는 키에 map값을 할당
		request.setAttribute("map", map);
		//11. searchVO라는 키에 searchVO인스턴스를 할당
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/user/listUser.jsp";
	}
}