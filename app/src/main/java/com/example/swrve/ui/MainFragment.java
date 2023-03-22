package com.example.swrve.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.swrve.R;
import com.example.swrve.adapters.ImageAdapter;
import com.example.swrve.viewmodels.MainViewModel;
import com.example.swrve.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private ImageAdapter mAdapter;
    private MainViewModel mViewModel;
    private FragmentMainBinding binding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setMViewModel(mViewModel);
        binding.setLifecycleOwner(requireActivity());

        // initialize the adapter and attach it to the RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mAdapter = new ImageAdapter();
        binding.recyclerView.setAdapter(mAdapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // open downloadFragment
        binding.addFAB.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new DownloadFragment(), "downloadFragment")
                .addToBackStack(null)
                .commit());

        // notify the adapter when change the list
        mViewModel.getImageLiveData().observe(requireActivity(), list -> {
            mAdapter.setImages(list);
        });

        // notify delete item
        mAdapter.getImageDelete().observe(requireActivity(), mViewModel::deleteImage);
    }
}
