package com.example.pezyandroid.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pezyandroid.R;
import com.example.pezyandroid.databinding.FragmentNewMainNavBinding;
import com.opensooq.pluto.PlutoView;
import com.opensooq.pluto.base.PlutoAdapter;
import com.opensooq.pluto.base.PlutoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mainViewModel;

    private FragmentNewMainNavBinding fBind;

    private PlutoView slide;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainViewModel =
                ViewModelProviders.of(this).get(MainViewModel.class);

        fBind = FragmentNewMainNavBinding.inflate(inflater, container, false);

        slideShow();

        return fBind.getRoot();
    }

    private void slideShow() {
        List<Integer> imgs = new ArrayList<>();
        imgs.add(R.drawable.slide_banner);
        imgs.add(R.drawable.slide_banner);
        imgs.add(R.drawable.slide_banner);
        imgs.add(R.drawable.slide_banner);
        imgs.add(R.drawable.slide_banner);
        imgs.add(R.drawable.slide_banner);

        SlideAdapter adapter = new SlideAdapter(imgs);
        fBind.sliderView.create(adapter, getLifecycle());
    }

    private class SlideAdapter extends PlutoAdapter<Integer, SlideAdapter.SlideViewHolder>{

        public SlideAdapter(List<Integer> items){
            super(items);
        }

        @Override
        public SlideViewHolder getViewHolder(ViewGroup viewGroup, int i) {
            return new SlideViewHolder(viewGroup, R.layout.layout_slide_show);
        }

        private class SlideViewHolder extends PlutoViewHolder<Integer>{

            private ImageView img;

            public SlideViewHolder(ViewGroup parent, int layout){
                super(parent, layout);
                img = getView(R.id.imgSlide);
            }

            @Override
            public void set(Integer integer, int i) {
                img.setImageResource(integer);
            }
        }
    }

}