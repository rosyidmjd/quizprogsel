package com.app.kuis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private SharedPrefManager sharedPrefManager;
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private ImageView ivLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefManager = new SharedPrefManager(this);
        if(sharedPrefManager.getIsLogin()){
            startHomeUi();
        }else {
            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            btnLogin = findViewById(R.id.btnLogin);
            ivLogin = findViewById(R.id.ivLogin);

            login();
        }
    }

    private void startHomeUi() {
        Intent i = new Intent(MainActivity.this, Home.class);
        startActivity(i);
        finishAffinity();
    }

    private void login() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();


                ivLogin.setVisibility(View.VISIBLE);

                if (username.isEmpty() || password.isEmpty()) {

                    ivLogin.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Username dan Password tidak boleh Kosong", Toast.LENGTH_SHORT).show();
                }else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String spUsername = sharedPrefManager.getUsername();
                            String spPassword = sharedPrefManager.getPassword();

                            Log.d("username", "user"+username);
                            Log.d("password", "pass"+password);

                            if (username.equals(spUsername) && password.equals(spPassword)) {
                                Intent i = new Intent(MainActivity.this, Home.class);
                                sharedPrefManager.saveIsLogin(true);
                                finishAffinity();
                                startActivity(i);
                            } else {

                                ivLogin.setVisibility(View.VISIBLE);
                                Toast.makeText(MainActivity.this, "Username dan Password Salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 10);
                }
            }
        });
    }
}