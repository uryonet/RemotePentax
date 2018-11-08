package com.uryonet.remotepentax.presenter.contract;

import com.uryonet.remotepentax.model.entity.PhotoDir;

import java.util.List;

public interface MainContract {

    interface View {

        void displayPhotoList(List<PhotoDir> photoDirList);
        void displayError(String s);

    }

    interface Presenter {

        void getPhotoList();

    }

}
