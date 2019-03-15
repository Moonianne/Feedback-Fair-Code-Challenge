package org.pursuit.feedbackfaircodechallenge.view.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.feedbackfaircodechallenge.R;
import org.pursuit.feedbackfaircodechallenge.controller.UserRepository;
import org.pursuit.feedbackfaircodechallenge.controller.UserAdapter;
import org.pursuit.feedbackfaircodechallenge.listener.OnUserListClickListener;
import org.pursuit.feedbackfaircodechallenge.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public final class UserListFragment extends Fragment {
    private OnUserListClickListener onUserListClickListener;
    private UserAdapter userAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserListClickListener) {
            onUserListClickListener = (OnUserListClickListener) context;
        } else {
            throw new RuntimeException("Need to implement OnUserListClickListener in host activity.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView(view);

        Observable.interval(0, 1, TimeUnit.MILLISECONDS)
//                .doOnNext(value -> Log.d("test", "onNext: " + value))
                .throttleLatest(1, TimeUnit.SECONDS)
                .subscribe(value -> {
                    Log.d("test", "subscribe: " + value);
                });

        Observable<Long> secondsCounter = Observable.interval(0, 1, TimeUnit.MILLISECONDS);

        Observable<User> usersSource = UserRepository.getInstance().getUsers()
                .flatMapIterable(items -> items);

        Observable.zip(secondsCounter, usersSource, (second, user) -> {
            user.name = user.name + second;
            return user;
        }).toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        users -> { // onNext
                            userAdapter.updateList(users);
                        }
                );

        UserRepository.getInstance().getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    userAdapter.updateList(users);
                });

//        Observable.interval(0, 1, TimeUnit.SECONDS)
//                .doOnNext(item -> Log.d("test", "onNext: " + item))
//                .map(item -> "cat" + item)
//                .map(cat -> {
//                    if (cat.equals("cat10")) {
//                        throw new RuntimeException("MEOW");
//                    }
//                    return cat;
//                })
//                .subscribe(item -> {
//                    Log.d("test", "subscribe onNext: " + item);
//                }, throwable -> {
//                    Log.d("test", "onError", throwable);
//                }, () -> {
//                    Log.d("test", "onComplete");
//                });

//        UserRepository.getInstance().getCallBack(userAdapter);
    }

    private void setupRecyclerView(@NonNull View view) {
        RecyclerView recyclerView = view.findViewById(R.id.users_view);
        userAdapter = new UserAdapter(new ArrayList<>(), onUserListClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(userAdapter);
    }
}
