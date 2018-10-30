package com.uryonet.remotepentax.model.network;

import com.uryonet.remotepentax.model.entity.PhotoList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CameraAPI {

    @GET("photos")
    Call<PhotoList> getPhotoList();

}
