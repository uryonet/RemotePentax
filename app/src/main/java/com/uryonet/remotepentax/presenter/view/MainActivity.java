package com.uryonet.remotepentax.presenter.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.uryonet.remotepentax.R;
import com.uryonet.remotepentax.model.entity.PhotoDir;
import com.uryonet.remotepentax.presenter.contract.MainContract;
import com.uryonet.remotepentax.presenter.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.testBtn)
    Button testBtn;

    private static final String TAG = "MainActivity";

    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupMVP();

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.getPhotoList();
            }
        });
    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void displayPhotoList(List<PhotoDir> photoDirs) {
        if(photoDirs != null) {
            Log.d(TAG, photoDirs.get(0).getFiles().get(0));
        } else {
            Log.d(TAG, "PhotoDirs response null");
        }
    }

    @Override
    public void displayError(String s) {
        Log.e(TAG, s);
    }
}
