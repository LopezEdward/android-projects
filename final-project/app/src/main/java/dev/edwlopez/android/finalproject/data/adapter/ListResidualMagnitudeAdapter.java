package dev.edwlopez.android.finalproject.data.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import dev.edwlopez.android.finalproject.data.entity.ResidualCategory;
import dev.edwlopez.android.finalproject.data.entity.ResidualMagnitude;
import dev.edwlopez.android.finalproject.data.entity.collection.ListResidualCategory;
import dev.edwlopez.android.finalproject.data.entity.collection.ListResidualMagnitude;

public class ListResidualMagnitudeAdapter extends TypeAdapter<ListResidualMagnitude> {
    @Override
    public void write(JsonWriter out, ListResidualMagnitude value) throws IOException {
        out.beginArray();

        for (ResidualMagnitude cat: value.getMagnitudes()) {
            out.beginObject();

            out.name("id"); out.value(cat.getId());
            out.name("name"); out.value(cat.getName());

            out.endObject();
        }

        out.endArray();
    }

    @Override
    public ListResidualMagnitude read(JsonReader in) throws IOException {
        var list = new ListResidualMagnitude();

        if (in.peek() != JsonToken.BEGIN_ARRAY) return list;
        in.beginArray();

        var innerList = new ArrayList<ResidualMagnitude>();
        list.setMagnitudes(innerList);

        String name;

        while (in.peek() != JsonToken.END_ARRAY) {
            if (in.peek() != JsonToken.BEGIN_OBJECT) return list;

            in.beginObject();

            if (in.peek() != JsonToken.NAME) return list;

            var mag = new ResidualMagnitude();
            innerList.add(mag);

            name = in.nextName();
            basedOnString(name, in, mag);

            if (in.peek() != JsonToken.NAME) return list;

            name = in.nextName();
            basedOnString(name, in, mag);

            in.endObject();
        }

        in.endArray();

        return list;
    }

    private void basedOnString (String name, JsonReader in, ResidualMagnitude cat) throws IOException {
        // Se espera que se haya consumido el token de nombre (JsonToken.NAME)

        switch (name) {
            case "id":
                if (in.peek() == JsonToken.NUMBER) {
                    cat.setId(in.nextLong());
                } else if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    //cat.setId(null);
                }

                break;
            case "name":
                if (in.peek() == JsonToken.STRING) {
                    cat.setName(in.nextString());
                }

                break;
            default:
                return;
        }
    }
}
