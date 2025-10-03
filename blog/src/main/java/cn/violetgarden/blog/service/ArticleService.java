package cn.violetgarden.blog.service;

import java.io.IOException;
import java.util.List;

import cn.violetgarden.blog.controller.request_body.ArticleRequestBody;
import cn.violetgarden.blog.dao.Article;
public interface ArticleService {

    public List<Article> get_articles(ArticleRequestBody articleRequestBody);

    public Article selectById(Long id);

    public Integer update_read_byId(Integer article_id);

    public Long insert(Article article) throws IOException;

    public Integer delete(Article article);

    public Integer update(Article aritlce) throws IOException;
}
