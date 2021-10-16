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

public class network_change_notify_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimsbf ");
        parseData("descriptor_length 8 uimsbf ");
        parseData("descriptor_tag_extension 8 uimsbf ");
        {
            pushNode("cells");
            int _position9 = getBufferLen();
            while (getToken() < _position9) // N
            {
                pushElement();
                {
                    parseData("cell_id 16 uimsbf ");
                    parseData("loop_length 8 uimsbf ");
                    {
                        pushNode("networks");
                        int loop_length_bytes = contextGet("loop_length") + getToken();
                        while (getToken() < loop_length_bytes) // N
                        {
                            pushElement();
                            {
                                parseData("network_change_id 8 uimsbf ");
                                parseData("network_change_version 8 uimsbf ");
                                parseData("start_time_of_change 40 bslbf ");
                                parseData("change_duration 24 uimsbf ");
                                parseData("receiver_category 3 uimsbf ");
                                parseData("invariant_ts_present 1 uimsbf ");
                                parseData("change_type 4 uimsbf ");
                                parseData("message_id 8 uimsbf ");
                                if (contextGet("invariant_ts_present") == 1) {
                                    parseData("invariant_ts_tsid 16 uimsbf ");
                                    parseData("invariant_ts_onid 16 uimsbf ");
                                }
                            }
                            popElement();
                        }
                        popNode("networks");
                    }
                }
                popElement();
            }
            popNode("cells");
        }
    }

    public String getName() {
        return "network_change_notify_descriptor";
    }

}
