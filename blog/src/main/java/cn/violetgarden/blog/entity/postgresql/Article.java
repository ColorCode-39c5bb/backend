package cn.violetgarden.blog.entity.postgresql;

public class Article {
    private Long id;
	private String account_number;
    private String title;
    private String content;
    private String cover;
    private Long read;
    private Long like;
    private String create_date;
    private String update_date;

	private String[] tags;
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
""", id, account_number, title, content, cover, read, like, create_date, update_date
	);}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
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

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
}
