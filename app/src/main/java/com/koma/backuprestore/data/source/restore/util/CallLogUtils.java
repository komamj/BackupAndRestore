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
package com.koma.backuprestore.data.source.restore.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koma.backuprestore.data.entities.CallLogEntry;
import com.koma.loglibrary.KomaLog;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by koma on 5/4/18.
 */

public class CallLogUtils {
    private CallLogUtils() {
    }

    private static String getCallLogInfo(final String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = -1;
            byte[] buffer = new byte[512];
            while ((len = inputStream.read(buffer, 0, 512)) != -1) {
                baos.write(buffer, 0, len);
            }
            KomaLog.e("", "" + baos.toString());
            return baos.toString();
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static List<CallLogEntry> parseCallLogEntries(final Gson gson, final String fileName) {
        Type listType = new TypeToken<List<CallLogEntry>>() {
        }.getType();
        return gson.fromJson(getCallLogInfo(fileName), listType);
    }
}
