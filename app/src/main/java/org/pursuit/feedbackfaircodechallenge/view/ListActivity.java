package org.pursuit.feedbackfaircodechallenge.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.pursuit.feedbackfaircodechallenge.R;
import org.pursuit.feedbackfaircodechallenge.listener.OnUserListClickListener;
import org.pursuit.feedbackfaircodechallenge.model.User;

public final class ListActivity extends AppCompatActivity implements OnUserListClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void viewUser(final User user) {
        startActivity(UserActivity.newIntent(this, user));
    }
}