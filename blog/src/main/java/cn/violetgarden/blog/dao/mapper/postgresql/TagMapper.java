package cn.violetgarden.blog.dao.mapper.postgresql;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import cn.violetgarden.blog.entity.postgresql.Tag;

public interface TagMapper {

@Select("""
SELECT * FROM public."Tag"
""")
Tag[] select_all();

Map<String, Object>[] select_by_articles(Long[] articleids);
//{
//	article_id: Long,
//	tag_name: String,
//	...Tag
//}
}
