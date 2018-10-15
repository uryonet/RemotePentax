package com.uryonet.remotepentax.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uryonet.remotepentax.R;
import com.uryonet.remotepentax.databinding.PhotoListItemBinding;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    List<? extends String> imageUrls;

    public PhotoAdapter() {
    }

    public void setPhotoList(final List<? extends String> imageUrls) {
        this.imageUrls = imageUrls;
        notifyItemRangeInserted(0, imageUrls.size());
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PhotoListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.photo_list_item, parent, false);
        return new PhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.binding.setImagePath(imageUrls.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return imageUrls == null ? 0 : imageUrls.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {

        final PhotoListItemBinding binding;

        public PhotoViewHolder(PhotoListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
