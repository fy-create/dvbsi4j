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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtil {
    private static final byte[] HEX_CHAR_TABLE = { (byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6', (byte) '7',
            (byte) '8', (byte) '9', (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F' };

    static String getHexString(byte[] raw) {
        return getHexString(raw, 0);
    }

    public static String getHexString(byte[] raw, int newLineLength, String prefix) {
        int    len = (2 + 1) * raw.length + (newLineLength == 0 ? 0 : ((raw.length / newLineLength) * (2 + prefix.length()) + 1));
        byte[] hex = new byte[len];
        Arrays.fill(hex, (byte) 0x0);
        int index = 0;
        int time  = 0;
        for (byte b : raw) {
            time++;
            int v = b & 0xFF;
            hex[index++] = HEX_CHAR_TABLE[v >>> 4];
            hex[index++] = HEX_CHAR_TABLE[v & 0xF];
            hex[index++] = ' ';
            if (newLineLength > 0) {
                if (index != 0 && (time % newLineLength == 0)) {
                    hex[index++] = '\n';
                    for (int i = 0; i < prefix.length(); i++) {
                        hex[index++] = ' ';
                    }
                }
            }
        }
        return new String(hex, StandardCharsets.US_ASCII).trim();

//        return null;
    }

    public static String getHexString(byte[] raw, int maxLen) {
        return getHexString(raw, maxLen, "");
    }

    public static String getHexString(byte[] raw, int start, int end, int maxLen) {
        byte[] hex = Arrays.copyOfRange(raw, start, end);
        return getHexString(hex, maxLen);
    }

    public static List<String> string2List(String str, String split, String filter, boolean trim) {
        StringTokenizer st   = new StringTokenizer(str, split);
        List<String>    list = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if (filter != null && filter.length() > 0)
                if (s.indexOf(filter) == -1)
                    continue;
            if (trim) {
                if (s.trim().length() > 0) {
                    list.add(s.trim());
                }
                // else
                // list.add(s);
            } else {
                if (s.length() > 0)
                    list.add(s);
            }

        }
        return list;
    }

    public static List<String> string2List(String str, String split, String filter) { //
        return string2List(str, split, filter, true);
    }

    public static List<String> string2List(String str, String split) {
        return string2List(str, split, null);
    }

    public static String getString(int n, char c) {
        char[] ret = new char[n];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = c;
        }
        return new String(ret);
    }

    public static int replace(StringBuffer source, String aim, String to) { //
        return replace(source, aim, to, 0);
    }

    public static int replace(StringBuffer source, String aim, String to, int startPos) { //
        int offset = source.indexOf(aim, startPos);
        source.delete(offset, offset + aim.length());
        if (to == null)
            return offset;
        source.insert(offset, to);
        return offset + to.length();
    }

    public static void replaceAll(StringBuffer source, String aim, String to) { //
        int startPos = 0;
        while (source.indexOf(aim, startPos) != -1) {
            startPos = replace(source, aim, to, startPos);
        }
    }

    public static String formatString(String str, int n) {
        if (str.length() > n && n != 0)
            return str.substring(0, n) + "...";

        StringBuffer sb = new StringBuffer(str);
        for (int i = 0; i < n - str.length(); i++)
            sb.append(' ');

        return sb.toString();
    }

    public static String formatString(int value, int n) {
        return formatString(String.valueOf(value), n);
    }

    public static String object2hex(Object obj) {
        if (obj == null) {
            return null;
        }
        Class<? extends Object> clazz = obj.getClass();
        if (clazz == null) {
            return null;
        }
        if (clazz == Byte.class) {
            return "0x" + Integer.toHexString(((Byte) obj).intValue()).toUpperCase();
        } else if (clazz == Short.class) {
            return "0x" + Integer.toHexString((Short) obj).toUpperCase();
        } else if (clazz == Integer.class) {
            return "0x" + Integer.toHexString((Integer) obj).toUpperCase();
        }
        return null;
    }

    public static String toHex(int obj) {
        return "0x" + Integer.toHexString(obj).toUpperCase();
    }

    public static int byteArrayToInt(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;
        }
        return value;
    }

}
