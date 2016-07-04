package com.example.guju.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.guju.R;

/**
 * Created by green on 2016/7/4.
 */
public class BaseFragment extends Fragment {
    private ImageView login;
    private ImageView freeDesign;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_base,null);
        login = ((ImageView) view.findViewById(R.id.image_login_id));
        freeDesign = ((ImageView) view.findViewById(R.id.image_freedesign_id));
        return view;
    }
}
