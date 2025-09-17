package cn.violetgarden.blog.controller.request_body;

public class LoginRequestBody{
    private String username;
    private String password;

    public LoginRequestBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString(){
        return
            "LoginRequestBody: {\n"+
                "   username: "+username+",\n"+
                "   password: "+password+",\n"+
            "}\n";
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

    
}