package dev.edwlopez.android.finalproject.data.entity;

import java.time.LocalDateTime;

public class UserResidualRegister {
    private Long id;
    private String category;
    private String magnitude;
    private String description;
    private LocalDateTime submitDate;

    public UserResidualRegister () {};

    public UserResidualRegister(Long id, String category, String magnitude, String description, LocalDateTime submitDate) {
        this.id = id;
        this.category = category;
        this.magnitude = magnitude;
        this.description = description;
        this.submitDate = submitDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDateTime submitDate) {
        this.submitDate = submitDate;
    }
}
