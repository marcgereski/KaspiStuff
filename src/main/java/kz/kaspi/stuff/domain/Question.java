package kz.kaspi.stuff.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "QUESTIONS")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "QUESTION_ID")
    private Long questionId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private User user;

    @Column(name = "DATE")
    private Date date;

    public Question() {
    }

    public Question(String description, String text, Category category, User user, Date date) {
        this.description = description;
        this.text = text;
        this.category = category;
        this.user = user;
        this.date = date;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
