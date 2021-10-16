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

public class NumberUtil {
    public static String Object2Hex(Object object) {
        if (object.getClass() == Long.class) {
            return Long.toHexString((Long) object).toUpperCase();
        } else if (object.getClass() == Integer.class) {
            return Integer.toHexString((Integer) object).toUpperCase();
        } else if (object.getClass() == Short.class) {
            return Integer.toHexString((Short) object).toUpperCase();
        } else if (object.getClass() == Byte.class) {
            return Integer.toHexString((Byte) object).toUpperCase();
        } else if (object.getClass() == byte[].class) {
            byte[] bytes = (byte[]) object;
            if (bytes.length <= 4) {
                return StringUtil.getHexString(bytes);
            }
        }
        return "";
    }

    public static int getIntValue(Object object) {
        if (object == null) {
            return -1;
        }
        if (object.getClass() == Long.class) {
            return ((Long) object).intValue();
        } else if (object.getClass() == Integer.class) {
            return ((Integer) object).intValue();
        } else if (object.getClass() == Short.class) {
            return ((Short) object).intValue();
        } else if (object.getClass() == Byte.class) {
            return ((Byte) object).intValue();
        }
        return -1;
    }

    public static int unsignedByteToInt(byte b) {
        return (int) b & 0xFF;
    }
}
