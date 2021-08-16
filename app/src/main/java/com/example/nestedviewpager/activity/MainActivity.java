package com.example.nestedviewpager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.nestedviewpager.R;
import com.example.nestedviewpager.adapter.VerticalPagerAdapter;
import com.example.nestedviewpager.bean.PostMessage;
import com.example.nestedviewpager.fragment.MessageFullScreenFragment;
import com.example.nestedviewpager.viewpager.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private VerticalViewPager verticalPager;
    private List<MessageFullScreenFragment> fragments = new ArrayList<>();
    private List<PostMessage> mMessageList = new ArrayList<>();
    private PostMessage currentPostMessage;
    private int messagePosition;
    private VerticalPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verticalPager = findViewById(R.id.vertical_pager);
        initVerticalViewPager();
    }


    private void initVerticalViewPager() {
        messagePosition = getIntent().getIntExtra("messagePosition", 0);
        int photoPosition = getIntent().getIntExtra("photoPosition", 0);
        mMessageList = generateMessageList();
        if (mMessageList.size() > 0 ) {
            currentPostMessage = mMessageList.get(messagePosition);
        }
        for (int i = 0; i < mMessageList.size(); i++) {
            MessageFullScreenFragment fragment = new MessageFullScreenFragment(this, mMessageList.get(i), (messagePosition == i ? photoPosition : 0));
            fragments.add(fragment);
        }
        verticalPager.setOffscreenPageLimit(3);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        pagerAdapter = new VerticalPagerAdapter(fragments, supportFragmentManager);
        verticalPager.setAdapter(pagerAdapter);
        verticalPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPostMessage = mMessageList.get(position);
                messagePosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        verticalPager.setCurrentItem(messagePosition);
    }

    private List<PostMessage> generateMessageList() {
        List<PostMessage> resultList = new ArrayList<>();

        PostMessage message1 = new PostMessage();
        message1.setId(1);
        message1.setContent("第一页");
        List<String> list1 = new ArrayList<>();
        list1.add("https://s3.bmp.ovh/imgs/2021/08/9851754afbdc3191.png");
        list1.add("https://s3.bmp.ovh/imgs/2021/08/4637de621c8600db.png");
        list1.add("https://s3.bmp.ovh/imgs/2021/08/1e2102faeb2fed52.png");
        message1.setImageList(list1);
        resultList.add(message1);


        PostMessage message2 = new PostMessage();
        message2.setId(2);
        message2.setContent("第二页");
        List<String> list2 = new ArrayList<>();
        list2.add("https://s3.bmp.ovh/imgs/2021/08/f70134e23a8d06c9.png");
        list2.add("https://s3.bmp.ovh/imgs/2021/08/750947b5f8fdaec2.png");
        list2.add("https://s3.bmp.ovh/imgs/2021/08/441937a25aa29c67.png");
        message2.setImageList(list2);
        resultList.add(message2);

        PostMessage message3 = new PostMessage();
        message3.setId(3);
        message3.setContent("第三页");
        List<String> list3 = new ArrayList<>();
        list3.add("https://s3.bmp.ovh/imgs/2021/08/fde1e5700bc9bab8.png");
        list3.add("https://s3.bmp.ovh/imgs/2021/08/56a3c5cc9df841aa.png");
        list3.add("https://s3.bmp.ovh/imgs/2021/08/41911e9490092005.png");
        message3.setImageList(list3);
        resultList.add(message3);

        return resultList;
    }

}