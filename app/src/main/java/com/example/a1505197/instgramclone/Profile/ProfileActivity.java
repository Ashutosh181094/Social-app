package com.example.a1505197.instgramclone.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.a1505197.instgramclone.BottomNavigationViewHelper;
import com.example.a1505197.instgramclone.GridImageAdapter;
import com.example.a1505197.instgramclone.Home.HomeActivity;
import com.example.a1505197.instgramclone.R;
import com.example.a1505197.instgramclone.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

/**
 * Created by 1505197 on 9/5/2017.
 */

public class  ProfileActivity extends AppCompatActivity
{
    private  static final int ACTIVITY_NUM=4;
    ProgressBar mprogressbar;
    Toolbar toolbar;
    private ImageView profilePhoto;
    private static final int NUM_GRID_COLUMNS=3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });


      setupBottomNavigationView();
        setUpActivityWidgets();
        setProfileImage();
        tempGridSetup();
    }
    private void setUpActivityWidgets()
    {
        mprogressbar= (ProgressBar) findViewById(R.id.profileProgressBar);
        mprogressbar.setVisibility(View.GONE);
        profilePhoto=(ImageView)findViewById(R.id.profile_photo);
    }

    private void setupBottomNavigationView()
    {
        BottomNavigationViewEx bottomNavigationViewEx=(BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(ProfileActivity.this,bottomNavigationViewEx);
        Menu menu=bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }
    private void tempGridSetup()
    {
        ArrayList<String> imgURLs=new ArrayList<>();
        imgURLs.add("https://res.cloudinary.com/simpleview/image/upload/c_limit,f_auto,q_65,w_845/v1/clients/vancouverbc/a-guide-to-grouse-mountain_930ab127-285b-4263-b2b2-7d8c8c250717.jpg");
        imgURLs.add("https://cloudfront.ualberta.ca/-/media/ualberta/courses/mountains-101/mountains-101-logo-adj.jpg");
        imgURLs.add("http://www.mountainprofessor.com/images/Mountain-Ranges-Colorado-2.jpg");
        imgURLs.add("http://www.telegraph.co.uk/content/dam/Travel/galleries/travel/activityandadventure/The-worlds-most-beautiful-mountains/mountains-kailash_3374111a.jpg");
        imgURLs.add("https://cdn.pixabay.com/photo/2016/06/17/04/26/mountain-1462655_960_720.jpg");
        imgURLs.add("https://cdn.pixabay.com/photo/2017/03/14/17/43/mountain-2143877_960_720.jpg");
        imgURLs.add("https://cdn.pixabay.com/photo/2017/02/01/22/02/mountain-landscape-2031539_960_720.jpg");
        imgURLs.add("https://onehdwallpaper.com/wp-content/uploads/2015/07/Free-Download-Mountain-Hd-Wallpapers.jpg");
        imgURLs.add("https://mountaincountieswater.com/wp-content/uploads/2011/04/2013-Snow-Sierras-2.jpg");
        imgURLs.add("http://www.mwis.org.uk/jpg/background-image.jpg");
        imgURLs.add("http://awallpapersimages.com/wp-content/uploads/2016/07/Mountain-Wallpaper.jpeg");
        imgURLs.add("http://theuiaa.org/wp-content/uploads/2016/09/mountain-protection-award-conservation-1200x800-1200x700.jpg");
        setupImageGrid(imgURLs);
    }
    private void setupImageGrid(ArrayList<String> imgURLs)
    {
        GridView gridView= (GridView) findViewById(R.id.gridView);
        int gridWidth=getResources().getDisplayMetrics().widthPixels;
        int imageWidth=gridWidth/NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);
        GridImageAdapter adapter=new GridImageAdapter(ProfileActivity.this,R.layout.layout_grid_imageview,"",imgURLs);
        gridView.setAdapter(adapter);
    }
    public void setProfileImage()
    {
        String imgUrl="www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge/public/article_images/2016/08/ac-lloyd.jpg?itok=bb72IeLf";
        UniversalImageLoader.setImage(imgUrl,profilePhoto,mprogressbar,"https://");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tab_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.settings:
                EditProfileFragment editProfileFragment=new EditProfileFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.relativelayout3,editProfileFragment);
                transaction.addToBackStack(null);
                transaction.commit();

        }
        return super.onOptionsItemSelected(item);
    }

}
