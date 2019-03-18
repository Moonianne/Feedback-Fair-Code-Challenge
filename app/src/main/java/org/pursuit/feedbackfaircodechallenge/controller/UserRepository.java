package org.pursuit.feedbackfaircodechallenge.controller;

import org.pursuit.feedbackfaircodechallenge.model.User;
import org.pursuit.feedbackfaircodechallenge.network.UserRetrofit;
import org.pursuit.feedbackfaircodechallenge.network.UserService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public final class UserRepository {
    private static final UserService USER_SERVICE =
            UserRetrofit.getInstance()
                    .create(UserService.class);

    private static UserRepository instance;

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public Observable<List<User>> getUsers() {
        return USER_SERVICE.getUsers()
                .subscribeOn(Schedulers.io());
    }
}