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
package com.koma.backuprestore.modellibrary.source.backup;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.koma.backuprestore.modellibrary.entities.Apk;
import com.koma.backuprestore.modellibrary.entities.Image;
import com.koma.backuprestore.modellibrary.entities.Video;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * Created by koma on 2/28/18.
 */

@Singleton
public class BackupDataSource implements IBackupDataSource {
    private static final String TAG = IBackupDataSource.class.getSimpleName();
    private static final String[] PROJECTION_VIDEO = {MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME};
    private static final String[] PROJECTION_IMAGE = {MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.DISPLAY_NAME};

    private final Context mContext;

    @Inject
    public BackupDataSource(Context context) {
        mContext = context;
    }

    @Override
    public Flowable<List<Video>> getVideos() {
        return Flowable.create(new FlowableOnSubscribe<List<Video>>() {
            @Override
            public void subscribe(FlowableEmitter<List<Video>> emitter) throws Exception {
                List<Video> videos = new ArrayList<>();
                Cursor cursor = null;
                try {
                    cursor = mContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                            PROJECTION_VIDEO, null, null, null);
                    if (cursor != null && cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        do {
                            Video video = new Video();
                            video.id = cursor.getInt(0);
                            video.displayName = cursor.getString(1);
                            videos.add(video);
                        } while (cursor.moveToNext());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }

                emitter.onNext(videos);
                emitter.onComplete();
            }
        }, BackpressureStrategy.LATEST);
    }

    @Override
    public Flowable<List<Apk>> getApps() {
        return null;
    }

    @Override
    public Flowable<List<Image>> getImages() {
        return null;
    }
}
