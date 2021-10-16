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
            int _1b674687_7e19_4dc6_84bd_1331a0e4c1d4 = (contextGet("broadcast_source_loop_length")) + getToken();
            while (getToken() < _1b674687_7e19_4dc6_84bd_1331a0e4c1d4) // broadcast_source_loop_length
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
                        int _0a728d31_5755_4435_9f46_82253dbea8d0 = (contextGet("transport_stream_loop_length")) + getToken();
                        while (getToken() < _0a728d31_5755_4435_9f46_82253dbea8d0) // transport_stream_loop_length
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
                                                    int _3501e0e8_0a4a_4843_bfe2_628d35a741ea = (contextGet("section_length") - 12) + getToken();
                                                    while (getToken() < _3501e0e8_0a4a_4843_bfe2_628d35a741ea) // section_length-12
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
                                    int _eb336657_4a56_481e_97c1_92ca072cfe5b = (contextGet("program_loop_length")) + getToken();
                                    while (getToken() < _eb336657_4a56_481e_97c1_92ca072cfe5b) // program_loop_length
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
                                                int _eae14adf_2d81_491e_aae9_56f9d044198a = (contextGet("section_length")
                                                        - contextGet("program_info_length") - 4 - 9) + getToken();
                                                while (getToken() < _eae14adf_2d81_491e_aae9_56f9d044198a) // section_length-program_info_length-4-9
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
            int _797e2005_6a31_482f_8e13_38834223ae1e = (contextGet("service_list_loop_length")) + getToken();
            while (getToken() < _797e2005_6a31_482f_8e13_38834223ae1e) // service_list_loop_length
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
