package com.uryonet.remotepentax.model.network;

import com.uryonet.remotepentax.model.entity.PhotoList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CameraAPI {

    @GET("photos")
    Observable<PhotoList> getPhotoList();

}
