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

public class PidMeta {
    private Integer pid;
    private String  name;
    private boolean enable;

    public PidMeta() {
        super();
    }

    public PidMeta(Integer pid, String name) { //
        super();
        this.pid    = pid;
        this.name   = name;
        this.enable = true;
    }

    public PidMeta(Integer pid, String name, boolean enable) {
        super();
        this.pid    = pid;
        this.name   = name;
        this.enable = enable;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int hashCode() {
        final int prime  = 31;
        int       result = 1;
        result = prime * result + ((pid == null) ? 0 : pid.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PidMeta other = (PidMeta) obj;
        if (pid == null) {
            return other.pid == null;
        } else
            return pid.equals(other.pid);
    }

    public String toString() {
        return "PidMeta [pid=" + pid + ", name=" + name + ", enable=" + enable + "]";
    }

}
