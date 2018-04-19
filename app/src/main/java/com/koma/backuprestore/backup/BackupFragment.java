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
package com.koma.backuprestore.backup;

import android.os.Bundle;
import android.view.View;

import com.koma.backuprestore.R;
import com.koma.backuprestore.commonlibrary.base.BaseFragment;
import com.koma.loglibrary.KomaLog;

public class BackupFragment extends BaseFragment implements BackupContract.View {
    private static final String TAG = BackupFragment.class.getSimpleName();

    private BackupContract.Presenter mPresenter;

    public static BackupFragment newInstance() {
        return new BackupFragment();
    }

    public BackupFragment() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        KomaLog.i(TAG, "onViewCreated");

        if (mPresenter != null) {
            mPresenter.subscribe();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        KomaLog.i(TAG, "onDestroyView");

        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.backup_fragment;
    }

    @Override
    public void setPresenter(BackupContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return this.isAdded();
    }
}
