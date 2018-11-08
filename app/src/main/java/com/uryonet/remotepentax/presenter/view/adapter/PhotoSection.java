package com.uryonet.remotepentax.presenter.view.adapter;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.uryonet.remotepentax.R;
import com.uryonet.remotepentax.app.MyApplication;
import com.uryonet.remotepentax.model.network.RetrofitInstance;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

import static android.content.Context.WINDOW_SERVICE;

public class PhotoSection extends StatelessSection {

    String name;
    List<String> files;
    Context context;

    public PhotoSection(String name, List<String> files, Context context) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.row_photo)
                .headerResourceId(R.layout.section_header)
                .build());
        this.name = name;
        this.files = files;
        this.context = context;
    }

    @Override
    public int getContentItemsTotal() {
        return files.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

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

        Glide.with(context).load(RetrofitInstance.BASE_URL + "photos/" + name + "/" + files.get(position) + "?size=thumb").apply(new RequestOptions().override(ScreenWidthHalf, ScreenWidthHalf)).into(itemHolder.ivPhoto);
        itemHolder.tvPhoto.setText(files.get(position));
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvTitle.setText(name);
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivPhoto;
        private final TextView tvPhoto;

        ItemViewHolder(View view) {
            super(view);

            ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);
            tvPhoto = (TextView) view.findViewById(R.id.tvPhoto);
        }
    }

}
