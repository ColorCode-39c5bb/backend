package cn.violetgarden.blog.entity.postgresql;

import java.sql.Date;

import cn.violetgarden.blog.entity.postgresql.annotation.AssociationKey;
import cn.violetgarden.blog.entity.postgresql.annotation.PrimaryKey;

@PrimaryKey("id")
public class Article {
    public Long id;
	private String account_number;
    private String title;
    private String content;
    private String cover;
    private Long read;
    private Long like;
    private Date date_create;
    private Date date_update;

	@AssociationKey private Tag[] tags;
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
	tags=%s
}
"""
	, id, account_number, title, content, cover, read, like, date_create, date_update, tags);}


	public Article() {}


	public Article(Long id, String account_number, String title, String content, String cover, Long read, Long like,
			Date date_create, Date date_update) {
		this.id = id;
		this.account_number = account_number;
		this.title = title;
		this.content = content;
		this.cover = cover;
		this.read = read;
		this.like = like;
		this.date_create = date_create;
		this.date_update = date_update;
	}
	public Article(Long id, String account_number, String title, String content, String cover, Long read, Long like,
			Date date_create, Date date_update, Tag[] tags) {
		this.id = id;
		this.account_number = account_number;
		this.title = title;
		this.content = content;
		this.cover = cover;
		this.read = read;
		this.like = like;
		this.date_create = date_create;
		this.date_update = date_update;
		this.tags = tags;
	}



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
