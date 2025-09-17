package cn.violetgarden.blog.dao;

import org.springframework.web.multipart.MultipartFile;

public class Profile{

    private Long id;
    private String  nick;
    private String  profile;
    private String  signatures;
    private String  about;


    private MultipartFile profileFile;

    public Profile(){}



    @Override
    public String toString(){
        return
            "Profile: {\n"+
            "   id:         "+id+",\n"+
            "   nick:       "+nick+",\n"+
            "   profile:    "+profile+",\n"+
            "   signatures: "+signatures+",\n"+
            "   about:      "+about+",\n"+
            "}";
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getNick() {
        return nick;
    }



    public void setNick(String nick) {
        this.nick = nick;
    }



    public String getProfile() {
        return profile;
    }



    public void setProfile(String profile) {
        this.profile = profile;
    }



    public String getSignatures() {
        return signatures;
    }



    public void setSignatures(String signatures) {
        this.signatures = signatures;
    }



    public String getAbout() {
        return about;
    }



    public void setAbout(String about) {
        this.about = about;
    }



    public MultipartFile getProfileFile() {
        return profileFile;
    }



    public void setProfileFile(MultipartFile profileFile) {
        this.profileFile = profileFile;
    }

    
}