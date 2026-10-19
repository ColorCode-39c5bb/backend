package cn.violetgarden.blog.service.postgresql.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.controller.postgresql.requestbody.ArticleRequestBody;
import cn.violetgarden.blog.dao.mapper.postgresql.ArticleMapper;
import cn.violetgarden.blog.dao.mapper.postgresql.association.Article_Tag_Mapper;
import cn.violetgarden.blog.entity.postgresql.Article;
import cn.violetgarden.blog.entity.postgresql.Tag;
import cn.violetgarden.blog.service.postgresql.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private Article_Tag_Mapper article_tag_mapper;

	@Override
	public Article get_article(Long id) {
		return articleMapper.select_by_id(id);
	}

	@Override
	public Article[] get_articles(ArticleRequestBody arb) throws NoSuchFieldException, IllegalAccessException {
		Article[] articles = articleMapper.select_all(arb);
		if(articles.length==0) return articles;
		Map<Article, List<Tag>> maps = article_tag_mapper.select_by_c0_manualassembly(articles);
		maps.forEach((article, tags) -> article.setTags(tags.toArray(new Tag[0])));
		return articles;
	}
}
