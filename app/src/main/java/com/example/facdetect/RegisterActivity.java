package com.example.facdetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText emailReg;
    EditText passwordReg;
    EditText passwordRegConfirm;
    Button register;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        emailReg = (EditText) findViewById(R.id.emailReg);
        passwordReg = (EditText) findViewById(R.id.passwordReg);
        passwordRegConfirm = (EditText) findViewById(R.id.passwordRegConfirm);
        register = (Button) findViewById(R.id.register);
        login = (TextView) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rEmail = emailReg.getText().toString().trim();
                String pwd = passwordReg.getText().toString().trim();
                String c_pwd = passwordRegConfirm.getText().toString().trim();

                if (rEmail.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Enter Email or Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (pwd.equals(c_pwd)) {
                        long val = db.addUser(rEmail, pwd);
                        if (val > 0) {
                            Toast.makeText(RegisterActivity.this, "Your Account is created successfully", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(moveToLogin);
                        } else {
                            Toast.makeText(RegisterActivity.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
