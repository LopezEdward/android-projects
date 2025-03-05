package dev.edwlopez.android.finalproject.data.entity;

public class AuthFlattenUserResidualRegister {
    private AuthUser user;
    private FlattenUserResidualRegister register;

    public AuthFlattenUserResidualRegister () {};

    public AuthFlattenUserResidualRegister(AuthUser user, FlattenUserResidualRegister register) {
        this.user = user;
        this.register = register;
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }

    public FlattenUserResidualRegister getRegister() {
        return register;
    }

    public void setRegister(FlattenUserResidualRegister register) {
        this.register = register;
    }
}
