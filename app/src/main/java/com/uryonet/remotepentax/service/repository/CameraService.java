package com.uryonet.remotepentax.service.repository;

import com.uryonet.remotepentax.service.model.PhotoList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CameraService {

    // Retrofitインタフェース
    String HTTP_CAMERA_API_URL = "http://192.168.0.1/v1/";

    // 写真一覧取得
    @GET("photos")
    Call<PhotoList> getPicList();
}
