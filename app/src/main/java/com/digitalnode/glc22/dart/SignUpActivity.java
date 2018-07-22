package com.digitalnode.glc22.dart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText email, username, password, passConf;
    String em, us, pass, pC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = (EditText) findViewById(R.id.emailAdd);
        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        passConf = (EditText) findViewById(R.id.passConf);
    }

    public void createNewUser(View view)
    {
        em = email.getText().toString();
        us = username.getText().toString();
        pass = password.getText().toString();
        pC = passConf.getText().toString();

        if(filledOut())
        {
            if(pass.equals(pC)) {
                User newUser = new User(em, us, pass, SignUpActivity.this);

                if(User.getSignUpStatus().equals("clear"))
                {
                    Toast.makeText(this, "Welcome, " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName() + "!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, User.getSignUpStatus(), Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Passwords must match!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ALL fields are required!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean filledOut()
    {
        if(us.replaceAll(" ", "") != null) {
            return true;
        } else {
            return false;
        }
    }

    public static Intent makeIntent(Context context)
    {
        return new Intent(context, SignUpActivity.class);
    }
}
