/*
 * Copyright 2017 Koma
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.koma.backuprestore.restore.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.TextView;

import com.koma.backuprestore.R;
import com.koma.backuprestore.commonlibrary.base.BaseDialogFragment;
import com.koma.loglibrary.KomaLog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by koma on 4/8/18.
 */

public class RestoreProgressDialogFragment extends BaseDialogFragment {
    private static final String TAG = RestoreProgressDialogFragment.class.getSimpleName();
    private static final String DIALOG_TAG = "restore_progress_dialog";

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_current_count)
    TextView mCurrentCount;
    @BindView(R.id.tv_done)
    TextView mDone;
    @BindView(R.id.tv_total_count)
    TextView mTotalCount;
    @BindView(R.id.progress_bar)
    ContentLoadingProgressBar mProgressBar;

    @OnClick(R.id.tv_done)
    void onCompleted() {
        this.dismiss();
    }

    public static void showProgressDialog(FragmentManager fragmentManager) {
        RestoreProgressDialogFragment dialogFragment = new RestoreProgressDialogFragment();
        dialogFragment.show(fragmentManager, DIALOG_TAG);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(STYLE_NORMAL, R.style.RestoreDilogTheme);

        setCancelable(false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        KomaLog.i(TAG, "onViewCreated");

        mTitle.setText(mContext.getString(R.string.updating_data));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        KomaLog.i(TAG, "onDestroyView");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.restore_dialog;
    }
}
