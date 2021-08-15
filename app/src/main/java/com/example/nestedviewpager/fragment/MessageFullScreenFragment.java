package com.example.nestedviewpager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.nestedviewpager.R;
import com.example.nestedviewpager.adapter.HorizontalPhotoPagerAdapter;
import com.example.nestedviewpager.bean.PostMessage;
import com.example.nestedviewpager.viewpager.HorizontalViewPager;

import java.util.ArrayList;
import java.util.List;

public class MessageFullScreenFragment extends Fragment {

    private List<String> photoList = new ArrayList<>();
    private HorizontalPhotoPagerAdapter photoPagerAdapter;
    private int photoPosition;
    private PostMessage postMessage;
    private Context mContext;

    private TextView tvTotal;
    private TextView tvCurrentPosition;
    private TextView tvContent;
    private HorizontalViewPager photoPager;

    public MessageFullScreenFragment(Context context, PostMessage postMessage, int photoPosition) {
        photoPagerAdapter = new HorizontalPhotoPagerAdapter(context);
        this.postMessage = postMessage;
        if (postMessage != null && postMessage.getImageList() != null) {
            this.photoList =  postMessage.getImageList();
        }
        this.photoPosition = photoPosition;
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_message_fullscreen, null);
        tvTotal = contentView.findViewById(R.id.tv_total);
        tvCurrentPosition = contentView.findViewById(R.id.tv_current_position);
        tvContent = contentView.findViewById(R.id.tv_content);
        photoPager = contentView.findViewById(R.id.photo_pager);
        return contentView;
    }

    public int getPhotoPosition() {
        return this.photoPosition;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        photoPager.setAdapter(photoPagerAdapter);
        photoPager.setOffscreenPageLimit(3);
        List<String> urlList = new ArrayList<>();
        for (String uri : photoList) {
            urlList.add(uri == null ? "" :  uri);
        }
        photoPagerAdapter.setPhotos(urlList);
        photoPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                photoPosition = position;
                tvCurrentPosition.setText(String.valueOf(position+1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        photoPager.setCurrentItem(photoPosition);
        tvTotal.setText(String.valueOf(photoList.size()));
        tvContent.setText(postMessage.getContent());
    }


}
