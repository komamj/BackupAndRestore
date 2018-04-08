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
package com.koma.backuprestore;

import android.app.Application;
import android.os.StrictMode;

import com.bumptech.glide.Glide;
import com.koma.backuprestore.modellibrary.ApplicationModule;
import com.koma.backuprestore.modellibrary.BackupRestoreRepositoryComponent;
import com.koma.backuprestore.modellibrary.BackupRestoreRepositoryModule;
import com.koma.backuprestore.modellibrary.DaggerBackupRestoreRepositoryComponent;
import com.koma.loglibrary.KomaLog;

/**
 * Created by koma on 2/28/18.
 */

public class BackupRestoreApplication extends Application {
    private static final String TAG = BackupRestoreApplication.class.getSimpleName();

    private BackupRestoreRepositoryComponent mRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mRepositoryComponent = DaggerBackupRestoreRepositoryComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .backupRestoreRepositoryModule(new BackupRestoreRepositoryModule())
                .build();

        if (BuildConfig.DEBUG) {
            setStrictMode();
        }
    }

    public BackupRestoreRepositoryComponent getRepositoryComponent() {
        return mRepositoryComponent;
    }

    private void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        KomaLog.e(TAG, "onLowMemory");

        //clear cache
        Glide.get(this).clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        KomaLog.i(TAG, "onTrimMemory level : " + level);

        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }

        Glide.get(this).trimMemory(level);
    }
}
