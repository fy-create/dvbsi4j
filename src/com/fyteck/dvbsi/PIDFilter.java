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

import java.util.HashMap;
import java.util.Map;

public class PIDFilter {
    private final Map<Integer, String> pids = new HashMap<Integer, String>();

    public void addPidFilter(int pid, String name) {
        if (pids.containsValue(name) && name.equals("PCR_PID")) {
            return;
        }
        if (pids.containsKey(pid)) {
            return;
        }
        pids.put(pid, name);
    }

    public boolean inPidFilter(int pid) {
        return pids.containsKey(pid);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Filter \r\n");
        for (Integer pid : pids.keySet()) {
            builder.append("pid:" + pid + " name:" + pids.get(pid) + "\r\n");
        }
        return builder.toString();
    }

}