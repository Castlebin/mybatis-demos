package org.mybatis.example;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class TestMybatis {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		String resource = "org/mybatis/example/mybatis-config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetBlogWithoutMapper() {
		SqlSession session = sqlSessionFactory.openSession();
		Blog blog = (Blog)session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 1);
		System.out.println(blog.getTitle());
		session.close();
	}
	
	@Test
	public void testGetBlogById() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
		  BlogMapper blogMapper = session.getMapper(BlogMapper.class);
		  Blog blog = blogMapper.selectBlog(1);
		  
		  System.out.println(blog.getTitle());
		} finally {
		  session.close();
		}
	}
	
}
