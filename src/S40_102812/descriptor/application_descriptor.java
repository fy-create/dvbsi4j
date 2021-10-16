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
package S40_102812.descriptor;

import com.fyteck.dvbsi.CommonParser;

public class application_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimsbf ");
        parseData("descriptor_length 8 uimsbf ");
        parseData("application_profiles_length 8 uimsbf ");
        {
            pushNode("application_profiles");
            int application_profiles_length_bytes = contextGet("application_profiles_length") + getToken();
            while (getToken() < application_profiles_length_bytes) // N
            {
                pushElement();
                {
                    parseData("application_profile 16 uimsbf ");
                    parseData("version_major 8 uimsbf ");
                    parseData("version_minor 8 uimsbf ");
                    parseData("version_micro 8 uimsbf ");
                }
                popElement();
            }
            popNode("application_profiles");
        }
        parseData("service_bound_flag 1 bslbf ");
        parseData("visibility 2 bslbf ");
        parseData("reserved_future_use 5 bslbf ");
        parseData("application_priority 8 uimsbf ");
        // Use dynamic length parse buffer
        parseBytes("transport_protocol_label " + (getBufferLen() - getToken()) * 8 + " uimsbf ");
    }

    public String getName() {
        return "application_descriptor";
    }

}
