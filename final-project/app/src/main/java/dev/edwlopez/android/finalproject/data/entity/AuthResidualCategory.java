package dev.edwlopez.android.finalproject.data.entity;

public class AuthResidualCategory {
    private AuthUser authUser;
    private ResidualCategory category;

    public AuthResidualCategory(AuthUser authUser, ResidualCategory category) {
        this.authUser = authUser;
        this.category = category;
    }

    public AuthResidualCategory () {

    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public ResidualCategory getCategory() {
        return category;
    }

    public void setCategory(ResidualCategory category) {
        this.category = category;
    }
}
