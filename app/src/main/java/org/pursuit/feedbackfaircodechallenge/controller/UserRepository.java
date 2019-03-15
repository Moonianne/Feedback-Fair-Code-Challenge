package org.pursuit.feedbackfaircodechallenge.controller;

import android.support.annotation.NonNull;
import android.util.Log;

import org.pursuit.feedbackfaircodechallenge.model.User;
import org.pursuit.feedbackfaircodechallenge.network.UserRetrofit;
import org.pursuit.feedbackfaircodechallenge.network.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public final class UserRepository {
    private static UserRepository instance;
    private final UserService userService;

    private UserRepository(@NonNull UserService userService) {
        this.userService = userService;
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            Retrofit retrofit = UserRetrofit.getInstance();
            UserService userService = retrofit.create(UserService.class);

            instance = new UserRepository(userService);
        }
        return instance;
    }

    public Observable<List<User>> getUsers() {
        return userService.getUsers()
                .subscribeOn(Schedulers.io());
    }
}