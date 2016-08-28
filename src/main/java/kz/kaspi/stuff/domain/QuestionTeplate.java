package kz.kaspi.stuff.domain;

public class QuestionTeplate {
    private Long userId;
    private Long categoryId;
    private String description;
    private String text;

    public QuestionTeplate() {
    }

    public QuestionTeplate(Long userId, Long categoryId, String description, String text) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.description = description;
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
