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
package com.fyteck.dvbsi;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextUtil {
    private static final Iso6937ToUnicode    iso6937ToUnicode    = new Iso6937ToUnicode();

    // Big5 Big5
    // Big5-HKSCS Big5-HKSCS
    // EUC-JP EUC-JP
    // EUC-KR EUC-KR
    // GB18030 GB18030
    // GB2312 GB2312
    // GBK GBK
    // ISO-2022-CN ISO-2022-CN
    // ISO-2022-JP ISO-2022-JP
    // ISO-2022-JP-2 ISO-2022-JP-2
    // ISO-2022-KR ISO-2022-KR
    // ISO-8859-1 ISO-8859-1
    // ISO-8859-13 ISO-8859-13
    // ISO-8859-15 ISO-8859-15
    // ISO-8859-2 ISO-8859-2
    // ISO-8859-3 ISO-8859-3
    // ISO-8859-4 ISO-8859-4
    // ISO-8859-5 ISO-8859-5
    // ISO-8859-6 ISO-8859-6
    // ISO-8859-7 ISO-8859-7
    // ISO-8859-8 ISO-8859-8
    // ISO-8859-9 ISO-8859-9
    // JIS_X0201 JIS_X0201
    // JIS_X0212-1990 JIS_X0212-1990
    // KOI8-R KOI8-R
    // KOI8-U KOI8-U
    // Shift_JIS Shift_JIS
    // TIS-620 TIS-620
    // US-ASCII US-ASCII
    // UTF-16 UTF-16
    // UTF-16BE UTF-16BE
    // UTF-16LE UTF-16LE
    // UTF-32 UTF-32
    // UTF-32BE UTF-32BE
    // UTF-32LE UTF-32LE
    // UTF-8 UTF-8
    // x-Big5-Solaris x-Big5-Solaris
    // x-euc-jp-linux x-euc-jp-linux
    // x-EUC-TW x-EUC-TW
    // x-eucJP-Open x-eucJP-Open
    // x-ISCII91 x-ISCII91
    // x-ISO-2022-CN-CNS x-ISO-2022-CN-CNS
    // x-ISO-2022-CN-GB x-ISO-2022-CN-GB
    // x-iso-8859-11 x-iso-8859-11
    // x-JIS0208 x-JIS0208
    // x-JISAutoDetect x-JISAutoDetect
    // x-Johab x-Johab
    // x-SJIS_0213 x-SJIS_0213
    // x-UTF-16LE-BOM x-UTF-16LE-BOM
    // X-UTF-32BE-BOM X-UTF-32BE-BOM
    // X-UTF-32LE-BOM X-UTF-32LE-BOM

    // 0x01 ISO/IEC 8859-5 [27] Latin/Cyrillic alphabet A.2
    // 0x02 ISO/IEC 8859-6 [28] Latin/Arabic alphabet A.3
    // 0x03 ISO/IEC 8859-7 [29] Latin/Greek alphabet A.4
    // 0x04 ISO/IEC 8859-8 [30] Latin/Hebrew alphabet A.5
    // 0x05 ISO/IEC 8859-9 [31] Latin alphabet No. 5 A.6
    // 0x06 ISO/IEC 8859-10 [32] Latin alphabet No. 6 A.7
    // 0x07 ISO/IEC 8859-11 [33] Latin/Thai (draft only) A.8
    // 0x08 ISO/IEC 8859-12 [i.5] possibly reserved for Indian
    // 0x09 ISO/IEC 8859-13 [34] Latin alphabet No. 7 A.9
    // 0x0A ISO/IEC 8859-14 [35] Latin alphabet No. 8 (Celtic) A.10
    // 0x0B ISO/IEC 8859-15 [36] Latin alphabet No. 9 A.11
    // 0x11 ISO/IEC 10646 [16] Basic Multilingual Plane
    // 0x12 KSX1001-2004 [44] Korean Character Set
    // 0x13 GB-2312-1980 Simplified Chinese Character
    // 0x14 Big5 subset of ISO/IEC 10646 [16] Traditional Chinese
    // 0x15 UTF-8 encoding of ISO/IEC 10646 [16] Basic Multilingual Plane
    // 0x1F Described by encoding_type_id Described by 8 bit encoding_type_id
    // conveyed in second byte of the string
    private static final Map<String, String> langCharDecodingMap = new HashMap<String, String>();
    private static final String              defaultLang         = "chi";

    static {
        langCharDecodingMap.put("eng", null);
        langCharDecodingMap.put("chi", "GB2312");
        langCharDecodingMap.put("ger", "ISO-8859-9");// German
        langCharDecodingMap.put("deu", "ISO-8859-9");// German
        langCharDecodingMap.put("fre", "ISO-8859-9");// French
        langCharDecodingMap.put("fra", "ISO-8859-9");// French
        langCharDecodingMap.put("swe", "ISO-8859-9");// Swedish
        langCharDecodingMap.put("sve", "ISO-8859-9");// Swedish
        langCharDecodingMap.put("ita", "ISO-8859-9");// Italian
        langCharDecodingMap.put("spa", "ISO-8859-9");// Spanish
        langCharDecodingMap.put("esl", "ISO-8859-9");// Spanish
        langCharDecodingMap.put("fin", "ISO-8859-9");// Finnish
        langCharDecodingMap.put("nld", "ISO-8859-9");//
        langCharDecodingMap.put("nor", "ISO-8859-9");// Norwegian
        langCharDecodingMap.put("por", "ISO-8859-2");// Portuguese
        langCharDecodingMap.put("dut", "ISO-8859-9");// Dutch Netherlands
        langCharDecodingMap.put("nla", "ISO-8859-9");// Dutch Netherlands
        langCharDecodingMap.put("cat", "ISO-8859-1");// Catalan
        langCharDecodingMap.put("tur", null);// Turkish Turkey Default 6937
        langCharDecodingMap.put("dan", "ISO-8859-9");// Denmark Danish
        langCharDecodingMap.put("pol", "null");// Polish
        langCharDecodingMap.put("rus", "ISO-8859-5");// Russian
        langCharDecodingMap.put("hun", "ISO-8859-2");// Hungary
        langCharDecodingMap.put("ell", null);// Hungary
        langCharDecodingMap.put("gre", null);// Hungary
        langCharDecodingMap.put("chn", null);//
        langCharDecodingMap.put("gat", null);//
        langCharDecodingMap.put("jpn", "ISO-2022-JP");// Japanese

    }

    private static String getDefaultCodingByLang(String ISO_639_language_code) {
        if (ISO_639_language_code == null) {
            return null;
        }
        return langCharDecodingMap.get(ISO_639_language_code.toLowerCase());
    }

    public static String getTextFromByte(byte[] buffer, List<NodeValue> nodeListForISO639)/***/
            throws UnsupportedEncodingException {
        if (buffer == null || buffer.length <= 0) {
            return null;
        }
        int    length                      = buffer.length;
        int    controlCode                 = buffer[0] & 0x000000FF;
        int    controlCodeLen              = 0;
        String charMap                     = null;
        String text                        = null;

        int    ISO_639_language_code_raw   = 0;
        byte[] ISO_639_language_code_bytes = new byte[3];
        String ISO_639_language_code       = null;

        if (controlCode >= 0x20 && controlCode <= 0xFF) {// have no control code
            // use 6937 decode
            // use language configure decode character
            String coding = null;
            String str    = null;
            Object value  = TSUtil.getObjectByName(nodeListForISO639, "ISO_639_language_code");
            if (value != null) {
                ISO_639_language_code_raw      = ((Long) value).intValue();
                ISO_639_language_code_bytes[0] = (byte) (ISO_639_language_code_raw >> 16);
                ISO_639_language_code_bytes[1] = (byte) (ISO_639_language_code_raw >> 8);
                ISO_639_language_code_bytes[2] = (byte) (ISO_639_language_code_raw >> 0);
                ISO_639_language_code          = new String(ISO_639_language_code_bytes);
                coding                         = getDefaultCodingByLang(ISO_639_language_code);
                // log.info(ISO_639_language_code + "\t" + coding);
            } else {
                if (coding == null) {
                    coding = getDefaultCodingByLang(defaultLang);
                }
            }

            if (coding != null) {
                str = new String(buffer, coding);
            } else {
                char[] arb = new char[buffer.length];
                for (int i = 0; i < arb.length; i++) {
                    arb[i] = (char) (buffer[i] & 0x00ff);
                }
                str = iso6937ToUnicode.convert(arb);
            }

            return str;
        } else if (controlCode >= 0x01 && controlCode <= 0x0B) {
            controlCodeLen = 1;
            charMap        = "ISO-8859-" + (controlCode + 4);
            text           = new String(buffer, controlCodeLen, length - controlCodeLen, charMap);
            return text;
        } else if (controlCode == 0x10 && length >= 3 && buffer[1] == 0x0) {
            controlCodeLen = 3;
            int controlCode2 = buffer[2];
            charMap = "ISO-8859-" + (controlCode2);
            text    = new String(buffer, controlCodeLen, length - controlCodeLen, charMap);
            return text;
        } else if (controlCode == 0x11) {// 0x11 ISO/IEC 10646 [16] Basic Multilingual Plane
            controlCodeLen = 1;
            charMap        = "UTF-16";
            text           = new String(buffer, controlCodeLen, length - controlCodeLen, charMap);
            return text;
        } else if (controlCode == 0x12) {// TODO 0x12 KSX1001-2004 [44] Korean Character Set
            controlCodeLen = 1;
            // charMap = "UTF-16";
            text           = new String(buffer, controlCodeLen, length - controlCodeLen);
            return text;
        } else if (controlCode == 0x13) {// 0x13 GB-2312-1980 Simplified Chinese Character
            controlCodeLen = 1;
            charMap        = "GB2312";
            System.out.println(charMap);
            text = new String(buffer, controlCodeLen, length - controlCodeLen, charMap);
            return text;
        } else if (controlCode == 0x14) {// 0x14 Big5 subset of ISO/IEC 10646 [16] Traditional Chinese
            controlCodeLen = 1;
            charMap        = "UTF-16";                                                            // "Big5";//
            text           = new String(buffer, controlCodeLen, length - controlCodeLen, charMap);
            return text;
        } else if (controlCode == 0x15) {// 0x15 UTF-8 encoding of ISO/IEC 10646 [16] Basic Multilingual Plane
            controlCodeLen = 1;
            charMap        = "UTF-8";
            text           = new String(buffer, controlCodeLen, length - controlCodeLen, charMap);
            return text;
        } else if (controlCode == 0x1F) {// 0x1F Described by encoding_type_id Described by 8 bit
            // encoding_type_id conveyed in second byte of the string
            controlCodeLen = 1;
            text           = new String(buffer);
            return text;
        }

        return new String(buffer);
    }

    public static void main(String[] args) {
        String       rawStr   = "4D 45 47 41 53 49 4E 45";
        List<String> list     = StringUtil.string2List(rawStr, " ");
        byte[]       bytes    = new byte[list.size()];
        int          listSize = list.size();
        for (int j = 0; j < listSize; j++) {
            bytes[j] = Integer.valueOf(list.get(j), 16).byteValue();
        }

        try {
            String str = TextUtil.getTextFromByte(bytes, null);
            System.out.println(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
