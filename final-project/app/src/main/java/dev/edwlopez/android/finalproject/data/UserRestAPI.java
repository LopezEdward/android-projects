package dev.edwlopez.android.finalproject.data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;

import dev.edwlopez.android.finalproject.credential.UserCredential;
import dev.edwlopez.android.finalproject.data.adapter.LocalDateTimeAdapter;
import dev.edwlopez.android.finalproject.data.adapter.UserStatesAdapter;
import dev.edwlopez.android.finalproject.data.entity.User;
import dev.edwlopez.android.finalproject.data.entity.UserStates;
import dev.edwlopez.android.finalproject.data.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRestAPI {
    private final static String SERVER_URL = "https://api-rest-5mk8.onrender.com";
    private UserService userService;
    private Retrofit serviceManager;
    private static final GsonConverterFactory JSON_CONVERTER = GsonConverterFactory.create(new GsonBuilder()
            .registerTypeAdapter(UserStates.class, new UserStatesAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create());
    private static UserRestAPI instance = null;

    private UserRestAPI (){};
    public static UserRestAPI getInstance() {
        if (instance == null) {
            instance = new UserRestAPI();
            instance.serviceManager = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(JSON_CONVERTER)
                    .build();
        }

        return instance;
    }

    public void getUserCredential (Consumer<User> onResult, Consumer<Throwable> onFailure, String username) throws IOException {
        initializeUserService();

        final String usr = username.trim();

        this.userService.getUserByUsername(usr, true).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                onResult.accept(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                if (onFailure == null) return;

                onFailure.accept(throwable);
            }
        });
    }

    /*public UserCredential getUserCredential (Consumer<Throwable> onFailure, String username) throws IOException {
        initializeUserService();

        final String usr = username.trim();
        Function<User, UserCredential> parser = usr -> {
            return UserCredential.fromEntity(usr);
        }

        this.userService.getUserByUsername(usr, true).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                if (onFailure == null) return;

                onFailure.accept(throwable);
            }
        });
    }*/

    private void initializeUserService () {
        if (this.userService == null) this.userService = serviceManager.create(UserService.class);
    }
/*
    // Wrapper
    private class Wrapper<T> {
        private T data;

        public Wrapper (T data) { this.data = data; };
        public Wrapper () {};

        public void setData (T data) {
            this.data = data;
        }

        public T getData () {
            return data;
        }
    }*/
}
