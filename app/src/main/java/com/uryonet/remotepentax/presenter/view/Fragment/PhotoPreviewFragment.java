package com.uryonet.remotepentax.presenter.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.uryonet.remotepentax.R;
import com.uryonet.remotepentax.model.network.RetrofitInstance;

public class PhotoPreviewFragment extends Fragment {

    public static final String TAG = "PhotoPreviewFragment";
    private static final String IMAGE_TAG = "current_item";

    public PhotoPreviewFragment(){};

    public static PhotoPreviewFragment newInstance(String photoUrl) {
        PhotoPreviewFragment fragment = new PhotoPreviewFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_TAG, photoUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_preview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String photoUrl = getArguments().getString(IMAGE_TAG);
        final PhotoView pv_photo = (PhotoView) view.findViewById(R.id.pv_photo);
        Glide.with(getActivity()).load(RetrofitInstance.BASE_URL + "photos/" + photoUrl + "?size=view").into(pv_photo);
    }

}
