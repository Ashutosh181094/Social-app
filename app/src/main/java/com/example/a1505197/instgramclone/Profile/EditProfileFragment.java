package com.example.a1505197.instgramclone.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.a1505197.instgramclone.R;
import com.example.a1505197.instgramclone.UniversalImageLoader;

/**
 * Created by 1505197 on 10/11/2017.
 */

public class EditProfileFragment extends Fragment {
    private ImageView mProfilePhoto;
    private static final String TAG = "EditProfileFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_editprofile,container,false);
        mProfilePhoto= (ImageView) view.findViewById(R.id.profile_photo);

        setProfileImage();
        ImageView backArrow= (ImageView) view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }

    private void setProfileImage()
    {
        String imgUrl="www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge/public/article_images/2016/08/ac-lloyd.jpg?itok=bb72IeLf";
        UniversalImageLoader.setImage(imgUrl,mProfilePhoto,null,"https://");
    }
}
