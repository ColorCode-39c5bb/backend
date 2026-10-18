package cn.violetgarden.blog.dao.mapper.postgresql;

import org.apache.ibatis.annotations.Select;

import cn.violetgarden.blog.controller.postgresql.requestbody.ArticleRequestBody;
import cn.violetgarden.blog.entity.postgresql.Article;

public interface ArticleMapper{
@Select("""
SELECT * FROM public."Article" WHERE id = #{id}
""")
public Article select_by_id(Long id);


public Article[] select_all(ArticleRequestBody arb);
}