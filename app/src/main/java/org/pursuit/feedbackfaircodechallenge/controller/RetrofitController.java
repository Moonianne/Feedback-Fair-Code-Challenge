package org.pursuit.feedbackfaircodechallenge.controller;

import android.util.Log;

import org.pursuit.feedbackfaircodechallenge.model.User;
import org.pursuit.feedbackfaircodechallenge.network.UserRetrofit;
import org.pursuit.feedbackfaircodechallenge.network.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class RetrofitController {
    private static final String TAG = "RetrofitController.GEO";

    private static RetrofitController instance;

    private List<User> userList;

    private RetrofitController() {
    }

    public static RetrofitController getInstance() {
        if (instance == null) {
            instance = new RetrofitController();
        }
        return instance;
    }

    public void getCallBack(final UserAdapter adapter) {
        UserRetrofit.getInstance()
                .create(UserService.class)
                .getUsers()
                .enqueue(new Callback<ArrayList<User>>() {
                    @Override
                    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                        userList = response.body();
                        if (userList != null) {
                            adapter.updateList((ArrayList<User>) userList);
                        } else {
                            throw new RuntimeException("User List not received.");
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }
}