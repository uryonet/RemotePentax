package com.uryonet.remotepentax.presenter.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.uryonet.remotepentax.presenter.view.Fragment.PhotoPreviewFragment;

import java.util.ArrayList;

public class PhotoPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> photoUrlList;

    public PhotoPagerAdapter(FragmentManager fm, ArrayList<String> photoUrlList) {
        super(fm);
        this.photoUrlList = photoUrlList;
    }

    @Override
    public Fragment getItem(int position) {
        String photoUrl = photoUrlList.get(position);
        return PhotoPreviewFragment.newInstance(photoUrl);
    }

    @Override
    public int getCount() {
        return photoUrlList.size();
    }

}
