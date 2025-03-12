package dev.edwlopez.android.finalproject.data;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import dev.edwlopez.android.finalproject.data.adapter.ListResidualMagnitudeAdapter;
import dev.edwlopez.android.finalproject.data.entity.ResidualMagnitude;
import dev.edwlopez.android.finalproject.data.entity.collection.ListResidualMagnitude;
import dev.edwlopez.android.finalproject.data.service.UserCategoryService;
import dev.edwlopez.android.finalproject.data.service.UserMagnitudeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserResidualMagnitudeAPI extends AbstractRestAPI {
    private UserMagnitudeService magnitudeService;
    private UserResidualMagnitudeAPI () {super();}
    private static UserResidualMagnitudeAPI instance;
    private final static GsonConverterFactory JSON_CONVERTER = GsonConverterFactory.create(new GsonBuilder()
                    .registerTypeAdapter(ListResidualMagnitude.class, new ListResidualMagnitudeAdapter())
            .create());

    public static UserResidualMagnitudeAPI getInstance() {
        if (instance != null) return instance;

        instance = new UserResidualMagnitudeAPI();

        instance.serviceManager = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(JSON_CONVERTER)
                .build();

        return instance;
    }

    public void getAllMagnitudes (Consumer<List<ResidualMagnitude>> onSuccess, BiConsumer<Throwable, String> onFailure) throws IOException {
        initMagnitudeService();

        magnitudeService.getAllMagnitudes().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<ResidualMagnitude>> call, Response<List<ResidualMagnitude>> response) {
                onSuccess.accept(response.body());
            }

            @Override
            public void onFailure(Call<List<ResidualMagnitude>> call, Throwable t) {
                if (onFailure == null) return;

                onFailure.accept(t, "Error getting magnitudes. Intent more later!");
            }
        });
    }

    public void getMagnitudeByName (Consumer<ResidualMagnitude> onSuccess, Consumer<Throwable> onFailure, String name) {
        initMagnitudeService();

        magnitudeService.getMagnitudeByName(name).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ResidualMagnitude> call, Response<ResidualMagnitude> response) {
                onSuccess.accept(response.body());
            }

            @Override
            public void onFailure(Call<ResidualMagnitude> call, Throwable t) {
                if (onFailure == null) return;
                onFailure.accept(t);
            }
        });
    }

    private void initMagnitudeService () {
        if (magnitudeService == null) magnitudeService = instance.serviceManager.create(UserMagnitudeService.class);
    }
}
