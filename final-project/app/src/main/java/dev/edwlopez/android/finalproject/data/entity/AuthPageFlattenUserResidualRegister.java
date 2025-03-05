package dev.edwlopez.android.finalproject.data.entity;

public class AuthPageFlattenUserResidualRegister {
    private AuthUser user;
    private Integer min;
    private Integer max;

    public AuthPageFlattenUserResidualRegister () {};

    public AuthPageFlattenUserResidualRegister(AuthUser user, Integer min, Integer max) {
        this.user = user;
        this.min = min;
        this.max = max;
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
