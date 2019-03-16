package org.pursuit.feedbackfaircodechallenge.controller;

import org.pursuit.feedbackfaircodechallenge.model.User;
import org.pursuit.feedbackfaircodechallenge.network.UserRetrofit;
import org.pursuit.feedbackfaircodechallenge.network.UserService;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public final class UserController {
    public static final User ERROR_USER =
            new User(null,
                    "Error Connecting to Network.",
                    "Make sure your WiFi/LTE is enabled.",
                    "-1");
    private static UserController instance;

    private UserController() {
    }

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public Single<List<User>> callUserList() {
        return UserRetrofit.getInstance()
                .create(UserService.class)
                .getUsers()
                .subscribeOn(Schedulers.io())
                .flatMapIterable(users -> users)
                .toList();
    }
}