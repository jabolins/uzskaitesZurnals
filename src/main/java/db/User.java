package db;

public class User {


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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    private String username;
    private String password;
    private String eMail;
    private String accessRights;

    public User(String username, String password, String eMail, String accessRights){
        this.username = username;
        this.password = password;
        this.eMail = eMail;
        this.accessRights = accessRights;
    }

}
