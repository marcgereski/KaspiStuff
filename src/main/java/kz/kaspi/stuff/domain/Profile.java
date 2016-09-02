package kz.kaspi.stuff.domain;

public class Profile {
    private Long userid;
    private String username;
    private String email;
    private String role;
    private String password;
    private String pic;
    private String token;

    public Profile() {
    }

    public Profile(Long userid, String username, String email, String role, String password, String token, String pic) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.pic = pic;
        this.userid = userid;
        this.password = password;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getPic() {
        return pic;
    }

    public Long getUserid() {
        return userid;
    }

    public boolean hasUsername() {
        return username != null;
    }

    public boolean hasEmail() {
        return  email != null;
    }

    public boolean hasPic() {
        return pic != null;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }
}
