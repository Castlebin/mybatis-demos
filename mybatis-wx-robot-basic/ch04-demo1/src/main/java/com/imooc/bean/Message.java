package com.imooc.bean;

/**
 * 对应message表的JavaBean
 * 
 * @author Z003ASXM
 */
public class Message {
	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 指令
	 */
	private String command;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 收到指令回复的内容
	 */
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
