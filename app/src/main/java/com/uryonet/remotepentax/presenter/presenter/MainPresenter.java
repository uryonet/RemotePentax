package com.uryonet.remotepentax.presenter.presenter;

import android.util.Log;

import com.uryonet.remotepentax.model.event.ErrorEvent;
import com.uryonet.remotepentax.model.event.PhotoListEvent;
import com.uryonet.remotepentax.model.network.CameraDataSource;
import com.uryonet.remotepentax.presenter.contract.MainContract;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";
    private CameraDataSource cameraDataSource;

    MainContract.View mainContractView;

    public MainPresenter(MainContract.View mainContractView) {
        this.mainContractView = mainContractView;
    }

    @Override
    public void getPhotoList() {
        cameraDataSource.getPhotoList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPhotoListEvent(PhotoListEvent event) {
        Log.d(TAG, event.photoList.getDirs().get(0).getFiles().get(0));
        mainContractView.displayPhotoList(event.photoList.getDirs());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent event) {
        event.e.printStackTrace();
        mainContractView.displayError("Error get photolist");
    }

}
