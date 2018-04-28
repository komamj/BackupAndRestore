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

public class MmsXmlInfo {

    public static class MmsXml {
        public static final String ROOT = "mms";
        public static final String RECORD = "record";
        // public static final String CATEGORY = "category";
        public static final String ID = "_id";
        public static final String ISREAD = "isread";
        // public static final String LOCALDATE = "local_date";
        // public static final String ST = "st";
        public static final String MSGBOX = "msg_box";
        public static final String DATE = "date";
        public static final String SIZE = "m_size";
        public static final String SIMID = "sim_id";
        public static final String ISLOCKED = "islocked";

        public static final String THREAD_ID = "thread_id";
        public static final String DATE_SEND = "date_send";
        public static final String MESSAGE_ID = "m_id";
        public static final String SUBJECT = "sub";
        public static final String MESSAGE_TYPE = "m_type";
        public static final String MESSAGE_SIZE = "message-size";
        public static final String TR_ID = "tr_id";
    }

    // private String mCategory;
    private String mId;
    private String mIsRead;
    // private String mLocalDate;
    // private String mST;
    private String mMsgBox;
    private String mDate;
    private String mSize;
    private String mSimId;
    private String mIsLocked;

    private String mThreadId;
    private String mDateSent;
    private String mMessageId;
    private String mSub;
    private String mMessageType;
    private String mMessageSize;
    private String mTrId;

    public String getThreadId() {
        return mThreadId;
    }

    public void setThreadId(String threadId) {
        mThreadId = threadId;
    }

    public String getDateSent() {
        return mDateSent;
    }

    public void setDateSent(String dateSent) {
        mDateSent = dateSent;
    }

    public String getMessageId() {
        return (mMessageId == null) ? "" : mMessageId;

    }

    public void setMessageId(String messageId) {
        mMessageId = messageId;
    }

    public String getSub() {
        return (mSub == null) ? "" : mSub;
    }

    public void setSub(String sub) {
        mSub = sub;
    }

    public String getMessageType() {
        return mMessageType;
    }

    public void setMessageType(String messageType) {
        mMessageType = messageType;
    }

    public String getMessageSize() {
        return mMessageSize;
    }

    public void setMessageSize(String messageSize) {
        mMessageSize = messageSize;
    }

    public String getTrId() {
        return (mTrId == null) ? "" : mTrId;
    }

    public void setTrId(String trId) {
        mTrId = trId;
    }

    // public void setCategory(String category) {
    // mCategory = category;
    // }

    // public String getCategory() {
    // return (mCategory == null) ? "0" : mCategory;
    // }

    public void setID(String id) {
        mId = id;
    }

    public String getID() {
        return (mId == null) ? "" : mId;
    }

    public void setIsRead(String isread) {
        mIsRead = isread;
    }

    public String getIsRead() {
        return ((mIsRead == null) || mIsRead.equals("")) ? "1" : mIsRead;
    }

    // public void setLocalDate(String date) {
    // mLocalDate = date;
    // }

    // public String getLocalDate() {
    // return (mLocalDate == null) ? "" : mLocalDate;
    // }

    // public void setST(String st) {
    // mST = st;
    // }

    // public String getST() {
    // return (mST == null) ? "" : mST;
    // }

    public void setMsgBox(String msgBox) {
        mMsgBox = msgBox;
    }

    public String getMsgBox() {
        return ((mMsgBox == null) || mMsgBox.equals("")) ? "1" : mMsgBox;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getDate() {
        return (mDate == null) ? "" : mDate;
    }

    public void setSize(String size) {
        mSize = size;
    }

    public String getSize() {
        return ((mSize == null) || mSize.equals("")) ? "0" : mSize;
    }

    public void setSimId(String simId) {
        mSimId = simId;
    }

    public String getSimId() {
        return ((mSimId == null) || mSimId.equals("")) ? "0" : mSimId;
    }

    public void setIsLocked(String islocked) {
        mIsLocked = islocked;
    }

    public String getIsLocked() {
        return ((mIsLocked == null) || mIsLocked.equals("")) ? "0" : mIsLocked;
    }
}
