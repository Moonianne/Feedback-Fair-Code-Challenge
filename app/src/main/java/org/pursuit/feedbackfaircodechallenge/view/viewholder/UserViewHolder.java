package org.pursuit.feedbackfaircodechallenge.view.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.pursuit.feedbackfaircodechallenge.R;
import org.pursuit.feedbackfaircodechallenge.listener.OnFragmentInteractionListener;
import org.pursuit.feedbackfaircodechallenge.model.User;

public final class UserViewHolder extends RecyclerView.ViewHolder {

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(final User user, final OnFragmentInteractionListener onFragmentInteractionListener) {
        itemView.<TextView>findViewById(R.id.recycler_user_name).setText(user.name);
        itemView.setOnClickListener(v -> onFragmentInteractionListener.viewUser(user));
    }
}
