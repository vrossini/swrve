package com.example.swrve.viewmodels;

import android.os.AsyncTask;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.swrve.util.Utils;
import com.example.swrve.models.Image;
import com.example.swrve.network.ImageDownloader;

import java.util.List;
import java.net.URISyntaxException;
import java.net.MalformedURLException;

public class MainViewModel extends BaseViewModel {

    public MutableLiveData<String> url = new MutableLiveData<>();
    public MutableLiveData<Boolean> invalidUri = new MutableLiveData<>();
    public MutableLiveData<Long> insertSuccessful = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Image>> getImageLiveData() {
        return mDb.imageDAO().loadAllImages();
    }

    public void deleteImage(Image image) {
        AsyncTask.execute(() -> mDb.imageDAO().delete(image));
    }

    public void actionClicked() {
        try {
            if (Utils.isValidURL(url.getValue()))
                ImageDownloader.getImage(url.getValue());
        } catch (MalformedURLException | URISyntaxException e) {
            invalidUri.setValue(true);
        }
    }

    public MutableLiveData<Boolean> isInvalidUri() {
        return invalidUri;
    }

    public MutableLiveData<Image> getImageMutableLiveData() {
        return ImageDownloader.imageLiveData;
    }

    public void insertImage(Image image) {
        AsyncTask.execute(() -> insertSuccessful.postValue(mDb.imageDAO().insertImage(image)));
    }
}
