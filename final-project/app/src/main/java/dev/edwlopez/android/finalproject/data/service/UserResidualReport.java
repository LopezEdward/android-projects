package dev.edwlopez.android.finalproject.data.service;

import java.time.LocalDateTime;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface UserResidualReport {
    @Streaming
    @GET("/user/recycler/report/create")
    Call<ResponseBody> generateReport (@Query("from")LocalDateTime minDateTime, @Query("to") LocalDateTime maxDateTime);
}
