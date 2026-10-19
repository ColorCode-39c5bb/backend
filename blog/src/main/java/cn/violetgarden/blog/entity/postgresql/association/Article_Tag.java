package cn.violetgarden.blog.entity.postgresql.association;

import cn.violetgarden.blog.dao.mapper.postgresql.association.PrimaryKey;

public class Article_Tag {
	public Long article_id;
	public String tag_name;
	@PrimaryKey("id") public Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Long article_id) {
		this.article_id = article_id;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	
}
