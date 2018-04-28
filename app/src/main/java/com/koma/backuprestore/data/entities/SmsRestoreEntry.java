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
package com.koma.backuprestore.data.entities;

/**
 * Created by koma on 4/26/18.
 */

public class SmsRestoreEntry {
    private String mTimeStamp;
    private String mReadByte;
    private String mSeen;
    private String mBoxType;
    private String mSimCardid;
    private String mLocked;
    private String mSmsAddress;
    private String mBody;

    public String getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.mTimeStamp = timeStamp;
    }

    public String getReadByte() {
        return mReadByte == null ? "READ" : mReadByte;
    }

    public void setReadByte(String readByte) {
        this.mReadByte = readByte;
    }

    public String getSeen() {
        return mSeen == null ? "1" : mSeen;
    }

    public void setSeen(String seen) {
        this.mSeen = seen;
    }

    public String getBoxType() {
        return mBoxType;
    }

    public void setBoxType(String boxType) {
        this.mBoxType = boxType;
    }

    public String getSimCardid() {
        return mSimCardid;
    }

    public void setSimCardid(String simCardid) {
        this.mSimCardid = simCardid;
    }

    public String getLocked() {
        return mLocked;

    }

    public void setLocked(String locked) {
        this.mLocked = locked;
    }

    public String getSmsAddress() {
        return mSmsAddress;
    }

    public void setSmsAddress(String smsAddress) {
        this.mSmsAddress = smsAddress;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        this.mBody = body;
    }
}
