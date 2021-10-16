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

public class DVBConfig {
    private List<PidMeta>   PIDS        = new ArrayList<PidMeta>();
    private List<TableMeta> tableIdList = new ArrayList<TableMeta>();

    public static DVBConfig checkConfig(File configFile) {
        if (!configFile.exists()) {
            DVBConfig dvbConfig = new DVBConfig();
            dvbConfig.defaultValue();
            String str = GlobalConfig.gson.toJson(dvbConfig);
            FileUtil.writeStringToFile(configFile.getAbsolutePath(), str);
            return dvbConfig;
        } else {
            StringBuffer sb        = FileUtil.readFileToStringBuffer(configFile.getAbsolutePath());
            DVBConfig    dvbConfig = GlobalConfig.gson.fromJson(sb.toString(), DVBConfig.class);
            return dvbConfig;
        }
    }

    private void defaultValue() {
        PIDS.add(new PidMeta(0x0000, "PAT"));
        PIDS.add(new PidMeta(0x0001, "CAT"));
        PIDS.add(new PidMeta(0x0002, "TSDT"));
        PIDS.add(new PidMeta(0x0010, "NIT, ST"));
        PIDS.add(new PidMeta(0x0011, "SDT, BAT, ST"));
        PIDS.add(new PidMeta(0x0012, "EIT, ST CIT"));
        PIDS.add(new PidMeta(0x0013, "RST, ST"));
        PIDS.add(new PidMeta(0x0014, "TDT, TOT, ST"));
        PIDS.add(new PidMeta(0x0015, "network synchronization"));
        PIDS.add(new PidMeta(0x0016, "RNT"));// (TS 102 323 [13])
        PIDS.add(new PidMeta(0x001C, "inband signalling"));
        PIDS.add(new PidMeta(0x001D, "measurement"));
        PIDS.add(new PidMeta(0x001E, "DIT"));
        PIDS.add(new PidMeta(0x001F, "SIT"));

        PIDS.add(new PidMeta(900, "CanalDigitaal SD"));
        PIDS.add(new PidMeta(901, "CanalDigitaal HD"));
        PIDS.add(new PidMeta(910, "TV VLAANDEREN SD"));
        PIDS.add(new PidMeta(911, "TV VLAANDEREN HD"));
        PIDS.add(new PidMeta(920, "TeleSAT Belgium"));
        PIDS.add(new PidMeta(921, "TeleSAT Luxemburg"));
        PIDS.add(new PidMeta(950, "AustriaSat"));

        tableIdList.add(new TableMeta(0x00, 0x00, "PAT", "program_association_section", "S10_13818.section.program_association_section"));
        tableIdList.add(new TableMeta(0x01, 0x00, "CAT", "conditional_access_section", "S10_13818.section.CA_section"));
        tableIdList.add(new TableMeta(0x02, 0x00, "PMT", "program_map_section", "S10_13818.section.TS_program_map_section"));
        tableIdList.add(new TableMeta(0x40, 0x00, "NIT_actual", "network_information_section - actual_network",
                "S20_300468.section.network_information_section"));
        tableIdList.add(new TableMeta(0x41, 0x00, "NIT_other", "network_information_section - other_network",
                "S20_300468.section.network_information_section"));

        tableIdList.add(new TableMeta(0x42, 0x00, "SDT_actual", "service_description_section - actual_transport_stream",
                "S20_300468.section.service_description_section"));
        tableIdList.add(new TableMeta(0x46, 0x00, "SDT_other", "service_description_section - other_transport_stream",
                "S20_300468.section.service_description_section"));
        tableIdList.add(new TableMeta(0x4A, 0x00, "BAT", "bouquet_association_section", "S20_300468.section.bouquet_association_section"));

        tableIdList.add(new TableMeta(0x4E, 0x00, "EIT_actual_pf", "event_information_section - actual_transport_stream,present/following",
                "S20_300468.section.event_information_section"));
        tableIdList.add(new TableMeta(0x4F, 0x00, "EIT_other_pf", "event_information_section - other_transport_stream,present/following",
                "S20_300468.section.event_information_section"));
        tableIdList.add(new TableMeta(0x50, 0x5F, "EIT_actual_schedule", "event_information_section - actual_transport_stream,schedule",
                "S20_300468.section.event_information_section"));
        tableIdList.add(new TableMeta(0x60, 0x6F, "EIT_other_schedule", "event_information_section - other_transport_stream,schedule",
                "S20_300468.section.event_information_section"));
        tableIdList.add(new TableMeta(0x70, 0x00, "TDT", "time_date_section", "S20_300468.section.time_date_section"));
        tableIdList.add(new TableMeta(0x71, 0x00, "RST", "running_status_section", "S20_300468.section.running_status_section"));
        tableIdList.add(new TableMeta(0x72, 0x00, "stuffing_section", "stuffing_section", "S20_300468.section.stuffing_section"));
        tableIdList.add(new TableMeta(0x73, 0x00, "TOT", "time_offset_section", "S20_300468.section.time_offset_section"));
        tableIdList.add(new TableMeta(0x74, 0x00, "AIT", "application information section (TS 102 812 [15])",
                "S40_102812.section.application_information_section"));
        tableIdList.add(new TableMeta(0x91, 0x00, "SGT", "Service Guide Table ASTRA_LCN_v2_4", "S60_other.section.service_guide_section"));
        tableIdList.add(new TableMeta(0xBD, 0x00, "FST", "Fastscan Services Table (FST)", "S60_other.section.FST_section"));
        tableIdList.add(new TableMeta(0xBC, 0x00, "FNT", "Fastscan Network Table (FNT)", "S60_other.section.FNT_section"));
    }

    public List<PidMeta> getPIDS() {
        return PIDS;
    }

    public void setPIDS(List<PidMeta> pIDS) {
        PIDS = pIDS;
    }

    public List<TableMeta> getTableIdList() {
        return tableIdList;
    }

    public void setTableIdList(List<TableMeta> tableIdList) {
        this.tableIdList = tableIdList;
    }
}
