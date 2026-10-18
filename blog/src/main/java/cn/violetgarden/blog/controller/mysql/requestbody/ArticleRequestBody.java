package cn.violetgarden.blog.controller.mysql.requestbody;

import java.util.List;

import cn.violetgarden.blog.entity.mysql.Tag;

public class ArticleRequestBody{
    private Integer page;
    private Integer pagesize;
    private String  keyword;
    private List<Tag> tags;

    
    @Override
    public String toString(){
        return
            "ArticleRequestBody: {\n"+
                "   page: "+page+",\n"+
                "   pagesize: "+pagesize+",\n"+
                "   keyword: \""+keyword+"\",\n"+
                "   tags: "+tags+",\n"+
            "}\n";
    }


    public Integer getPage() {
        return page;
    }


    public void setPage(Integer page) {
        this.page = page;
    }


    public Integer getPagesize() {
        return pagesize;
    }


    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }


    public String getKeyword() {
        return keyword;
    }


    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public List<Tag> getTags() {
        return tags;
    }


    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    
}