package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pezyandroid.entity.User;

public class MainActivity extends AppCompatActivity {

    private TextView txtHello;

    private Context fContext;

    private User fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fContext = this;

        bindObj();

        fUser = getUser("user");

        showMessage(fUser);
    }

    private void showMessage(User user) {
        String hello = txtHello.getText().toString();
        txtHello.setText(String.format("Hello %s \n %s", user.getUsername(), hello));
    }

    private User getUser(String key) {
        return (User) getIntent().getSerializableExtra(key);
    }

    private void bindObj() {

        txtHello = findViewById(R.id.txtHello);
    }
}
