package org.pursuit.feedbackfaircodechallenge.view.fragment;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.feedbackfaircodechallenge.R;
import org.pursuit.feedbackfaircodechallenge.controller.UserController;
import org.pursuit.feedbackfaircodechallenge.controller.UserAdapter;
import org.pursuit.feedbackfaircodechallenge.listener.OnUserListClickListener;
import org.pursuit.feedbackfaircodechallenge.model.User;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

public final class UserListFragment extends Fragment {
    private OnUserListClickListener onUserListClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        UserController.getInstance().callUserList();
        if (context instanceof OnUserListClickListener) {
            onUserListClickListener = (OnUserListClickListener) context;
        } else {
            throw new RuntimeException(
                    "Need to implement OnUserListClickListener in host activity.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView(view);
    }

    private void setupRecyclerView(@NonNull View view) {
        RecyclerView recyclerView = view.findViewById(R.id.users_view);
        UserAdapter userAdapter = new UserAdapter(onUserListClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(userAdapter);

        UserController.getInstance()
                .callUserList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newList -> userAdapter.updateList(newList)
                        , throwable -> {
                            List<User> error = new LinkedList<>();
                            if (throwable instanceof NetworkErrorException) {
                                error.add(UserController.ERROR_USER);
                                userAdapter.updateList(error);
                                throw new RuntimeException(throwable);
                            } else {
                                error.add(UserController.ERROR_USER);
                                userAdapter.updateList(error);
                            }
                        });
    }
}
