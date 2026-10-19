package cn.violetgarden.blog.entity.postgresql;

import cn.violetgarden.blog.dao.mapper.postgresql.association.PrimaryKey;
@PrimaryKey("name")
public class Tag {
    public String name;
	private Article[] articles;

	@Override
	public String toString(){ return String.format(
"""
Tag{
	name='%s',
	articles=%s
}
"""
	, name, articles);}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Article[] getArticles() {
		return articles;
	}

	public void setArticles(Article[] articles) {
		this.articles = articles;
	}

	
	
}
