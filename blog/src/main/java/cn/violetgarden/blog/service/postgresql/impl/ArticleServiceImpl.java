package cn.violetgarden.blog.service.postgresql.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.controller.request_body.ArticleRequestBody;
import cn.violetgarden.blog.dao.mapper.postgresql.ArticleMapper;
import cn.violetgarden.blog.entity.postgresql.Article;
import cn.violetgarden.blog.service.postgresql.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public Article get_article(Long id) {
		return articleMapper.select_by_id(id);
	}

	@Override
	public Article[] get_articles(ArticleRequestBody arb) {
		return articleMapper.select_all(arb);
	}
}
