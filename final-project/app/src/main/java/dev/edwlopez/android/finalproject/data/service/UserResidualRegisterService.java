package dev.edwlopez.android.finalproject.data.service;

import dev.edwlopez.android.finalproject.data.entity.AuthFlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.AuthUser;
import dev.edwlopez.android.finalproject.data.entity.FlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.collection.ListFlattenUserResidualRegister;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserResidualRegisterService {
    final String API_ROUTE = "/api/v1/residual_app/register";

    @POST(API_ROUTE + "/new")
    Call<FlattenUserResidualRegister> addNewRegister (@Body AuthFlattenUserResidualRegister authUserRegister);

    @GET(API_ROUTE + "/user")
    Call<ListFlattenUserResidualRegister> getAllRegistersByAuthUser (@Body AuthUser authUser);
}
