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
package S10_13818.section;

import com.fyteck.dvbsi.CommonParser;

public class TS_program_map_section extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("table_id 8 uimsbf ");
        parseData("section_syntax_indicator 1 bslbf ");
        parseData("noused 1 bslbf ");
        parseData("reserved 2 bslbf ");
        parseData("section_length 12 uimsbf ");
        parseData("program_number 16 uimsbf ");
        parseData("reserved 2 bslbf ");
        parseData("version_number 5 uimsbf ");
        parseData("current_next_indicator 1 bslbf ");
        parseData("section_number 8 uimsbf ");
        parseData("last_section_number 8 uimsbf ");
        parseData("reserved 3 bslbf ");
        parseData("PCR_PID 13 uimsbf ");
        parseData("reserved 4 bslbf ");
        parseData("program_info_length 12 uimsbf ");
        // Process DESCRIPTOR
        {
            int    program_info_length_bytes = contextGet("program_info_length");
            byte[] buffer                    = parseData(program_info_length_bytes * 8, false, false);
            parseDescriptors("descriptors", buffer);//
        }

        {
            pushNode("streams");
            int _dynamic_3 = (contextGet("section_length") - contextGet("program_info_length") - 4 - 9) + getToken();
            while (getToken() < _dynamic_3) // section_length-program_info_length-4-9
            {
                pushElement();
                {
                    parseData("stream_type 8 uimsbf ");
                    parseData("reserved 3 bslbf ");
                    parseData("elementary_PID 13 uimsnf ");
                    parseData("reserved 4 bslbf ");
                    parseData("ES_info_length 12 uimsbf ");
                    // Process DESCRIPTOR
                    {
                        int    ES_info_length_bytes = contextGet("ES_info_length");
                        byte[] buffer               = parseData(ES_info_length_bytes * 8, false, false);
                        parseDescriptors("descriptors", buffer);//
                    }

                }
                popElement();
            }
            popNode("streams");
        }
        parseData("CRC_32 32 rpchof ");
    }

    public String getName() {
        return "TS_program_map_section";
    }

}
