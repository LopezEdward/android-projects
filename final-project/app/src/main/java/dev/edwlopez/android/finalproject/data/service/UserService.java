package dev.edwlopez.android.finalproject.data.service;

import java.util.List;

import dev.edwlopez.android.finalproject.data.entity.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    String API_ROUTE = "/api/v1/residual_app/user";

    @GET(API_ROUTE + "/all")
    Call<List<User>> getAllUsers(User user);

    @POST(API_ROUTE + "/new")
    Call<User> createNewUser(User user);

    @GET(API_ROUTE + "/id/{id}")
    Call<User> getUserById (@Path("id") Long id);

    @GET(API_ROUTE + "/name/{username}")
    Call<User> getUserByUsername(@Path("username") String username, @Query("forAuth") boolean isForAuth);
}
