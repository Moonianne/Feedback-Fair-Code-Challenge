package org.pursuit.feedbackfaircodechallenge.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.pursuit.feedbackfaircodechallenge.R;
import org.pursuit.feedbackfaircodechallenge.controller.UserRepository;
import org.pursuit.feedbackfaircodechallenge.listener.OnUserListClickListener;
import org.pursuit.feedbackfaircodechallenge.model.User;
import org.pursuit.feedbackfaircodechallenge.view.fragment.UserListFragment;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public final class ListActivity extends AppCompatActivity implements OnUserListClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUsersFromNetwork();
    }

    @Override
    public void viewUser(final User user) {
        startActivity(UserActivity.newIntent(this, user));
    }

    private void getUsersFromNetwork() {
        UserRepository.getInstance()
                .getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newList -> showUsersList(newList)
                        , getErrorThrowable());
    }

    @NotNull
    private Consumer<Throwable> getErrorThrowable() {
        return throwable ->
                this.<TextView>findViewById(R.id.text_network_status)
                        .setText(getString(R.string.network_fail_string));
    }

    private void showUsersList(List<User> newList) {
        UserRepository.getInstance().setUserList(newList);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.user_list_fragment_container,
                        UserListFragment.newInstance())
                .commit();
    }
}