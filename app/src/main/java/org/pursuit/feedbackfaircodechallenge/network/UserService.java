package org.pursuit.feedbackfaircodechallenge.network;

import org.pursuit.feedbackfaircodechallenge.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    String ENDPOINT = "users";

    @GET(ENDPOINT)
    Call<ArrayList<User>> getUsers();
}
