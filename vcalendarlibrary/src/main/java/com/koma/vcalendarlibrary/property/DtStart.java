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
import android.provider.CalendarContract.Events;

import com.koma.vcalendarlibrary.VCalendarException;
import com.koma.vcalendarlibrary.component.Component;
import com.koma.vcalendarlibrary.parameter.Parameter;
import com.koma.vcalendarlibrary.parameter.TzId;
import com.koma.vcalendarlibrary.utils.LogUtil;
import com.koma.vcalendarlibrary.utils.Utility;

import java.util.TimeZone;

/**
 * Define the DtStart property
 */
public class DtStart extends Property {
    public static final String TAG = "DtStart";

    /**
     * Constructor.
     *
     * @param value the property value
     */
    public DtStart(String value) {
        super(DTSTART, value);
        LogUtil.d(TAG, "Constructor: DtStart property created");
    }

    @Override
    public void writeInfoToContentValues(ContentValues cv)
            throws VCalendarException {
        LogUtil.d(TAG, "writeInfoToContentValues()");
        super.writeInfoToContentValues(cv);

        if (Component.VEVENT.equals(mComponent.getName())) {
            cv.put(Events.DTSTART, getValueMillis());

            //Simple parse tzid as real time zone identify.
            //And the actually time zone will be parsed in VEVENT using X-TIMEZONE.
            if (!cv.containsKey(Events.EVENT_TIMEZONE)) {
                String localTimezone = Utility
                        .getLocalTimezone((TzId) getFirstParameter(Parameter.TZID), mValue);
                TimeZone timezone = TimeZone.getTimeZone(localTimezone);
                cv.put(Events.EVENT_TIMEZONE, timezone.getID());
                LogUtil.v(TAG, "set a timezone, timezone.getID()=" + timezone.getID()
                        + ";localTimezone=" + localTimezone);
            }
        }
    }

    /**
     * Get the DtStart in millisecond.
     *
     * @return start time in millisecond
     * @throws VCalendarException when parse the DtStart value String
     */
    public long getValueMillis() throws VCalendarException {
        return Utility.getTimeInMillis(
                (TzId) getFirstParameter(Parameter.TZID), mValue);
    }

}
