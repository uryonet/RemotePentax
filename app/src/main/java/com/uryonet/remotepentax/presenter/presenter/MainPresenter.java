package com.uryonet.remotepentax.presenter.presenter;

import android.util.Log;

import com.uryonet.remotepentax.model.entity.PhotoList;
import com.uryonet.remotepentax.model.network.CameraAPI;
import com.uryonet.remotepentax.model.network.CameraDataSource;
import com.uryonet.remotepentax.presenter.contract.MainContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";
    private CameraDataSource cameraDataSource = new CameraDataSource();

    MainContract.View mainContractView;

    public MainPresenter(MainContract.View mainContractView) {
        this.mainContractView = mainContractView;
    }

    @Override
    public void getPhotoList() {
        getObservable().subscribeWith(getObserver());
    }

    public Observable<PhotoList> getObservable() {
        return CameraDataSource.getRetrofit().create(CameraAPI.class).getPhotoList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<PhotoList> getObserver() {

        return new DisposableObserver<PhotoList>() {
            @Override
            public void onNext(PhotoList photoList) {
                Log.d(TAG, photoList.getDirs().get(0).getFiles().get(0));
                mainContractView.displayPhotoList(photoList.getDirs());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mainContractView.displayError("Error get photolist");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Comepleted!");
            }
        };

    }

}
