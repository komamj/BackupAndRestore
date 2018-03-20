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

import com.koma.backuprestore.modellibrary.entities.Apk;
import com.koma.backuprestore.modellibrary.entities.Image;
import com.koma.backuprestore.modellibrary.entities.Video;
import com.koma.backuprestore.modellibrary.source.BackupRestoreDataSource;
import com.koma.backuprestore.modellibrary.source.backup.BackupDataSource;
import com.koma.backuprestore.modellibrary.source.restore.RestoreDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/**
 * Created by koma on 2/28/18.
 */

@Singleton
public class BackupRestoreRepository implements BackupRestoreDataSource {
    private final BackupDataSource mBackupDataSource;

    private final RestoreDataSource mRestoreDataSource;

    @Inject
    public BackupRestoreRepository(BackupDataSource backupDataSource, RestoreDataSource restoreDataSource) {
        mBackupDataSource = backupDataSource;

        mRestoreDataSource = restoreDataSource;
    }

    @Override
    public Flowable<List<Video>> getVideos() {
        return mBackupDataSource.getVideos();
    }

    @Override
    public Flowable<List<Apk>> getApps() {
        return mBackupDataSource.getApps();
    }

    @Override
    public Flowable<List<Image>> getImages() {
        return mBackupDataSource.getImages();
    }
}
