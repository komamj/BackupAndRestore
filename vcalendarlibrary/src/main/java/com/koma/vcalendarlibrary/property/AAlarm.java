/* Copyright Statement:
 *
 * This software/firmware and related documentation ("MediaTek Software") are
 * protected under relevant copyright laws. The information contained herein is
 * confidential and proprietary to MediaTek Inc. and/or its licensors. Without
 * the prior written permission of MediaTek inc. and/or its licensors, any
 * reproduction, modification, use or disclosure of MediaTek Software, and
 * information contained herein, in whole or in part, shall be strictly
 * prohibited.
 *
 * MediaTek Inc. (C) 2010. All rights reserved.
 *
 * BY OPENING THIS FILE, RECEIVER HEREBY UNEQUIVOCALLY ACKNOWLEDGES AND AGREES
 * THAT THE SOFTWARE/FIRMWARE AND ITS DOCUMENTATIONS ("MEDIATEK SOFTWARE")
 * RECEIVED FROM MEDIATEK AND/OR ITS REPRESENTATIVES ARE PROVIDED TO RECEIVER
 * ON AN "AS-IS" BASIS ONLY. MEDIATEK EXPRESSLY DISCLAIMS ANY AND ALL
 * WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NONINFRINGEMENT. NEITHER DOES MEDIATEK PROVIDE ANY WARRANTY WHATSOEVER WITH
 * RESPECT TO THE SOFTWARE OF ANY THIRD PARTY WHICH MAY BE USED BY,
 * INCORPORATED IN, OR SUPPLIED WITH THE MEDIATEK SOFTWARE, AND RECEIVER AGREES
 * TO LOOK ONLY TO SUCH THIRD PARTY FOR ANY WARRANTY CLAIM RELATING THERETO.
 * RECEIVER EXPRESSLY ACKNOWLEDGES THAT IT IS RECEIVER'S SOLE RESPONSIBILITY TO
 * OBTAIN FROM ANY THIRD PARTY ALL PROPER LICENSES CONTAINED IN MEDIATEK
 * SOFTWARE. MEDIATEK SHALL ALSO NOT BE RESPONSIBLE FOR ANY MEDIATEK SOFTWARE
 * RELEASES MADE TO RECEIVER'S SPECIFICATION OR TO CONFORM TO A PARTICULAR
 * STANDARD OR OPEN FORUM. RECEIVER'S SOLE AND EXCLUSIVE REMEDY AND MEDIATEK'S
 * ENTIRE AND CUMULATIVE LIABILITY WITH RESPECT TO THE MEDIATEK SOFTWARE
 * RELEASED HEREUNDER WILL BE, AT MEDIATEK'S OPTION, TO REVISE OR REPLACE THE
 * MEDIATEK SOFTWARE AT ISSUE, OR REFUND ANY SOFTWARE LICENSE FEES OR SERVICE
 * CHARGE PAID BY RECEIVER TO MEDIATEK FOR SUCH MEDIATEK SOFTWARE AT ISSUE.
 *
 * The following software/firmware and/or related documentation ("MediaTek
 * Software") have been modified by MediaTek Inc. All revisions are subject to
 * any receiver's applicable license agreements with MediaTek Inc.
 */

package com.koma.vcalendarlibrary.property;

import android.content.ContentValues;
import android.provider.CalendarContract.Reminders;

import com.koma.vcalendarlibrary.VCalendarException;
import com.koma.vcalendarlibrary.component.Component;
import com.koma.vcalendarlibrary.utils.LogUtil;
import com.koma.vcalendarlibrary.utils.Utility;
import com.koma.vcalendarlibrary.valuetype.DDuration;

import java.util.LinkedList;

/**
 * For iCalendar1.0, in version 1.0, Alarms are defined in property
 */
public class AAlarm extends Property {
    private static final String TAG = "AAlarm";

    /**
     * AAlarm Property Constructor.
     *
     * @param value AAlarm begin time string
     */
    public AAlarm(String value) {
        super(AALARM, value);
        LogUtil.d(TAG, "Constructor: AAlarm property created.");
    }

    /**
     * Write the alarm information to ContentValues list.
     *
     * @param cvList           content values list
     * @param eventStartMillis start time in milliseconds.
     * @throws VCalendarException the cvList is null
     */
    public void writeInfoToContentValues(final LinkedList<ContentValues> cvList, long eventStartMillis)
            throws VCalendarException {
        LogUtil.d(TAG, "writeInfoToContentValues()");

        if (cvList == null) {
            LogUtil.e(TAG, "writeInfoToContentValues(): the argument ContentValue must not be null.");
            throw new VCalendarException();
        }

        if (Component.VEVENT.equals(mComponent.getName())) {
            ContentValues cv = new ContentValues();
            // format cv based on this alarms's info
            long millis = Utility.getTimeInMillis(null, mValue) - eventStartMillis;
            cv.put(Reminders.MINUTES, -1 * millis / DDuration.MILLIS_IN_MIN);
            cv.put(Reminders.METHOD, Reminders.METHOD_ALERT);

            cvList.add(cv);
        }
    }

}
