package com.uryonet.remotepentax.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uryonet.remotepentax.service.model.PhotoList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoRepository {

    // Retrofitインタフェース
    private CameraService cameraService;

    // staticに提供できるRepository
    private static PhotoRepository photoRepository;

    // コンストラクタでRetrofitインスタンスを作成
    private PhotoRepository() {
        // インタフェースを呼び出し
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(CameraService.HTTP_CAMERA_API_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        cameraService = retrofit.create(CameraService.class);
    }

    // singletonでRepositoryインスタンスを取る
    public synchronized static PhotoRepository getInstance() {
        if (photoRepository == null) {
            photoRepository = new PhotoRepository();
        }
        return photoRepository;
    }

    // 写真一覧を取得して、レスポンスをLiveDataで返す
    public LiveData<PhotoList> getPicList() {
        final MutableLiveData<PhotoList> data = new MutableLiveData<>();

        //Retrofitで非同期APIリクエスト
        cameraService.getPicList().enqueue(new Callback<PhotoList>() {
            @Override
            public void onResponse(Call<PhotoList > call, Response<PhotoList> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PhotoList> call, Throwable t) {
                System.out.println("Error getPicList!!");
                t.printStackTrace();
            }
        });

        return data;

    }




}
