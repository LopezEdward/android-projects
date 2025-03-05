package dev.edwlopez.android.finalproject.data.entity;

public class ResidualCategory {
    private Long id;
    private String name;

    public ResidualCategory() {

    }

    public ResidualCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
