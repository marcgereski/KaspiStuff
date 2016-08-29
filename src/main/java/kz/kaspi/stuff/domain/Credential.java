package kz.kaspi.stuff.domain;

import javax.persistence.*;

@Entity
@Table(name = "CREDENTIALS")
public class Credential {
    @Id
    @Column(name = "FK_USER_ID")
    private Long userId;

    @Column(name = "PASS")
    private String pass;

    @Column(name = "TOKEN")
    private String token;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_USER_ID")
    private User user;

    public Credential() {
    }

    public Credential(Long userId, String pass, String token) {
        this.userId = userId;
        this.pass = pass;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }
}
