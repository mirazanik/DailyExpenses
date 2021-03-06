package com.dailyexpences.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginActivity extends AppCompatActivity {

    private EditText loginPhoneNumberEditText, loginPasswordEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        loginPhoneNumberEditText = findViewById(R.id.loginPhoneNumberEditText);
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText);
    }

    public void onClickSignUpNowButton(View view) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    public void onClickSignInButton(View view) {
        final String phoneNumber, password;
        phoneNumber = loginPhoneNumberEditText.getText().toString().replace("+880","");
        password = loginPasswordEditText.getText().toString();

        if (TextUtils.isEmpty(phoneNumber)) {
            loginPhoneNumberEditText.setError("Enter phone number");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            loginPasswordEditText.setError("Enter password");
            return;
        }

        mAuth.signInWithEmailAndPassword(phoneNumber + "@" +"dailyexpenses" + ".com", password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(getApplicationContext(), "Sign in successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else {
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            Log.e("SignIn", "Failed Login", e);
                            Toast.makeText(getApplicationContext(), "Sign in failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
