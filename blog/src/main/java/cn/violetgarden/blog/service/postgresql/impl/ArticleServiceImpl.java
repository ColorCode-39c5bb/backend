package cn.violetgarden.blog.service.postgresql.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.dao.mapper.postgresql.ArticleMapper;
import cn.violetgarden.blog.entity.postgresql.Article;
import cn.violetgarden.blog.service.postgresql.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public Article get_article(Long id) {
		System.out.println("get_article: "+id);
		return articleMapper.select_article_by_id(id);
	}
}
