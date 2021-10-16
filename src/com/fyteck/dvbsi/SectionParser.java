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
package com.fyteck.dvbsi;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class SectionParser { //
    private static final Logger log = Logger.getLogger(SectionParser.class);
    private byte[]              buffer;
    private int                 bufferLen;
    private int                 token;
    private int                 bitUsed;
    private int                 validBitPos;
    private int                 needsBits;
    private int                 allNeedsBits;

    public SectionParser() {
        super();
        token        = 0;
        bitUsed      = 0;
        validBitPos  = 0;
        needsBits    = 0;
        allNeedsBits = 0;
    }

    public void reset() { //
        token        = 0;
        bitUsed      = 0;
        validBitPos  = 0;
        needsBits    = 0;
        allNeedsBits = 0;
        buffer       = null;
        bufferLen    = 0;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public int getBufferLen() {
        return bufferLen;
    }

    public void setBufferLen(int bufferLen) {
        this.bufferLen = bufferLen;
    }

    public Object parseData(int bitused) throws Exception { //
        return parseData(bitused, true, false);
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public Object parseData(String syntax) throws Exception { //
        List<String> list    = StringUtil.string2List(syntax, " ");
        // 1.varname
        // 2.bitused
        // 3.sequency not used
        int          bitused = Integer.parseInt(list.get(1));      // 2.bitused
        return parseData(bitused, true, false);
    }

    public Object parseData(int bitused, boolean parseBuffer, boolean skip) throws Exception { //
        Object obj = null;
        needsBits    = bitused;
        // System.out.print(token + " " + validBitPos + " " + needsBits);
        allNeedsBits = validBitPos + needsBits;
        if (parseBuffer) {
            if (bitused == 0) {
                return null;
            }
            if (allNeedsBits <= 8) {
                short _short = (short) (//
                (buffer[token] & ((0x1 << (8 - validBitPos)) - 1)) >> (8 - needsBits - validBitPos)//
                );
                _short &= 0x00FF;
                obj     = new Integer(_short);
            } else if (allNeedsBits <= 16) {
                int _int = (//
                (buffer[token] & 0xff) << 8) | //
                        (buffer[token + 1] & 0xFF);
                _int = (_int & ((0x1 << (16 - validBitPos)) - 1)) >> (16 - needsBits - validBitPos);
                obj  = new Integer(_int);
            } else if (allNeedsBits <= 32) {
                long _long = 0;
                if (allNeedsBits <= 24) {
                    //
                    _long = ((buffer[token] & 0xFF) << 24) | //
                            ((buffer[token + 1] & 0xFF) << 16) | //
                            ((buffer[token + 2] & 0xFF) << 8); //
                } else {
                    //
                    //
                    _long = ((buffer[token] & 0xFF) << 24) | //
                            ((buffer[token + 1] & 0xFF) << 16) | //
                            ((buffer[token + 2] & 0xFF) << 8) | //
                            ((buffer[token + 3] & 0xFF) & 0xFF);
                }
                _long = (_long & ((0x1L << (32 - validBitPos)) - 1)) >> (32 - needsBits - validBitPos);
                obj   = new Long(_long);
            } else {// copy buffer
                byte[] byteBuffer = new byte[bitused / 8];
                System.arraycopy(buffer, token, byteBuffer, 0, bitused / 8);
                obj = byteBuffer;
            }
        } else {
            if (!skip) {
                byte[] byteBuffer = new byte[bitused / 8];
                try {
                    System.arraycopy(buffer, token, byteBuffer, 0, bitused / 8);
                } catch (Exception e) {
                    throw e;
                }
                obj = byteBuffer;
            }
        }

        bitUsed     += needsBits;
        token        = bitUsed >> 3;
        validBitPos  = bitUsed - (token << 3);
        return obj;
    }

    public void printCurrent() { //
        log.info("Length=" + bufferLen + " Token=" + token + " bitused=" + bitUsed + "(" + bitUsed / 8 + ")"//
                + " validBitPos=" + validBitPos);
    }

    public boolean isEnd() {
        return (token >= this.bufferLen);
    }

    public void rewindBits(int bits) {
        bitUsed     -= bits;
        token        = bitUsed >> 3;
        validBitPos  = bitUsed - (token << 3);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SectionParser [buffer=");
        builder.append(Arrays.toString(buffer));
        builder.append(", bufferLen=");
        builder.append(bufferLen);
        builder.append(", token=");
        builder.append(token);
        builder.append(", bitUsed=");
        builder.append(bitUsed);
        builder.append(", validBitPos=");
        builder.append(validBitPos);
        builder.append(", needsBits=");
        builder.append(needsBits);
        builder.append(", allNeedsBits=");
        builder.append(allNeedsBits);
        builder.append("]");
        return builder.toString();
    }
}
