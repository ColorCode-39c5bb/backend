package cn.violetgarden.blog.dao;

public class User{

    private Integer id;
    private String  username;
    private String  password;
    private Integer age;
    private Integer gender;
    private String  portrait;
    private String  create_date;
    private String  update_date;

    public User(){}
    public User(Integer id, String username, String password, Integer age, Integer gender, String portrait, String create_date, String update_date){
        this.id         = id;
        this.username   = username;
        this.password   = password;
        this.age        = age;
        this.gender     = gender;
        this.portrait   = portrait;
        this.create_date= create_date;
        this.update_date= update_date;
    }

    @Override
    public String toString(){
        return
            "User: {\n"+
                "   id: "+id+",\n"+
                "   username: \""+username+"\",\n"+
                "   password: \""+password+"\",\n"+
                "   age: "+age+",\n"+
                "   gender: "+gender+",\n"+
                "   portrait: \""+portrait+"\",\n"+
                "   create_date: \""+create_date+"\",\n"+
                "   update_date: \""+update_date+"\",\n"+
            "}";
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public String getPortrait() {
        return portrait;
    }
    public void setPortrait(String portrait) {
        this.portrait = portrait;
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