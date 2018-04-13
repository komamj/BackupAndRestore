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
package com.koma.backuprestore.commonlibrary.util;

import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by koma on 3/25/18.
 */

public class Constants {
    public static final String AUTHORITY = "com.koma.backuprestore.fileprovider";

    public static final Uri CONTACT_URI = ContactsContract.Contacts.CONTENT_URI;
    public static final Uri SMS_URI = Telephony.Sms.CONTENT_URI;
    public static final Uri MMS_URI = Telephony.Mms.CONTENT_URI;
    public static final Uri CALL_LOG_URI = CallLog.Calls.CONTENT_URI;
    public static final Uri CALENDAR_URI = CalendarContract.Events.CONTENT_URI;
    public static final Uri IMAGE_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    public static final Uri AUDIO_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    public static final Uri VIDEO_URI = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    public static final Uri DOCUMENT_URI = MediaStore.Files.getContentUri("external");

    public static final String DEFAULT_SMS_PACKAGE_NAME ="default_sms_package_name";

    public static final String CATEGORY_TAG = "category_tag";
    public static final int CATEGORY_CANTACT = 0;
    public static final int CATEGORY_SMS = 1;
    public static final int CATEGORY_CALL_LOG = 2;
    public static final int CATEGORY_CALENDAR_EVENT = 3;
    public static final int CATEGORY_IMAGE = 4;
    public static final int CATEGORY_VIDEO = 5;
    public static final int CATEGORY_AUDIO = 6;
    public static final int CATEGORY_DOCUMENT = 7;
    public static final int CATEGORY_APP = 8;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CATEGORY_CANTACT, CATEGORY_SMS, CATEGORY_CALL_LOG, CATEGORY_CALENDAR_EVENT,
            CATEGORY_IMAGE, CATEGORY_VIDEO, CATEGORY_AUDIO, CATEGORY_DOCUMENT, CATEGORY_APP})
    public @interface Category {
    }
}
