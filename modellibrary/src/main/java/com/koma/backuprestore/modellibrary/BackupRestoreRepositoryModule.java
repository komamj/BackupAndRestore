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
package com.koma.backuprestore.modellibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.koma.backuprestore.modellibrary.source.backup.BackupDataSource;
import com.koma.backuprestore.modellibrary.source.backup.BackupDataSourceImp;
import com.koma.backuprestore.modellibrary.source.restore.RestoreDataSource;
import com.koma.backuprestore.modellibrary.source.restore.RestoreDataSourceImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by koma on 2/28/18.
 */
@Singleton
@Module
public class BackupRestoreRepositoryModule {
    @Singleton
    @Provides
    SharedPreferences provideSharePreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    BackupDataSource provideBackupDataSource(Context context) {
        return new BackupDataSourceImp(context);
    }

    @Singleton
    @Provides
    RestoreDataSource provideRestoreDataSource(Context context) {
        return new RestoreDataSourceImp(context);
    }
}