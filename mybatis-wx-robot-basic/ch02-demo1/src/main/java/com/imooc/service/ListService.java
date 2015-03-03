package com.imooc.service;

import java.sql.SQLException;
import java.util.List;

import com.imooc.bean.Message;
import com.imooc.dao.MessageDao;

public class ListService {
	/**
	 * @param command
	 * @param description
	 * @return List<Message>
	 * @throws SQLException 
	 */
	public List<Message> queryMessageList(String command, String description) throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessageList(command, description);
	}
}
