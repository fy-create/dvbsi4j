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

public class terrestrial_delivery_system_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimsbf ");
        parseData("descriptor_length 8 uimsbf ");
        parseData("centre_frequency 32 uimsbf ");
        parseData("bandwidth 3 bslbf ");
        parseData("priority 1 bslbf ");
        parseData("Time_Slicing_indicator 1 bslbf ");
        parseData("MPE-FEC_indicator 1 bslbf ");
        parseData("reserved_future_use 2 bslbf ");
        parseData("constellation 2 bslbf ");
        parseData("hierarchy_information 3 bslbf ");
        parseData("code_rate-HP_stream 3 bslbf ");
        parseData("code_rate-LP_stream 3 bslbf ");
        parseData("guard_interval 2 bslbf ");
        parseData("transmission_mode 2 bslbf ");
        parseData("other_frequency_flag 1 bslbf ");
        parseData("reserved_future_use 32 bslbf ");
    }

    public String getName() {
        return "terrestrial_delivery_system_descriptor";
    }

}
