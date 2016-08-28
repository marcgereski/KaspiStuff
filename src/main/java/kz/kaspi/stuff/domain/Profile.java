package kz.kaspi.stuff.domain;

public class Profile {
    private Long userid;
    private String username;
    private String email;
    private String role;
    private String pic;

    public Profile() {
    }

    public Profile(Long userid, String username, String email, String role, String pic) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.pic = pic;
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pass) {
        this.pic = pass;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }



    public boolean hasUsername() {
        return username != null;
    }

    public boolean hasEmail() {
        return  email != null;
    }

    public boolean hasRole() {
        return role != null;
    }

    public boolean hasPic() {
        return pic != null;
    }

    public boolean hasUserid() {
        return userid != null;
    }

}
