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

import com.koma.backuprestore.data.entities.App;
import com.koma.backuprestore.data.entities.Image;
import com.koma.backuprestore.data.entities.Video;
import com.koma.backuprestore.data.source.IBackupRestoreDataSource;
import com.koma.backuprestore.data.source.backup.IBackupDataSource;
import com.koma.backuprestore.data.source.restore.IRestoreDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/**
 * Created by koma on 2/28/18.
 */

@Singleton
public class BackupRestoreRepository implements IBackupRestoreDataSource, IBackupDataSource, IRestoreDataSource {
    private final IBackupDataSource mBackupDataSource;

    private final IRestoreDataSource mRestoreDataSource;

    @Inject
    public BackupRestoreRepository(IBackupDataSource backupDataSource, IRestoreDataSource restoreDataSource) {
        mBackupDataSource = backupDataSource;

        mRestoreDataSource = restoreDataSource;
    }

    @Override
    public Flowable<List<Video>> getVideos() {
        return mBackupDataSource.getVideos();
    }

    @Override
    public Flowable<List<App>> getApps() {
        return mBackupDataSource.getApps();
    }

    @Override
    public Flowable<List<Image>> getImages() {
        return mBackupDataSource.getImages();
    }

    @Override
    public Flowable<String> getContactCount() {
        return null;
    }

    @Override
    public Flowable<String> getSmsCount() {
        return null;
    }

    @Override
    public Flowable<String> getCallLogCount() {
        return null;
    }

    @Override
    public Flowable<String> getCalendarEventCount() {
        return null;
    }

    @Override
    public Flowable<String> getImageCount() {
        return null;
    }

    @Override
    public Flowable<String> getVideoCount() {
        return null;
    }

    @Override
    public Flowable<String> getAudioCount() {
        return null;
    }

    @Override
    public Flowable<String> getDocmentCount() {
        return null;
    }

    @Override
    public Flowable<String> getAppCount() {
        return null;
    }

    @Override
    public Flowable<Integer> getContactCount(String fileName) {
        return mRestoreDataSource.getContactCount(fileName);
    }

    @Override
    public Flowable<Integer> restoreContacts(String fileName) {
        return mRestoreDataSource.restoreContacts(fileName);
    }

    @Override
    public Flowable<Integer> restoreCalendarEvents(String fileName) {
        return mRestoreDataSource.restoreCalendarEvents(fileName);
    }
}
