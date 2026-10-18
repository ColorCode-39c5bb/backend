package cn.violetgarden.blog.entity.postgresql.association;

import cn.violetgarden.blog.dao.mapper.postgresql.association.PrimaryKey;

public class Article_Tag {
	@PrimaryKey("id") private Long id;
	private Long article_id;
	private Long tag_name;
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
	public Long getTag_name() {
		return tag_name;
	}
	public void setTag_name(Long tag_name) {
		this.tag_name = tag_name;
	}

	
}
