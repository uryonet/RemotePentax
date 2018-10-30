package com.uryonet.remotepentax.model.domain;

import com.uryonet.remotepentax.model.entity.PhotoList;
import com.uryonet.remotepentax.model.event.ErrorEvent;
import com.uryonet.remotepentax.model.event.PhotoListEvent;
import com.uryonet.remotepentax.model.network.RetrofitInstance;
import com.uryonet.remotepentax.model.repository.CameraRepository;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraDomain {

    private static CameraRepository service = RetrofitInstance.getRetrofitInstance().create(CameraRepository.class);

    public static void getPhotoList() {
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
