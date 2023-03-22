package com.example.swrve.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swrve.R;
import com.example.swrve.databinding.ImageItemBinding;
import com.example.swrve.models.Image;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

    private List<Image> mImageList;
    private MutableLiveData<Image> imageDelete = new MutableLiveData<>();

    public ImageAdapter() {
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.image_item, parent, false);
        return new ImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.binding.imageId.setText(String.valueOf(mImageList.get(position).getId()));
        holder.binding.url.setText(mImageList.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        if (mImageList == null) {
            return 0;
        }
        return mImageList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setImages(List<Image> imgList) {
        mImageList = imgList;
        notifyDataSetChanged();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageItemBinding binding;
        ImageViewHolder(ImageItemBinding imageItemBinding) {
            super(imageItemBinding.getRoot());
            this.binding = imageItemBinding;
            binding.deleteImage.setOnClickListener(v -> {
                imageDelete.setValue(mImageList.get(getAdapterPosition()));
            });
        }
    }

    public MutableLiveData<Image> getImageDelete() {
        return imageDelete;
    }
}
