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

public class image_icon_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimsbf ");
        parseData("descriptor_length 8 uimsbf ");
        parseData("descriptor_tag_extension 8 uimsbf ");
        parseData("descriptor_number 4 uimsbf ");
        parseData("last_descriptor_number 4 uimsbf ");
        parseData("reserved_future_use 5 uimsbf ");
        parseData("icon_id 3 uimsbf ");
        if (contextGet("descriptor_number") == 0x00) {
            parseData("icon_transport_mode 2 uimsbf ");
            parseData("position_flag 1 bslbf ");
            if (contextGet("position_flag") == 0x01) {
                parseData("coordinate_system 3 uimsbf ");
                parseData("reserved_future_use 2 bslbf ");
                parseData("icon_horizontal_origin 12 uimsbf ");
                parseData("icon_vertical_origin 12 uimsbf ");
            } else {
                parseData("reserved_future_use 5 bslbf ");
            }
            parseData("icon_type_length 8 uimsbf ");
            // Use dynamic context length parse buffer
            parseBytes("icon_type_char " + (contextGet("icon_type_length")) * 8 + " uimsbf ");
            if (contextGet("icon_transport_mode") == 0x00) {
                parseData("icon_data_length 8 uimsbf ");
                // Use dynamic context length parse buffer
                parseBytes("icon_data_byte " + (contextGet("icon_data_length")) * 8 + " uimsbf ");
            } else if (contextGet("icon_transport_mode") == 0x01) {
                parseData("url_length 8 uimsbf ");
                // Use dynamic context length parse buffer
                parseBytes("url_char " + (contextGet("url_length")) * 8 + " uimsbf ");
            }
        } else {
            parseData("icon_data_length 8 uimsbf ");
            // Use dynamic context length parse buffer
            parseBytes("icon_data_byte " + (contextGet("icon_data_length")) * 8 + " uimsbf ");
        }
    }

    public String getName() {
        return "image_icon_descriptor";
    }

}
