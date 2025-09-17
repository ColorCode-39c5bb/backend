
package cn.violetgarden.blog.dao;

public class Tag{

    private Integer id;
    private String  tagname;

    private Integer article_count;

    public Tag() {
    }
    public Tag(Integer id) {
        this.id = id;
    }
    public Tag(String tagname) {
        this.tagname = tagname;
    }

    @Override
    public String toString(){
        return
            "Tag: {\n"+
                "   id:         "+id+",\n"+
                "   tagname:    \""+tagname+"\",\n"+
                "   article_count: \""+article_count+"\",\n"+
            "}";
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTagname() {
        return tagname;
    }
    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
    public Integer getArticle_count() {
        return article_count;
    }
    public void setArticle_count(Integer article_count) {
        this.article_count = article_count;
    }

    
}