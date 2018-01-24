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
import android.widget.Toast;

import com.example.a1505197.instgramclone.Home.HomeActivity;
import com.example.a1505197.instgramclone.R;
import com.example.a1505197.instgramclone.StringManipulation;
import com.example.a1505197.instgramclone.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by 1505197 on 10/4/2017.
 */

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText mEmail,mFirstName,mLastName,mPassword,mPhoneNumber;
    Button mCreateAccount;
    private FirebaseDatabase mFirebaseDatabase;
    String email,password,firstname,lastname,phonenumber;
    private DatabaseReference mRef;
    String append;
    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEmail= (EditText) findViewById(R.id.etEmail);
        mFirstName= (EditText) findViewById(R.id.etFirstName);
        mLastName= (EditText) findViewById(R.id.etLastName);
        mPassword= (EditText) findViewById(R.id.etPassword);
        mPhoneNumber= (EditText) findViewById(R.id.etPhone);
        mCreateAccount=(Button)findViewById(R.id.btnCreateAccount);
        setupFirebaseAuth();
        init();
    }
public void init()
{
    mCreateAccount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            email=mEmail.getText().toString();
            password=mPassword.getText().toString();
            firstname= mFirstName.getText().toString();
            lastname=mLastName.getText().toString();
            phonenumber=mPhoneNumber.getText().toString();
            if(isStringNull(email)||isStringNull(password)||isStringNull(firstname)||isStringNull(lastname)||isStringNull(phonenumber))
            {
                Toast.makeText(RegisterActivity.this,"Fill all the Fields",Toast.LENGTH_LONG).show();
            }
            else
            {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, R.string.auth_failed,
                                            Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(RegisterActivity.this,R.string.auth_sucess,Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(RegisterActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                }

                                // ...
                            }
                        });
            }

        }
    });
}
    public void setupFirebaseAuth()
    {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mRef=mFirebaseDatabase.getReference();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String username=firstname+" "+lastname;
                        if(checkifUsernameExist(username,dataSnapshot))
                        {
                           append=mRef.push().getKey().substring(3,7);
                            username=append;
                        }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }
    public boolean checkifUsernameExist(String username,DataSnapshot dataSnapshot)
    {
        Log.d(TAG,"checkifUsernameExist:checking if"+username+"already exists");
        User user=new User();
        for(DataSnapshot ds:dataSnapshot.getChildren())
        {
            user.setUsername(ds.getValue(User.class).getUsername());
            if(StringManipulation.expandUsername(user.getUsername()).equals(username))
            {
                return true;
            }
        }

return false;
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
}



