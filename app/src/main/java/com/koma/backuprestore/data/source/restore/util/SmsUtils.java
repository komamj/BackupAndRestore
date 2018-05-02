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

import android.content.ContentValues;
import android.provider.Telephony;

import com.koma.backuprestore.data.entities.SmsRestoreEntry;
import com.koma.loglibrary.KomaLog;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by koma on 5/2/18.
 */

public class SmsUtils {
    private static final String TAG = SmsUtils.class.getSimpleName();

    private static final String VMESSAGE_END_OF_LINE = "\r\n";
    private static final String BEGIN_VMSG = "BEGIN:VMSG";
    private static final String END_VMSG = "END:VMSG";
    private static final String VERSION = "VERSION:";
    private static final String BEGIN_VCARD = "BEGIN:VCARD";
    private static final String END_VCARD = "END:VCARD";
    private static final String BEGIN_VBODY = "BEGIN:VBODY";
    private static final String END_VBODY = "END:VBODY";
    private static final String FROMTEL = "TEL:";
    private static final String XBOX = "X-BOX:";
    private static final String XREAD = "X-READ:";
    private static final String XSEEN = "X-SEEN:";
    private static final String XSIMID = "X-SIMID:";
    private static final String XLOCKED = "X-LOCKED:";
    private static final String XTYPE = "X-TYPE:";
    private static final String DATE = "Date:";
    private static final String SUBJECT = "Subject";

    private static final String ESCAPE_STR = "=";
    private static final String REPLACED_STR = "==";
    private static final byte ESCAPE_CHAR = '=';

    private static final String VMESSAGE_END_OF_COLON = ":";

    private SmsUtils() {
    }

    public static List<SmsRestoreEntry> getSmsRestoreEntry(String fileName) {
        List<SmsRestoreEntry> smsEntryList = new ArrayList<SmsRestoreEntry>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        InputStream instream = null;
        try {
            File file = new File(fileName);
            instream = new FileInputStream(file);
            InputStreamReader inreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inreader);
            String line = null;
            StringBuffer tmpbody = new StringBuffer();
            boolean appendbody = false;
            SmsRestoreEntry smsentry = null;
            while ((line = buffreader.readLine()) != null) {
                if (line.startsWith(BEGIN_VMSG) && !appendbody) {
                    smsentry = new SmsRestoreEntry();
                    continue;
                }

                if (line.startsWith(FROMTEL) && !appendbody && smsentry != null) {
                    smsentry.setSmsAddress(line.substring(FROMTEL.length()));
                    continue;
                }
                if (line.startsWith(XBOX) && !appendbody && smsentry != null) {
                    smsentry.setBoxType(line.substring(XBOX.length()));
                    continue;
                }
                if (line.startsWith(XREAD) && !appendbody && smsentry != null) {
                    smsentry.setReadByte(line.substring(XREAD.length()));
                    continue;
                }
                if (line.startsWith(XSEEN) && !appendbody && smsentry != null) {
                    smsentry.setSeen(line.substring(XSEEN.length()));
                    continue;
                }
                if (line.startsWith(XSIMID) && !appendbody && smsentry != null) {
                    smsentry.setSimCardid(line.substring(XSIMID.length()));
                    continue;
                }
                if (line.startsWith(XLOCKED) && !appendbody && smsentry != null) {
                    smsentry.setLocked(line.substring(XLOCKED.length()));
                    continue;
                }
                if (line.startsWith(DATE) && !appendbody && smsentry != null) {
                    long result = sd.parse(line.substring(DATE.length())).getTime();
                    smsentry.setTimeStamp(String.valueOf(result));
                    continue;
                }

                if (line.startsWith(SUBJECT) && !appendbody) {
                    String bodySlash = line.substring(line.indexOf(VMESSAGE_END_OF_COLON) + 1);

                    tmpbody.append(bodySlash);
                    appendbody = true;
                    continue;
                }
                if (line.startsWith(END_VBODY) && smsentry != null) {
                    appendbody = false;
                    smsentry.setBody(tmpbody.toString());
                    smsEntryList.add(smsentry);
                    tmpbody.setLength(0);
                    continue;
                }
                if (appendbody) {
                    if (tmpbody.toString().endsWith(ESCAPE_STR)) {
                        tmpbody.delete(tmpbody.lastIndexOf(ESCAPE_STR), tmpbody.length());
                    }
                    tmpbody.append(line);
                }
            }
        } catch (Exception e) {
            KomaLog.e(TAG, "getSmsRestoreEntry error : " + e.getMessage());
        } finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return smsEntryList;
    }

    public static ContentValues parseVmessage(SmsRestoreEntry pdu) {
        ContentValues values = new ContentValues();
        values.put(Telephony.Sms.ADDRESS, pdu.getSmsAddress());
        String decodeBody = pdu.getBody();
        boolean mInd = decodeBody.endsWith(ESCAPE_STR);
        if (mInd) {
            decodeBody = decodeBody.substring(0, decodeBody.lastIndexOf(ESCAPE_STR));
        }
        decodeBody = decodeQuotedPrintable(decodeBody.getBytes());
        if (decodeBody == null) {
            return null;
        }
        int m = decodeBody.indexOf("END:VBODY");
        if (m > 0) {
            StringBuffer tempssb = new StringBuffer(decodeBody);
            do {
                if (m > 0) {
                    tempssb.deleteCharAt(m - 1);
                } else {
                    break;
                }
            } while ((m = tempssb.indexOf("END:VBODY", m + "END:VBODY".length())) > 0);
            decodeBody = tempssb.toString();
        }
        values.put(Telephony.Sms.BODY, decodeBody);
        values.put(Telephony.Sms.READ, (pdu.getReadByte().equals("UNREAD") ? 0 : 1));
        values.put(Telephony.Sms.SEEN, pdu.getSeen());
        values.put(Telephony.Sms.LOCKED, (pdu.getLocked().equals("LOCKED") ? "1" : "0"));
        values.put(Telephony.Sms.DATE, pdu.getTimeStamp());
        values.put(Telephony.Sms.TYPE, (pdu.getBoxType().equals("INBOX") ? 1 : 2));
        return values;
    }

    private static String decodeQuotedPrintable(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int length = bytes.length;
        for (int j = length - 1; j > 0; j--) {
            if (bytes[j] == ESCAPE_CHAR || '\r' == (char) bytes[j] || '\n' == (char) bytes[j]) {
            } else {
                length = j + 1;
                break;
            }
        }
        for (int i = 0; i < length; i++) {
            int b = bytes[i];
            if (b == ESCAPE_CHAR && i + 2 < length) {
                try {
                    if (i + 2 < length && '\r' == (char) bytes[i + 1]
                            && '\n' == (char) bytes[i + 2]) {
                        i += 2;
                        continue;
                    }
                    if (i + 1 < length && ('\r' == (char) bytes[i + 1])) {
                        i += 1;
                        continue;
                    }
                    if (i + 1 < length && ('\n' == (char) bytes[i + 1])) {
                        i += 1;
                        continue;
                    }
                    int u = Character.digit((char) bytes[++i], 16);
                    int l = Character.digit((char) bytes[++i], 16);
                    if (u == -1 || l == -1) {
                        return null;
                    }
                    buffer.write((char) ((u << 4) + l));

                } catch (ArrayIndexOutOfBoundsException e) {
                    return null;
                }
            } else {
                buffer.write(b);
            }
        }
        return new String(buffer.toByteArray());
    }
}
