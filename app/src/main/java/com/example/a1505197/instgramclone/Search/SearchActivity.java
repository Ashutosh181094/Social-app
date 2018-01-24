package com.example.a1505197.instgramclone.Search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a1505197.instgramclone.BottomNavigationViewHelper;
import com.example.a1505197.instgramclone.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * Created by 1505197 on 9/5/2017.
 */

public class SearchActivity extends AppCompatActivity
{
    private  static final int ACTIVITY_NUM=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);
        setupBottomNavigationView();
    }
    private void setupBottomNavigationView()
    {
        BottomNavigationViewEx bottomNavigationViewEx=(BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(SearchActivity.this,bottomNavigationViewEx);
        Menu menu=bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }
}
