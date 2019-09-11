package user;

public class User {
    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public User(String name, String pwd, Integer id){
        this.username = name;
        this.pwd = pwd;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public User(){}
    private String username;
    private String pwd;
    private Integer id;
}
