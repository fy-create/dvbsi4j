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
package S91_TKGS.descriptor;

import com.fyteck.dvbsi.CommonParser;

public class multilingual_category_label_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimsbf ");
        parseData("descriptor_length 8 uimsbf ");
        parseData("reserved 4 bslbf ");
        parseData("category_id 12 uimsbf ");
        {
            pushNode("languages");
            int _dynamic_14 = (contextGet("descriptor_length") - 2) + getToken();
            while (getToken() < _dynamic_14) // descriptor_length-2
            {
                pushElement();
                {
                    parseData("ISO_639_language_code 24 bslbf ");
                    parseData("name_length 8 uimsbf ");
                    // Use dynamic context length parse buffer
                    parseBytes("char " + (contextGet("name_length")) * 8 + " uimsbf ");
                }
                popElement();
            }
            popNode("languages");
        }
    }

    public String getName() {
        return "multilingual_category_label_descriptor";
    }

}
