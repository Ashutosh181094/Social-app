package com.example.a1505197.instgramclone.Home;

/**
 * Created by 1505197 on 9/5/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a1505197.instgramclone.BottomNavigationViewHelper;
import com.example.a1505197.instgramclone.Login.LoginActivity;
import com.example.a1505197.instgramclone.R;
import com.example.a1505197.instgramclone.UniversalImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;


public class HomeActivity extends AppCompatActivity {
    private  static final int ACTIVITY_NUM=0;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);
        initImageLoader();
          setupFirebaseAuth();
        setupBottomNavigationView();
        setupViewPager();

    }
    private void initImageLoader()
    {
        UniversalImageLoader universalImageLoader=new UniversalImageLoader(HomeActivity.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
    private void setupViewPager()
    {
        SectionsPagerAdapter adapter=new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragment());//index 0
        adapter.addFragment(new HomeFragment());//index 1
        adapter.addFragment(new MessagesFragment());//index 2
        ViewPager viewPager= (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);

    }
    private void setupBottomNavigationView()
    {
        BottomNavigationViewEx bottomNavigationViewEx=(BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(HomeActivity.this,bottomNavigationViewEx);
        Menu menu=bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }
    private void checkCurrentUser(FirebaseUser user)
    {
        if(user==null)
        {
            Intent intent=new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
    public void setupFirebaseAuth()
    {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                checkCurrentUser(user);
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
        checkCurrentUser(mAuth.getCurrentUser());
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}

