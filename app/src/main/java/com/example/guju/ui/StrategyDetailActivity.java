package com.example.guju.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.guju.R;

public class StrategyDetailActivity  extends Activity {
    private String[] titles;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decorate_rvdetails_activity);
    }
}
