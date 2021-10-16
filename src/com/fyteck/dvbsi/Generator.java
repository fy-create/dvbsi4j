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

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Generator {
    private static final Logger       log                 = Logger.getLogger(Generator.class);

    private static final StringBuffer registerClassNameSB = new StringBuffer();
    private static final List<String> bagNames            = new ArrayList<String>();
    private static int                lengthIndex         = 0;

    public static void generatorSyntax(String rootDir, boolean toFile) {
        List<String> fileList      = new ArrayList<String>();
        String       syntaxRootDir = rootDir + File.separator + "syntax" + File.separator + "input";
        FileUtil.ListDirectory(new File(syntaxRootDir), fileList);
        String errFileName = null;
        try {
            for (String fileName : fileList) {
                if (!(fileName.endsWith("_descriptor") || fileName.endsWith("_section"))) {
                    continue;
                }

                log.info("Process file:" + fileName);
                errFileName = fileName;
                parseFile(new File(fileName), toFile);
            }

            File   SyntaxConst_temp = new File("./src/com/fyteck/dvbsi/SyntaxConst.temp");
            // System.out.println(SyntaxConst_temp.getAbsolutePath());
            String content          = FileUtil.readFileToStringBuffer(SyntaxConst_temp.getAbsolutePath()).toString();
            content = content.replaceAll("/\\* \\$registClassName\\$ \\*/", registerClassNameSB.toString());

            File SyntaxConst_java = new File("./src/com/fyteck/dvbsi/SyntaxConst.java");
            FileUtil.writeStringToFile(SyntaxConst_java.getAbsolutePath(), content);
            // System.out.println(content);

        } catch (Exception e) {
            System.out.println("Parse " + errFileName + "Error\r\n" + e.getMessage());
        }
    }

    public static String parseFile(File file, boolean toFile) throws Exception {
        List<String> stringList = FileUtil.readFileToList(file.getAbsolutePath());

        // process call
        for (int i = 0; i < stringList.size(); i++) {
            String sourceSyntax = stringList.get(i);
            if (sourceSyntax.indexOf("CALL ") > 0) {// (str.indexOf("KEY_CALL ") > 0
                String       syntaxPath = ReplaceUtil.replace(sourceSyntax, "CALL ", "").trim(); //
                File         syntaxFile =                                                        //
                        new File("." + File.separator + "syntax" + File.separator + "input"      //
                                + File.separator + syntaxPath.replace(".", File.separator));

                // log.info(syntaxFile.getAbsolutePath());
                List<String> list       = FileUtil.readFileToList(syntaxFile.getAbsolutePath());
                StringBuffer sb         = new StringBuffer();
                for (int k = 1; k < list.size() - 1; k++) {
                    sb.append(list.get(k) + "\n");
                }
                String newBufferSyntax = sourceSyntax.replaceAll("CALL.*", sb.toString());
                stringList.set(i, newBufferSyntax);
            }
        }

        StringBuffer sbSource = new StringBuffer();
        for (String item : stringList) {
            sbSource.append(item + "\n");
        }

        InputStream                    in     = new ByteArrayInputStream(sbSource.toString().getBytes());
        ANTLRInputStream               input  = new ANTLRInputStream(in);
        BitStreamLexer                 lexer  = new BitStreamLexer(input);
        CommonTokenStream              tokens = new CommonTokenStream(lexer);
        BitStreamParser                parser = new BitStreamParser(tokens);
        BitStreamParser.program_return root;
        root = parser.program();
        List<String> errors = parser.getErrors();
        if (errors.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < errors.size(); i++) {
                sb.append(errors.get(i) + "\n");
            }
            throw new Exception(sb.toString());
        }
        CommonTree tree       = root.tree;
        // log.info("Tree=" + tree.toStringTree());

        String     treeSyntax = walker(tree);
        String     outFile    = file.getAbsolutePath().replaceAll("syntax.*input", "src") + ".java";
        outFile = outFile.replaceAll("-", "_");

        // log.info("treeSyntax=" + treeSyntax.toString());
        String javaCode = genJava(file, outFile, treeSyntax);

        if (toFile) {
            FileUtil.writeStringToFile(outFile, javaCode);
        }
        return javaCode;// return java code
    }

    private static String walker(Tree root) {
        if (root.getChildCount() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < root.getChildCount(); i++) {
            Tree child = root.getChild(i);
            pretyPrint(sb, child.toStringTree().trim());
        }

        return sb.toString();
    }

    private static void pretyPrint(StringBuffer sb, String str) {
        int    number = 0;
        char[] s      = str.toCharArray();
        for (int i = 0; i < s.length; i++) {
            char c = s[i];
            if (c == '(') {
                number++;
            }
            if (c == ')') {
                number--;
            }
            if (c == '}') {
                sb.append('\n');
                for (int j = 0; j < number; j++) {
                    sb.append("  ");
                }
            }
            if (c == '(' && number > 0) {
                sb.append('\n');
                for (int j = 0; j < number; j++) {
                    sb.append("  ");
                }
            }
            if (c != '(' && c != ')') {
                sb.append(c);
            }
        }
    }

    /**
     * Process KEY BUFFER
     */
    private static void preProcessSyntax(List<String> strings) {
        int stringsSize = strings.size();
        for (int i = 0; i < stringsSize; i++) {
            String sourceSyntax = strings.get(i);
            if (sourceSyntax.indexOf("KEY_FOR ") > 0) {// Found KEY_FOR
                if (strings.get(i + 3).indexOf("KEY_POP") > 0) {// Found KEY BUFFER
                    String identfySyntax = strings.get(i + 2);
                    String identfy       = StringUtil.string2List(identfySyntax, " ").get(1);
                    // KEY_FOR_START NEWLINE KEY_FOR N KEY_PUSH
                    String length        = StringUtil.string2List(strings.get(i), " ").get(3);

                    if (length.equalsIgnoreCase("N") || length.equalsIgnoreCase("M")) {// Unknow name
                        String       lastLengthIndicator = strings.get(i - 1);
                        List<String> _list               = StringUtil.string2List(lastLengthIndicator, " ");
                        if (_list != null && _list.size() > 1) {
                            String _length = StringUtil.string2List(lastLengthIndicator, " ").get(1);
                            if (_length.endsWith("_length")) {
                                length = _length;
                                // log.debug(strings.get(i - 1));
                            }

                        }
                    }
                    String newBufferSyntax = sourceSyntax.replaceAll("KEY_FOR.*", "KEY_BYTE_BUFFER " + identfy + " " + length);
                    strings.set(i, newBufferSyntax);
                    strings.set(i + 1, ""/* +strings.get(i+1) */);
                    strings.set(i + 2, ""/* +strings.get(i+2) */);
                    strings.set(i + 3, ""/* +strings.get(i+3) */);
                }
            }
        }
    }

    private static String genJava(File infile, String outFile, String treeSyntax) {
        StringBuffer sb         = new StringBuffer(treeSyntax);
        String       idKey      = null;
        String       idValue    = null;
        String       syntaxName = null;
        List<String> strings    = StringUtil.string2List(sb.toString(), "\n", null, false);
        if (strings.size() <= 0) {
            return null;
        }
        String identify = strings.get(0).replaceAll("-", "_");

        // has tag=0xXXX or table_id=0xXX
        if (identify.indexOf("KEY_IDENT") != -1) {
            String idInfo = identify.substring(0, identify.indexOf("NEWLINE"));
            identify = identify.substring(identify.indexOf("NEWLINE") + "NEWLINE".length());
            List<String> idInfoList = StringUtil.string2List(idInfo, " ");
            idKey   = idInfoList.get(1);
            idValue = idInfoList.get(2);
            // log.info("process descriptor tag=" + idValue + "\t" + infile.getName());
        }
        syntaxName = identify;

        StringBuffer genCode      = new StringBuffer();

        StringBuffer syntax       = new StringBuffer();
        /* Append source file syntax */
        boolean      genRawSyntax = false;
        if (genRawSyntax) {
            List<String> sourceList = FileUtil.readFileToList(infile.getAbsolutePath());
            syntax = new StringBuffer("public String getSyntax(){\n");
            syntax.append("StringBuffer sb = new StringBuffer();\n");
            for (String line : sourceList) {
                genCode.append("//" + line + "\n");
                syntax.append("sb.append(\"" + line + "\\n\");\n");
            }
            syntax.append("return sb.toString();\n");
            syntax.append("}\n");

            syntax.append("public  String getIdInfo(){\n");
            syntax.append("StringBuffer sb = new StringBuffer();\n");
            if (idKey == null || idValue == null) {
                syntax.append("sb.append(\"No ID,maybe is section\");");
            } else {
                syntax.append("sb.append(\"" + idKey + "=" + idValue + "\");");
            }
            syntax.append("return sb.toString();\n");
            syntax.append("}\n");
        }

        syntax.append("public  String getName(){\n");
        syntax.append("return \"" + syntaxName + "\";\n");
        syntax.append("}\n");

        // log.info(syntax.toString());

        preProcessSyntax(strings);
        int     stringsSize;

        boolean addSyntaxInCode = false;
        if (addSyntaxInCode) {
            stringsSize = strings.size();
            for (int i = 0; i < stringsSize; i++) {
                if (strings.get(i).length() > 0) {
                    genCode.append("//" + strings.get(i) + "\n");
                }
            }
        }

        String packageName = outFile.replaceAll(".*src", "")//
                .replaceAll(identify + ".*", "")//
                .replaceAll("\\" + File.separator, "\\ ").trim()//
                .replaceAll(" ", "\\.");

        genCode.append("package " + packageName + ";\n"//
                + "import com.fyteck.dvbsi.CommonParser;\n"//
                + "public class " + identify + " extends CommonParser{" + "\n"//
                + "public void parse(byte[] descriptBuffer,int len) throws Exception"//
        );
        // class name
        strings.set(0, genCode.toString());
        /* add parse function */
        strings.add(2, "super.parse(descriptBuffer, len);");

        // add syntax method
        strings.add(syntax.toString());
        strings.add("}");

        if (strings.size() <= 1) {
            return null;
        }
        stringsSize = strings.size();
        for (int i = 0; i < stringsSize; i++) {
            String getSyntaxStr = strings.get(i);
            if (getSyntaxStr.indexOf("public String getSyntax") >= 0) {
                strings.add(i - 1, "setParseFlag(true);");
                break;
            }
        }

        sb          = new StringBuffer(strings.get(0));
        stringsSize = strings.size();
        for (int i = 1; i < stringsSize; i++) {
            String str = strings.get(i);
            if (str.trim().length() > 0) {
                sb.append(genJavaSingleLine(strings, i) + "\n");
            }
        }

        // Here is java source code buffer
        // log.info(sb.toString());

        String resourceName  = outFile.replaceAll(".*src", "")     //
                .replaceAll("\\" + File.separator, "\\ ").trim()   //
                .replaceAll(" ", "\\.")                            //
                .replaceAll(".java", "");

        int    descriptorTag = -1;
        if (idKey != null && idValue != null && idKey.equals("tag")) {
            try {
                descriptorTag = Integer.parseInt(idValue.replaceAll("0x", ""), 16);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        // tag is descriptor tag ,-1 mean no tag
        String javaCode = sb.toString();
        SyntaxFactory.registClassName(descriptorTag, resourceName);
        registerClassNameSB.append("SyntaxFactory.registClassName(" //
                + descriptorTag + "\t,\"" + resourceName + "\");\n");
        return javaCode;
    }

    private static String genJavaSingleLine(List<String> strings, int i) {
        String str = strings.get(i);

        if (str.indexOf("KEY_FOR_START ") > 0) {
            List<String> stringsList = StringUtil.string2List(str, " ", null, false);
            String       bagname     = null;
            if (stringsList != null && stringsList.size() > 1) {
                bagname = stringsList.get(1);
                /* no bag name assigned */
                if (bagname.equalsIgnoreCase("NEWLINE")) {
                    bagname = null;
                }
            }
            if (bagname == null) {
                bagname = "OBJECTs" + (lengthIndex++);
            }
            bagNames.add(bagname);
            str = str.replaceAll(bagname, "")//
                    .replaceAll("KEY_FOR_START", "{pushNode(\"" + bagname + "\"); ");
        }

        if (str.indexOf("KEY_FOR_END ") > 0) {
            String bagname = bagNames.get(bagNames.size() - 1);
            str = str.replaceAll("KEY_FOR_END", "popNode(\"" + bagname + "\"); }");
            bagNames.remove(bagNames.size() - 1);
        }

        if (str.indexOf("NEWLINE ") > 0) {
            str = str.replaceAll("NEWLINE", "\n");
        }

        if (str.indexOf("KEY_FOR ") > 0) {
            if (i > 0) {
                // check this syntax has length indicator
                List<String> _list              = StringUtil.string2List(str, " ");
                boolean      hasLengthIndicator = false;
                String       lengthIndicator    = null;
                if (_list != null && _list.size() >= 2) {
                    /* Sample: [{pushNode("programs");, KEY_FOR, N, KEY_PUSH] */
                    lengthIndicator    = _list.get(2);

                    hasLengthIndicator = lengthIndicator.indexOf('N') < 0;
                }

                /**
                 * <code>
                 * Last is length syntax
                 * section_length-12
                 * int section_length_bytes = contextGet("section_length") - 12 + getToken();
                 * while (getToken() < section_length_bytes) // section_length
                 * </code>
                 */

                String lastSyntax = strings.get(i - 1);
                if (hasLengthIndicator) {
                    String uuid         = getUUID();
                    String lengthPosStr = ReplaceUtil.replace(lengthIndicator, "([a-z|_|A-Z]*?)_length", "contextGet(\"$1_length\")"); //
                    lengthPosStr  = "int " + uuid + "=(" + lengthPosStr + ")" + " + getToken();\n";
                    lengthPosStr += "while (getToken() < " + uuid + ") //";
                    str           = str.replaceAll("KEY_FOR ", lengthPosStr);

                } else if (lastSyntax.indexOf("length") > 0) {
                    /* has length indicator */
                    String lengthVar = StringUtil.string2List(lastSyntax, " ").get(1);
                    str = str.replaceAll("KEY_FOR ", /***/
                            "int " + lengthVar + "_bytes = contextGet(\"" + lengthVar + "\")+getToken();\n" /***/
                                    + "while (getToken() < " + lengthVar + "_bytes ) //");
                } else {
                    /* Search has CRC code */
                    boolean hasCRC      = false;
                    int     StringsSize = strings.size();
                    for (int j = i; j < StringsSize; j++) {
                        if (strings.get(j).indexOf("KEY_DEF CRC_32") > 0) {
                            hasCRC = true;
                            break;
                        }
                    }
                    if (hasCRC) {
                        String positionVar = "_position" + (lengthIndex++) + "_";
                        str = str.replaceAll("KEY_FOR ", //
                                "int " + positionVar + " = getBufferLen() - 4 /*4 bytes CRC32*/;\n" //
                                        + "while (getToken() < " + positionVar + ")//");
                    } else {
                        String positionVar = "_position" + (lengthIndex++);
                        str = str.replaceAll("KEY_FOR ", //
                                "int " + positionVar + " = getBufferLen() ;\n" //
                                        + "while (getToken() < " + positionVar + " ) //");
                    }
                    str = str.replaceAll("KEY_FOR ", "while (tempb)//TODO ");//
                }
            }
        }

        if (str.indexOf("KEY_BYTE_BUFFER ") > 0) {
            List<String> list = StringUtil.string2List(str, " ");
            if (list != null && list.size() >= 3) {
                String identify  = list.get(1);
                String lengthExp = list.get(2);

                String newSyntax = null;
                if (lengthExp.equalsIgnoreCase("N")) {// Unknow name
                    newSyntax  = "//Use dynamic length parse buffer \n";
                    newSyntax += "parseBytes(\"" + identify + " \" + (getBufferLen() - getToken())* 8 + \" uimsbf \");";
                } else {
                    String newString = ReplaceUtil.replace(lengthExp, "(.*?)_length", "contextGet(\"$1_length\")");//
                    newSyntax  = "//Use dynamic context length parse buffer \n";
                    newSyntax += "parseBytes(\"" + identify + " \"+( " + newString + " ) * 8 + \" uimsbf \");";
                }
                str = str.replaceAll("KEY_BYTE_BUFFER .*", newSyntax);
            }
        }

        if (str.indexOf("KEY_FOR_DESCRIPTOR ") > 0) {
            if (i > 0) {
                /* Last is length syntax */
                String lastSyntax = strings.get(i - 1);
                if (lastSyntax.indexOf("_length") > 0) {
                    String lengthVar = StringUtil.string2List(lastSyntax, " ").get(1);
                    str = str.replaceAll("KEY_FOR_DESCRIPTOR .*", /***/
                            "//Process DESCRIPTOR\n" //
                                    + "{\n" //
                                    + "    int " + lengthVar + "_bytes = contextGet(\"" + lengthVar + "\");\n" /***/
                                    + "    byte[] buffer = parseData(" + lengthVar + "_bytes * 8 " + ", false, false);\n"/***/
                                    + "    parseDescriptors(\"descriptors\",buffer);//\n" /***/
                                    + "}\n" //

                    );
                } else {
                    /* Search has CRC code */
                    boolean hasCRC      = false;
                    int     stringsSize = strings.size();
                    for (int j = i; j < stringsSize; j++) {
                        if (strings.get(j).indexOf("KEY_DEF CRC_32") > 0) {
                            hasCRC = true;
                            break;
                        }
                    }
                    String CRC;
                    if (hasCRC) {
                        CRC = " -4 /*-4 bytes for CRC32*/";
                    } else {
                        CRC = "";
                    }
                    String lengthVar = "_guessLen" + (lengthIndex++) + "_";
                    str = str.replaceAll("KEY_FOR_DESCRIPTOR .*", /***/
                            "//Process DESCRIPTOR\n" //
                                    + "{\n" //
                                    + "    int " + lengthVar + " = getBufferLen() - getToken() " + CRC + ";\n"/***/
                                    + "    byte[] buffer = parseData(" + lengthVar + " * 8 " + ", false, false);\n"/***/
                                    + "    parseDescriptors(\"descriptors\",buffer);//\n" /***/
                                    + "}"/***/
                    );
                }

            }
        }

        if (str.indexOf("KEY_WHILE ") > 0) {
            str = str.replaceAll("KEY_WHILE ", "while ");
        }

        if (str.indexOf("KEY_BREAK ") > 0) {
            str = str.replaceAll("KEY_BREAK ", "popElement();break; ");
        }

        if (str.indexOf("KEY_REWIND ") > 0) {
            str  = ReplaceUtil.replace(str, "KEY_REWIND (.*?)", "rewindBits $1 ");//
            str += ";";
        }

        if (str.indexOf("KEY_IF ") > 0) {
            str = str.replaceAll("KEY_IF ", "if ");
        }
        if (str.indexOf("KEY_ELSEIF ") > 0) {
            str = str.replaceAll("KEY_ELSEIF ", "else if");
        }
        if (str.indexOf("KEY_ELSE ") > 0) {
            str = str.replaceAll("KEY_ELSE ", "else");
        }
        if (str.indexOf("KEY_DEF ") > 0) {
            str = str.replaceAll("KEY_DEF ", "parseData(\"") + "\");";
        }
        if (str.indexOf("KEY_FCT ") > 0) {
            str = str.replaceAll("KEY_FCT ", "//TODO call fucntion ") + "();";//
        }
        if (str.indexOf("KEY_LB ") > 0) {
            str = str.replaceAll("KEY_LB ", "(");
        }
        if (str.indexOf("KEY_RB ") > 0) {
            str = str.replaceAll("KEY_RB ", ")");
        }

        if (str.indexOf("KEY_LOGIC ") > 0) {
            str = str.replaceAll("KEY_LOGIC ", "");
            List<String> stringsList = StringUtil.string2List(str, " ", null, false);
            String       key         = stringsList.get(0);
            str = str.replaceAll(key, "contextGet(\"" + key + "\")");
        }

        if (str.indexOf("KEY_PUSH ") > 0) {
            str = str.replaceAll("KEY_PUSH ", "\n{\n pushElement();");
        }

        if (str.indexOf("KEY_POP ") > 0) {
            str = str.replaceAll("KEY_POP ", "\n popElement();\n}");
        }

        if (str.indexOf("KEY_LOOP_INDICATOR ") > 0) {
            str = str.replaceAll("KEY_LOOP_INDICATOR ", "//TODO KEY_LOOP_INDICATOR ");// TODO
        }

        if (str.indexOf("BAG_START ") > 0) {
            List<String> list = StringUtil.string2List(str, " ");
            if (list != null && list.size() >= 2) {
                String bagName = list.get(1);
                str = str.replaceAll("BAG_START .*", "pushNode(\"" + bagName + "\"); ");
            }
        }

        if (str.indexOf("BAG_END ") > 0) {
            List<String> list = StringUtil.string2List(str, " ");
            if (list != null && list.size() >= 2) {
                String bagName = list.get(1);
                str = str.replaceAll("BAG_END .*", "popNode(\"" + bagName + "\"); ");
            }
        }

        if (str.indexOf("KEY_NODE_START ") > 0) {
            str  = ReplaceUtil.replace(str, "KEY_NODE_START ", "pushNode (\"").trim();//
            str += "\");";
        }

        if (str.indexOf("KEY_NODE_END ") > 0) {
            str  = ReplaceUtil.replace(str, "KEY_NODE_END ", "popNode (\"").trim();//
            str += "\");";
        }

        return str;
    }

    private static int dynamicId = 0;

    public static String getUUID() {
//        return "_" + UUID.randomUUID().toString().replace("-", "_");
        return "_dynamic_" + dynamicId++;
    }
}
