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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static StringBuffer readFileToStringBuffer(String name) {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader in = new BufferedReader(new FileReader(name));
            String         str;
            while ((str = in.readLine()) != null) {
                sb.append(str).append("\n");
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    public static List<String> readFileToList(String name) {
        List<String> list = new ArrayList<String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(name));
            String         str;
            while ((str = in.readLine()) != null) {
                list.add(str);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void writeStringToFile(String fileName, String content, boolean append) { //
        try {
            File dir = new File(fileName).getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, append));
            out.write(content);
            out.close();
        } catch (IOException e) {
        }
    }

    public static void writeStringToFile(String fileName, String content) {
        writeStringToFile(fileName, content, false);
    }

    public static void ListDirectory(File sourceLocation, List<String> fileLists) {
        if (sourceLocation.isDirectory()) {
            String[] children = sourceLocation.list();
            if (children == null) {
                return;
            }
            for (int i = 0; i < children.length; i++) {
                ListDirectory(new File(sourceLocation, children[i]), fileLists);
            }
        } else {
            fileLists.add(sourceLocation.getAbsolutePath());
        }
    }

}
