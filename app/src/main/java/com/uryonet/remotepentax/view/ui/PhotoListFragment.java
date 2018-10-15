package com.uryonet.remotepentax.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uryonet.remotepentax.R;
import com.uryonet.remotepentax.databinding.FragmentPhotoListBinding;
import com.uryonet.remotepentax.view.adapter.PhotoAdapter;
import com.uryonet.remotepentax.viewmodel.PhotoListViewModel;

import java.util.List;

public class PhotoListFragment extends Fragment {

    public static final String TAG = "PhotoListFragment";

    private PhotoAdapter photoAdapter;
    private FragmentPhotoListBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo_list, container, false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        binding.photoList.setLayoutManager(layoutManager);
        photoAdapter = new PhotoAdapter();
        binding.photoList.setAdapter(photoAdapter);
        binding.setIsLoading(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final PhotoListViewModel viewModel = ViewModelProviders.of(this).get(PhotoListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(PhotoListViewModel viewModel) {
        // データが変更されたときにアップデートする
        viewModel.getPhotoListObservable().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                if (strings != null) {
                    binding.setIsLoading(false);
                    photoAdapter.setPhotoList(strings);
                }
            }
        });
    }
}
