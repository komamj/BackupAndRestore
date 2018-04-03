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
package com.koma.backuprestore.restore;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.koma.backuprestore.BackupRestoreApplication;

import javax.inject.Inject;

public class RestoreService extends Service implements RestoreContract.View {
    private static final String TAG = RestoreService.class.getSimpleName();

    @Inject
    RestorePresenter mPresenter;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerRestoreComponent.builder()
                .backupRestoreRepositoryComponent(
                        ((BackupRestoreApplication) getApplication()).getRepositoryComponent())
                .restorePresenterModule(new RestorePresenterModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void setPresenter(RestoreContract.Presenter presenter) {

    }
}
