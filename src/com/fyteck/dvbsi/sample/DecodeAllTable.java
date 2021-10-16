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
package com.fyteck.dvbsi.sample;

import com.fyteck.dvbsi.*;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Sample: Parse TS file
 *
 * @author liubin
 */
public class DecodeAllTable {
    private static final Logger log           = Logger.getLogger(DecodeAllTable.class);
    protected BitStream         bitStream     = null;
    protected TunerFromTSFile   fileDecode    = null;

    protected ISectionNotify    sectionNotify = /***/
            new ISectionNotify() {
                @Override
                public void sectionNotify(TSSection section) {
                    log.info(section.getTableName());

                    /** should switch thread context */
                    log.info("\n" + section.dumpTextResult());
                }
            };

    public static void main(String[] args) {
        DecodeAllTable decodeAllTable = new DecodeAllTable();
        decodeAllTable.init();
        decodeAllTable.bitStream.setSectionNotify(decodeAllTable.sectionNotify);
        decodeAllTable.decodeTSFile(new File("./dvbtest.ts"));
    }

    protected void init() {
        /* initial DVB PID TID configure */
        String configFilePath = new File(".").getAbsolutePath();
        String configFile     = configFilePath + File.separator + "dvbConfig.json";
        GlobalConfig.dvbConfig = DVBConfig.checkConfig(new File(configFile));

        /* initial section and descriptor syntax */
        SyntaxConst.init();

        /* initial bit stream */
        bitStream  = new BitStream();

        fileDecode = new TunerFromTSFile(bitStream);
    }

    protected void decodeTSFile(File file) {
        if (fileDecode != null) {
            try {
                fileDecode.decodeTSFile(file, 50 * 1024 * 1024);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
