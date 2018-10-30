package com.example.kinglu.movierank.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kinglu.movierank.R;

/**
 * Created by 风临城城主 on 2018/10/10.
 */

public class QueryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.query_layout, container, false);
        return view;
    }
}
