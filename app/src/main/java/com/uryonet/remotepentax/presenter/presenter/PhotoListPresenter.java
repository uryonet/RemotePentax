package com.uryonet.remotepentax.presenter.presenter;

import com.uryonet.remotepentax.model.domain.CameraDomain;
import com.uryonet.remotepentax.model.entity.PhotoDir;
import com.uryonet.remotepentax.model.event.ErrorEvent;
import com.uryonet.remotepentax.model.event.PhotoListEvent;
import com.uryonet.remotepentax.presenter.contract.PhotoListContract;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class PhotoListPresenter implements PhotoListContract.Presenter {

    private static final String TAG = "PhotoListPresenter";
    PhotoListContract.View photoListContractView;

    public PhotoListPresenter(PhotoListContract.View photoListContractView) {
        this.photoListContractView = photoListContractView;
    }

    @Override
    public void getPhotoList() {
        CameraDomain.getPhotoList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPhotoListEvent(PhotoListEvent event) {
        List<String> photoUrlList = new ArrayList<>();
        List<PhotoDir> photoDirs = event.photoList.getDirs();
        for(PhotoDir dir : photoDirs) {
            for(String file : dir.getFiles()) {
                photoUrlList.add(dir.getName() + "/" + file);
            }
        }
        photoListContractView.displayPhotoList(photoDirs, photoUrlList);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent event) {
        event.e.printStackTrace();
        photoListContractView.displayError("Error get photolist");
    }

}
