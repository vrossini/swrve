package com.example.swrve;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;

import com.example.swrve.ui.MainFragment;
import com.example.swrve.ui.ImageDialogFragment;
import com.example.swrve.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        // show alert when insert DB failed
        mViewModel.insertSuccessful.observe(this, value -> {
            if (value < 0)
                Toast.makeText(this, getResources().getString(R.string.database_error), Toast.LENGTH_SHORT).show();
        });

        // show alert when URL is invalid
        mViewModel.isInvalidUri().observe(this, value -> {
            if (value)
                Toast.makeText(this, getResources().getString(R.string.invalid_url), Toast.LENGTH_SHORT).show();
        });

        mViewModel.isInvalidUri().observe(this, value -> {
            if (value)
                Toast.makeText(this, getResources().getString(R.string.invalid_url), Toast.LENGTH_SHORT).show();
        });


        // open dialog when image downloaded
        mViewModel.getImageMutableLiveData().observe(this, image -> {
            if (image.getBitmap() != null)
                new Handler().postDelayed(() -> new ImageDialogFragment().show(getSupportFragmentManager(), "imageDialog"), 3000);
        });
    }
}
