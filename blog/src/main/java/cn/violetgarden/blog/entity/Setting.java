package cn.violetgarden.blog.entity;

public class Setting {

    private Long id;

    private Integer pagesize;
    Integer visitview;
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
            "   visitview:   \"" + visitview + "\",\n" +
            // "   siteUrl:    \"" + siteUrl + "\",\n" +
            // "   siteDescription: \"" + siteDescription + "\",\n" +
            // "   siteKeywords: \"" + siteKeywords + "\",\n" +
            // "   siteLogo:   \"" + siteLogo + "\",\n" +
            // "   siteIcp:    \"" + siteIcp + "\"\n" +
            "}";
    }

    public Setting() {
    }

    public Setting(Long id, int visitview) {
        this.id = id;
        this.visitview = visitview;
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

    public Integer getVisitview() {
        return visitview;
    }

    public void setVisitview(Integer visitview) {
        this.visitview = visitview;
    }


    
}