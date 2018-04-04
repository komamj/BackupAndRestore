package com.koma.backuprestore.util;

import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;

/**
 * Created by koma on 4/4/18.
 */

public class Utils {
    public static void setDefaultSmsPackage(Context context) {
        Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
        intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, context.getPackageName());
        context.startActivity(intent);
    }
}
