package cn.violetgarden.blog.dao.mapper.postgresql;

import org.apache.ibatis.annotations.Select;

import cn.violetgarden.blog.entity.postgresql.Article;

public interface ArticleMapper{
@Select("""
    SELECT * FROM public."Article" WHERE id = #{id}
""")
public Article select_article_by_id(Long id);
}