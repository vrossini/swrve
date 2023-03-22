package com.example.swrve.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.swrve.R;
import com.example.swrve.viewmodels.MainViewModel;
import com.example.swrve.databinding.FragmentDownloadBinding;

public class DownloadFragment extends Fragment {

    private MainViewModel mViewModel;
    private FragmentDownloadBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_download, container, false);
        binding.setMViewModel(mViewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.editUrl.setText("");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // clean editText when download is successful
        mViewModel.insertSuccessful.observe(requireActivity(), value -> {
            if (value > -1)
                binding.editUrl.setText("");
        });
    }
}
