package dev.edwlopez.android.finalproject.data.service;

import java.util.List;

import dev.edwlopez.android.finalproject.data.entity.AuthFlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.AuthPageFlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.FlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserResidualRegister {
    final String API_ROUTE = "/api/v1/residual_app/user/work";
    final String SPECIFIC_ROUTE = "/recycle";

    @GET(API_ROUTE + SPECIFIC_ROUTE + "/page")
    Call<List<FlattenUserResidualRegister>> getPageOfRegisters (AuthPageFlattenUserResidualRegister authPage);

    @GET(API_ROUTE + SPECIFIC_ROUTE + "/get/{id}")
    Call<FlattenUserResidualRegister> getRegister (@Path("id") long id);

    @POST(API_ROUTE + SPECIFIC_ROUTE + "/add")
    Call<FlattenUserResidualRegister> addRegister (AuthFlattenUserResidualRegister authRegister);
}
