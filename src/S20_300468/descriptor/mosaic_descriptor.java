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

public class mosaic_descriptor extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        parseData("descriptor_tag 8 uimsbf ");
        parseData("descriptor_length 8 uimsbf ");
        parseData("mosaic_entry_point 1 bslbf ");
        parseData("number_of_horizontal_elementary_cells 3 uimsbf ");
        parseData("reserved_future_use 1 bslbf ");
        parseData("number_of_vertical_elementary_cells 3 uimsbf ");
        {
            pushNode("logical_cells");
            int _position5 = getBufferLen();
            while (getToken() < _position5) // N
            {
                pushElement();
                {
                    parseData("logical_cell_id 6 uimsbf ");
                    parseData("reserved_future_use 7 bslbf ");
                    parseData("logical_cell_presentation_info 3 uimsbf ");
                    parseData("elementary_cell_field_length 8 uimsbf ");
                    {
                        pushNode("elementary_cell");
                        int _3c408a0c_f148_47fe_9a32_15cf39c093b4 = (contextGet("_field_length")) + getToken();
                        while (getToken() < _3c408a0c_f148_47fe_9a32_15cf39c093b4) // _field_length
                        {
                            pushElement();
                            {
                                parseData("reserved_future_use 2 bslbf ");
                                parseData("elementary_cell_id 6 uimsbf ");
                            }
                            popElement();
                        }
                        popNode("elementary_cell");
                    }
                    parseData("cell_linkage_info 8 uimsbf ");
                    if (contextGet("cell_linkage_info") == 0x01) {
                        parseData("bouquet_id 16 uimsbf ");
                    }
                    if (contextGet("cell_linkage_info") == 0x02) {
                        parseData("original_network_id 16 uimsbf ");
                        parseData("transport_stream_id 16 uimsbf ");
                        parseData("service_id 16 uimsbf ");
                    }
                    if (contextGet("cell_linkage_info") == 0x03) {
                        parseData("original_network_id 16 uimsbf ");
                        parseData("transport_stream_id 16 uimsbf ");
                        parseData("service_id 16 uimsbf ");
                    }
                    if (contextGet("cell_linkage_info") == 0x04) {
                        parseData("original_network_id 16 uimsbf ");
                        parseData("transport_stream_id 16 uimsbf ");
                        parseData("service_id 16 uimsbf ");
                        parseData("event_id 16 uimsbf ");
                    }
                }
                popElement();
            }
            popNode("logical_cells");
        }
    }

    public String getName() {
        return "mosaic_descriptor";
    }

}
