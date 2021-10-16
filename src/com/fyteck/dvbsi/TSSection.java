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

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TSSection implements Comparable<TSSection> {
    private static final Logger          log         = Logger.getLogger(TSSection.class);
    private static final int             section_len = 1024 * 4;                         /* 4K Buffer */
    private final Stack<List<NodeValue>> valueStack  = new Stack<List<NodeValue>>();
    private BitStream                    bitStream   = null;
    private int                          pid;
    private/* byte **/ int               table_id;                                       /* table_id 8 uimsbf */
    private/* byte **/ int               section_syntax_indicator;                       /* s_s_i.. 1 bslbf */
    private/* byte **/ int               reserved_future_use;                            /* reserved_future_use 1 bslbf */
    private/* byte **/ int               reserved1;                                      /* reserved 2 bslbf */
    private/* short */ int               section_length;                                 /* section_length 12 uimsbf */
    private/* short */ int               table_id_extension;                             /* table_id_extension 16 uimsbf */
    private/* byte **/ int               reserved2;                                      /* reserved 2 bslbf */
    private/* byte **/ int               version_number;                                 /* version_number 5 uimsbf */
    private/* byte **/ int               current_next_indicator;                         /* current_next_indicator 1 bslbf */
    private/* byte **/ int               section_number;                                 /* section_number 8 uimsbf */
    private/* byte **/ int               last_section_number;                            /* last_section_number 8 uimsbf */
    private List<TSPacket>               packetList  = new ArrayList<TSPacket>();
    private ByteBuffer                   sectionData = ByteBuffer.allocate(section_len); //
    private int                          got_private_section_length;
    private CommonParser                 commonParser;
    private String                       tableName;
    private boolean                      hasParse    = false;

    public TSSection(BitStream bitStream, int pid) {
        super();
        this.bitStream = bitStream;
        this.pid       = pid;
        List<NodeValue> valueList = new ArrayList<NodeValue>();
        valueStack.push(valueList);
        Arrays.fill(sectionData.array(), (byte) (0x0));
    }

    public int getSection_length() {
        return section_length;
    }

    public int getTable_id_extension() {
        return table_id_extension;
    }

    public int getGot_private_section_length() {
        return got_private_section_length;
    }

    public ByteBuffer getSectionData() {
        return sectionData;
    }

    public int getTable_id() {
        return table_id;
    }

    public int getSection_syntax_indicator() {
        return section_syntax_indicator;
    }

    public int getReserved_future_use() {
        return reserved_future_use;
    }

    public int getVersion_number() {
        return version_number;
    }

    public int getCurrent_next_indicator() {
        return current_next_indicator;
    }

    public int getSection_number() {
        return section_number;
    }

    public int getLast_section_number() {
        return last_section_number;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public CommonParser getCommonParser() {
        return commonParser;
    }

    public void setCommonParser(CommonParser commonParser) {
        this.commonParser = commonParser;
    }

    public BitStream getBitStream() {
        return bitStream;
    }

    public void setBitStream(BitStream bitStream) {
        this.bitStream = bitStream;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public boolean isHasParse() {
        return hasParse;
    }

    public void setHasParse(boolean hasParse) {
        this.hasParse = hasParse;
    }

    public Stack<List<NodeValue>> getValueStack() {
        return valueStack;
    }

    public List<NodeValue> getRoot() {
        List<NodeValue> root = valueStack.firstElement();
        return root;
    }

    public List<TSPacket> getPacketList() {
        return packetList;
    }

    public void setPacketList(List<TSPacket> packetList) {
        this.packetList = packetList;
    }

    private boolean parseSectionHeader() throws Exception {
        byte[] rawSection = sectionData.array();
        if (rawSection == null)
            return false;

        SectionParser sectionParser = new SectionParser();
        sectionParser.setBuffer(rawSection);

        table_id                  = (Integer) (sectionParser.parseData(8)); //
        section_syntax_indicator  = (Integer) (sectionParser.parseData(1));

        /* reserved_future_use 1 bslbf */
        reserved_future_use       = (Integer) (sectionParser.parseData(1));

        /* reserved 2 bslbf */
        reserved1                 = (Integer) (sectionParser.parseData(2));

        /* section_length 12 uimsbf section_length -- This is a 12 bit field, the first
         * two bits of which shall be '00'. It specifies the number of bytes of the
         * section starting immediately following the section_length field, and
         * including the CRC. */
        section_length            = (Integer) (sectionParser.parseData(12));

        /* there are 3 bytes before field "section_length" */
        section_length           += 3;

        /* service_id 16 uimsbf */
        table_id_extension        = (Integer) (sectionParser.parseData(16));

        /* reserved 2 bslbf */
        reserved2                 = (Integer) (sectionParser.parseData(2));

        /* version_number 5 uimsbf */
        version_number            = (Integer) (sectionParser.parseData(5));

        /* current_next_indicator 1 bslbf */
        current_next_indicator    = (Integer) (sectionParser.parseData(1));

        /* section_number 8 uimsbf */
        section_number            = (Integer) (sectionParser.parseData(8));

        /* last_section_number 8 uimsbf */
        last_section_number       = (Integer) (sectionParser.parseData(8));
        return true;
    }

    /* 13818 Annex C. Table 2-30 -- Private section */
    public void addTsPacket(TSPacket tsPacket, boolean lastPacketWithPointOfField) throws Exception {
        this.packetList.add(tsPacket);
        int    payload_unit_start_indicator = tsPacket.getPayload_unit_start_indicator();
        short  point_of_field               = tsPacket.getPoint_of_field();
        byte[] tsPacketRawData              = tsPacket.getRawData();
        int    startPos                     = 0;

        if (point_of_field > BitStream.PACKAGE_LEN) {
            log.error("Wrong point_of_field value=" + point_of_field);
            return;
        }

        try {
            if (lastPacketWithPointOfField == true) {
                startPos = 4 + 1;
                sectionData.put(tsPacketRawData, startPos, point_of_field);
                got_private_section_length += point_of_field;
            } else {
                startPos = 4;

                /* skip Adaptation_field_length if have */
                /* bin 10 adaptation_field only, no payload */
                /* bin 11 adaptation_field followed by payload */
                if (tsPacket.getAdaptation_field_control_byte() == 2 || tsPacket.getAdaptation_field_control_byte() == 3) {
                    startPos += 1;
                    startPos += tsPacket.getAdaptation_field_length();
                }

                if (payload_unit_start_indicator == 0x1) {
                    /* skip point_field */
                    startPos += 1;
                    startPos += point_of_field;
                    sectionData.put(tsPacketRawData, startPos, BitStream.PACKAGE_LEN - startPos);
                    got_private_section_length += BitStream.PACKAGE_LEN - startPos;
                    this.parseSectionHeader();
                } else {
                    if (sectionData.position() + (BitStream.PACKAGE_LEN - startPos) <= section_len) {
                        sectionData.put(tsPacketRawData, startPos, BitStream.PACKAGE_LEN - startPos);
                        got_private_section_length += BitStream.PACKAGE_LEN - startPos;
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public String dumpSectionData() {
        if (sectionData == null) {
            return null;
        }
        StringBuffer   sb        = new StringBuffer();
        byte[]         ab        = sectionData.array();
        List<TSPacket> tspackets = this.packetList;
        if (tspackets.size() > 0) {
            sb.append("Section Infomation\n");
            sb.append("------------------------------------------------\n");
            sb.append(StringUtil.getHexString(ab, 0, section_length, GlobalConfig.byteLineNumber));
            sb.append("\n------------------------------------------------\n");

            return sb.toString();
        }
        return null;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Section [tid=");
        builder.append(table_id);
        builder.append('[' + StringUtil.toHex(table_id) + ']');
        builder.append(", ext=");
        builder.append(table_id_extension);
        builder.append('[' + StringUtil.toHex(table_id_extension) + ']');

        builder.append(", ver=");
        builder.append(version_number);

        builder.append(", sec_no=");
        builder.append(section_number);
        builder.append("/" + last_section_number);

        builder.append(", section_syntax_indicator=");
        builder.append(section_syntax_indicator);

        builder.append(", section_length=");
        builder.append(section_length);
        builder.append('[' + StringUtil.toHex(section_length) + ']');

        builder.append(", current_next_indicator=");
        builder.append(current_next_indicator);

        builder.append("] ");

        return builder.toString();
    }

    public String briefTimeInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append("Section [table_id=");
        builder.append(table_id);
        builder.append('[' + StringUtil.toHex(table_id) + ']');
        builder.append(", table_id_extension=");
        builder.append(table_id_extension);
        builder.append(", section_number=");
        builder.append(section_number);
        builder.append(", version=");
        builder.append(version_number);
        builder.append("]");

        return builder.toString();
    }

    /**
     * Dump section raw packet data
     */
    public String dumpSectionRawPacket() {
        if (packetList == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("Packet Infomation\n");
        int packetListSize = packetList.size();
        for (int i = 0; i < packetListSize; i++) {
            TSPacket packet = packetList.get(i);
            sb.append(packet.dump2String());
        }
        return sb.toString();
    }

    public void dispose() {
        sectionData.clear();
        sectionData = null;

        packetList.clear();
        packetList = null;
    }

    public String dumpTextResult() {
        if (commonParser == null) {
            return "";
        }
        List<NodeValue> values = getRoot();
        StringBuffer    sb     = new StringBuffer();
        int             step   = 0;
        TSUtil.dumpNode(sb, values, step, 50);
        return sb.toString();
    }

    public int hashCode() {
        final int prime  = 31;
        int       result = 1;
        result = prime * result + current_next_indicator;
        result = prime * result + last_section_number;
        result = prime * result + reserved1;
        result = prime * result + reserved2;
        result = prime * result + reserved_future_use;
        result = prime * result + section_length;
        result = prime * result + section_number;
        result = prime * result + section_syntax_indicator;
        result = prime * result + table_id;
        result = prime * result + table_id_extension;
        result = prime * result + version_number;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TSSection))
            return false;
        TSSection other = (TSSection) obj;

        if (this.getTable_id() == 0x80 || this.getTable_id() == 0x81) {// is CA Message section ECM
            return Arrays.equals(this.getSectionData().array(), other.getSectionData().array());
        }

        /* For DSMCC section ??? */
        // if (this.getTable_id() >= 0x3A && this.getTable_id() <= 0x3E &&
        // this.getTable_id() == 0x3C) {
        // if (Arrays.equals(this.getSectionData().array(),
        // other.getSectionData().array())) {
        // return true;
        // } else {
        // return false;
        // }
        // }

        if (current_next_indicator != other.current_next_indicator)
            return false;
        if (last_section_number != other.last_section_number)
            return false;
        if (section_number != other.section_number)
            return false;
        if (section_syntax_indicator != other.section_syntax_indicator)
            return false;
        if (table_id != other.table_id)
            return false;
        if (table_id_extension != other.table_id_extension)
            return false;
        return version_number == other.version_number;
    }

    public String shortName() {
        StringBuilder builder = new StringBuilder();
        builder.append("TID = " + table_id + "[0x" + Integer.toHexString(table_id).toUpperCase() + "] ");
        builder.append("Ext = " + table_id_extension + "[0x" + Integer.toHexString(table_id_extension).toUpperCase() + "] ");
        builder.append("Ver = " + StringUtil.formatString(version_number, 3) + " ");
        builder.append("Sec_no = " + StringUtil.formatString(section_number, 3) + "/" + StringUtil.formatString(last_section_number, 3));

        return builder.toString();
    }

    public int compareTo(TSSection o) {
        if (this.table_id < o.table_id) {
            return -1;
        } else if (this.table_id == o.table_id) {
            if (this.table_id_extension < o.table_id_extension) {
                return -1;
            } else if (this.table_id_extension == o.table_id_extension) {
                if (this.version_number < o.version_number) {
                    return -1;
                } else if (this.version_number == o.version_number) {
                    if (this.section_number < o.section_number) {
                        return -1;
                    } else if (this.section_number == o.section_number) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }

            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

}
