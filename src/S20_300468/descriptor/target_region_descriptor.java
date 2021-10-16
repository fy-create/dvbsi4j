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

public class target_region_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimsbf ");
        parseData("descriptor_length 8 uimsbf ");
        parseData("descriptor_tag_extension 8 uimsbf ");
        parseData("country_code 24 bslbf ");
        {
            pushNode("regions");
            int _position6 = getBufferLen();
            while (getToken() < _position6) // N
            {
                pushElement();
                {
                    parseData("reserved 5 uimsbf ");
                    parseData("country_code_flag 1 bslbf ");
                    parseData("region_depth 2 bslbf ");
                    if (contextGet("country_code_flag") == 1) {
                        parseData("country_code 24 bslbf ");
                    }
                    if (contextGet("region_depth") >= 1) {
                        parseData("primary_region_code 8 bslbf ");
                        if (contextGet("region_depth") >= 2) {
                            parseData("secondary_region_code 8 bslbf ");
                            if (contextGet("region_depth") == 3) {
                                parseData("tertiary_region_code 16 bslbf ");
                            }
                        }
                    }
                }
                popElement();
            }
            popNode("regions");
        }
    }

    public String getName() {
        return "target_region_descriptor";
    }

}
