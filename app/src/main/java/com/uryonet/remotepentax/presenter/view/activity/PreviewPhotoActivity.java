package com.uryonet.remotepentax.presenter.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.uryonet.remotepentax.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreviewPhotoActivity extends AppCompatActivity {

    @BindView(R.id.iv_preview)
    ImageView ivPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_photo);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        String photoUrl = intent.getStringExtra("photoUrl");
        Glide.with(getApplicationContext()).load(photoUrl + "?size=view").into(ivPreview);
    }

}
