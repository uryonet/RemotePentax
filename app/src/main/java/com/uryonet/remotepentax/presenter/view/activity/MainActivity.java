package com.uryonet.remotepentax.presenter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.uryonet.remotepentax.R;
import com.uryonet.remotepentax.model.entity.PhotoDir;
import com.uryonet.remotepentax.presenter.contract.MainContract;
import com.uryonet.remotepentax.presenter.presenter.MainPresenter;
import com.uryonet.remotepentax.presenter.view.adapter.FilterableSection;
import com.uryonet.remotepentax.presenter.view.adapter.PhotoSection;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.rvPhotoList)
    RecyclerView rvPhotoList;
    Context context;

    private static final String TAG = "MainActivity";
    SectionedRecyclerViewAdapter sectionAdapter;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupMVP();
        setupViews();
        getPhotoList();
        setPhotoFilter();
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
        GridLayoutManager glm = new GridLayoutManager(this, 3);
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

    private void setPhotoFilter() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.rgChangeFile);
        rg.check(R.id.rbAll);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton currentRb = (RadioButton) findViewById(i);
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
    public void displayPhotoList(List<PhotoDir> photoDirList) {
        if(photoDirList != null) {
            sectionAdapter = new SectionedRecyclerViewAdapter();

            for(PhotoDir dir : photoDirList) {
                sectionAdapter.addSection(new PhotoSection(dir.getName(), dir.getFiles(), MainActivity.this) {
                    @Override
                    protected void onPhotoClicked(View view, @NonNull String dirName, @NonNull String photoFile) {
                        super.onPhotoClicked(view, dirName, photoFile);
                        Intent intent = new Intent(view.getContext(), PreviewPhotoActivity.class);
                        intent.putExtra("dirName", dirName);
                        intent.putExtra("photoFile", photoFile);
                        startActivity(intent);
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
