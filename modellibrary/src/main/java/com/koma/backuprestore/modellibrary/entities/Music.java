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
package com.koma.backuprestore.modellibrary.entities;

import android.text.TextUtils;

import java.util.Arrays;

/**
 * Created by koma on 3/2/18.
 */

public class Music {
    public long id;
    public String displayName;
    public String artistName;
    public String path;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("music has id ");
        builder.append(id);
        builder.append(",displayName ");
        builder.append(displayName);
        builder.append(",artistName ");
        builder.append(artistName);
        builder.append(",path ");
        builder.append(path);
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Music music = (Music) o;

        return this.id == music.id && TextUtils.equals(this.displayName, music.displayName)
                && TextUtils.equals(artistName, music.artistName)
                && TextUtils.equals(this.path, music.path);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{id, displayName, artistName, path});
    }
}
