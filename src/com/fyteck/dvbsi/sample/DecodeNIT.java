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

public class DecodeNIT extends DecodeAllTable {
    private static final Logger log           = Logger.getLogger(DecodeNIT.class);

    protected ISectionNotify    sectionNotify = /***/
            new ISectionNotify() {
                @Override
                public void sectionNotify(TSSection section) {
                    String sectionName = section.getCommonParser().getName();

                    if (sectionName.equalsIgnoreCase("program_association_section")) {
                        List<NodeValue> root     = section.getRoot();
                        List<NodeValue> programsNodeList = TSUtil.getObjectByName(root, "programs");
                        for (NodeValue programNode : programsNodeList) {
                            List<NodeValue> programNodeList = programNode.getValue();
                            int     program_number  = TSUtil.getObjectByName(programNodeList, "program_number");

                            if (program_number == 0) {
                                int network_PID = TSUtil.getObjectByName(programNodeList, "network_PID");
                                section.getBitStream().getPIDFilter().addPidFilter(network_PID, "NIT");
                            }
                        }
                    } else if (sectionName.equalsIgnoreCase("network_information_section")) {
                        log.info(sectionName);

                        /** should switch thread context */
                        log.info("\n" + section.dumpTextResult());
                    }
                }
            };

    public static void main(String[] args) {
        DecodeNIT decodeNIT = new DecodeNIT();
        decodeNIT.init();
        decodeNIT.bitStream.setSectionNotify(decodeNIT.sectionNotify);
        decodeNIT.decodeTSFile(new File("./dvbtest.ts"));
    }
}
