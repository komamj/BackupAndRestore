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

import com.koma.backuprestore.BackupRestoreApplication;
import com.koma.backuprestore.R;
import com.koma.backuprestore.commonlibrary.base.BaseActivity;
import com.koma.loglibrary.KomaLog;

import javax.inject.Inject;

public class BackupActivity extends BaseActivity {
    private static final String TAG = BackupActivity.class.getSimpleName();

    @Inject
    BackupPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        KomaLog.i(TAG, "onCreate");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backup;
    }

    @Override
    protected String[] getPermissions() {
        return new String[0];
    }

    @Override
    protected void onPermissonGranted() {
        BackupFragment backupFragment = (BackupFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_main);

        if (backupFragment == null) {
            backupFragment = BackupFragment.newInstance();
        }

        DaggerBackupComponent.builder()
                .backupRestoreRepositoryComponent(((BackupRestoreApplication) getApplication()).getRepositoryComponent())
                .backupPresenterModule(new BackupPresenterModule(backupFragment))
                .build()
                .inject(this);
    }
}
