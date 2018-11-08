package com.uryonet.remotepentax.presenter.presenter;

import com.uryonet.remotepentax.model.domain.CameraDomain;
import com.uryonet.remotepentax.model.event.ErrorEvent;
import com.uryonet.remotepentax.model.event.PhotoListEvent;
import com.uryonet.remotepentax.presenter.contract.MainContract;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
//        List<String> photoUrlList = new ArrayList<>();
//        List<PhotoDir> photoDirs = event.photoList.getDirs();
//        for(PhotoDir dir : photoDirs) {
//            for(String file : dir.getFiles()) {
//                if (file.contains("JPG")) {
//                    photoUrlList.add(dir.getName() + "/" + file);
//                }
//            }
//        }
        mainContractView.displayPhotoList(event.photoList.getDirs());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent event) {
        event.e.printStackTrace();
        mainContractView.displayError("Error get photolist");
    }

}
