package com.example.pezyandroid.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pezyandroid.R;
import com.example.pezyandroid.databinding.FragmentNewMainNavBinding;
import com.opensooq.pluto.base.PlutoAdapter;
import com.opensooq.pluto.base.PlutoViewHolder;

public class MainFragment extends Fragment {

    private MainViewModel mainViewModel;

    private FragmentNewMainNavBinding fBind;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainViewModel =
                ViewModelProviders.of(this).get(MainViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_new_main_nav, container, false);

        fBind = FragmentNewMainNavBinding.inflate(inflater, container, false);



        return fBind.getRoot();
    }

}