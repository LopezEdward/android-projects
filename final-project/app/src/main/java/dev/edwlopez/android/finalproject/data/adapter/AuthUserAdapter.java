package dev.edwlopez.android.finalproject.data.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import dev.edwlopez.android.finalproject.data.entity.AuthUser;

public class AuthUserAdapter extends TypeAdapter<AuthUser> {
    private final String AUTH_USERNAME_FIELD_NAME = "username";
    private final String AUTH_PASSWORD_FIELD_NAME = "password";


    @Override
    public void write(JsonWriter out, AuthUser value) throws IOException {
        out.beginObject();

        out.name(AUTH_USERNAME_FIELD_NAME); out.value(value.getUsername());
        out.name(AUTH_PASSWORD_FIELD_NAME); out.value(value.getPassword());

        out.endObject();
    }

    @Override
    public AuthUser read(JsonReader in) throws IOException {
        AuthUser authUser = new AuthUser();
        String fieldName;

        if (in.peek() != JsonToken.BEGIN_OBJECT) return authUser;

        in.beginObject();

        while (in.peek() != JsonToken.END_OBJECT) {
            if (in.peek() != JsonToken.NAME) return authUser;

            fieldName = in.nextName();

            consumeValues(in, authUser, fieldName);
        }

        in.endObject();

        return authUser;
    }

    public void consumeValues (JsonReader in, AuthUser user, String fieldName) throws IOException {
        switch (fieldName) {
            case AUTH_USERNAME_FIELD_NAME:
                user.setUsername(in.nextString());

                break;

            case AUTH_PASSWORD_FIELD_NAME:
                user.setPassword(in.nextString());

                break;
        }
    }

}
