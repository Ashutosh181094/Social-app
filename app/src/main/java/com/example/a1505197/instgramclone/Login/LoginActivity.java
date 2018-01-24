package com.example.a1505197.instgramclone.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1505197.instgramclone.Home.HomeActivity;
import com.example.a1505197.instgramclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by 1505197 on 10/4/2017.
 */

public class LoginActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText mEmail,mPassword,phone;
    private static final String TAG = "LoginActivity";
    TextView createAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mEmail= (EditText) findViewById(R.id.etEmail);
        mPassword=(EditText)findViewById(R.id.etPassword);
        phone=(EditText)findViewById(R.id.etPhone);
        createAccount= (TextView) findViewById(R.id.tvCreateAccount);
        setupFirebaseAuth();
        init();
    }
    private boolean isStringNull(String string)
    {
        if(string.equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
private void init()
{
    Button btSignIn= (Button) findViewById(R.id.btSignIn);
    btSignIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email=mEmail.getText().toString();
            String password=mPassword.getText().toString();
            if(isStringNull(email)&&isStringNull(password))
            {
                Toast.makeText(LoginActivity.this,"You must fill all the feilds",Toast.LENGTH_LONG).show();
            }
            else
            {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                            Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this, R.string.auth_sucess,
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }
        }
    });
    createAccount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
    });

    if(mAuth.getCurrentUser()!=null)
    {
        Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
    public void setupFirebaseAuth()
    {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
