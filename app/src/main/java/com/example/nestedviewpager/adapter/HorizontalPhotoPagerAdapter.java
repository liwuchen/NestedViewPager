package com.example.nestedviewpager.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.nestedviewpager.R;

import java.util.List;

public class HorizontalPhotoPagerAdapter extends PagerAdapter {

    private List<String> photos;
    private Context mContext;

    public HorizontalPhotoPagerAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return null == photos ? 0 : photos.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //TODO update to viewHoder
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_horizontal_photo_adapter, container, false);
        AppCompatImageView imageView = view.findViewById(R.id.img_photo_fullscreen);
        if (photos != null && photos.size() != 0) {
            Glide.with(mContext).load(photos.get(position))
                    .placeholder(R.drawable.svg_ic_img_nonetwork)
                    .error(R.drawable.svg_ic_img_nonetwork)
                    .into(imageView);
        }
        container.addView(view);
        return view;
    }
}
