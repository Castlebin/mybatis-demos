package com.imooc.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.service.ListService;

/**
 * 列表页面初始化控制
 * 
 * @author heller
 *
 */
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// jsp页面中已经设置编码为UTF-8了，所以，这一行不需要
	//	req.setCharacterEncoding("UTF-8");
		
		// 接受页面的值
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		
		// 向页面传值
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		
		ListService listService = new ListService();
		// 查询消息列表并传给页面
		try {
			req.setAttribute("messageList", listService.queryMessageList(command, description));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 跳转到list页面
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
