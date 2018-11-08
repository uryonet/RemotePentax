package com.uryonet.remotepentax.presenter.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.uryonet.remotepentax.R;
import com.uryonet.remotepentax.model.network.RetrofitInstance;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreviewPhotoActivity extends AppCompatActivity {

    @BindView(R.id.iv_preview)
    PhotoView ivPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_photo);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        String dirName = intent.getStringExtra("dirName");
        String photoFile = intent.getStringExtra("photoFile");
        Glide.with(getApplicationContext()).load(RetrofitInstance.BASE_URL + "photos/" + dirName + "/" + photoFile + "?size=view").into(ivPreview);
    }

}
