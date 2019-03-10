package org.pursuit.feedbackfaircodechallenge.view.fragment;


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
import org.pursuit.feedbackfaircodechallenge.controller.UserRepository;
import org.pursuit.feedbackfaircodechallenge.controller.UserAdapter;
import org.pursuit.feedbackfaircodechallenge.listener.OnFragmentInteractionListener;
import org.pursuit.feedbackfaircodechallenge.model.User;

import java.util.ArrayList;

public final class UserListFragment extends Fragment {
    private OnFragmentInteractionListener onFragmentInteractionListener;
    private UserAdapter userAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            onFragmentInteractionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException("Need to implement OnFragmentInteractionListener in host activity.");
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
        UserRepository.getInstance().getCallBack(userAdapter);
        userAdapter.updateList(UserRepository.getInstance().getCallBack());
    }

    private void setupRecyclerView(@NonNull View view) {
        RecyclerView recyclerView = view.findViewById(R.id.users_view);
        userAdapter = new UserAdapter(new ArrayList<User>(), onFragmentInteractionListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(userAdapter);
    }
}
