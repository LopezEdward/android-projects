package dev.edwlopez.android.finalproject.data.entity;

public class AuthFlattenUserResidualRegister {
    private AuthUser authUserDTO;
    private FlattenUserResidualRegister registerDTO;

    public AuthFlattenUserResidualRegister () {};

    public AuthFlattenUserResidualRegister(AuthUser user, FlattenUserResidualRegister register) {
        this.authUserDTO = user;
        this.registerDTO = register;
    }

    public AuthUser getAuthUserDTO() {
        return authUserDTO;
    }

    public void setAuthUserDTO(AuthUser authUserDTO) {
        this.authUserDTO = authUserDTO;
    }

    public FlattenUserResidualRegister getRegisterDTO() {
        return registerDTO;
    }

    public void setRegisterDTO(FlattenUserResidualRegister registerDTO) {
        this.registerDTO = registerDTO;
    }
}
