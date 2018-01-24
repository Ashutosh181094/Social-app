/**
 * Created by 1505197 on 9/5/2017.
 */

package com.example.a1505197.instgramclone;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.a1505197.instgramclone.Home.HomeActivity;
import com.example.a1505197.instgramclone.Likes.LikesActivity;
import com.example.a1505197.instgramclone.Profile.ProfileActivity;
import com.example.a1505197.instgramclone.Search.SearchActivity;
import com.example.a1505197.instgramclone.Share.ShareActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * Created by 1505197 on 9/4/2017.
 */

public class BottomNavigationViewHelper
{
    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx)
    {
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }
    public static void enableNavigation(final Context context,BottomNavigationViewEx view)
    {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.ic_house:
                        Intent intent=new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                        break;
                    case R.id.ic_search:
                        Intent intent2=new Intent(context, SearchActivity.class);
                        context.startActivity(intent2);
                        break;
                    case R.id.ic_circle:
                        Intent intent3=new Intent(context, ShareActivity.class);
                        context.startActivity(intent3);
                        break;
                    case R.id.ic_alert:
                        Intent intent4=new Intent(context,LikesActivity.class);
                        context.startActivity(intent4);
                        break;
                    case R.id.ic_action_name:
                        Intent intent5=new Intent(context, ProfileActivity.class);
                        context.startActivity(intent5);
                        break;
                }
                return false;
            }
        });
    }
}
