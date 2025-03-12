package dev.edwlopez.android.finalproject.data.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import dev.edwlopez.android.finalproject.data.entity.AuthFlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.AuthUser;
import dev.edwlopez.android.finalproject.data.entity.FlattenUserResidualRegister;

public class AuthFlattenUserResidualRegisterAdapter extends TypeAdapter<AuthFlattenUserResidualRegister> {
    //private final static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    private static final FlattenUserResidualRegisterAdapter registerAdapter = new FlattenUserResidualRegisterAdapter();
    private static final AuthUserAdapter authUserAdapter = new AuthUserAdapter();
    private final String AUTH_USER_FIELD_NAME = "authUserDTO";
    private final String AUTH_REGISTER_FIELD_NAME = "registerDTO";

    @Override
    public void write(JsonWriter out, AuthFlattenUserResidualRegister value) throws IOException {
        out.beginObject();
        out.name(AUTH_USER_FIELD_NAME);
        //out.beginObject();
            authUserAdapter.write(out, value.getAuthUserDTO());
        //out.endObject();

        out.name(AUTH_REGISTER_FIELD_NAME);
        //out.beginObject();
            registerAdapter.write(out, value.getRegisterDTO());
        out.endObject();
    }

    @Override
    public AuthFlattenUserResidualRegister read(JsonReader in) throws IOException {
        var authRegister = new AuthFlattenUserResidualRegister();

        if (in.peek() != JsonToken.BEGIN_OBJECT) return authRegister;

        String fieldName;

        in.beginObject();
            if (in.peek() != JsonToken.NAME) return authRegister;
            fieldName = in.nextName();

            if (!fieldName.equals(AUTH_USER_FIELD_NAME)) return authRegister;

            AuthUser authUser = authUserAdapter.read(in);

            authRegister.setAuthUserDTO(authUser);
            if (in.peek() != JsonToken.NAME) return authRegister;

            fieldName = in.nextName();
            if (!fieldName.equals(AUTH_REGISTER_FIELD_NAME)) return authRegister;

            var register = registerAdapter.read(in);

            authRegister.setRegisterDTO(register);

        in.endObject();

        return authRegister;
    }
}
