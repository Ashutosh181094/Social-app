package com.example.a1505197.instgramclone;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by 1505197 on 10/11/2017.
 */

public class GridImageAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private LayoutInflater mInflater;
    private int layoutResource;
    private String mAppend;
    private ArrayList<String> imgURLs;

    public GridImageAdapter(@NonNull Context context, @LayoutRes int layoutResource, String mAppend,ArrayList<String> imgURLs) {
        super(context, layoutResource,imgURLs);
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        this.layoutResource = layoutResource;
        this.mAppend = mAppend;
        this.imgURLs = imgURLs;
    }
    private static class ViewHolder
    {
        SquareImageView Image;
        ProgressBar mProgressBar;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        final ViewHolder holder;
        if(convertView==null)
        {
            convertView=mInflater.inflate(layoutResource,parent,false);
            holder=new ViewHolder();
            holder.mProgressBar= (ProgressBar) convertView.findViewById(R.id.profileProgressBar);
            holder.Image=(SquareImageView) convertView.findViewById(R.id.gridImageView);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }
        String imgURL=getItem(position);
        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.displayImage(mAppend + imgURL, holder.Image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageURL, View view) {
                if (holder.mProgressBar != null) {
                    holder.mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageURL, View view, FailReason failReason) {
                if (holder.mProgressBar != null) {
                    holder.mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageURL, View view, Bitmap loadedImage) {
                if (holder.mProgressBar != null) {
                    holder.mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageURL, View view) {
                if (holder.mProgressBar != null) {
                    holder.mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        return convertView;
    }
}