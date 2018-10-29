package com.uryonet.remotepentax.model.network;

import com.uryonet.remotepentax.model.entity.PhotoList;
import com.uryonet.remotepentax.model.event.ErrorEvent;
import com.uryonet.remotepentax.model.event.PhotoListEvent;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CameraDataSource {
    private CameraAPI service;

    public void CameraDataSource() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.1/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        service = retrofit.create(CameraAPI.class);
    }

    public void getPhotoList() {
        Call<PhotoList> call = service.getPhotoList();
        call.enqueue(new Callback<PhotoList>() {
            @Override
            public void onResponse(Call<PhotoList> call, Response<PhotoList> response) {
                EventBus.getDefault().post(new PhotoListEvent(response.body()));
            }

            @Override
            public void onFailure(Call<PhotoList> call, Throwable t) {
                EventBus.getDefault().post(new ErrorEvent(t));
            }
        });
    }

}
