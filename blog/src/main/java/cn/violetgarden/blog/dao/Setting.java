package cn.violetgarden.blog.dao;

public class Setting {

    private Long id;

    private Integer pagesize;
    // private String siteName;
    // private String siteUrl;
    // private String siteDescription;
    // private String siteKeywords;
    // private String siteLogo;
    // private String siteIcp;

    @Override
    public String toString() {
        return 
            "Setting: {\n" +
            "   id:         " + id + ",\n" +
            "   pagesize:   " + pagesize + ",\n" +
            // "   siteName:   \"" + siteName + "\",\n" +
            // "   siteUrl:    \"" + siteUrl + "\",\n" +
            // "   siteDescription: \"" + siteDescription + "\",\n" +
            // "   siteKeywords: \"" + siteKeywords + "\",\n" +
            // "   siteLogo:   \"" + siteLogo + "\",\n" +
            // "   siteIcp:    \"" + siteIcp + "\"\n" +
            "}";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    
}