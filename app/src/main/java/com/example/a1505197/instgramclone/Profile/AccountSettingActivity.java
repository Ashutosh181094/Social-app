package com.example.a1505197.instgramclone.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.a1505197.instgramclone.R;

import java.util.ArrayList;

/**
 * Created by 1505197 on 9/6/2017.
 */

public class AccountSettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        ImageView backArrow=(ImageView)findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setUpSettingsList();
    }
    private void setUpSettingsList()
    {
        ListView listView=(ListView)findViewById(R.id.listViewAccountSettings);
        ArrayList<String> options=new ArrayList<>();
        options.add(getString(R.string.edit_profile));
        options.add(getString(R.string.sign_out));
        ArrayAdapter adapter=new ArrayAdapter(AccountSettingActivity.this,android.R.layout.simple_list_item_1,options);
        listView.setAdapter(adapter);
    }
}
