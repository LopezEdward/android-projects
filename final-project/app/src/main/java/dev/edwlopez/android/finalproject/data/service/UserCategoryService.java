package dev.edwlopez.android.finalproject.data.service;

import java.util.List;

import dev.edwlopez.android.finalproject.data.entity.AuthFlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.FlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.ResidualCategory;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserCategoryService {
    final String API_ROUTE = "/api/v1/residual_app/register/category";

    @GET(API_ROUTE + "/all")
    Call<List<ResidualCategory>> getAllCategories ();

    /*@GET(API_ROUTE + "/all")
    Call<List<String>> getAllLightCategories ();*/
}
