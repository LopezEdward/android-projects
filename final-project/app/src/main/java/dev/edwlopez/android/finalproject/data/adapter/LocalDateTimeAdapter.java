package dev.edwlopez.android.finalproject.data.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        if (value == null) out.nullValue();

        out.value(value.format(DATETIME_FORMATTER));
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        if (in.peek() == null) { in.nextNull(); return null; }

        String dateTimeString = in.nextString();

        return LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER);
    }
}
