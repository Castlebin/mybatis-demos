package com.imooc.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.bean.Message;

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
		try {
			// jsp页面中已经设置编码为UTF-8了，所以，这一行不需要
		//	req.setCharacterEncoding("UTF-8");
			
			String command = req.getParameter("command");
			String description = req.getParameter("description");
			req.setAttribute("command", command);
			req.setAttribute("description", description);
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "123456");
			StringBuilder sql = new StringBuilder("select ID, COMMAND, DESCRIPTION, CONTENT from message where 1=1");
			
			// 进行SQL的拼接
			List<String> paramList = new ArrayList<>();
			if(command!=null && !command.trim().equals("")){
				sql.append(" and COMMAND=?");
				paramList.add(command);
			}
			if(description!=null && !description.trim().equals("")){
				sql.append(" and DESCRIPTION like '%' ? '%'");// 使用模糊匹配。mysql中进行字符串拼接使用的是空格
				paramList.add(description);
			}
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			// 填充PreparedStatement的参数
			for(int i = 0; i < paramList.size(); i++) {
				pstmt.setString(i + 1, paramList.get(i));
			}
			
			ResultSet rs = pstmt.executeQuery();
			List<Message> messageList = new ArrayList<>();
			while(rs.next()) {
				Message message = new Message();
				message.setId(rs.getInt("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));
				
				messageList.add(message);
			}
			
			// 将结果放到request域中
			req.setAttribute("messageList", messageList);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 直接跳转到list页面
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
