package com.example.nestedviewpager.adapter;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.nestedviewpager.fragment.MessageFullScreenFragment;

import java.util.List;

public class VerticalPagerAdapter extends FragmentStatePagerAdapter {

    private List<MessageFullScreenFragment> list;

    public VerticalPagerAdapter(List<MessageFullScreenFragment> list , FragmentManager fm) {
        super(fm);
        this.list = list;
    }

    @Override
    public MessageFullScreenFragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list!=null ? list.size() : 0;
    }

}
