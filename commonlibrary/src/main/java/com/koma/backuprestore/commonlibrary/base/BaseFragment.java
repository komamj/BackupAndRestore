/*
 * Copyright (C) 2018, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * BaseFragment.java
 *
 * Description
 *
 * Author MaoJun
 *
 * Ver 1.0, Mar 29, 2018, MaoJun, Create file
 */
package com.koma.backuprestore.commonlibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savdInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    public abstract int getLayoutId();
}
