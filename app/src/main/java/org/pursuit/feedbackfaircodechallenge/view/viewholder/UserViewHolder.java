package org.pursuit.feedbackfaircodechallenge.view.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding3.view.RxView;

import org.pursuit.feedbackfaircodechallenge.R;
import org.pursuit.feedbackfaircodechallenge.listener.OnUserListClickListener;
import org.pursuit.feedbackfaircodechallenge.model.User;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class UserViewHolder extends RecyclerView.ViewHolder {
    private static final long DEBOUNCE_TIMEOUT = 300;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(final User user, final OnUserListClickListener onUserListClickListener) {
        itemView.<TextView>findViewById(R.id.recycler_user_name).setText(user.name);
        RxView.clicks(itemView)
                .debounce(DEBOUNCE_TIMEOUT, TimeUnit.MILLISECONDS)
                .map(click -> user)
                .subscribe(clickedUser -> onUserListClickListener.viewUser(clickedUser));
    }
}
