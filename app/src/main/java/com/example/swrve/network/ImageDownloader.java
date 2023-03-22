package com.example.swrve.network;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.lifecycle.MutableLiveData;

import com.example.swrve.models.Image;

import com.squareup.picasso.Target;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.NetworkPolicy;

public class ImageDownloader {
    private static String uri;
    public static MutableLiveData<Image> imageLiveData = new MutableLiveData<>();

    public static void getImage(String url) {
        imageLiveData.setValue(new Image());
        uri = url;
        if (url != null) {
            Picasso.get()
                    .load(url)
                    .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                    .into(mTarget);
        }
    }

    private static Target mTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            imageLiveData.setValue(new Image(uri, bitmap));
        }
        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {}
        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {}
    };
}
