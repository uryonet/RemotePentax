package com.uryonet.remotepentax.model.repository;

import com.uryonet.remotepentax.model.entity.PhotoList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CameraRepository {

    // SDカードに保存されている写真一覧を取得
    @GET("photos")
    Call<PhotoList> getPhotoList();

}
