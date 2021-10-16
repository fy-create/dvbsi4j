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
package S20_300468.descriptor;

import com.fyteck.dvbsi.CommonParser;

public class linkage_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimsbf ");
        parseData("descriptor_length 8 uimsbf ");
        parseData("transport_stream_id 16 uimsbf ");
        parseData("original_network_id 16 uimsbf ");
        parseData("service_id 16 uimsbf ");
        parseData("linkage_type 8 uimsbf ");
        if (contextGet("linkage_type") == 0x08) {
            parseData("hand-over_type 4 bslbf ");
            parseData("reserved_future_use 3 bslbf ");
            parseData("origin_type 1 bslbf ");
            if (contextGet("hand-over_type") == 0x01 || contextGet("hand-over_type") == 0x02 || contextGet("hand-over_type") == 0x03) {
                parseData("network_id 16 uimsbf ");
            }
            if (contextGet("origin_type") == 0x00) {
                parseData("initial_service_id 16 uimsbf ");
            }
        }
        if (contextGet("linkage_type") == 0x0D) {
            parseData("target_event_id 16 uimsbf ");
            parseData("target_listed 1 bslbf ");
            parseData("event_simulcast 1 bslbf ");
            parseData("reserved 6 bslbf ");
        }
        // Use dynamic length parse buffer
        parseBytes("private_data_byte " + (getBufferLen() - getToken()) * 8 + " uimsbf ");
    }

    public String getName() {
        return "linkage_descriptor";
    }

}
