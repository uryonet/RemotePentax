package com.uryonet.remotepentax.presenter.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.uryonet.remotepentax.R;
import com.uryonet.remotepentax.presenter.view.Fragment.PhotoListFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, PhotoListFragment.newInstance(), PhotoListFragment.TAG).commit();

    }

}
