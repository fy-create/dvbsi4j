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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class only for pretty format all syntax
 */
public class SyntaxFormat {
    private static void processFile(String fileName) {
        System.out.println("Process file:" + fileName);
        int          maxLen = 0;
        String       reg    = "^(\\s+)(\\w+)(\\s+)(\\d+)(\\s+)(\\w+).*";
        List<String> list   = FileUtil.readFileToList(fileName);
        for (String item : list) {
            item = item.replaceAll("\\t", "    ");
            boolean b = item.matches(reg);
            if (b) {
                String key = ReplaceUtil.replace(item, reg, "$1$2");
                maxLen = (key.length() > maxLen) ? key.length() : maxLen;
            }
        }

        maxLen += 4;

        StringBuffer sb = new StringBuffer();
        for (String item : list) {
            item = item.replaceAll("\\t", "    ");
            boolean b = item.matches(reg);
            if (b) {
                String key = ReplaceUtil.replace(item, reg, "$1$2");
                key = StringUtil.formatString(key, maxLen);

                String len       = ReplaceUtil.replace(item, reg, "$4");
                String type      = ReplaceUtil.replace(item, reg, "$6");

                String newSyntax = key + "    " /***/
                        + StringUtil.formatString(len, 4) /***/
                        + "    " /***/
                        + StringUtil.formatString(type, 8) /***/
                ;
                sb.append(newSyntax + "\n");
            } else {
                sb.append(item + "\n");
            }
        }
        FileUtil.writeStringToFile(fileName, sb.toString());
    }

    public static void main(String[] args) {
        List<String> fileList      = new ArrayList<String>();
        String       syntaxRootDir = "." + File.separator + "syntax" + File.separator + "input";
        FileUtil.ListDirectory(new File(syntaxRootDir), fileList);
        try {
            for (String fileName : fileList) {
                processFile(fileName);
            }
        } catch (Exception e) {
        }
    }
}
