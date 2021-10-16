/*******************************************************************************
 * Copyright (c) 2021 FuYou Technology
 *
 *
 *   The content of this file includes portions of the FY Technology
 *   released in source code form as part of the SDK installer package.
 *
 *   Commercial License Usage
 *
 *   Licensees holding valid commercial licenses to the FY Technology
 *   may use this file in accordance with the end user license agreement provided 
 *   with the software or, alternatively, in accordance with the terms contained in a
 *   written agreement between you and FY Inc.
 *
 *
 *
 *   GNU General Public License Usage
 *
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program; if not, write to the Free Software
 *   Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA
 *******************************************************************************/
package com.fyteck.dvbsi.sample;

import com.fyteck.dvbsi.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DecodeEIT extends DecodeAllTable {
    private static final Logger log           = Logger.getLogger(DecodeEIT.class);
    protected ISectionNotify    sectionNotify = /***/
            new ISectionNotify() {
                @Override
                public void sectionNotify(TSSection section) {
                    String sectionName = section.getCommonParser().getName();

                    if (sectionName.equalsIgnoreCase("event_information_section")) {
                        log.info(sectionName + " " + section.briefTimeInfo());

                        /** should switch thread context */
                        dumpEITEvent(section);
                    }
                }
            };

    private static Calendar getDefaultCal() {
        Calendar calendar = new GregorianCalendar();
        return calendar;
    }

    private static Calendar MJD2Cal(int MJD) {
        int Y_ = (int) ((MJD - 15078.2) / 365.25);
        int M_ = (int) ((MJD - 14956.1 - (Y_ * 365.25)) / 30.6001);
        int D_ = MJD - 14956 - (int) (Y_ * 365.25) - (int) (M_ * 30.6001);

        int K  = 0;
        if (M_ == 14 || M_ == 15) {
            K = 1;
        } else {
            K = 0;
        }
        int      Y   = Y_ + K + 1900;
        int      M   = M_ - 1 - K * 12;
        Calendar cal = getDefaultCal();
        /* month the value used to set the MONTH calendar field. Month value is 0-based.
         * e.g., 0 for January */
        cal.set(Y, M - 1, D_);
        return cal;
    }

    public static Calendar getCalFromBuffer(byte[] dt) {
        Calendar cal = getDefaultCal();
        try {
            if (dt.length == 5) {
                int MJD = (dt[0] & 0x000000FF) << 8;
                MJD += (dt[1] & 0x000000FF);
                cal  = MJD2Cal(MJD);
            }

            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(Integer.toHexString(dt[2] & 0x000000FF)));
            cal.set(Calendar.MINUTE, Integer.parseInt(Integer.toHexString(dt[3] & 0x000000FF)));
            cal.set(Calendar.SECOND, Integer.parseInt(Integer.toHexString(dt[4] & 0x000000FF)));
        } catch (NumberFormatException e) {
            log.info("NumberFormatException");
        }
        return cal;
    }

    public static String getCalendarString(Calendar cal, String format) {
        StringBuffer sb = new StringBuffer();
        if (cal == null) {
            return "";
        }
        if (format.indexOf("yyyy/") != -1) {
            sb.append(cal.get(Calendar.YEAR));
            sb.append("/");
        }
        if (format.indexOf("MM/") != -1) {
            sb.append(cal.get(Calendar.MONTH) + 1);
            sb.append("/");
        }
        if (format.indexOf("dd") != -1) {
            sb.append(cal.get(Calendar.DATE));
            sb.append(" ");
        }
        if (format.indexOf("hh") != -1) {
            if (format.indexOf("hh:") != -1) {
                sb.append(cal.get(Calendar.HOUR_OF_DAY));
                sb.append(":");
            } else {
                sb.append(cal.get(Calendar.HOUR_OF_DAY));
            }
        }
        if (format.indexOf("mm") != -1) {
            if (format.indexOf("mm:") != -1) {
                sb.append(cal.get(Calendar.MINUTE));
                sb.append(":");
            } else {
                sb.append(cal.get(Calendar.MINUTE));
            }
        }

        if (format.indexOf("ss") != -1) {
            sb.append(cal.get(Calendar.SECOND));
        }

        return sb.toString();
    }

    public static String getEventInfo(List<NodeValue> eventNodeList) {
        /**
         * <code>
         *
         * event_information_section(){
         * 	    table_id                           8       uimsbf
         * 	    section_syntax_indicator           1       bslbf
         * 	    reserved_future_use                1       bslbf
         * 	    reserved                           2       bslbf
         * 	    section_length                     12      uimsbf
         * 	    service_id                         16      uimsbf
         * 	    reserved                           2       bslbf
         * 	    version_number                     5       uimsbf
         * 	    current_next_indicator             1       bslbf
         * 	    section_number                     8       uimsbf
         * 	    last_section_number                8       uimsbf
         * 	    transport_stream_id                16      uimsbf
         * 	    original_network_id                16      uimsbf
         * 	    segment_last_section_number        8       uimsbf
         * 	    last_table_id                      8       uimsbf
         * 	    for(i=0;i<N;i++)/*events* /{                            - eventNodeList
         * 	        event_id                       16      uimsbf
         * 	        start_time                     40      bslbf        - ❶
         * 	        duration                       24      uimsbf       - ❷
         * 	        running_status                 3       uimsbf
         * 	        free_CA_mode                   1       bslbf
         * 	        descriptors_loop_length        12      uimsbf
         * 	        for(i=0;i<N;i++){                                   - ❸
         * 	            descriptor()
         *            }
         *        }
         * 	    CRC_32                             32      rpchof
         *    }
         *	</code>
         */

        StringBuffer sb            = new StringBuffer();

        byte[]       start_timeObj = TSUtil.getObjectByName(eventNodeList, "start_time");/** ❶ */
        Calendar     startTime     = getCalFromBuffer(start_timeObj);
        long         duration      = TSUtil.getObjectByName(eventNodeList, "duration"); /** ❷ */
        /* Get hour from BCD */
        int          hour          = Integer.parseInt(Long.toString((duration & 0x00FF0000) >> 16, 16));
        int          minute        = Integer.parseInt(Long.toString((duration & 0x0000FF00) >> 8, 16));
        int          second        = Integer.parseInt(Long.toString((duration & 0x000000FF) >> 0, 16));
        duration = hour * 3600 + minute * 60 + second; // To second
        Calendar endtime = (Calendar) startTime.clone();
        endtime.add(Calendar.SECOND, (int) duration);

        sb.append(" start_time=" + getCalendarString(startTime, "yyyy/MM/dd hh:mm:ss"));
        sb.append(" end_time=" + getCalendarString(endtime, "yyyy/MM/dd hh:mm:ss"));

        int    ISO_639_language_code_raw   = 0;

        int    descriptorsLen              = TSUtil.getObjectLenByName(eventNodeList, "descriptors");
        byte[] ISO_639_language_code_bytes = new byte[3];
        String ISO_639_language_code       = null;
        try {
            for (int j = 0; j < descriptorsLen; j++) {

                /**
                 * <code>
                 *
                 * tag=0x4d
                 * short_event_descriptor(){
                 *  descriptor_tag               8       uimsbf
                 *  descriptor_length            8       uimsbf
                 *  ISO_639_language_code        24      bslbf               - ❹
                 *  event_name_length            8       uimsbf
                 *  for (i=0;i<event_name_length;i++){
                 *      event_name_char          8       uimsbf              - ❺
                 *  }
                 *  text_length                  8       uimsbf
                 *  for (i=0;i<text_length;i++){
                 *      text_char                8       uimsbf              - ❻
                 *  }
                 * }
                 *
                 * </code>
                 */

                List<NodeValue> descriptorNodeList = TSUtil.getObjectByNameIdx(eventNodeList, "descriptors", j); /** ❸ */
                int             descriptTag        = TSUtil.getObjectByName(descriptorNodeList, "descriptor_tag");

                /* is not short_event_descriptor */
                if (descriptTag != 0x4d) {
                    continue;
                }

                /* descriptorNodeList is a short_event_descriptor() */

                /** ❹ */
                ISO_639_language_code_raw      = ((Long) TSUtil.getObjectByName(descriptorNodeList, "ISO_639_language_code")).intValue();
                ISO_639_language_code_bytes[0] = (byte) (ISO_639_language_code_raw >> 16);
                ISO_639_language_code_bytes[1] = (byte) (ISO_639_language_code_raw >> 8);
                ISO_639_language_code_bytes[2] = (byte) (ISO_639_language_code_raw >> 0);
                ISO_639_language_code          = new String(ISO_639_language_code_bytes);

                byte[] event_name_char_bytes = TSUtil.getObjectByName(descriptorNodeList, "event_name_char");/** ❺ */
                sb.append(" event_name=");
                sb.append(TextUtil.getTextFromByte(event_name_char_bytes, descriptorNodeList));
                sb.append("[" + ISO_639_language_code + "]");

                byte[] text_char_bytes = TSUtil.getObjectByName(descriptorNodeList, "text_char");/** ❻ */
                String text_char       = TextUtil.getTextFromByte(text_char_bytes, descriptorNodeList);
                sb.append(" event_text=");
                sb.append(text_char);
                if (text_char != null) {
                    sb.append("[" + ISO_639_language_code + "]");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        DecodeEIT decodeEIT = new DecodeEIT();
        decodeEIT.init();
        decodeEIT.bitStream.setSectionNotify(decodeEIT.sectionNotify);
        decodeEIT.decodeTSFile(new File("./dvbtest.ts"));
    }

    public void dumpEITEvent(TSSection eitSection) {
        List<NodeValue> root           = eitSection.getRoot();
        List<NodeValue> eventsNodeList = TSUtil.getObjectByName(root, "events");
        for (NodeValue eventNode : eventsNodeList) {
            List<NodeValue> eventNodeList = eventNode.getValue();
            int             event_id      = NumberUtil.getIntValue(/***/
                    TSUtil.getObjectByName(eventNodeList, "event_id"));

            log.info("event_id=" + event_id + getEventInfo(eventNodeList));
        }
    }

}
