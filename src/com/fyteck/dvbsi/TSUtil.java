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

import java.util.ArrayList;
import java.util.List;

public class TSUtil {

    public static <T extends Object> T getObjectByName(List<NodeValue> nodeList, String name) {
        if (nodeList == null || name == null) {
            return null;
        }
        T value = null;
        for (NodeValue node : nodeList) {
            if (node.getName().equals(name)) {
                value = node.getValue();
                break;
            }
        }

        return value;
    }

    public static int getObjectLenByName(List<NodeValue> objectList, String name) {
        if (objectList == null || name == null) {
            return 0;
        }
        if (objectList.getClass() != ArrayList.class) {
            return 0;
        }

        for (NodeValue node : objectList) {
            if (node.getName().equals(name)) {
                Object obj = node.getValue();
                if (obj.getClass() == ArrayList.class) {
                    List<NodeValue> _list = uncheckedCast(obj);
                    return _list.size();
                }
            }
        }
        return 0;
    }

    /**
     * @param <T>
     * @param nodeList
     * @param name
     * @param index
     * @return Object or List<NodeValue>
     */
    public static <T extends Object> T getObjectByNameIdx(List<NodeValue> nodeList, String name, int index) {
        if (nodeList.getClass() == ArrayList.class) {
            Object object = getObjectByName(nodeList, name);
            if (object.getClass() == ArrayList.class) {
                List<NodeValue> list = uncheckedCast(object);
                return list.get(index).getValue();
            }
        }
        return null;
    }

    public static void dumpNode(StringBuffer sb, List<NodeValue> values, int step, int formatLen) {
        if (values == null) {
            return;
        }

        for (NodeValue node : values) {
            if (node.getValue() == null) {
                continue;
            }
            if (node.getValue().getClass() == ArrayList.class) {
                String name = StringUtil.formatString(StringUtil.getString(step, ' ') + node.getName(), formatLen);
                sb.append(name + "\n");
                step += 2;
                List<NodeValue> list = node.getValue();
                dumpNode(sb, list, step, formatLen);
                step -= 2;
            } else if (node.getValue().getClass() == byte[].class) {
                String name      = StringUtil.formatString(StringUtil.getString(step, ' ') /***/
                        + node.getName() + "    ", formatLen);

                String hexPreFix = StringUtil.getString(name.length(), ' ');
                sb.append(name + StringUtil.getHexString(node.getValue(), 16, hexPreFix) + "\n");
            } else {
                String name     = StringUtil.formatString(StringUtil.getString(step, ' ') /***/
                        + node.getName() + "    ", formatLen);

                String value    = StringUtil.formatString(node.getValue().toString(), 10);
                String hexValue = StringUtil.formatString(NumberUtil.Object2Hex(node.getValue()), 10);
                sb.append(name + value + " [0x" + hexValue + "]" + "\n");
            }
        }
    }

    public static <T> T uncheckedCast(Object obj) {
        @SuppressWarnings("unchecked")
        T t = (T) obj;
        return t;
    }

}
