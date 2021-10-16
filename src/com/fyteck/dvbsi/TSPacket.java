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

import java.util.HashMap;
import java.util.Map;

public class TSPacket {
    private static final Logger        log                              = Logger.getLogger(TSPacket.class);

    private static final String        sync_byte                        = "sync byte                   ";
    private static final String        transport_error_indicator        = "transport error indicator   ";
    private static final String        payload_unit_start_indicator     = "payload unit start indicator";
    private static final String        transport_priority               = "transport priority          ";
    private static final String        packet_identifier                = "packet identifier           ";
    private static final String        transport_scrambling_control     = "transport scrambling control";
    private static final String        adaptation_field_control         = "adaptation field control    ";
    private static final String        continuity_counter               = "continuity counter          ";
    private static final byte[]        HEADER_BIT_DESCRIPT              = { 8, 1, 1, 1, 13, 2, 2, 4 };
    private static final String[]      HEADER_NAME                      = {
            /***/
            sync_byte, /***/
            transport_error_indicator, /***/
            payload_unit_start_indicator, /***/
            transport_priority, /***/
            packet_identifier, /***/
            transport_scrambling_control, /***/
            adaptation_field_control, /***/
            continuity_counter /***/
    };

    private final BitStream            bitStream;
    private final byte[]               rawData                          = new byte[BitStream.PACKAGE_LEN];
    private final byte[]               headerBytes                      = new byte[4];
    private final Map<String, Integer> header                           = new HashMap<String, Integer>();
    /* field for first packet */
    private int                        payload_unit_start_indicator_pos = 0;
    private short                      point_of_field                   = 0;
    private int                        adaptation_field_control_byte    = 0;
    private int                        adaptation_field_length          = 0;
    private int                        tableId                          = 0;

    public TSPacket(BitStream ts) {
        this.bitStream = ts;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public int getPayload_unit_start_indicator() {
        return payload_unit_start_indicator_pos;
    }

    public short getPoint_of_field() {
        return point_of_field;
    }

    public int getAdaptation_field_length() {
        return adaptation_field_length;
    }

    public int getAdaptation_field_control_byte() {
        return adaptation_field_control_byte;
    }

    public void parseHeader() {
        System.arraycopy(rawData, 0, headerBytes, 0, 4);

        SectionParser sectionParser = new SectionParser();
        sectionParser.setBuffer(headerBytes);

        int len = HEADER_BIT_DESCRIPT.length;
        try {
            for (int i = 0; i < len; i++) {
                Integer data = (Integer) sectionParser.parseData(HEADER_BIT_DESCRIPT[i]);
                header.put(HEADER_NAME[i], data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String dump2String() {
        StringBuffer sb = new StringBuffer();

        sb.append("Packet Header\n");
        sb.append("-----------------------------------------\n");
        for (int i = 0; i < HEADER_NAME.length; i++) {
            sb.append(HEADER_NAME[i] + //
                    "(" + StringUtil.formatString(HEADER_BIT_DESCRIPT[i] + "b", 4) + "):" + //
                    StringUtil.object2hex(header.get(HEADER_NAME[i])) + "\n"//
            );
        }
        sb.append("-----------------------------------------\n");
        sb.append("Raw Data\n" + StringUtil.getHexString(getRawData(), GlobalConfig.byteLineNumber));
        sb.append("\n\n");
        return sb.toString();
    }

    public boolean tryMakeSection() {
        payload_unit_start_indicator_pos = header.get(payload_unit_start_indicator);

        int    token = 4;
        // get point of field
        byte[] ab    = getRawData();
        int    pid   = header.get(packet_identifier);
        adaptation_field_control_byte = header.get(adaptation_field_control);

        try {
            /* binary 10 adaptation_field only, no payload */
            /* binary 11 adaptation_field followed by payload */
            if (adaptation_field_control_byte == 2 || adaptation_field_control_byte == 3) {
                /* should be skip */
                adaptation_field_length = (short) NumberUtil.unsignedByteToInt(ab[token]);
                token++;
                token += adaptation_field_length;
            }

            /* new table start here */
            if (payload_unit_start_indicator_pos == 1) {
                point_of_field = (short) NumberUtil.unsignedByteToInt(ab[token]);
                if (point_of_field > 0) {
                    bitStream.getSectionManager().sectionAppendPacket(pid, this, true);
                }

                /* Skip point of field */
                token++;
                token += point_of_field;

                if ((long) token >= BitStream.PACKAGE_LEN) {
                    log.error("Error point_of_field pid=" + pid);
                    return false;
                }
                tableId = (int) ab[token] & 0x000000ff;

                TableMeta tableRange = bitStream.getTableDecode().getTableConfigByTableId(tableId);
                if (tableRange != null) {
                    bitStream.getSectionManager().sectionStartPacket(pid, this);
                }
            } else {
                /**
                 * If the Transport Stream packet does not carry the first byte of a PSI
                 * section, the payload_unit_start_indicator value shall be '0', indicating that
                 * there is no pointer_field in the payload
                 */
                bitStream.getSectionManager().sectionAppendPacket(pid, this, false);
            }
        } catch (Exception e) {
            log.info(e);
        }
        return true;
    }
}
