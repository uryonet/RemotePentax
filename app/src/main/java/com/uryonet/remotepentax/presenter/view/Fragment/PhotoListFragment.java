package com.uryonet.remotepentax.presenter.view.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.uryonet.remotepentax.R;
import com.uryonet.remotepentax.model.entity.PhotoDir;
import com.uryonet.remotepentax.presenter.contract.MainContract;
import com.uryonet.remotepentax.presenter.presenter.MainPresenter;
import com.uryonet.remotepentax.presenter.view.adapter.FilterableSection;
import com.uryonet.remotepentax.presenter.view.adapter.PhotoSection;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class PhotoListFragment extends Fragment implements MainContract.View {

    RecyclerView rvPhotoList;

    public static final String TAG = "PhotoListFragment";

    SectionedRecyclerViewAdapter sectionAdapter;
    MainPresenter mainPresenter;

    public PhotoListFragment(){};

    public static PhotoListFragment newInstance() {
        return new PhotoListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPhotoList = (RecyclerView) view.findViewById(R.id.rvPhotoList);

        setupMVP();
        setupViews();
        getPhotoList();
        setPhotoFilter(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(mainPresenter);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(mainPresenter);
        super.onStop();
    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    private void setupViews() {
        GridLayoutManager glm = new GridLayoutManager(getContext(), 3);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (sectionAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 3;
                    default:
                        return 1;
                }
            }
        });
        rvPhotoList.setLayoutManager(glm);
    }

    private void getPhotoList() {
        mainPresenter.getPhotoList();
    }

    private void setPhotoFilter(View view) {
        final View localView = view;
        RadioGroup rg = (RadioGroup) localView.findViewById(R.id.rgChangeFile);
        rg.check(R.id.rbAll);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton currentRb = (RadioButton) localView.findViewById(i);
                String filterTxt = currentRb.getText().toString();
                if (currentRb.getText().toString().equals("RAW")) {
                    filterTxt = "PEF";
                }
                for (Section section : sectionAdapter.getCopyOfSectionsMap().values()) {
                    if (section instanceof FilterableSection) {
                        ((FilterableSection) section).filter(filterTxt);
                    }
                }
                sectionAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void displayPhotoList(List<PhotoDir> photoDirList, final List<String> photoUrlList) {
        if(photoDirList != null) {
            sectionAdapter = new SectionedRecyclerViewAdapter();

            for(PhotoDir dir : photoDirList) {
                sectionAdapter.addSection(new PhotoSection(dir.getName(), dir.getFiles(), getContext()) {
                    @Override
                    protected void onPhotoClicked(View view, @NonNull String photoFile) {
                        super.onPhotoClicked(view, photoFile);

                        PhotoPagerFragment ppFragment = PhotoPagerFragment.newInstance(photoFile, (ArrayList<String>) photoUrlList);

                        getFragmentManager().beginTransaction().addToBackStack(PhotoPagerFragment.TAG).replace(R.id.fragment_container, ppFragment).commit();
                    }
                });
            }
            rvPhotoList.setAdapter(sectionAdapter);
        } else {
            Log.d(TAG, "PhotoDirs response null");
        }
    }

    @Override
    public void displayError(String s) {
        Log.e(TAG, s);
    }

}
