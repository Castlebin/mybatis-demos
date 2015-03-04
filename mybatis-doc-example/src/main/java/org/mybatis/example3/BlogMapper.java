package org.mybatis.example3;

import org.apache.ibatis.annotations.Select;

public interface BlogMapper {

	@Select("SELECT * FROM blog WHERE id = #{id}")
	Blog selectBlog(int i);

}
