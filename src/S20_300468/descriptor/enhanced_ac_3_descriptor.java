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

public class enhanced_ac_3_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimsbf ");
        parseData("descriptor_length 8 uimsbf ");
        parseData("component_type_flag 1 bslbf ");
        parseData("bsid_flag 1 bslbf ");
        parseData("mainid_flag 1 bslbf ");
        parseData("asvc_flag 1 bslbf ");
        parseData("mixinfoexists 1 bslbf ");
        parseData("substream1_flag 1 bslbf ");
        parseData("substream2_flag 1 bslbf ");
        parseData("substream3_flag 1 bslbf ");
        if (contextGet("component_type_flag") == 1) {
            parseData("component_type 8 uimsbf ");
        }
        if (contextGet("bsid_flag") == 1) {
            parseData("bsid 8 uimsbf ");
        }
        if (contextGet("mainid_flag") == 1) {
            parseData("mainid 8 uimsbf ");
        }
        if (contextGet("asvc_flag") == 1) {
            parseData("asvc 8 bslbf ");
        }
        if (contextGet("substream1_flag") == 1) {
            parseData("substream1 8 uimsbf ");
        }
        if (contextGet("substream2_flag") == 1) {
            parseData("substream2 8 uimsbf ");
        }
        if (contextGet("substream3_flag") == 1) {
            parseData("substream3 8 uimsbf ");
        }
        // Use dynamic length parse buffer
        parseBytes("additional_info_byte " + (getBufferLen() - getToken()) * 8 + " uimsbf ");
    }

    public String getName() {
        return "enhanced_ac_3_descriptor";
    }

}
