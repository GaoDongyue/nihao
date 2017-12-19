package com.example.lenovo.yuekao.viewpageradapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/12/14.
 */
public class MyViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> mList;

    public MyViewPagerAdapter(ArrayList<View> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.isEmpty() ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }
}
