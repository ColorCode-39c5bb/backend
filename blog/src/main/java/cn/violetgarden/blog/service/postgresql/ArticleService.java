package cn.violetgarden.blog.service.postgresql;

import cn.violetgarden.blog.controller.request_body.ArticleRequestBody;
import cn.violetgarden.blog.entity.postgresql.Article;

public interface ArticleService {
	Article get_article(Long id);

	Article[] get_articles(ArticleRequestBody arb);

}
