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

import com.fyteck.dvbsi.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class DecodeSDT extends DecodeAllTable {
    private static final Logger log           = Logger.getLogger(DecodeSDT.class);

    protected ISectionNotify    sectionNotify = /***/
            new ISectionNotify() {
                @Override
                public void sectionNotify(TSSection section) {
                    String sectionName = section.getCommonParser().getName();

                    if (sectionName.equalsIgnoreCase("service_description_section")) {
                        log.info(sectionName);

                        /** should switch thread context */
                        log.info(sectionName + " \n" + section.dumpTextResult());
                        dumpService(section);
                    }
                }
            };

    public static void main(String[] args) {
        DecodeSDT decodeSDT = new DecodeSDT();
        decodeSDT.init();
        decodeSDT.bitStream.setSectionNotify(decodeSDT.sectionNotify);
        decodeSDT.decodeTSFile(new File("./dvbtest.ts"));
    }

    private String decodeServiceName(Object serviceNameBytes, List<NodeValue> nodeListForISO639) {
        try {
            return TextUtil.getTextFromByte((byte[]) serviceNameBytes, nodeListForISO639);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void dumpService(TSSection sdtSection) {
        String          serviceName = null;
        List<NodeValue> root        = null;

        /**
         * service_description_section,
         * S20_300468/section/service_description_section.java
         *
         * <code>
         * service_description_section(){                                   - ❶ root node
         *          table_id                              8       uimsbf
         *          section_syntax_indicator              1       bslbf
         *          reserved_future_use                   1       bslbf
         *          reserved                              2       bslbf
         *          section_length                        12      uimsbf
         *          transport_stream_id                   16      uimsbf
         *          reserved                              2       bslbf
         *          version_number                        5       uimsbf
         *          current_next_indicator                1       bslbf
         *          section_number                        8       uimsbf
         *          last_section_number                   8       uimsbf
         *          original_network_id                   16      uimsbf
         *          reserved_future_use                   8       bslbf
         *          for (i=0;i<section_length-12;i++) /*services* / {       - ❷ default named services
         *                                                                       user can change it in syntax
         *              service_id                        16      uimsbf
         *              reserved_future_use               6       bslbf
         *              EIT_schedule_flag                 1       bslbf
         *              EIT_present_following_flag        1       bslbf
         *              running_status                    3       uimsbf
         *              free_CA_mode                      1       bslbf
         *              descriptors_loop_length           12      uimsbf
         *              for (j=0;j<N;j++){                                  - ❸ fix named "descriptors"
         *                                                                      user can NOT change it
         *                  descriptor()
         *              }
         *          }
         *          CRC_32                                32      rpchof
         * }
         * </code>
         */

        root = sdtSection.getRoot();/* ❶ */
        if (root == null) {
            return;
        }

        int serviceNumber = TSUtil.getObjectLenByName(root, "services");

        for (int i = 0; i < serviceNumber; i++) {
            List<NodeValue> serviceNodeList = TSUtil.getObjectByNameIdx(root, "services", i);           /* ❷ */

            /* get descriptor count */
            int             desNumber       = TSUtil.getObjectLenByName(serviceNodeList, "descriptors");

            for (int j = 0; j < desNumber; j++) {
                List<NodeValue> descriptNodeList = TSUtil.getObjectByNameIdx(serviceNodeList, "descriptors", j);/* ❸ */
                if (descriptNodeList == null) {
                    continue;
                }

                /**
                 * service_descriptor, S20_300468/descriptor/service_descriptor.java
                 *
                 * <code>
                 * tag=0x48
                 * service_descriptor(){
                 *     descriptor_tag                      8       uimsbf      - ❹
                 *     descriptor_length                   8       uimsbf
                 *     service_type                        8       uimsbf
                 *     service_provider_name_length        8       uimsbf
                 *     for (i=0;i<N;I++){
                 *         service_provider_name           8       uimsbf
                 *     }
                 *     service_name_length                 8       uimsbf
                 *     for (i=0;i<N;I++){
                 *         service_name                    8       uimsbf      - ❺
                 *     }
                 * }
                 * </code>
                 */

                int descriptTag = TSUtil.getObjectByName(descriptNodeList, "descriptor_tag");/* ❹ */
                /* not service_descriptor, ignore */
                if (descriptTag != 0x48) {
                    continue;
                }

                byte[] serviceNameBytes = TSUtil.getObjectByName(descriptNodeList, "service_name"); /* ❺ */
                if (serviceNameBytes != null) {
                    serviceName = decodeServiceName(serviceNameBytes, descriptNodeList);
                    log.info("serviceName:" + serviceName);
                }
            }
        }
    }
}
