package org.pursuit.feedbackfaircodechallenge.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.pursuit.feedbackfaircodechallenge.R;
import org.pursuit.feedbackfaircodechallenge.listener.OnFragmentInteractionListener;
import org.pursuit.feedbackfaircodechallenge.model.User;
import org.pursuit.feedbackfaircodechallenge.view.viewholder.UserViewHolder;

import java.util.ArrayList;

public final class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final OnFragmentInteractionListener onFragmentInteractionListener;
    private static UserAdapter instance;
    private ArrayList<User> users;

    private UserAdapter(ArrayList<User> users, OnFragmentInteractionListener onFragmentInteractionListener) {
        this.onFragmentInteractionListener = onFragmentInteractionListener;
        this.users = users;
    }

    public static UserAdapter getInstance(OnFragmentInteractionListener onFragmentInteractionListener) {
        if (instance == null) {
            instance = new UserAdapter(new ArrayList<User>(), onFragmentInteractionListener);
        }
        return instance;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UserViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.onBind(users.get(i), onFragmentInteractionListener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    void updateList(ArrayList<User> newList) {
        users = newList;
        notifyDataSetChanged();
    }
}