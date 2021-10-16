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

import com.fyteck.dvbsi.BitStream;
import com.fyteck.dvbsi.StringUtil;
import com.fyteck.dvbsi.TSPacket;
import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class TunerFromTSFile {
    /* 100*PACKAGE_LEN */
    public static final int     BATCH_PROCESS_BUFFER_SIZE = 10 * BitStream.PACKAGE_LEN;
    private static final Logger log                       = Logger.getLogger(TunerFromTSFile.class);
    private final BitStream     bitStream;

    public TunerFromTSFile(BitStream bitStream) {
        super();
        this.bitStream = bitStream;
    }

    private int isValidTSPackage(byte[] header) {
        if (header == null) {
            return -1;
        }
        int pid  = 0;
        int head = StringUtil.byteArrayToInt(header, 0);
        pid = (((head >> 8) << 11 >> 11)) & 0x1fff;

        if (bitStream.getPIDFilter().inPidFilter(pid)) {
            return pid;
        }

        return -1;// invalid pid
    }

    /**
     * @param data       : 10 packages buffer
     * @param count      : real valid data length
     * @param totalCount
     * @return
     */
    private int decodeTSpackage(byte[] data, int count, long totalCount) {
        int retToken = 0;
        if (data == null) {
            return -1;
        }
        int      pid                 = 0;
        int      token               = 0;
        int      realValidDataLength = count;
        byte[]   header              = new byte[4];
        TSPacket tsPackage           = null;
        byte[]   rawData             = null;

        while (token < realValidDataLength) {
            /* found the SYNC byte */
            if (data[token] == (byte) BitStream.HEAD_SYNC_BYTE) {
                if (token + 4 >= realValidDataLength) {
                    break;
                }
                System.arraycopy(data, token, header, 0, 4);

                /* get PID from header */
                pid = this.isValidTSPackage(header);

                /* check is valid PID */
                if (pid >= 0) {
                    tsPackage = new TSPacket(bitStream);
                    /* rawData is PACKAGE_LEN package buffer */
                    rawData   = tsPackage.getRawData();

                    /* clear package buffer */
                    Arrays.fill(rawData, (byte) 0x0);

                    /* from current token has valid package data,copy TS package data to rawData.
                     * parse TS package header information. set new token */
                    if (realValidDataLength - token >= BitStream.PACKAGE_LEN) {
                        System.arraycopy(data, token, rawData, 0, BitStream.PACKAGE_LEN);
                        tsPackage.parseHeader();

                        // log.info("positionOffset=" + (positionOffset + token) + "\t token=" + token);
                        token += BitStream.PACKAGE_LEN;

                        /* try to push TS package to section manager,switch thread context */
                        bitStream.getSectionManager().addTSPackage(tsPackage);

                    } else {
                        // log.info("packet not complete!");
                        retToken  = token;
                        token    += realValidDataLength - token;
                        break;
                    }
                } else {
                    token += BitStream.PACKAGE_LEN;
                }

            } else {
                token++;
            }
        }
        return retToken;
    }

    public void decodeTSFile(File file, long maxCount) throws Exception {
        long                start            = System.currentTimeMillis();

        /* alloc 10 packages data buffer */
        byte[]              data             = new byte[BATCH_PROCESS_BUFFER_SIZE];

        byte[]              unprocessedData  = new byte[BitStream.PACKAGE_LEN];
        BufferedInputStream bis              = null;
        int                 unprocessedToken = 0;

        bis = new BufferedInputStream(new FileInputStream(file));
        int  count      = 0;
        long totalCount = 0;
        log.info("using file max length:" + maxCount + " Byte");

        int readOffset = 0;
        /* start with offset 0,read 10 * PACKAGE_LEN data (10 packages) */
        while ((count = bis.read(data, readOffset, BATCH_PROCESS_BUFFER_SIZE - readOffset)) > 0) {
            totalCount += count;
            if (totalCount >= maxCount) {
                break;
            }

            /* data is 10 packages buffer,count is real valid data length */
            unprocessedToken = decodeTSpackage(data, count, totalCount);
            if (unprocessedToken > 0) {
                /* copy unprocessed bytes to unprocessedData */
                Arrays.fill(unprocessedData, (byte) 0x0);
                System.arraycopy(data, unprocessedToken, unprocessedData, 0, count - unprocessedToken);
            }

            Arrays.fill(data, (byte) 0x0);

            if (unprocessedToken > 0) {
                System.arraycopy(unprocessedData, 0, data, 0, count - unprocessedToken);
                readOffset = unprocessedToken;
            } else {
                readOffset = 0;
            }

            /**
             * decode file is very fast,here delay 1ms for section manager has time to
             * process. <BR>
             * for example: PMT PID is reference by PAT, after PAT decoded PMT PID filter
             * can be set.
             */
//			Thread.sleep(1);

        }
        bis.close();

        long end = System.currentTimeMillis();
        log.info("read file finished,cost=" + (end - start) + " ms");

        /* stop section manager thread */
        bitStream.getSectionManager().stop();
    }

}
