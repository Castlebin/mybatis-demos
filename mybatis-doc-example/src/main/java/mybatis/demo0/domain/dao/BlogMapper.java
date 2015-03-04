package mybatis.demo0.domain.dao;

import mybatis.demo0.domain.bean.Blog;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface BlogMapper {
	
	@Insert("INSERT INTO blog(title, content) values(#{title}, #{content})")
	void insertBlog(Blog blog);
	
	@Select("SELECT * FROM blog WHERE id = #{id}")
	Blog getBlogById(int id);
	
}