package com.example.nestedviewpager.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * 竖直方向切换的ViewPager
 */
public class VerticalViewPager extends ViewPager {

    public VerticalViewPager(Context context) {
        super(context);
        init();
    }

    public void init(){
        setPageTransformer(true,new VerticalViewPagerTransform());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        init();
    }

    private MotionEvent swapXY(MotionEvent event) {
        float newX = event.getY();
        float newY = event.getX();

        event.setLocation(newX,newY);
        return event;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = super.onInterceptTouchEvent(swapXY(ev));
        swapXY(ev);
        return intercept;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapXY(ev));
    }


    //带有伸缩效果的翻页效果
    private class ExpandViewPagerTransform implements ViewPager.PageTransformer{

        private static final float Min_Scale = 0.65f;
        @Override
        public void transformPage(@NonNull View page, float position) {

            if(position < -1) {
                page.setAlpha(0);
            }else if(position <= 0){
                page.setAlpha(1);
                page.setTranslationX(page.getWidth() * -position);
                page.setTranslationY(page.getHeight() * position);
                page.setScaleX(1);
                page.setScaleY(1);
            }else if(position <= 1){
                page.setAlpha(1-position);
                page.setTranslationX(page.getWidth()* -position);
                page.setTranslationY(0);
                float scaleFactor = Min_Scale + (1 - Min_Scale) * (1 - Math.abs(position));
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            }else if(position > 1){
                page.setAlpha(0);
            }
        }
    }

    //平滑的翻页效果
    private class VerticalViewPagerTransform implements PageTransformer {

        @Override
        public void transformPage(@NonNull View page, float position) {
            if (position < -1) {
                page.setAlpha(0);
            } else if (position <= 1) {
                page.setAlpha(1);
                // Counteract the default slide transition
                float xPosition = page.getWidth() * -position;
                page.setTranslationX(xPosition);
                //set Y position to swipe in from top
                float yPosition = position * page.getHeight();
                page.setTranslationY(yPosition);
            } else {
                page.setAlpha(0);
            }
        }
    }

}