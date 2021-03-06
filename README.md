### License
dvbsi4j uses dual license, GPL or commercial license.


### Introduction
dvbsi4j is an efficient and scalable Java-based software SDK. This will make the analysis of PSI SI of digital TV more simple and intuitive. RD can be liberated from the heavy PSI SI table analysis and only need to focus on business logic .


### Features
#### Scalable SDK
dvbsi4j can simply analyze the general syntax in DTV SPEC and generate SDK.
Current support bellow DTV SPEC,You can simply extend it.
* 13818
* [ETSI EN 300 468](http://www.etsi.org/deliver/etsi_en/300400_300499/300468/01.11.01_60/en_300468v011101p.pdf)
* [ETSI TS 102 809](http://www.etsi.org/deliver/etsi_ts/102800_102899/102809/01.01.01_60/ts_102809v010101p.pdf)
* [ETSI TS 102 812](http://www.etsi.org/deliver/etsi_ts/102800_102899/102812/01.02.01_60/ts_102812v010201p.pdf)
* DTG D-Book
* [ETSI TR 101 202](http://www.etsi.org/deliver/etsi_tr/101200_101299/101202/01.02.01_60/tr_101202v010201p.pdf)
* Polsat_stb
* DVB-SI Extension For TURKSAT


Please see [DVB Syntax](https://github.com/fy-create/dvbsi4j/tree/main/syntax/input) for detail.
Here is sample for create SDK [GeneratorSDK](https://github.com/fy-create/dvbsi4j/blob/main/src/com/fyteck/dvbsi/sample/GeneratorSDK.java)


#### Customize PID/ Table ID filter
User can customize the PID filters easily for runtime usage.

[dvbConfig.json](https://github.com/fy-create/dvbsi4j/blob/main/dvbConfig.json) is a configuration file in json format, you can configure PID Table ID filter here, and you can change it through  API at runtime.
please reference [DecodePATPMT.java ](https://github.com/fy-create/dvbsi4j/blob/main/src/com/fyteck/dvbsi/sample/DecodePATPMT.java)for detail, this example show how to set PMT PID filter after receive PAT.


### Simple API to access Section/Descriptor data


```Java
/**
 * <code>
 * program_association_section () {                         - ❶ root node
 *     table_id                        8       uimsbf  
 *     section_syntax_indicator        1       bslbf   
 *     noused                          1       bslbf   
 *     reserved                        2       bslbf   
 *     section_length                  12      uimsbf  
 *     transport_stream_id             16      uimsbf  
 *     reserved                        2       bslbf   
 *     version_number                  5       uimsbf  
 *     current_next_indicator          1       bslbf   
 *     section_number                  8       uimsbf  
 *     last_section_number             8       uimsbf  
 *     
 *     for (i=0; i<N;i++) /*programs* / {                   - ❷ default named programs
 *                                                               user can change it in syntax
 *         program_number              16      uimsbf       - ❸
 *         reserved                    3       bslbf   
 *         if(program_number == 0x0) {
 *             network_PID             13      uimsbf  
 *         }
 *         else {
 *             program_map_PID         13      uimsbf       - ❹
 *         }
 *     }
 *     
 *     CRC_32                          32      rpchof  
 * }
 * </code>
 */

log.info("\n" + section.dumpTextResult());
log.info("\n" + section.dumpSectionRawPacket());
List<NodeValue> root     = (List<NodeValue>) section.getRoot();      /* ❶ */

List<NodeValue> programsNodeList = TSUtil.getObjectByName(root, "programs"); /* ❷ */

/** loop each program in programs */
for (NodeValue _programNode : programsNodeList) {
    List<NodeValue> program = _programNode.getValue();
    int     program_number = TSUtil.getObjectByName(program, "program_number"); /* ❸ */

    if (program_number != 0) {
        int program_map_PID = TSUtil.getObjectByName(program, "program_map_PID"); /** ❹ */

        /** dynamic set PMT PID filter */
        section.getBitStream().getPIDFilter().addPidFilter(program_map_PID, "PMT pid=>" + program_map_PID);
    }
}

Sample text result from DecodePATPMT.java :
DecodePATPMT:88  
table_id                                          0          [0x0         ]
section_syntax_indicator                          1          [0x1         ]
noused                                            0          [0x0         ]
reserved                                          3          [0x3         ]
section_length                                    37         [0x25        ]
transport_stream_id                               32778      [0x800A      ]
reserved                                          3          [0x3         ]
version_number                                    5          [0x5         ]
current_next_indicator                            1          [0x1         ]
section_number                                    0          [0x0         ]
last_section_number                               0          [0x0         ]
programs                                                                      /* ❷ */
  program                                         
    program_number                                0          [0x0         ]
    reserved                                      7          [0x7         ]
    network_PID                                   16         [0x10        ]
  program                                         
    program_number                                33984      [0x84C0      ]
    reserved                                      7          [0x7         ]
    program_map_PID                               1020       [0x3FC       ]
  program                                         
    program_number                                33472      [0x82C0      ]
    reserved                                      7          [0x7         ]
    program_map_PID                               1030       [0x406       ]
  program                                         
    program_number                                34048      [0x8500      ]
    reserved                                      7          [0x7         ]
    program_map_PID                               1040       [0x410       ]
  program                                         
    program_number                                33664      [0x8380      ]
    reserved                                      7          [0x7         ]
    program_map_PID                               1060       [0x424       ]
  program                                         
    program_number                                32842      [0x804A      ]
    reserved                                      7          [0x7         ]
    program_map_PID                               1010       [0x3F2       ]
  program                                         
    program_number                                34112      [0x8540      ]
    reserved                                      7          [0x7         ]
    program_map_PID                               1070       [0x42E       ]
CRC_32                                            3893584241 [0xE8136171  ]
```

### Sample
[Sample](https://github.com/fy-create/dvbsi4j/tree/main/src/com/fyteck/dvbsi/sample) 
