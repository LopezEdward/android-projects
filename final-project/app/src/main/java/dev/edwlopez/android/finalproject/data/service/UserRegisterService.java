package dev.edwlopez.android.finalproject.data.service;

import java.util.List;

import dev.edwlopez.android.finalproject.data.entity.AuthFlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.AuthUser;
import dev.edwlopez.android.finalproject.data.entity.FlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.collection.ListFlattenUserResidualRegister;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserRegisterService {
    String API_URL = "/api/v1/residual_app/register";

    @POST(API_URL + "/new")
    Call<FlattenUserResidualRegister> addNewRegister(@Body AuthFlattenUserResidualRegister body);

    @GET(API_URL + "/user")
    Call<ListFlattenUserResidualRegister> getRegistersByAuthUser (@Body AuthUser auth);

    @PUT(API_URL + "/update")
    Call<FlattenUserResidualRegister>  authUpdateRegister (@Body AuthFlattenUserResidualRegister body);
}
