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
package com.koma.backuprestore.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koma.backuprestore.data.source.backup.BackupDataSource;
import com.koma.backuprestore.data.source.backup.IBackupDataSource;
import com.koma.backuprestore.data.source.restore.IRestoreDataSource;
import com.koma.backuprestore.data.source.restore.RestoreDataSource;

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
    Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Singleton
    @Provides
    IBackupDataSource provideBackupDataSource(Context context, Gson gson) {
        return new BackupDataSource(context, gson);
    }

    @Singleton
    @Provides
    IRestoreDataSource provideRestoreDataSource(Context context, Gson gson) {
        return new RestoreDataSource(context, gson);
    }
}
