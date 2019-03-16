package org.pursuit.feedbackfaircodechallenge.network;

import org.pursuit.feedbackfaircodechallenge.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UserService {
    String ENDPOINT = "users";

    @GET(ENDPOINT)
    Observable<List<User>> getUsers();
}
