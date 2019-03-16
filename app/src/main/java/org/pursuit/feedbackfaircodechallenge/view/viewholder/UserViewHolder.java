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

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(final User user, final OnUserListClickListener onUserListClickListener) {
        itemView.<TextView>findViewById(R.id.recycler_user_name).setText(user.name);
        RxView.clicks(itemView)
                .debounce(200L, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .map(click -> user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(clickedUser -> onUserListClickListener.viewUser(clickedUser));
    }
}
