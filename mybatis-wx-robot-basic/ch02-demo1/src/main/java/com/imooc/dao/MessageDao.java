package com.imooc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.imooc.bean.Message;


/**
 * @author Z003ASXM
 * 
 * Message DAO 
 */
public class MessageDao {
	private static final Logger logger = Logger.getLogger(MessageDao.class);
	
	/**
	 * @param command
	 * @param description
	 * @return List<Message>
	 */
	public List<Message> queryMessageList(String command, String description) {
		List<Message> messageList = new ArrayList<>();
		
		try {
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
			logger.info(pstmt.toString());
			while(rs.next()) {
				Message message = new Message();
				message.setId(rs.getInt("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));
				
				messageList.add(message);
			}
			
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return messageList;
	}
}
