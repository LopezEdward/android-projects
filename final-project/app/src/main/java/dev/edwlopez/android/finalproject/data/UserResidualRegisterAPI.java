package dev.edwlopez.android.finalproject.data;

import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

import dev.edwlopez.android.finalproject.data.adapter.LocalDateTimeAdapter;
import dev.edwlopez.android.finalproject.data.adapter.UserStatesAdapter;
import dev.edwlopez.android.finalproject.data.entity.AuthFlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.AuthPageFlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.FlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.User;
import dev.edwlopez.android.finalproject.data.entity.UserStates;
import dev.edwlopez.android.finalproject.data.service.UserResidualRegister;
import dev.edwlopez.android.finalproject.data.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserResidualRegisterAPI {
    private final static String SERVER_URL = "https://api-rest-5mk8.onrender.com";
    private UserResidualRegister userRegisterService;
    private Retrofit serviceManager;

    private static final GsonConverterFactory JSON_CONVERTER = GsonConverterFactory.create(new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create());

    private static UserResidualRegisterAPI instance = null;

    private UserResidualRegisterAPI () {};

    public static UserResidualRegisterAPI getInstance() {
        if (instance == null) {
            instance = new UserResidualRegisterAPI();
            instance.serviceManager = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(JSON_CONVERTER)
                    .build();
        }

        return instance;
    }

    public void getPagesOfUserRegister (AuthPageFlattenUserResidualRegister authPages, Consumer<List<FlattenUserResidualRegister>> onSuccess, Consumer<Throwable> onFailure) {
        userRegisterService.getPageOfRegisters(authPages).enqueue(new Callback<List<FlattenUserResidualRegister>>() {
            @Override
            public void onResponse(Call<List<FlattenUserResidualRegister>> call, Response<List<FlattenUserResidualRegister>> response) {
                onSuccess.accept(response.body());
            }

            @Override
            public void onFailure(Call<List<FlattenUserResidualRegister>> call, Throwable t) {
                if (onFailure == null) return;

                onFailure.accept(t);
            }
        });
    }

    public void getUserRegister (long id, Consumer<FlattenUserResidualRegister> onSuccess, Consumer<Throwable> onFailure) {
        userRegisterService.getRegister(id).enqueue(new Callback<FlattenUserResidualRegister>() {
            @Override
            public void onResponse(Call<FlattenUserResidualRegister> call, Response<FlattenUserResidualRegister> response) {
                onSuccess.accept(response.body());
            }

            @Override
            public void onFailure(Call<FlattenUserResidualRegister> call, Throwable t) {
                if (onFailure == null) return;

                onFailure.accept(t);
            }
        });
    }

    public void addNewRegister (AuthFlattenUserResidualRegister authRegister, Consumer<FlattenUserResidualRegister> onSuccess, Consumer<Throwable> onFailure) {
        userRegisterService.addRegister(authRegister).enqueue(new Callback<FlattenUserResidualRegister>() {
            @Override
            public void onResponse(Call<FlattenUserResidualRegister> call, Response<FlattenUserResidualRegister> response) {
                onSuccess.accept(response.body());
            }

            @Override
            public void onFailure(Call<FlattenUserResidualRegister> call, Throwable t) {
                if (onFailure == null) return;

                onFailure.accept(t);
            }
        });
    }
    private void initServices () {
        userRegisterService = serviceManager.create(UserResidualRegister.class);
    }
}
