package cn.violetgarden.blog.controller;

public class ResponseBody{

    private boolean state;
    private String  message;
    private Object  data;
    private Object  dataExtra;

    public ResponseBody(boolean state, String message, Object data){
        this.state      = state;
        this.message    = message;
        this.data       = data;
        this.dataExtra  = null;
    }

    public ResponseBody(boolean state, String message, Object data, Object dataExtra){
        this.state      = state;
        this.message    = message;
        this.data       = data;
        this.dataExtra  = dataExtra;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getDataExtra() {
        return dataExtra;
    }

    public void setDataExtra(Object dataExtra) {
        this.dataExtra = dataExtra;
    }

    
}