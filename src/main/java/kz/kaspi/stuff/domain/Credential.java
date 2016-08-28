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

    public Credential() {
    }

    public Credential(Long userId, String pass) {
        this.userId = userId;
        this.pass = pass;
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
}
