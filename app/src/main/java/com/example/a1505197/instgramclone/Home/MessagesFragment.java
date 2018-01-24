package com.example.a1505197.instgramclone.Home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1505197.instgramclone.R;

/**
 * Created by 1505197 on 9/5/2017.
 */

public class MessagesFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_messages,container,false);
        return view;
    }
}
