package mybatis.demo0;

import java.io.IOException;
import java.io.InputStream;

import mybatis.demo0.domain.bean.Blog;
import mybatis.demo0.domain.dao.BlogMapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class Demo0 {
	public static SqlSessionFactory sqlSessionFactory;
	static {
		String resource = "mybatis/demo0/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertBlog() {
		SqlSession session = sqlSessionFactory.openSession();
		BlogMapper blogMapper = session.getMapper(BlogMapper.class);
		
		Blog blog = new Blog();
		blog.setTitle("大气污染来源研究");
		blog.setContent("大气污染来源主要有工业污染、化学污染、的对等等等");
		
		blogMapper.insertBlog(blog);
		session.commit();
		
		session.close();
	}
	
	@Test
	public void testSelectBlogById() {
		SqlSession session = sqlSessionFactory.openSession();
		BlogMapper blogMapper = session.getMapper(BlogMapper.class);
		
		Blog blog = blogMapper.getBlogById(1);
		System.out.println("id: " + blog.getId()+"\ntitle: " + blog.getTitle() + 
				"\ncontent: " + blog.getContent()+"\ncreatetime: " + blog.getCreatetime());
		
		session.close();
	}

}
