package com.uryonet.remotepentax.model.network;

import android.database.Observable;

import com.uryonet.remotepentax.model.entity.PhotoList;

import retrofit2.http.GET;

public interface CameraAPI {

    @GET("photos")
    Observable<PhotoList> getPhotoList();

}
