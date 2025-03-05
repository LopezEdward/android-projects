package dev.edwlopez.android.finalproject.data.entity;

public class UserResidualRegister {
    private Long id;
    private String name;
    private User user;
    private ResidualCategory category;

    public UserResidualRegister () {};

    public UserResidualRegister (Long id, String name, User user, ResidualCategory category) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ResidualCategory getCategory() {
        return category;
    }

    public void setCategory(ResidualCategory category) {
        this.category = category;
    }
}
