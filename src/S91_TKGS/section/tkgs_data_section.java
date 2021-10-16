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
package S91_TKGS.section;

import com.fyteck.dvbsi.CommonParser;

public class tkgs_data_section extends CommonParser {
    public void parse(byte[] descriptBuffer, int len) throws Exception {
        super.parse(descriptBuffer, len);
        pushNode("tkgs_info");
        parseData("reserved 4 bslbf ");
        parseData("tkgs_info_descriptors_length 12 uimbf ");
        // Process DESCRIPTOR
        {
            int    tkgs_info_descriptors_length_bytes = contextGet("tkgs_info_descriptors_length");
            byte[] buffer                             = parseData(tkgs_info_descriptors_length_bytes * 8, false, false);
            parseDescriptors("descriptors", buffer);//
        }

        popNode("tkgs_info");
        parseData("reserved 4 bslbf ");
        parseData("broadcast_source_loop_length 20 uimsbf ");
        {
            pushNode("broadcast_source_loops");
            int _dynamic_5 = (contextGet("broadcast_source_loop_length")) + getToken();
            while (getToken() < _dynamic_5) // broadcast_source_loop_length
            {
                pushElement();
                {
                    parseData("broadcast_source_descriptors_length 8 uimsbf ");
                    // Process DESCRIPTOR
                    {
                        int    broadcast_source_descriptors_length_bytes = contextGet("broadcast_source_descriptors_length");
                        byte[] buffer                                    = parseData(broadcast_source_descriptors_length_bytes * 8, false, false);
                        parseDescriptors("descriptors", buffer);//
                    }

                    parseData("reserved 4 bslbf ");
                    parseData("transport_stream_loop_length 20 uimsbf ");
                    {
                        pushNode("transport_streams");
                        int _dynamic_6 = (contextGet("transport_stream_loop_length")) + getToken();
                        while (getToken() < _dynamic_6) // transport_stream_loop_length
                        {
                            pushElement();
                            {
                                parseData("transport_stream_descriptors_length 8 uimbsf ");
                                // Process DESCRIPTOR
                                {
                                    int    transport_stream_descriptors_length_bytes = contextGet("transport_stream_descriptors_length");
                                    byte[] buffer                                    = parseData(transport_stream_descriptors_length_bytes * 8, false,
                                            false);
                                    parseDescriptors("descriptors", buffer);//
                                }

                                {
                                    pushNode("service_lists");
                                    int _position14_ = getBufferLen() - 4 /* 4 bytes CRC32 */;
                                    while (getToken() < _position14_)// N
                                    {
                                        pushElement();
                                        {
                                            parseData("read_sdt_table_id 8 uimsbf ");
                                            rewindBits(8);
                                            if (contextGet("read_sdt_table_id") == 0x42) {
                                                parseData("table_id 8 uimsbf ");
                                                parseData("section_syntax_indicator 1 bslbf ");
                                                parseData("reserved_future_use 1 bslbf ");
                                                parseData("reserved 2 bslbf ");
                                                parseData("section_length 12 uimsbf ");
                                                parseData("transport_stream_id 16 uimsbf ");
                                                parseData("reserved 2 bslbf ");
                                                parseData("version_number 5 uimsbf ");
                                                parseData("current_next_indicator 1 bslbf ");
                                                parseData("section_number 8 uimsbf ");
                                                parseData("last_section_number 8 uimsbf ");
                                                parseData("original_network_id 16 uimsbf ");
                                                parseData("reserved_future_use 8 bslbf ");
                                                {
                                                    pushNode("services");
                                                    int _dynamic_7 = (contextGet("section_length") - 12) + getToken();
                                                    while (getToken() < _dynamic_7) // section_length-12
                                                    {
                                                        pushElement();
                                                        {
                                                            parseData("service_id 16 uimsbf ");
                                                            parseData("reserved_future_use 6 bslbf ");
                                                            parseData("EIT_schedule_flag 1 bslbf ");
                                                            parseData("EIT_present_following_flag 1 bslbf ");
                                                            parseData("running_status 3 uimsbf ");
                                                            parseData("free_CA_mode 1 bslbf ");
                                                            parseData("descriptors_loop_length 12 uimsbf ");
                                                            // Process DESCRIPTOR
                                                            {
                                                                int    descriptors_loop_length_bytes = contextGet("descriptors_loop_length");
                                                                byte[] buffer                        = parseData(descriptors_loop_length_bytes * 8,
                                                                        false, false);
                                                                parseDescriptors("descriptors", buffer);//
                                                            }

                                                        }
                                                        popElement();
                                                    }
                                                    popNode("services");
                                                }
                                                parseData("CRC_32 32 rpchof ");
                                            } else {
                                                popElement();
                                                break;

                                            }
                                        }
                                        popElement();
                                    }
                                    popNode("service_lists");
                                }
                                parseData("program_loop_length 16 uimbsf ");
                                {
                                    pushNode("programs");
                                    int _dynamic_8 = (contextGet("program_loop_length")) + getToken();
                                    while (getToken() < _dynamic_8) // program_loop_length
                                    {
                                        pushElement();
                                        {
                                            parseData("reserved 3 uimsbf ");
                                            parseData("tkgs_service_locator 13 uimsbf ");
                                            parseData("table_id 8 uimsbf ");
                                            parseData("section_syntax_indicator 1 bslbf ");
                                            parseData("noused 1 bslbf ");
                                            parseData("reserved 2 bslbf ");
                                            parseData("section_length 12 uimsbf ");
                                            parseData("program_number 16 uimsbf ");
                                            parseData("reserved 2 bslbf ");
                                            parseData("version_number 5 uimsbf ");
                                            parseData("current_next_indicator 1 bslbf ");
                                            parseData("section_number 8 uimsbf ");
                                            parseData("last_section_number 8 uimsbf ");
                                            parseData("reserved 3 bslbf ");
                                            parseData("PCR_PID 13 uimsbf ");
                                            parseData("reserved 4 bslbf ");
                                            parseData("program_info_length 12 uimsbf ");
                                            // Process DESCRIPTOR
                                            {
                                                int    program_info_length_bytes = contextGet("program_info_length");
                                                byte[] buffer                    = parseData(program_info_length_bytes * 8, false, false);
                                                parseDescriptors("descriptors", buffer);//
                                            }

                                            {
                                                pushNode("streams");
                                                int _dynamic_9 = (contextGet("section_length") - contextGet("program_info_length") - 4 - 9)
                                                        + getToken();
                                                while (getToken() < _dynamic_9) // section_length-program_info_length-4-9
                                                {
                                                    pushElement();
                                                    {
                                                        parseData("stream_type 8 uimsbf ");
                                                        parseData("reserved 3 bslbf ");
                                                        parseData("elementary_PID 13 uimsnf ");
                                                        parseData("reserved 4 bslbf ");
                                                        parseData("ES_info_length 12 uimsbf ");
                                                        // Process DESCRIPTOR
                                                        {
                                                            int    ES_info_length_bytes = contextGet("ES_info_length");
                                                            byte[] buffer               = parseData(ES_info_length_bytes * 8, false, false);
                                                            parseDescriptors("descriptors", buffer);//
                                                        }

                                                    }
                                                    popElement();
                                                }
                                                popNode("streams");
                                            }
                                            parseData("CRC_32 32 rpchof ");
                                        }
                                        popElement();
                                    }
                                    popNode("programs");
                                }
                            }
                            popElement();
                        }
                        popNode("transport_streams");
                    }
                }
                popElement();
            }
            popNode("broadcast_source_loops");
        }
        parseData("reserved 4 bslbf ");
        pushNode("service_list_loop");
        parseData("service_list_loop_length 20 uimsbf ");
        {
            pushNode("service_lists");
            int _dynamic_10 = (contextGet("service_list_loop_length")) + getToken();
            while (getToken() < _dynamic_10) // service_list_loop_length
            {
                pushElement();
                {
                    parseData("service_list_descriptors_length 16 uimsbf ");
                    // Process DESCRIPTOR
                    {
                        int    service_list_descriptors_length_bytes = contextGet("service_list_descriptors_length");
                        byte[] buffer                                = parseData(service_list_descriptors_length_bytes * 8, false, false);
                        parseDescriptors("descriptors", buffer);//
                    }

                }
                popElement();
            }
            popNode("service_lists");
        }
        popNode("service_list_loop");
        pushNode("category_loop");
        parseData("category_loop_length 16 uimsbf ");
        // Process DESCRIPTOR
        {
            int    category_loop_length_bytes = contextGet("category_loop_length");
            byte[] buffer                     = parseData(category_loop_length_bytes * 8, false, false);
            parseDescriptors("descriptors", buffer);//
        }

        popNode("category_loop");
        parseData("reserved 16 bslbf ");
    }

    public String getName() {
        return "tkgs_data_section";
    }

}
