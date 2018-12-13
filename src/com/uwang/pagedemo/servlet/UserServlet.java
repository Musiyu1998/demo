package com.uwang.pagedemo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uwang.pagedemo.dao.UserDaoImpl;
import com.uwang.pagedemo.entity.PageDemo;
import com.uwang.pagedemo.entity.UserEntity;

@WebServlet("/user")
public class UserServlet extends  HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String findUserPage = req.getParameter("command");
		if("findUserPage".equals(findUserPage)) {
			this.findUserPage( req,  resp);
		}
	}
	
	private void findUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��ȷ�����ط�ҳ��ģ��
		PageDemo<UserEntity> demoPage = new PageDemo<UserEntity>();
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		// ��ѯ���ݿ�������
		int totalNumber = userDaoImpl.getTotalNumber();
		// �õ����ݵ���������װ��pagedemoģ��
		demoPage.setTotalNum(totalNumber);
		// ��ȡǰ̨�ķ�ҳ����
		String thisPage = req.getParameter("thisPage");
		// �����Ϊnull�Ļ�����ת��
		if(null != thisPage){
			demoPage.setThisPage(Integer.parseInt(thisPage));
		}
		// ��ȡ��ҳ��  
		int totalPage = (demoPage.getTotalNum() -1)/demoPage.getPageSize()+1;
		demoPage.setTotalPage(totalPage);
		// ����һ��List
		List<UserEntity> userList = userDaoImpl.findUserPage((demoPage.getThisPage()-1) * demoPage.getPageSize(), demoPage.getPageSize()); 
		demoPage.setPageList(userList);
		// ���ݸ�ҳ��
		req.setAttribute("demoPage", demoPage);
		// ����ҳ��
		req.getRequestDispatcher("/main.jsp").forward(req, resp);
	}

}
