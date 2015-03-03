package com.imooc.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 
 * @author Z003ASXM
 *
 */
public class DBAccess {
	/** 获取SqlSession
	 * @return
	 * @throws IOException
	 */
	public SqlSession getSqlSession() throws IOException {
		Reader reader = Resources.getResourceAsReader("com/imooc/config/mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		
		// 打开一个SqlSession并返回
		return sqlSessionFactory.openSession();
	}
}
