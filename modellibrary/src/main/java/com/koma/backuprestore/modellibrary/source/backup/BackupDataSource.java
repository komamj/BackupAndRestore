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
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;

import com.koma.backuprestore.modellibrary.entities.App;
import com.koma.backuprestore.modellibrary.entities.Image;
import com.koma.backuprestore.modellibrary.entities.Video;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private static final String TAG = BackupDataSource.class.getSimpleName();

    private static final String[] MUSIC_PROJECTION = {
            MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA
    };

    private static final String[] PROJECTION_VIDEO = {MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.Media.DATA};

    private static final String[] PROJECTION_IMAGE = {MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATA};

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
                            video.path = cursor.getString(2);
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
    public Flowable<List<Image>> getImages() {
        return null;
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
        return Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                emitter.onNext(String.valueOf(getApplicationInfos(mContext).size()));
                emitter.onComplete();
            }
        }, BackpressureStrategy.LATEST);
    }

    @Override
    public Flowable<List<App>> getApps() {
        return Flowable.create(new FlowableOnSubscribe<List<App>>() {
            @Override
            public void subscribe(FlowableEmitter<List<App>> emitter) throws Exception {
                PackageManager packageManager = mContext.getPackageManager();

                List<App> apps = new ArrayList<>();

                for (ApplicationInfo applicationInfo : getApplicationInfos(mContext)) {
                        App app = new App();
                        app.label = applicationInfo.loadLabel(packageManager);
                        app.packageName = applicationInfo.packageName;
                        app.icon = applicationInfo.loadIcon(packageManager);
                        apps.add(app);
                }

                // sort apps
                Collections.sort(apps, new Comparator<App>() {
                    @Override
                    public int compare(App app1, App app2) {
                        String left = new StringBuilder(app1.label).toString();
                        String right = new StringBuilder(app2.label).toString();
                        if (left != null && right != null) {
                            return left.compareTo(right);
                        }
                        return 0;
                    }
                });

                emitter.onNext(apps);
                emitter.onComplete();
            }
        }, BackpressureStrategy.LATEST);
    }

    private static List<ApplicationInfo> getApplicationInfos(Context context) {
        List<ApplicationInfo> applicationInfos = new ArrayList<>();

        for (ApplicationInfo applicationInfo : context.getPackageManager()
                .getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES)) {
            if (!((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM)
                    && !applicationInfo.packageName.equalsIgnoreCase(context.getPackageName())) {
                applicationInfos.add(applicationInfo);
            }
        }

        return applicationInfos;
    }
}
