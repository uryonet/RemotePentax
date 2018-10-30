package com.uryonet.remotepentax.presenter.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.uryonet.remotepentax.R;
import com.uryonet.remotepentax.presenter.contract.MainContract;
import com.uryonet.remotepentax.presenter.presenter.MainPresenter;
import com.uryonet.remotepentax.presenter.view.adapter.MainAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.rvPhotoList)
    RecyclerView rvPhotoList;

    private static final String TAG = "MainActivity";
    RecyclerView.Adapter adapter;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupMVP();
        setupViews();
        getPhotoList();
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
        rvPhotoList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getPhotoList() {
        mainPresenter.getPhotoList();
    }

    @Override
    public void displayPhotoList(List<String> photoUrlList) {
        if(photoUrlList != null) {
            adapter = new MainAdapter(photoUrlList, MainActivity.this);
            rvPhotoList.setAdapter(adapter);
        } else {
            Log.d(TAG, "PhotoDirs response null");
        }
    }

    @Override
    public void displayError(String s) {
        Log.e(TAG, s);
    }
}
