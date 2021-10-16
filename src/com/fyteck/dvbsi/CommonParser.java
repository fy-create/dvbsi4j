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

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class CommonParser {
    private static final Logger    log        = Logger.getLogger(CommonParser.class);

    protected SectionParser        sp         = new SectionParser();
    private Stack<List<NodeValue>> valueStack = null;
    private TSSection              section    = null;

    public CommonParser() {
        super();
    }

    protected String getSyntax() {
        return null;
    }

    public String getIdInfo() {
        return null;
    }

    public abstract String getName();

    protected void setParseFlag(boolean flag) {
        if (section != null) {
            section.setHasParse(flag);
        }
    }

    public void setSection(TSSection section) {
        this.section = section;
    }

    public Stack<List<NodeValue>> getValueStack() {
        return valueStack;
    }

    public void setValueStack(Stack<List<NodeValue>> valueStack) {
        this.valueStack = valueStack;
    }

    public void reset() { //
        sp.reset();
    }

    private List<NodeValue> getCurrentParentList() {
        List<NodeValue> currentList = valueStack.lastElement();
        int             pos         = valueStack.indexOf(currentList);
        if (pos >= 1) {
            return valueStack.elementAt(pos - 1);
        }
        return null;
    }

    public List<NodeValue> getCurrentList() {
        List<NodeValue> currentList = valueStack.lastElement();
        return currentList;
    }

    protected int contextGet(String key) {
        int     size  = valueStack.size();
        boolean found = false;
        for (int i = size - 1; i >= 0; i--) {
            List<NodeValue> currentList = valueStack.elementAt(i);
            if (currentList != null) {
                for (NodeValue node : currentList) {
                    if (node.getName().equals(key)) {
                        found = true;
                        return NumberUtil.getIntValue(node.getValue());
                    }
                }
            }

            if (found == true) {
                break;
            }
        }
        if (found == false) {
            return -1;
        }
        return -1;
    }

    public void parse(byte[] descriptorBuffer, int len) throws Exception {
        sp.setBuffer(descriptorBuffer);
        sp.setBufferLen(len);
    }

    protected void parseData(String syntax) throws Exception {
        List<String> list    = StringUtil.string2List(syntax, " ");
        /**
         * <code>
         * 0.   variable name 
         * 1.   bits used 
         * 2.   xx
         * </code>
         */
        Object       obj     = null;
        int          bitused = Integer.parseInt(list.get(1));

        obj = sp.parseData(bitused, true, false);
        NodeValue       value       = new NodeValue(list.get(0), obj);
        List<NodeValue> currentList = getCurrentList();
        currentList.add(value);
    }

    protected void parseBytes(String syntax) throws Exception { //
        List<String>    list        = StringUtil.string2List(syntax, " ");
        /**
         * <code>
         * 0.   variable name 
         * 1.   bits used 
         * 2.   xx
         * </code>
         */

        int             bitused     = Integer.parseInt(list.get(1));
        NodeValue       value       = new NodeValue(list.get(0), null);
        List<NodeValue> currentList = getCurrentList();
        currentList.add(value);
        byte[] bytes = (byte[]) sp.parseData(bitused, false, false);
        value.setValue(bytes);

    }

    protected byte[] parseData(int bitused, boolean parseBuffer, boolean skip) throws Exception {
        if (bitused <= 0) {
            return null;
        }
        return (byte[]) sp.parseData(bitused, parseBuffer, skip);
    }

    protected int getToken() {
        return sp.getToken();
    }

    protected int getBufferLen() {
        return sp.getBufferLen();
    }

    protected void parseDescriptors(String name, byte[] buffer) throws Exception {
        if (buffer == null) {
            return;
        }
        List<NodeValue> currentList = getCurrentList();
        parseDescriptorsBuffer(currentList, name, buffer);
    }

    protected void pushNode(String bagName) { //
        List<NodeValue> valueList   = new ArrayList<NodeValue>();
        NodeValue       newNode     = new NodeValue(bagName, valueList);
        List<NodeValue> currentList = getCurrentList();
        currentList.add(newNode);
        valueStack.push(valueList);
    }

    protected void popNode(String bagName) {
        valueStack.pop();
    }

    protected void pushElement() {
        List<NodeValue> currentParentList = getCurrentParentList();
        List<NodeValue> currentList       = getCurrentList();
        String          bagName           = null;
        for (NodeValue node : currentParentList) {
            if (node.getValue() == currentList) {
                bagName = node.getName();
                break;
            }
        }

        if (bagName != null && bagName.endsWith("s")) {
            bagName = bagName.substring(0, bagName.length() - 1);
        } else {
            if (bagName != null) {
                bagName = bagName.replaceAll("s.*", "");
            }
        }
        List<NodeValue> valueList = new ArrayList<NodeValue>();
        NodeValue       newNode   = new NodeValue(bagName, valueList);
        currentList.add(newNode);
        valueStack.push(valueList);
    }

    protected void popElement() {
        valueStack.pop();
    }

    protected void rewindBits(int bits) {
        sp.rewindBits(bits);
    }

    protected List<NodeValue> parseDescriptorsBuffer(/***/
    List<NodeValue> currentList, String name, byte[] buffer) throws Exception {

        if (buffer == null || buffer.length < 2) {
            log.debug("buffer is null");
            return null;
        }
        List<Class<?>>  descriptorClasses;
        List<NodeValue> descriptors = new ArrayList<NodeValue>();
        NodeValue       newNode     = new NodeValue(name, descriptors);
        currentList.add(newNode);
        SectionParser sectionParser = new SectionParser();
        sectionParser.setBuffer(buffer);
        sectionParser.setBufferLen(buffer.length);

        int          descriptor_tag     = 0;
        int          descriptor_length  = 0;
        CommonParser descriptorInstance = null;

        while (!sectionParser.isEnd()) {
            descriptor_tag     = 0;
            descriptor_length  = 0;
            descriptorInstance = null;

            descriptor_tag     = buffer[sectionParser.getToken()] & 0xff;
            descriptor_length  = buffer[sectionParser.getToken() + 1] & 0xff;
            descriptorClasses  = SyntaxFactory.getClassByTag(descriptor_tag);

            /* Get single descriptors buffer data */
            byte[]    descriptorBuffer      = null;
            boolean   parseDescHasException = false;
            Exception parseDescException    = null;
            try {
                if (sectionParser.getToken() + (descriptor_length + 2) > buffer.length) {
                    log.error("Error:\t" + section.shortName() + "\tLen:" + //
                            StringUtil.formatString(section.getSection_length(), 5) + "\t" + //
                            "Descriptor:" + descriptorClasses + "\t Total len=" + buffer.length//
                            + "\tToken=" + sectionParser.getToken()//
                            + "\tNeed_buffer=" + (descriptor_length /* + 2 */));

                }

                descriptorBuffer = (byte[]) sectionParser.parseData(//
                        ((descriptor_length + 2)/* +2 mean add length for tag */) * 8, //
                        false, false);

            } catch (Exception e1) {
                log.info(e1.getMessage());
                e1.printStackTrace();
                parseDescHasException = true;
                parseDescException    = e1;
                // here do not throw
            }

            /* Try again using descriptor buffer as needed */
            if (parseDescHasException == true) {
                try {
                    /* -2 mean ignore tag length (2B) */
                    descriptor_length     = buffer.length - sectionParser.getToken() - 2;
                    parseDescHasException = true;

                    /* +2 mean add tag length (2B) */
                    descriptorBuffer      = (byte[]) sectionParser.parseData(((descriptor_length + 2)) * 8, false, false);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    throw e1;
                }
            }

            /* add null for UnknownDescriptor */
            descriptorClasses.add(null);
            Class<?> descriptorClass       = null;
            int      descriptorClassesSize = descriptorClasses.size();
            for (int i = 0; i < descriptorClassesSize; i++) {
                descriptorClass = descriptorClasses.get(i);
                String nodeName = null;
                if (descriptorClass == null) {
                    descriptorInstance = new UnknowDescriptor();
                    nodeName           = "UnknownDescriptor";
                } else {
                    /* using instance pool */
                    descriptorInstance = (CommonParser) SyntaxFactory.getInstanceByClass(descriptorClass);
                    nodeName           = descriptorInstance.getName();
                }

                /* descriptor_length: This 8-bit field specifies the total number of bytes of
                 * the data portion of the descriptor following the byte defining the value of
                 * this field. */

                try {
                    descriptorInstance.reset();
                    Stack<List<NodeValue>> valueStack = new Stack<List<NodeValue>>();
                    List<NodeValue>        valueList  = new ArrayList<NodeValue>();
                    valueStack.push(valueList);
                    descriptorInstance.setValueStack(valueStack);
                    log.debug("Parsing descriptor:" + StringUtil.formatString(descriptorInstance.getName(), 50) //
                            + "\t" + StringUtil.formatString(Integer.toHexString(descriptor_tag), 4)//
                            + "\tlength=" + (descriptor_length + 2));

                    /* +2 mean add tag length(2B) */
                    descriptorInstance.parse(descriptorBuffer, descriptor_length + 2);

                    /* Get parser result list */
                    List<NodeValue> values         = descriptorInstance.getCurrentList();
                    NodeValue       descriptorNode = null;

                    /* Create new node for single descriptor */
                    descriptorNode = new NodeValue(nodeName, values);

                    /* Add to descriptors list */
                    descriptors.add(descriptorNode);
                    break;
                } catch (Exception e) {
                    log.info("\t\t\tParse Error " + descriptorInstance.getName() + "\t" + descriptor_tag);
                    if (descriptor_tag == 0) {/* For wrong descriptor ,avoid dead loop ,just break it */
                        parseDescHasException = true;
                        break;
                    }

                    if (i == 0) {
                        /* Get parser result list */
                        List<NodeValue> values         = descriptorInstance.getCurrentList();
                        NodeValue       descriptorNode = null;

                        /* Create new node for single descriptor */
                        descriptorNode = new NodeValue(nodeName, values);

                        /* Add to descriptors list */
                        descriptors.add(descriptorNode);
                    }

                    if (i != descriptorClasses.size() - 1) {
                        continue;
                    }

                    log.info("descriptors parse error," + descriptorInstance);
                }
            }

            // last parse has exception
            if (parseDescHasException == true) {
                if (parseDescException != null) {
                    log.info(parseDescException.toString());
                    throw parseDescException;
                }
            }
        }
        return descriptors;
    }
}
