package dev.edwlopez.android.finalproject.data;

import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import dev.edwlopez.android.finalproject.data.adapter.AuthFlattenUserResidualRegisterAdapter;
import dev.edwlopez.android.finalproject.data.adapter.AuthUserAdapter;
import dev.edwlopez.android.finalproject.data.adapter.FlattenUserResidualRegisterAdapter;
import dev.edwlopez.android.finalproject.data.adapter.ListFlattenUserResidualRegisterAdapter;
import dev.edwlopez.android.finalproject.data.adapter.LocalDateTimeAdapter;
import dev.edwlopez.android.finalproject.data.entity.AuthFlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.AuthUser;
import dev.edwlopez.android.finalproject.data.entity.FlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.collection.ListFlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.service.UserResidualRegisterService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserResidualRegisterAPI extends AbstractRestAPI {
    private UserResidualRegisterService registerService;

    private static final GsonConverterFactory JSON_CONVERTER = GsonConverterFactory.create(new GsonBuilder()
                    //.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .registerTypeAdapter(AuthUser.class, new AuthUserAdapter())
                    .registerTypeAdapter(FlattenUserResidualRegister.class, new FlattenUserResidualRegisterAdapter())
                    .registerTypeAdapter(AuthFlattenUserResidualRegister.class, new AuthFlattenUserResidualRegisterAdapter())
                    .registerTypeAdapter(ListFlattenUserResidualRegister.class, new ListFlattenUserResidualRegisterAdapter())
            .create());

    private static UserResidualRegisterAPI instance = null;

    private UserResidualRegisterAPI () {super();};

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

    public void addNewRegister (Consumer<FlattenUserResidualRegister> onSuccess, Consumer<Throwable> onFailure, AuthFlattenUserResidualRegister authR) {
        initServices();

        registerService.addNewRegister(authR).enqueue(new Callback<>() {
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

    public void getAllUserRegisters (Consumer<ListFlattenUserResidualRegister> onSuccess, Consumer<Throwable> onFailure, AuthUser user) {
        registerService.getAllRegistersByAuthUser(user).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ListFlattenUserResidualRegister> call, Response<ListFlattenUserResidualRegister> response) {
                onSuccess.accept(response.body());
            }

            @Override
            public void onFailure(Call<ListFlattenUserResidualRegister> call, Throwable t) {
                if (onFailure == null) return;

                onFailure.accept(t);
            }
        });
    }

    private void initServices () {
        if (registerService != null) return;

        registerService = serviceManager.create(UserResidualRegisterService.class);
    }
}
