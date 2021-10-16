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
package S20_300468.section;

import com.fyteck.dvbsi.CommonParser;

public class selection_information_section extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("table_id 8 uimsbf ");
        parseData("section_syntax_indicator 1 bslbf ");
        parseData("DVB_reserved_future_use 1 bslbf ");
        parseData("ISO_reserved 2 bslbf ");
        parseData("section_length 12 uimsbf ");
        parseData("DVB_reserved_future_use 16 uimsbf ");
        parseData("ISO_reserved 2 bslbf ");
        parseData("version_number 5 uimsbf ");
        parseData("current_next_indicator 1 bslbf ");
        parseData("section_number 8 uimsbf ");
        parseData("last_section_number 8 uimsbf ");
        parseData("DVB_reserved_for_future_use 4 uimsbf ");
        parseData("transmission_info_loop_length 12 bslbf ");
        // Process DESCRIPTOR
        {
            int    transmission_info_loop_length_bytes = contextGet("transmission_info_loop_length");
            byte[] buffer                              = parseData(transmission_info_loop_length_bytes * 8, false, false);
            parseDescriptors("descriptors", buffer);//
        }

        {
            pushNode("services");
            int _position1_ = getBufferLen() - 4 /* 4 bytes CRC32 */;
            while (getToken() < _position1_)// N
            {
                pushElement();
                {
                    parseData("service_id 16 uimsbf ");
                    parseData("DVB_reserved_future_use 1 uimsbf ");
                    parseData("running_status 3 bslbf ");
                    parseData("service_loop_length 12 bslbf ");
                    // Process DESCRIPTOR
                    {
                        int    service_loop_length_bytes = contextGet("service_loop_length");
                        byte[] buffer                    = parseData(service_loop_length_bytes * 8, false, false);
                        parseDescriptors("descriptors", buffer);//
                    }

                }
                popElement();
            }
            popNode("services");
        }
        parseData("CRC_32 32 rpchof ");
    }

    public String getName() {
        return "selection_information_section";
    }

}
