package cn.violetgarden.blog.entity.postgresql;

import cn.violetgarden.blog.entity.postgresql.annotation.AssociationKey;
import cn.violetgarden.blog.entity.postgresql.annotation.PrimaryKey;
@PrimaryKey("name")
public class Tag {
    public String name;
	@AssociationKey private Article[] articles;

	@Override
	public String toString(){ return String.format(
"""
Tag{
	name='%s',
	articles=%s
}
"""
	, name, articles);}



	public Tag() {}



	public Tag(String name) {
		this.name = name;
	}



	public Tag(String name, Article[] articles) {
		this.name = name;
		this.articles = articles;
	}



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
