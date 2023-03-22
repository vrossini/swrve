package com.example.swrve.ui;

import android.os.Bundle;
import android.app.Dialog;
import android.os.Handler;
import android.view.Window;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.swrve.R;
import com.example.swrve.models.Image;
import com.example.swrve.viewmodels.MainViewModel;
import com.example.swrve.databinding.ImageLayoutBinding;

public class ImageDialogFragment extends DialogFragment {

    private MainViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Image image = mViewModel.getImageMutableLiveData().getValue();
        Dialog dialog = new Dialog(requireActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        ImageLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(requireActivity()),
                R.layout.image_layout, null, false);
        dialog.setContentView(binding.getRoot());
        assert image != null;
        binding.imageDownloaded.setImageBitmap(image.getBitmap());
        mViewModel.insertImage(image);
        return dialog;
    }
}
