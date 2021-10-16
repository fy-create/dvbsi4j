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
package com.fyteck.dvbsi.sample;

import com.fyteck.dvbsi.ISectionNotify;
import com.fyteck.dvbsi.NodeValue;
import com.fyteck.dvbsi.TSSection;
import com.fyteck.dvbsi.TSUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

public class DecodePATPMT extends DecodeAllTable {
    private static final Logger log           = Logger.getLogger(DecodePATPMT.class);

    protected ISectionNotify    sectionNotify =
            /***/
            new ISectionNotify() {
                @Override
                public void sectionNotify(TSSection section) {
                    String sectionName = section.getCommonParser().getName();

                    if (sectionName.equalsIgnoreCase("program_association_section")) {

                        /**
                         * <code>
                         * program_association_section () {                         - ❶ root node
                         *     table_id                        8       uimsbf  
                         *     section_syntax_indicator        1       bslbf   
                         *     noused                          1       bslbf   
                         *     reserved                        2       bslbf   
                         *     section_length                  12      uimsbf  
                         *     transport_stream_id             16      uimsbf  
                         *     reserved                        2       bslbf   
                         *     version_number                  5       uimsbf  
                         *     current_next_indicator          1       bslbf   
                         *     section_number                  8       uimsbf  
                         *     last_section_number             8       uimsbf  
                         *
                         *     for (i=0; i<N;i++) /*programs* / {                   - ❷ default named programs 
                         *                                                               user can change it in syntax
                         *         program_number              16      uimsbf       - ❸
                         *         reserved                    3       bslbf   
                         *         if(program_number == 0x0) {
                         *             network_PID             13      uimsbf  
                         *         }
                         *         else {
                         *             program_map_PID         13      uimsbf       - ❹
                         *         }
                         *     }
                         *
                         *     CRC_32                          32      rpchof  
                         * }
                         * </code>
                         */

                        log.info("\n" + section.dumpTextResult());
                        log.info("\n" + section.dumpSectionRawPacket());
                        List<NodeValue> root     = section.getRoot();                        /* ❶ */

                        List<NodeValue> programsNodeList = TSUtil.getObjectByName(root, "programs"); /* ❷ */

                        /** loop each program in programs */
                        for (NodeValue _programNode : programsNodeList) {
                            List<NodeValue> program = _programNode.getValue();
                            int     program_number = TSUtil.getObjectByName(program, "program_number"); /* ❸ */

                            if (program_number != 0) {
                                int program_map_PID = TSUtil.getObjectByName(program, "program_map_PID"); /** ❹ */

                                /** dynamic set PMT PID filter */
                                section.getBitStream().getPIDFilter().addPidFilter(program_map_PID, "PMT pid=>" + program_map_PID);
                            }
                        }
                    } else if (sectionName.equalsIgnoreCase("TS_program_map_section")) {
                        log.info(sectionName);

                        /** should switch thread context */
                        log.info("\n" + section.dumpTextResult());

                    }

                }
            };

    public static void main(String[] args) {
        DecodePATPMT decodePATPMT = new DecodePATPMT();
        decodePATPMT.init();
        decodePATPMT.bitStream.setSectionNotify(decodePATPMT.sectionNotify);
        decodePATPMT.decodeTSFile(new File("./dvbtest.ts"));
    }
}
