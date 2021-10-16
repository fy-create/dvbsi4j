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

public class SH_delivery_system_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimsbf ");
        parseData("descriptor_tag_extension 8 uimsbf ");
        parseData("descriptor_length 8 uimsbf ");
        parseData("diversity_mode 4 bslbf ");
        parseData("reserved 4 bslbf ");
        {
            pushNode("modulations");
            int _position8 = getBufferLen();
            while (getToken() < _position8) // N
            {
                pushElement();
                {
                    parseData("modulation_type 1 bslbf ");
                    parseData("interleaver_presence 1 bslbf ");
                    parseData("interleaver_type 1 bslbf ");
                    parseData("Reserved 5 bslbf ");
                    if (contextGet("modulation_type") == 0) {
                        parseData("Polarization 2 bslbf ");
                        parseData("roll_off 2 bslbf ");
                        parseData("modulation_mode 2 bslbf ");
                        parseData("code_rate 4 bslbf ");
                        parseData("symbol_rate 5 bslbf ");
                        parseData("Reserved 1 bslbf ");
                    } else {
                        parseData("bandwidth 3 bslbf ");
                        parseData("priority 1 bslbf ");
                        parseData("constellation_and_hierarchy 3 bslbf ");
                        parseData("code_rate 4 bslbf ");
                        parseData("guard_interval 2 bslbf ");
                        parseData("transmission_mode 2 bslbf ");
                        parseData("common_frequency 1 bslbf ");
                    }
                    if (contextGet("interleaver_presence") == 1) {
                        if (contextGet("interleaver_type") == 0) {
                            parseData("common_multiplier 6 uimsbf ");
                            parseData("nof_late_taps 6 uimsbf ");
                            parseData("nof_slices 6 uimsbf ");
                            parseData("slice_distance 8 uimsbf ");
                            parseData("non_late_increments 6 uimsbf ");
                        } else {
                            parseData("common_multiplier 6 uimsbf ");
                            parseData("reserved 2 uimsbf ");
                        }
                    }
                }
                popElement();
            }
            popNode("modulations");
        }
    }

    public String getName() {
        return "SH_delivery_system_descriptor";
    }

}
