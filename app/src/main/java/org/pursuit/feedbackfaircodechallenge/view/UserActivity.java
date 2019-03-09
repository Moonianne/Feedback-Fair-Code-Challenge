package org.pursuit.feedbackfaircodechallenge.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.pursuit.feedbackfaircodechallenge.R;
import org.pursuit.feedbackfaircodechallenge.model.User;

public final class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String NAME_KEY = "UserActivity.NAME";
    private static final String PHONE_KEY = "UserActivity.PHONE";
    private static final String EMAIL_KEY = "UserActivity.EMAIL";

    private TextView textName;
    private TextView textPhone;
    private TextView textEmail;
    private String phone;
    private String email;

    public static Intent newIntent(Context context, User user) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(NAME_KEY, user.name);
        intent.putExtra(PHONE_KEY, user.phone);
        intent.putExtra(EMAIL_KEY, user.email);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        findViews();
        Intent intent = getIntent();
        phone = intent.getStringExtra(PHONE_KEY);
        email = intent.getStringExtra(EMAIL_KEY);
        setText(intent);
        setClickListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_call:
            case R.id.text_phone:
                call();
                break;
            case R.id.button_email:
            case R.id.text_email:
                email();
                break;
        }
    }

    private void findViews() {
        textName = findViewById(R.id.text_name);
        textPhone = findViewById(R.id.text_phone);
        textEmail = findViewById(R.id.text_email);
    }

    private void setText(Intent intent) {
        textName.setText(intent.getStringExtra(NAME_KEY));
        textPhone.setText(getString(R.string.phone_string, phone));
        textEmail.setText(getString(R.string.email_string, email));
    }

    private void setClickListener() {
        this.<Button>findViewById(R.id.button_call).setOnClickListener(this);
        this.<Button>findViewById(R.id.button_email).setOnClickListener(this);
        textName.setOnClickListener(this);
        textPhone.setOnClickListener(this);
        textEmail.setOnClickListener(this);
    }

    private void call() {
        if (phone.contains("x")) {
            phone = phone.substring(0, phone.indexOf('x'));
        }
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
    }

    private void email() {
        startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email)));
    }
}
