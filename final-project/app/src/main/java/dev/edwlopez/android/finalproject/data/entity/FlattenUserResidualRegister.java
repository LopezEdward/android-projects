package dev.edwlopez.android.finalproject.data.entity;

public class FlattenUserResidualRegister {
    private Long id;
    private String name;
    private String category;
    private Long quantity;

    public FlattenUserResidualRegister() {
    }

    public FlattenUserResidualRegister(Long id, String name, String category, Long quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
