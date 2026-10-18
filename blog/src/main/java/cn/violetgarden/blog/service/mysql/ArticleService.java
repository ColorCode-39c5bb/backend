package cn.violetgarden.blog.service.mysql;

import java.io.IOException;
import java.util.List;

import cn.violetgarden.blog.controller.mysql.requestbody.ArticleRequestBody;
import cn.violetgarden.blog.entity.mysql.Article;
public interface ArticleService {

    public List<Article> get_articles(ArticleRequestBody articleRequestBody);

    public Article selectById(Long id);

    public Integer increment_read_byId(Integer article_id);

    public Long insert(Article article) throws IOException;

    public Integer delete(Article article);

    public Integer update(Article aritlce) throws IOException;
}
