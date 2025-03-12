package dev.edwlopez.android.finalproject.data.service;

import java.util.List;

import dev.edwlopez.android.finalproject.data.entity.ResidualCategory;
import dev.edwlopez.android.finalproject.data.entity.ResidualMagnitude;
import dev.edwlopez.android.finalproject.data.entity.collection.ListResidualMagnitude;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserMagnitudeService {
    String API_URL = "/api/v1/residual_app/register/magnitude";

    @GET(API_URL + "/all")
    Call<List<ResidualMagnitude>> getAllMagnitudes ();

    @GET(API_URL + "/name/{name}")
    Call<ResidualMagnitude> getMagnitudeByName (@Path("name") String name);
}
