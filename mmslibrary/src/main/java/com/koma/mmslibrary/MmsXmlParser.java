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

package com.koma.mmslibrary;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MmsXmlParser {
    private static final String CLASS_TAG = "/MmsXmlParser";

    public static ArrayList<MmsXmlInfo> parse(String mmsString) {
        MmsXmlInfo record = null;
        ArrayList<MmsXmlInfo> list = new ArrayList<MmsXmlInfo>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(mmsString));

            int eventType = parser.getEventType();
            String tagName = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        record = new MmsXmlInfo();
                        tagName = parser.getName();
                        if (tagName.equals(MmsXmlInfo.MmsXml.RECORD)) {
                            int attrNum = parser.getAttributeCount();
                            for (int i = 0; i < attrNum; ++i) {
                                String name = parser.getAttributeName(i);
                                String value = parser.getAttributeValue(i);
                                if (name.equals(MmsXmlInfo.MmsXml.ID)) {
                                    record.setID(value);
                                } else if (name.equals(MmsXmlInfo.MmsXml.ISREAD)) {
                                    record.setIsRead(value);
                                } else if (name.equals(MmsXmlInfo.MmsXml.MSGBOX)) {
                                    record.setMsgBox(value);
                                } else if (name.equals(MmsXmlInfo.MmsXml.DATE)) {
                                    record.setDate(value);
                                } else if (name.equals(MmsXmlInfo.MmsXml.SIZE)) {
                                    record.setSize(value);
                                } else if (name.equals(MmsXmlInfo.MmsXml.SIMID)) {
                                    record.setSimId(value);
                                } else if (name.equals(MmsXmlInfo.MmsXml.ISLOCKED)) {
                                    record.setIsLocked(value);
                                } else if (name.equals(MmsXmlInfo.MmsXml.DATE_SEND)) {
                                    record.setDateSent(value);
                                } else if (name.equals(MmsXmlInfo.MmsXml.MESSAGE_ID)) {
                                    record.setMessageId(value);
                                } else if (name.equals(MmsXmlInfo.MmsXml.TR_ID)) {
                                    record.setTrId(value);
                                } else if (name.equals(MmsXmlInfo.MmsXml.SUBJECT)) {
                                    record.setSub(value);
                                } else if (name.equals(MmsXmlInfo.MmsXml.MESSAGE_SIZE)) {
                                    record.setMessageSize(value);
                                } else if (name.equals(MmsXmlInfo.MmsXml.MESSAGE_TYPE)) {
                                    record.setMessageType(value);
                                }

                                Log.d(CLASS_TAG, "name:" + name + ",value:" + value);
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals(MmsXmlInfo.MmsXml.RECORD) && record != null) {
                            list.add(record);
                        }
                        break;

                    case XmlPullParser.END_DOCUMENT:
                        break;

                    default:
                        break;
                }

                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
