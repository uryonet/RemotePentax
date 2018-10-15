package com.uryonet.remotepentax.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.uryonet.remotepentax.service.model.Dirs;
import com.uryonet.remotepentax.service.model.PhotoList;
import com.uryonet.remotepentax.service.repository.PhotoRepository;

import java.util.ArrayList;
import java.util.List;

public class PhotoListViewModel extends AndroidViewModel {

    // 監視対象のLiveData
    private final LiveData<List<String>> imageUrlObservable;

    public PhotoListViewModel(Application application) {
        super(application);

        LiveData<PhotoList> photoListObservable = PhotoRepository.getInstance().getPicList();
        imageUrlObservable = Transformations.map(photoListObservable, photoList -> {
           List<Dirs> photoDirs = photoList.getDirs();
           List<String> tmpUrl = new ArrayList<>();
           for(Dirs dir : photoDirs){
               for(String file : dir.getFiles()){
                   tmpUrl.add(dir.getName() + "/" + file);
               }
           }
           return tmpUrl;
        });
    }

    // UIが観察できるようにコンストラクタで取得したLiveDataを公開
    public LiveData<List<String>> getPhotoListObservable() {
        return imageUrlObservable;
    }
}
