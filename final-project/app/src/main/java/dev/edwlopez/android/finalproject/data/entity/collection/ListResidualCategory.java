package dev.edwlopez.android.finalproject.data.entity.collection;

import java.util.List;

import dev.edwlopez.android.finalproject.data.entity.ResidualCategory;

public class ListResidualCategory {
    private List<ResidualCategory> categories;

    public ListResidualCategory () {};

    public ListResidualCategory (List<ResidualCategory> categories) {
        this.categories = categories;
    }

    public List<ResidualCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ResidualCategory> categories) {
        this.categories = categories;
    }
}
