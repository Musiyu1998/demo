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
		// 先确定返回分页的模型
		PageDemo<UserEntity> demoPage = new PageDemo<UserEntity>();
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		// 查询数据库总条数
		int totalNumber = userDaoImpl.getTotalNumber();
		// 得到数据的总条数后装给pagedemo模型
		demoPage.setTotalNum(totalNumber);
		// 获取前台的分页参数
		String thisPage = req.getParameter("thisPage");
		// 如果不为null的话，则转型
		if(null != thisPage){
			demoPage.setThisPage(Integer.parseInt(thisPage));
		}
		// 获取总页数  
		int totalPage = (demoPage.getTotalNum() -1)/demoPage.getPageSize()+1;
		demoPage.setTotalPage(totalPage);
		// 返回一个List
		List<UserEntity> userList = userDaoImpl.findUserPage((demoPage.getThisPage()-1) * demoPage.getPageSize(), demoPage.getPageSize()); 
		demoPage.setPageList(userList);
		// 传递给页面
		req.setAttribute("demoPage", demoPage);
		// 返回页面
		req.getRequestDispatcher("/main.jsp").forward(req, resp);
	}

}
