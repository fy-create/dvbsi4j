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

public class TableMeta {
    private int     start;
    private int     end;
    private String  shortName;
    private String  descript;
    private String  fullClassName;
    private boolean enable;

    public TableMeta() {
        super();
    }

    public TableMeta(int start, int end, String shortName, String descript, String fullClassName) { //
        super();
        this.start         = start;
        this.end           = end;
        this.shortName     = shortName;
        this.descript      = descript;
        this.fullClassName = fullClassName;
        this.enable        = true;
    }

    public TableMeta(int start, int end, String shortName, String descript, String fullClassName, boolean enable) {
        super();
        this.start         = start;
        this.end           = end;
        this.shortName     = shortName;
        this.descript      = descript;
        this.fullClassName = fullClassName;
        this.enable        = enable;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String toString() {
        return "[0x" + NumberUtil.Object2Hex(start) + "---0x" + NumberUtil.Object2Hex((byte) end) + "]\t" + descript;
    }

}
