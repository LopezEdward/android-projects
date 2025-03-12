package dev.edwlopez.android.finalproject.data.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import dev.edwlopez.android.finalproject.data.entity.FlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.collection.ListFlattenUserResidualRegister;

public class ListFlattenUserResidualRegisterAdapter extends TypeAdapter<ListFlattenUserResidualRegister> {
    private final FlattenUserResidualRegisterAdapter flattenRegisterAdapter = new FlattenUserResidualRegisterAdapter();

    @Override
    public void write(JsonWriter out, ListFlattenUserResidualRegister value) throws IOException {
        out.beginArray();

        if (value.getData() == null) {out.endArray(); return;}

        for (FlattenUserResidualRegister register: value.getData()) {
            flattenRegisterAdapter.write(out, register);
        }

        out.endArray();
    }

    @Override
    public ListFlattenUserResidualRegister read(JsonReader in) throws IOException {
        var residualRegisterList = new ListFlattenUserResidualRegister();

        if (in.peek() != JsonToken.BEGIN_ARRAY) return residualRegisterList;

        in.beginArray();

        if (in.peek() != JsonToken.BEGIN_OBJECT) return residualRegisterList;

        while (in.peek() != JsonToken.END_ARRAY) {
            flattenRegisterAdapter.read(in);
        }

        in.endArray();

        return residualRegisterList;
    }
}
