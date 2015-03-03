package com.imooc.service;

import java.io.IOException;
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
	 * @throws IOException 
	 */
	public List<Message> queryMessageList(String command, String description) {
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessageList(command, description);
	}
}
