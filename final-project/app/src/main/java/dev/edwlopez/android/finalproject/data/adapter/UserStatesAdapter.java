package dev.edwlopez.android.finalproject.data.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dev.edwlopez.android.finalproject.data.entity.UserStates;

public class UserStatesAdapter extends TypeAdapter<UserStates> {
    private final String id = "id";
    private final String banned = "banned";
    private final String blocked = "blocked";
    private final String accessIntents = "accessIntents";
    private final String blockedUntil = "blockedUntil";
    private final String blockedFrom = "blockedFrom";
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public void write(JsonWriter out, UserStates value) throws IOException {
        DATETIME_FORMATTER.format(value.getBlockedFrom());

        out
            .name(id).value(value.getId())
            .name(banned).value(value.isBanned())
            .name(blocked).value(value.isBlocked())
            .name(accessIntents).value(value.getAccessIntent())
            .name(blockedUntil).value(value.getBlockedUntil().format(DATETIME_FORMATTER))
            .name(blockedFrom).value(value.getBlockedFrom().format(DATETIME_FORMATTER))
            .flush();
    }

    @Override
    public UserStates read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL || in.peek() == JsonToken.END_DOCUMENT)  {in.nextNull(); return null;}

        UserStates states = new UserStates();
        String next = "";

        in.beginObject();

        while (in.hasNext()) {
            if (in.peek() == JsonToken.END_OBJECT) break;

            next = in.nextName();

            if (in.peek() == JsonToken.NULL) { in.nextNull(); consumeFields(in, states, next, null); continue;}
            if (in.peek() == JsonToken.BOOLEAN) { consumeFields(in, states, next, String.valueOf(in.nextBoolean())); continue;};

            consumeFields(in, states, next, in.nextString());
            //System.out.println(states.toString());
        }

        in.endObject();

        return states;
    }

    private void consumeFields (JsonReader in, UserStates ref, String property, String value) throws IOException {
        if (property.equals(id)) {
            ref.setId((value == null) ? null : Long.parseLong(value));
        } else if (property.equals(blocked)) {
            ref.setBlocked((value == null) ? null : Boolean.parseBoolean(value));
        } else if (property.equals(banned)) {
            ref.setBanned((value == null) ? null : Boolean.parseBoolean(value));
        } else if (property.equals(accessIntents)) {
            ref.setAccessIntent((value == null) ? null : Byte.parseByte(value));
        } else if (property.equals(blockedUntil)) {
            ref.setBlockedUntil((value == null) ? null : LocalDateTime.parse(value, DATETIME_FORMATTER));
        } else if (property.equals(blockedFrom)) {
            ref.setBlockedFrom((value == null) ? null : LocalDateTime.parse(value, DATETIME_FORMATTER));
            //managerNullValues(ref::setAccessIntents, value);
        } else {
            in.skipValue();
        }
    }
}
