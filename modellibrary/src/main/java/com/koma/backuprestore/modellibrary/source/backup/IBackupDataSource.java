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

import com.koma.backuprestore.modellibrary.entities.App;
import com.koma.backuprestore.modellibrary.entities.Image;
import com.koma.backuprestore.modellibrary.entities.Video;
import com.koma.backuprestore.modellibrary.source.IBackupRestoreDataSource;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by koma on 3/20/18.
 */

public interface IBackupDataSource extends IBackupRestoreDataSource {
    Flowable<List<Video>> getVideos();

    Flowable<List<App>> getApps();

    Flowable<List<Image>> getImages();

    Flowable<String> getContactCount();

    Flowable<String> getSmsCount();

    Flowable<String> getCallLogCount();

    Flowable<String> getCalendarEventCount();

    Flowable<String> getImageCount();

    Flowable<String> getVideoCount();

    Flowable<String> getAudioCount();

    Flowable<String> getDocmentCount();

    Flowable<String> getAppCount();


}
