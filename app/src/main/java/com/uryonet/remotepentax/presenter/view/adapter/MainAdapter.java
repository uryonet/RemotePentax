package com.uryonet.remotepentax.presenter.view.adapter;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.uryonet.remotepentax.R;
import com.uryonet.remotepentax.app.MyApplication;

import java.util.List;

import static android.content.Context.WINDOW_SERVICE;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    List<String> photoUrlList;
    Context context;

    public MainAdapter(List<String> photoUrlList, Context context) {
        this.photoUrlList = photoUrlList;
        this.context = context;
    }

//    protected void onPhotoClicked(View view, @NonNull String currentBrand) {
//    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_photo, parent, false);
        final MainHolder mh = new MainHolder(v);

//        mh.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final int position = mh.getAdapterPosition();
//                final String currentBrand = brandList.get(position).getCode();
//                onBrandClicked(view, currentBrand);
//            }
//        });

        return mh;
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        int ScreenWidthHalf = 0;

        // 画面の横幅の半分を計算
        WindowManager wm = (WindowManager)MyApplication.getAppContext().getSystemService(WINDOW_SERVICE);
        if(wm != null){
            Display disp = wm.getDefaultDisplay();
            Point size = new Point();
            disp.getSize(size);

            int screenWidth = size.x;
            ScreenWidthHalf = screenWidth / 3 - 6;
        }

        Glide.with(context).load(photoUrlList.get(position) + "?size=thumb").apply(new RequestOptions().override(ScreenWidthHalf, ScreenWidthHalf)).into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return photoUrlList.size();
    }

    public class MainHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;

        public MainHolder(View v) {
            super(v);
            ivPhoto = (ImageView) v.findViewById(R.id.ivPhoto);
        }
    }
}
