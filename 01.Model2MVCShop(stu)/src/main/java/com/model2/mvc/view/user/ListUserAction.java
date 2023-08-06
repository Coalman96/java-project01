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
		
		//1. ������, �˻������ ����ϱ����� SearchVO �ν��Ͻ� ����
		SearchVO searchVO=new SearchVO();
		
		//2. ���ú����� ������ �� ����
		int page=1;
		
		//3.listUser.jsp���� �Ҵ��� page�� ���� �����ͼ� ���ú����� �Ҵ�
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		//4.������������ ���θ��� searchVO�� ���� ������ 1
		searchVO.setPage(page);
		//5.�˻������� �����ͼ� ���θ��� searchVO�� ����
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		//6. �˻�Ű���带 ���θ��� searchVO�� ����
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		//7. ������ ����� ���� ������ ����� context�κ��� �о����
		String pageUnit=getServletContext().getInitParameter("pageSize");
		//8. ���θ��� searchVO�� ������ �� ����
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		//9.���Ӱ� ������ searchVO�� getUserList�� ����
		UserService service=new UserServiceImpl();
		HashMap<String,Object> map=service.getUserList(searchVO);
		
		//10. map�̶�� Ű�� map���� �Ҵ�
		request.setAttribute("map", map);
		//11. searchVO��� Ű�� searchVO�ν��Ͻ��� �Ҵ�
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/user/listUser.jsp";
	}
}