package com.uryonet.remotepentax.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.uryonet.remotepentax.service.model.PhotoList;
import com.uryonet.remotepentax.service.repository.PhotoRepository;

public class PhotoListViewModel extends AndroidViewModel {

    // 監視対象のLiveData
    private final LiveData<PhotoList> photoListObservable;

    public PhotoListViewModel(Application application) {
        super(application);

        photoListObservable = PhotoRepository.getInstance().getPicList();
    }

    // UIが観察できるようにコンストラクタで取得したLiveDataを公開
    public LiveData<PhotoList> getPhotoListObservable() {
        return photoListObservable;
    }
}
