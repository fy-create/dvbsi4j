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

public class announcement_support_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimsbf ");
        parseData("descriptor_length 8 uimsbf ");
        parseData("announcement_support_indicator 16 bslbf ");
        {
            pushNode("announce");
            int _position3 = getBufferLen();
            while (getToken() < _position3) // N
            {
                pushElement();
                {
                    parseData("announcement_type 4 uimsbf ");
                    parseData("reserved_future_use 1 bslbf ");
                    parseData("reference_type 3 uimsbf ");
                    if (contextGet("reference_type") == 0x01 || contextGet("reference_type") == 0x02 || contextGet("reference_type") == 0x03) {
                        parseData("original_network_id 16 uimsbf ");
                        parseData("transport_stream_id 16 uimsbf ");
                        parseData("service_id 16 uimsbf ");
                        parseData("component_tag__ 8 uimsbf ");
                    }
                }
                popElement();
            }
            popNode("announce");
        }
    }

    public String getName() {
        return "announcement_support_descriptor";
    }

}
