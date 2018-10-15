package com.uryonet.remotepentax;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.uryonet.remotepentax.view.ui.PhotoListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            PhotoListFragment fragment = new PhotoListFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, PhotoListFragment.TAG).commit();
        }
    }
}
