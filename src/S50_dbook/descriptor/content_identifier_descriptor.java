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
package S50_dbook.descriptor;

import com.fyteck.dvbsi.CommonParser;

public class content_identifier_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimsbf ");
        parseData("descriptor_length 8 uimsbf ");
        {
            pushNode("OBJECTs16");
            int descriptor_length_bytes = contextGet("descriptor_length") + getToken();
            while (getToken() < descriptor_length_bytes) // N
            {
                pushElement();
                {
                    parseData("crid_type 6 uimsbf ");
                    parseData("crid_location 2 uimsbf ");
                    if (contextGet("crid_location") == 0) {
                        parseData("crid_length 8 uimsbf ");
                        // Use dynamic context length parse buffer
                        parseBytes("crid_byte " + (contextGet("crid_length")) * 8 + " uimsbf ");
                    }
                    if (contextGet("crid_location") == 1) {
                        parseData("crid_ref 16 uimsbf ");
                    }
                }
                popElement();
            }
            popNode("OBJECTs16");
        }
    }

    public String getName() {
        return "content_identifier_descriptor";
    }

}
