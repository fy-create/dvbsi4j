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

import org.apache.log4j.Logger;

import java.util.List;

public class TableDecode {
    private static final Logger log = Logger.getLogger(TableDecode.class);
    private final BitStream     bitStream;

    public TableDecode(BitStream bitStream) {
        this.bitStream = bitStream;
    }

    public BitStream getBitStream() {
        return bitStream;
    }

    public TableMeta getTableConfigByTableId(int tableId) {
        TableMeta       tableConfig     = null;
        List<TableMeta> tableIdList     = GlobalConfig.dvbConfig.getTableIdList();
        int             tableIdListSize = tableIdList.size();
        for (int i = 0; i < tableIdListSize; i++) {
            tableConfig = tableIdList.get(i);
            if (tableConfig.isEnable() == false)
                continue;

            if (tableConfig.getStart() == tableId || /***/
                    (tableConfig.getStart() <= tableId && tableId <= tableConfig.getEnd())) {
                return tableConfig;
            }
        }
        return null;
    }

    public void parse(TSSection section) {
        section.setHasParse(false);

        TableMeta tableConfig = getTableConfigByTableId(section.getTable_id());
        if (tableConfig == null || tableConfig.isEnable() == false) {
            return;
        }

        String fullClassName = tableConfig.getFullClassName();
        if (fullClassName == null) {
            return;
        }

        Class<?> clazz = null;
        try {
            clazz = SyntaxFactory.getClazz(fullClassName);
            if (clazz == null) {
                log.debug("Table Id:" + section.getTable_id() + "" + tableConfig + " not implement!!");
                return;
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        CommonParser commonParser = null;
        try {
            /* using pool */
            commonParser = (CommonParser) SyntaxFactory.getInstanceByClass(clazz);
            commonParser.setValueStack(section.getValueStack());
            commonParser.reset();
            commonParser.setSection(section);

            List<NodeValue> root = commonParser.getCurrentList();
            section.setTableName(commonParser.getClass().toString());
            section.setCommonParser(commonParser);
            commonParser.parse(section.getSectionData().array(), section.getSection_length());

            /* Disable auto set PMT NIT PID filter function, The client is responsible for
             * doing this */
            boolean autoGetPmtNit = false;
            if (autoGetPmtNit) {
                /* When got PAT decode PMT NIT PID from PAT */
                if (commonParser.getClass().getSimpleName().equals("program_association_section")) {
                    /* Add NIT PMT Filter */
                    List<NodeValue> programs = TSUtil.getObjectByName(root, "programs");
                    for (NodeValue program : programs) {
                        List<NodeValue> programList    = program.getValue();

                        int             program_number = NumberUtil.getIntValue( /***/
                                TSUtil.getObjectByName(programList, "program_number"));

                        if (program_number == 0) {
                            int network_PID = NumberUtil.getIntValue(/***/
                                    TSUtil.getObjectByName(programList, "network_PID"));

                            this.getBitStream().getPIDFilter().addPidFilter(network_PID, "NIT");
                        } else {
                            int program_map_PID = NumberUtil.getIntValue(/***/
                                    TSUtil.getObjectByName(programList, "program_map_PID"));

                            this.getBitStream().getPIDFilter().addPidFilter(program_map_PID, "PMT pid=>" + program_map_PID);
                        }
                    }
                }
            }

            section.setHasParse(true);
        } catch (Exception e) {
            section.setHasParse(false);
            e.printStackTrace();
            log.info(section);
            log.info(section.dumpTextResult());
            log.info(section.dumpSectionData());
            log.error("Parse error:" + section.shortName());
        }
    }

    public void dumpInfo() {
        log.info("Table\t=>Pid");
        List<PidMeta> pidMetaList = GlobalConfig.dvbConfig.getPIDS();
        for (PidMeta pid : pidMetaList) {
            log.info(StringUtil.formatString(pid.getName(), 20) + "\t=>" + /***/
                    StringUtil.formatString("" + pid.getPid().shortValue(), 3) + /***/
                    "(0x" + Integer.toHexString(pid.getPid().shortValue()).toUpperCase() + ")");
        }
    }
}
