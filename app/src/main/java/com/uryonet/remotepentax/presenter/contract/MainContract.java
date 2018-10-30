package com.uryonet.remotepentax.presenter.contract;

import java.util.List;

public interface MainContract {

    interface View {

        void displayPhotoList(List<String> photoUrlList);
        void displayError(String s);

    }

    interface Presenter {

        void getPhotoList();

    }

}
