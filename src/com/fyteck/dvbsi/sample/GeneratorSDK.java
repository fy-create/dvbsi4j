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

import com.fyteck.dvbsi.Generator;

import java.io.File;

/**
 * Generator DVBSI SDK from Syntax,currently support bellow SPEC
 *
 * <code>
 * syntax
 * syntax/input
 * syntax/input/S10_13818
 * syntax/input/S20_300468
 * syntax/input/S30_102809
 * syntax/input/S40_102812
 * syntax/input/S50_dbook
 * syntax/input/S60_other
 * syntax/input/S70_101202
 * syntax/input/S80_polsat_stb
 * syntax/input/S90_Indonesia_EWS
 * syntax/input/S91_TKGS
 * <p>
 * create java source code
 * <p>
 * S10_13818.descriptor
 * S10_13818.section
 * S20_300468.descriptor
 * S20_300468.section
 * S30_102809.descriptor
 * S40_102812.descriptor
 * S40_102812.section
 * S50_dbook.descriptor
 * S60_other.descriptor
 * S60_other.section
 * S70_101202.section
 * S80_polsat_stb.section
 * S90_Indonesia_EWS.section
 * S91_TKGS.descriptor
 * S91_TKGS.section
 * </code>
 * <p>
 * You may format these code for pretty print.
 *
 * @author liubin
 */
public class GeneratorSDK {
    public static void main(String[] args) {
        String syntaxDir = new File(".").getAbsolutePath();
        Generator.generatorSyntax(syntaxDir, true);
    }
}
