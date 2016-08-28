package kz.kaspi.stuff.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "FK_ROLE_ID")
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Question> questions;

    @OneToMany(mappedBy = "user")
    private Set<Answer> answers;

    @Column(name = "PIC")
    private String pic;

    public User() {
    }

    public User(String username, String email, Role role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}