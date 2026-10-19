package cn.violetgarden.blog.entity.postgresql;

import java.sql.Date;

import cn.violetgarden.blog.dao.mapper.postgresql.association.PrimaryKey;

public class Article {
    @PrimaryKey("id") private Long id;
	private String account_number;
    private String title;
    private String content;
    private String cover;
    private Long read;
    private Long like;
    private Date date_create;
    private Date date_update;

	private Tag[] tags;
	@Override
	public String toString(){ return String.format(
"""
Article{
	id=%d,
	account_number='%s',
	title='%s',
	content='%s',
	cover='%s',
	read=%d,
	like=%d,
	create_date='%s',
	update_date='%s'
}
"""
	, id, account_number, title, content, cover, read, like, date_create, date_update);}

	public Date getDate_create() {
		return date_create;
	}

	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}

	public Date getDate_update() {
		return date_update;
	}

	public void setDate_update(Date date_update) {
		this.date_update = date_update;
	}

	public Tag[] getTags() {
		return tags;
	}

	public void setTags(Tag[] tags) {
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Long getRead() {
		return read;
	}

	public void setRead(Long read) {
		this.read = read;
	}

	public Long getLike() {
		return like;
	}

	public void setLike(Long like) {
		this.like = like;
	}

}
