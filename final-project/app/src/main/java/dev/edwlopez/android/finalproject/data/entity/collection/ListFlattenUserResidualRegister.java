package dev.edwlopez.android.finalproject.data.entity.collection;

import java.util.List;

import dev.edwlopez.android.finalproject.data.entity.FlattenUserResidualRegister;

public class ListFlattenUserResidualRegister {
    private List<FlattenUserResidualRegister> data;

    public ListFlattenUserResidualRegister () {}
    public ListFlattenUserResidualRegister (List<FlattenUserResidualRegister> data) {
        this.data = data;
    }


    public List<FlattenUserResidualRegister> getData() {
        return data;
    }

    public void setData(List<FlattenUserResidualRegister> data) {
        this.data = data;
    }
}
