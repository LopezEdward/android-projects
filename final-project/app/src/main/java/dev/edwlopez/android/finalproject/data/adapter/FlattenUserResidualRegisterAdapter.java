package dev.edwlopez.android.finalproject.data.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dev.edwlopez.android.finalproject.data.entity.FlattenUserResidualRegister;

public class FlattenUserResidualRegisterAdapter extends TypeAdapter<FlattenUserResidualRegister> {
    private final String ID_NAME = "id";
    private final String CATEGORY_NAME = "category";
    private final String MAGNITUDE_NAME = "magnitude";
    private final String QUANTITY_NAME = "quantity";
    private final String SUBMIT_DATE_NAME = "submitDate";
    private final String DESCRIPTION_NAME = "description";

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public void write(JsonWriter out, FlattenUserResidualRegister value) throws IOException {
        out.beginObject();

        out.name(ID_NAME); if (value.getId() != null) out.value(value.getId()); else out.nullValue();
        out.name(CATEGORY_NAME); if (value.getCategory() != null) out.value(value.getCategory()); else out.nullValue();
        out.name(MAGNITUDE_NAME); if (value.getMagnitude() != null) out.value(value.getMagnitude()); else out.nullValue();
        out.name(QUANTITY_NAME); if (value.getQuantity() != null) out.value(value.getQuantity()); else out.nullValue();
        out.name(SUBMIT_DATE_NAME); if (value.getSubmitDate() != null) out.value(value.getSubmitDate().format(formatter)); else out.nullValue();
        out.name(DESCRIPTION_NAME); if (value.getDescription() != null) out.value(value.getDescription()); else out.nullValue();

        out.endObject();
    }

    @Override
    public FlattenUserResidualRegister read(JsonReader in) throws IOException {
        var flattenRegister = new FlattenUserResidualRegister();

        if (in.peek() != JsonToken.BEGIN_OBJECT) return flattenRegister;
        //if (in.peek() == JsonToken.NAME) property = in.nextName();

        //if (!property.equals())

        in.beginObject();

        String property;
        while (in.hasNext()) {
            if (in.peek() != JsonToken.NAME) return flattenRegister;

            property = in.nextName();

            consumeValues(in, flattenRegister, property);
        }

        in.endObject();

        return flattenRegister;
    }

    public void consumeValues (JsonReader in, FlattenUserResidualRegister register, String name) throws IOException {
        switch (name) {
            case ID_NAME:
                if (in.peek() == JsonToken.NULL) {
                    register.setId(null);
                    in.nextNull();
                    return;
                }

                register.setId(in.nextLong());
                break;
            case CATEGORY_NAME:
                if (in.peek() == JsonToken.NULL) {
                    register.setCategory(null);
                    in.nextNull();
                    return;
                }

                register.setCategory(in.nextString());
                break;
            case MAGNITUDE_NAME:
                if (in.peek() == JsonToken.NULL) {
                    register.setMagnitude(null);
                    in.nextNull();
                    return;
                }

                register.setMagnitude(in.nextString());
                break;
            case QUANTITY_NAME:
                if (in.peek() == JsonToken.NULL) {
                    register.setQuantity(null);
                    in.nextNull();
                    return;
                }

                register.setQuantity(in.nextDouble());
                break;
            case SUBMIT_DATE_NAME:
                if (in.peek() == JsonToken.NULL) {
                    register.setSubmitDate(null);
                    in.nextNull();
                    return;
                }

                register.setSubmitDate(LocalDateTime.parse(in.nextString(), formatter));
                break;
            case DESCRIPTION_NAME:
                if (in.peek() == JsonToken.NULL) {
                    register.setDescription(null);
                    in.nextNull();
                    return;
                }

                register.setDescription(in.nextString());
                break;
        }
    }
}
