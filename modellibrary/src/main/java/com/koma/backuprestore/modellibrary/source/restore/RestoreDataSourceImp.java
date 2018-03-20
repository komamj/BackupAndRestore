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
package com.koma.backuprestore.modellibrary.source.restore;

import android.content.Context;

import com.koma.backuprestore.modellibrary.entities.Apk;
import com.koma.backuprestore.modellibrary.entities.Image;
import com.koma.backuprestore.modellibrary.entities.Video;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/**
 * Created by koma on 3/20/18.
 */

@Singleton
public class RestoreDataSourceImp implements RestoreDataSource {
    private final Context mContext;

    @Inject
    public RestoreDataSourceImp(Context context) {
        mContext = context;
    }

    @Override
    public Flowable<List<Video>> getVideos() {
        return null;
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
