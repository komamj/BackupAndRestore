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
package com.koma.backuprestore.restorelibrary.apk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.koma.backuprestore.commonlibrary.util.Constants;

import java.io.File;

import backup.koma.com.loglibrary.KomaLog;

/**
 * Created by koma on 3/25/18.
 */

public class AutoInstallHelper {
    private static final String TAG = AutoInstallHelper.class.getSimpleName();
    private static final String APP_TYPE = "application/vnd.android.package-archive";

    private final Context mContext;

    public AutoInstallHelper(Context context) {
        mContext = context;
    }

    private void install(String filePath) {
        File file = new File(filePath);
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, Constants.AUTHORITY, file);
            intent.setDataAndType(contentUri, APP_TYPE);
        } else {
            intent.setDataAndType(uri, APP_TYPE);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        mContext.startActivity(intent);
        if (!isAccessibilitySettingsOn(mContext)) {
            toAccessibilityService();
        }
    }

    private void toAccessibilityService() {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        mContext.startActivity(intent);
    }


    private boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;

        final String service = mContext.getPackageName() + File.separator + InstallAccessibilityService.class.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            KomaLog.e(TAG, "Error finding setting, default accessibility to not found: "
                    + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            KomaLog.i(TAG, "accessbilityservice is enabled");
            String settingValue = Settings.Secure.getString(
                    mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();

                    KomaLog.i(TAG, "accessibilityService : " + accessibilityService
                            + ",service : " + service);
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        } else {
            KomaLog.e(TAG, "deny accessbilityservices ");
        }

        return false;
    }
}
