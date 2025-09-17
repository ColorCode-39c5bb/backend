package cn.violetgarden.blog.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Article {

    private Long id;
    private String title;
    private String content;
    private String cover;
    private Long read;
    private Long like;
    private String create_date;
    private String update_date;

    private MultipartFile coverFile;
    private List<Tag> tags;
    private Integer itemtotal;

    public Article() {
    }

    public Article(Long id, String cover) {
        this.id = id;
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Article: {\n"
                + "   id:         " + id + ",\n"
                + "   title:      \"" + title + "\",\n"
                + "   content:    \"" + content + "\",\n"
                + "   coverFile:  " + coverFile + ",\n"
                + "   cover:      \"" + cover + "\",\n"
                + "   read:       " + read + ",\n"
                + "   like:       " + like + ",\n"
                + "   create_date: \"" + create_date + "\",\n"
                + "   update_date: \"" + update_date + "\",\n"
                + "   tags:       " + tags + ",\n"
                + "   itemtotal:  " + itemtotal + ",\n"
                + "}\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public MultipartFile getCoverFile() {
        return coverFile;
    }

    public void setCoverFile(MultipartFile coverFile) {
        this.coverFile = coverFile;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Integer getItemtotal() {
        return itemtotal;
    }

    public void setItemtotal(Integer itemtotal) {
        this.itemtotal = itemtotal;
    }

}
