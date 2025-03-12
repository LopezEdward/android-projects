package dev.edwlopez.android.finalproject.data.entity;

import java.time.LocalDateTime;

public class FlattenUserResidualRegister {
    private Long id;
    private String description;
    private String category;
    private String magnitude;
    private Double quantity;
    private LocalDateTime submitDate;

    public FlattenUserResidualRegister() {
    }

    public FlattenUserResidualRegister(Long id, String description, String category, String magnitude, Double quantity, LocalDateTime submitDate) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.submitDate = submitDate;
        this.magnitude = magnitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDateTime submitDate) {
        this.submitDate = submitDate;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }
}
