package me.alexander.expensetracker.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tokens")
public class Token extends BaseEntity {

    @Column
    private String token;

    @Column
    private boolean loggedOut;

    @ManyToOne
    private User user;

    public Token() {}

    public Token(String token, boolean loggedOut, User user) {
        this.token = token;
        this.loggedOut = loggedOut;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLoggedOut() {
        return loggedOut;
    }

    public void setLoggedOut(boolean loggedOut) {
        this.loggedOut = loggedOut;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
