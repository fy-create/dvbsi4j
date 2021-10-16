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

public class supplementary_audio_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimbsf ");
        parseData("descriptor_length 8 uimbsf ");
        parseData("descriptor_tag_extension 8 uimbsf ");
        parseData("mix_type 1 uimbsf ");
        parseData("editorial_classification 5 uimbsf ");
        parseData("reserved 1 uimbsf ");
        parseData("language_code_present 1 uimbsf ");
        if (contextGet("language_code_present") == 1) {
            parseData("ISO_639_language_code 24 bslbf ");
        }
        // Use dynamic length parse buffer
        parseBytes("private_data_byte " + (getBufferLen() - getToken()) * 8 + " uimsbf ");
    }

    public String getName() {
        return "supplementary_audio_descriptor";
    }

}
