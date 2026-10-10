package cn.violetgarden.blog.dao.mapper.postgresql;

import org.apache.ibatis.annotations.Select;

import cn.violetgarden.blog.entity.postgresql.Tag;

public interface TagMapper {

@Select("""
SELECT * FROM public."Tag"
""")
Tag[] select_all();

}
