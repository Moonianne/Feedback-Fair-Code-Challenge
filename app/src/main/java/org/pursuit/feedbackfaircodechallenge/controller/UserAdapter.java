package org.pursuit.feedbackfaircodechallenge.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.pursuit.feedbackfaircodechallenge.R;
import org.pursuit.feedbackfaircodechallenge.listener.OnUserListClickListener;
import org.pursuit.feedbackfaircodechallenge.model.User;
import org.pursuit.feedbackfaircodechallenge.view.viewholder.UserViewHolder;

import java.util.ArrayList;

public final class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final OnUserListClickListener onUserListClickListener;
    private ArrayList<User> users;

    public UserAdapter(ArrayList<User> users, OnUserListClickListener onUserListClickListener) {
        this.onUserListClickListener = onUserListClickListener;
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UserViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.onBind(users.get(i), onUserListClickListener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void updateList(ArrayList<User> newList) {
        users = newList;
        notifyDataSetChanged();
    }
}