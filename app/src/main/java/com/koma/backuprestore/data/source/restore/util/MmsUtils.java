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

import android.net.Uri;
import android.provider.Telephony;

import com.koma.backuprestore.commonlibrary.util.Constants;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MmsUtils {
    private MmsUtils() {
    }

    public static String getXmlInfo(String fileName) {
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = -1;
            byte[] buffer = new byte[512];
            while ((len = is.read(buffer, 0, 512)) != -1) {
                baos.write(buffer, 0, len);
            }

            return baos.toString();
        } catch (IOException | IndexOutOfBoundsException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Describe <code>getMsgBoxUri</code> method here.
     *
     * @param msgBox a <code>String</code> value
     * @return an <code>Uri</code> value
     */
    public static Uri getMsgBoxUri(String msgBox) {
        if (msgBox.equals(Constants.MESSAGE_BOX_TYPE_INBOX)) {
            return Telephony.Mms.Inbox.CONTENT_URI;
        } else if (msgBox.equals(Constants.MESSAGE_BOX_TYPE_SENT)) {
            return Telephony.Mms.Sent.CONTENT_URI;
        } else if (msgBox.equals(Constants.MESSAGE_BOX_TYPE_DRAFT)) {
            return Telephony.Mms.Draft.CONTENT_URI;
        } else if (msgBox.equals(Constants.MESSAGE_BOX_TYPE_OUTBOX)) {
            return Telephony.Mms.Outbox.CONTENT_URI;
        }

        return Telephony.Mms.Inbox.CONTENT_URI;
    }

    /**
     * Describe <code>readFileContent</code> method here.
     *
     * @param fileName a <code>String</code> value
     * @return a <code>byte[]</code> value
     */
    public static byte[] readFileContent(String fileName) {
        try {
            InputStream is = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = -1;
            byte[] buffer = new byte[512];
            while ((len = is.read(buffer, 0, 512)) != -1) {
                baos.write(buffer, 0, len);
            }

            is.close();
            return baos.toByteArray();
        } catch (IOException | IndexOutOfBoundsException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }
}
