package dev.edwlopez.android.finalproject.data.entity;

public class User {
    private Long id;
    private String username;
    private String password;
    private UserStates userStates;

    public User(){}

    public User(Long id, String username, String password, UserStates userStates) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userStates = userStates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UserStates getUserStatesId() {
        return userStates;
    }

    public void setUserStates(UserStates userStates) {
        this.userStates = userStates;
    }

    @Override
    public String toString () {
        return "User[username=" + username + ",password=" + password + "]";
    }
}
