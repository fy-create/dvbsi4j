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

import java.util.List;

public class BitStream {
    public static final int      PACKAGE_LEN    = 188;
    public static final int      HEAD_SYNC_BYTE = 0x47;
    private final PIDFilter      pidFilter      = new PIDFilter();
    private final SectionManager sectionManager;
    private final TableDecode    tableDecode;
    private ISectionNotify       sectionNotify  = null;

    public BitStream() {
        super();
        sectionManager = new SectionManager(this);
        tableDecode    = new TableDecode(this);

        List<PidMeta> pids = GlobalConfig.dvbConfig.getPIDS();
        for (PidMeta pid : pids) {
            if (pid.isEnable()) {
                pidFilter.addPidFilter(pid.getPid().shortValue(), pid.getName());
            }
        }
    }

    public SectionManager getSectionManager() {
        return sectionManager;
    }

    public PIDFilter getPIDFilter() {
        return pidFilter;
    }

    public TableDecode getTableDecode() {
        return tableDecode;
    }

    public ISectionNotify getSectionNotify() {
        return sectionNotify;
    }

    public void setSectionNotify(ISectionNotify sectionNotify) {
        this.sectionNotify = sectionNotify;
    }

    public String dumpSectionBriefInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append(sectionManager.sectionBreifInfo());
        return sb.toString();
    }

    public void dumpSections(boolean withDetail) {
        if (sectionManager != null) {
            sectionManager.dumpSections(withDetail);
        }
    }
}
