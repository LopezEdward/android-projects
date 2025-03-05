package dev.edwlopez.android.app5.data.entity.interfaces;

public interface Client {
    String getName();
    String getEmail();
    Integer getTelephoneNumber ();
    void setName(String name);
    void setEmail(String email);
    void setTelephoneNumber (Integer telephoneNumber);
}
