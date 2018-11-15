package com.uryonet.remotepentax.presenter.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uryonet.remotepentax.R;
import com.uryonet.remotepentax.presenter.view.adapter.CustomViewPager;
import com.uryonet.remotepentax.presenter.view.adapter.PhotoPagerAdapter;

import java.util.ArrayList;

public class PhotoPagerFragment extends Fragment {

    private CustomViewPager viewPager;

    public static final String TAG = "PhotoPagerFragment";
    private static final String FILE_TAG = "photoFile";
    private static final String LIST_TAG = "photoUrlList";

    public PhotoPagerFragment(){};

    public static PhotoPagerFragment newInstance(String photoFile, ArrayList<String> photoUrlList) {
        PhotoPagerFragment fragment = new PhotoPagerFragment();
        Bundle args = new Bundle();
        args.putString(FILE_TAG, photoFile);
        args.putStringArrayList(LIST_TAG, photoUrlList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String photoFile = getArguments().getString(FILE_TAG);
        ArrayList<String> photoUrlList = getArguments().getStringArrayList(LIST_TAG);
        int currentPosition = 0;

        int i = 0;
        for (String url : photoUrlList) {
            if (url.contains(photoFile)) {
                currentPosition = i;
                break;
            }
            i++;
        }

        PhotoPagerAdapter photoPagerAdapter = new PhotoPagerAdapter(getChildFragmentManager(), photoUrlList);
        CustomViewPager viewPager = (CustomViewPager) view.findViewById(R.id.vp_preview);
        viewPager.setAdapter(photoPagerAdapter);
        viewPager.setCurrentItem(currentPosition);
    }

}
