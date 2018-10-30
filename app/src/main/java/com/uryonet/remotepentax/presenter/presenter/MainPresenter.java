package com.uryonet.remotepentax.presenter.presenter;

import android.util.Log;

import com.uryonet.remotepentax.model.domain.CameraDomain;
import com.uryonet.remotepentax.model.entity.PhotoDir;
import com.uryonet.remotepentax.model.event.ErrorEvent;
import com.uryonet.remotepentax.model.event.PhotoListEvent;
import com.uryonet.remotepentax.model.network.RetrofitInstance;
import com.uryonet.remotepentax.presenter.contract.MainContract;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";
    MainContract.View mainContractView;

    public MainPresenter(MainContract.View mainContractView) {
        this.mainContractView = mainContractView;
    }

    @Override
    public void getPhotoList() {
        CameraDomain.getPhotoList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPhotoListEvent(PhotoListEvent event) {
        Log.d(TAG, event.photoList.getDirs().get(0).getFiles().get(0));
        List<String> photoUrlList = new ArrayList<>();
        List<PhotoDir> photoDirs = event.photoList.getDirs();
        for(PhotoDir dir : photoDirs) {
            for(String file : dir.getFiles()) {
                photoUrlList.add(RetrofitInstance.BASE_URL + "photos/" + dir.getName() + "/" + file);
            }
        }
        Log.d(TAG, photoUrlList.get(0));
        mainContractView.displayPhotoList(photoUrlList);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent event) {
        event.e.printStackTrace();
        mainContractView.displayError("Error get photolist");
    }

}
