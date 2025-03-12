package dev.edwlopez.android.finalproject.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import dev.edwlopez.android.finalproject.data.adapter.ListResidualCategoryAdapter;
import dev.edwlopez.android.finalproject.data.adapter.ListResidualMagnitudeAdapter;
import dev.edwlopez.android.finalproject.data.entity.ResidualCategory;
import dev.edwlopez.android.finalproject.data.entity.collection.ListResidualCategory;
import dev.edwlopez.android.finalproject.data.entity.collection.ListResidualMagnitude;
import dev.edwlopez.android.finalproject.data.service.UserCategoryService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserResidualCategoryAPI extends AbstractRestAPI {
    private UserCategoryService categoryService;
    private UserResidualCategoryAPI () {super();}
    private static UserResidualCategoryAPI instance;
    private final static GsonConverterFactory JSON_CONVERTER = GsonConverterFactory.create(new GsonBuilder()
                    .registerTypeAdapter(ListResidualCategory.class, new ListResidualCategoryAdapter())
            .create());

    public static UserResidualCategoryAPI getInstance () {
        if (instance != null) return instance;

        instance = new UserResidualCategoryAPI();
        instance.serviceManager = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(JSON_CONVERTER)
                .build();

        return instance;
    }

    public void getAllCategories (Consumer<List<ResidualCategory>> onSuccess, BiConsumer<Throwable, String > onFailure) throws IOException {
        initCategoryService();

        categoryService.getAllCategories().enqueue(new Callback<List<ResidualCategory>>() {
            @Override
            public void onResponse(Call<List<ResidualCategory>> call, Response<List<ResidualCategory>> response) {
                onSuccess.accept(response.body());
            }

            @Override
            public void onFailure(Call<List<ResidualCategory>> call, Throwable t) {
                if (onFailure == null) return;

                onFailure.accept(t, "Error getting categories. Intent more later!");
            }
        });
    }

    public void initCategoryService () {
        if (this.categoryService == null) this.categoryService = serviceManager.create(UserCategoryService.class);
    }
}
