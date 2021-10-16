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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

class SectionManagerThread extends Thread {
    private static final Logger                   log            = Logger.getLogger(SectionManagerThread.class);
    private final ConcurrentLinkedQueue<TSPacket> tsPackageQueue = /***/
            new ConcurrentLinkedQueue<TSPacket>();
    private volatile boolean                      stop           = false;

    public SectionManagerThread(String name) {
        setName(name);
    }

    public void addTSPackage(TSPacket tsPacket) {
        /* switch thread context */
        if (tsPacket != null) {
            tsPackageQueue.add(tsPacket);
        }
    }

    @Override
    public void run() {
        super.run();

        while (!stop || tsPackageQueue.size() > 0) {
            TSPacket tsPacket = tsPackageQueue.poll();
            if (tsPacket != null) {
                log.debug("Receive tspackage=" + tsPacket);
                tsPacket.tryMakeSection();
            } else {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setStop() {
        stop = true;
    }

}

public class SectionManager {
    private static final Logger                 log                  = Logger.getLogger(SectionManager.class);
    private final List<TSSection>               sectionList          = new ArrayList<TSSection>();
    private final Map<Integer, TSSection>       pidSection           = new HashMap<Integer, TSSection>();
    private final Map<Integer, List<TSSection>> tableidSection       = new HashMap<Integer, List<TSSection>>();
    private final BitStream                     bitStream;
    private SectionManagerThread                sectionManagerThread = null;

    public SectionManager(BitStream ts) {
        this.bitStream       = ts;

        sectionManagerThread = new SectionManagerThread("SecThread");
        sectionManagerThread.start();
    }

    /**
     * Parse section
     *
     * @param section
     */
    private void parseSection(TSSection section) {
        TableDecode     tableParser = bitStream.getTableDecode();

        int             tableId     = section.getTable_id();
        List<TSSection> sections    = tableidSection.get(tableId);
        if (sections == null) {
            sections = new ArrayList<TSSection>();
            tableidSection.put(tableId, sections);
        }
        try {
            tableParser.parse(section);
        } catch (Exception e) {
            log.info(section.dumpTextResult());
            log.info(section.dumpSectionData());
            e.printStackTrace();
        }

        /* for success parse section put into sectionList */
        sections.add(section);

        if (bitStream != null && bitStream.getSectionNotify() != null) {
            bitStream.getSectionNotify().sectionNotify(section);
        }

    }

    public void addTSPackage(TSPacket tsPackage) {
        if (sectionManagerThread != null) {
            sectionManagerThread.addTSPackage(tsPackage);
        }
    }

    public void sectionStartPacket(int pid, TSPacket tsPacket) {
        TSSection section = new TSSection(bitStream, pid);
        try {
            section.addTsPacket(tsPacket, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int pos = sectionList.indexOf(section);
        if (pos >= 0) {
            /* Section already in list */
            TSSection findSection = sectionList.get(pos);

            /* Not parsed ,maybe section data not enough,maybe parse error */
            if (findSection.isHasParse() == false) {
                sectionList.remove(pos);
                List<TSSection> tableIdSections = tableidSection.get(section.getTable_id());
                if (tableIdSections != null) {
                    tableIdSections.remove(section);
                }
            } else {
                section.dispose();
                section = null;
                return;
            }
        }

        /* One Section should be transfer over before an other section start */
        pidSection.put(pid, section);
        section.setPid(pid);

        /* Section transfer over,parse section here */

        if (section.getGot_private_section_length() >= section.getSection_length() //
                && section.getSection_length() > 0) {
            log.debug(section.getGot_private_section_length() + "\t" //
                    + section.getSection_length() + "\t" + section);

            /* Parse section data */
            parseSection(section);

            /* parse section successful , put it into sectionList */
            sectionList.add(section);

            pidSection.remove(pid);
        }
    }

    public void sectionAppendPacket(int pid, TSPacket tsPacket, boolean lastPacketWithPointOfField) {
        TSSection section = pidSection.get(pid);
        if (section != null) {
            try {
                section.addTsPacket(tsPacket, lastPacketWithPointOfField);
            } catch (Exception e) {
                e.printStackTrace();
            }

            /* Section transfer finished,parse section */
            if (section.getGot_private_section_length() >= section.getSection_length() //
                    && section.getSection_length() > 0) {

                log.debug(section.getGot_private_section_length() + "\t" //
                        + section.getSection_length() + "\t" + section);

                int pos = sectionList.indexOf(section);
                if (pos >= 0) {
                    /* section already in list */
                    TSSection findSection = sectionList.get(pos);

                    /* not parsed ,maybe section data not enough,maybe parse error */
                    if (findSection.isHasParse() == false) {
                        sectionList.remove(pos);
                        List<TSSection> tableIdSections = tableidSection.get(section.getTable_id());
                        if (tableIdSections != null) {
                            tableIdSections.remove(section);
                        }
                    } else {
                        section.dispose();
                        section = null;
                        return;
                    }
                }

                parseSection(section);

                /* parse section success ,put into sectionList */
                sectionList.add(section);

                pidSection.remove(pid);
                // log.info("End section");
            }
        }
    }

    public String sectionBreifInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("Total section number(No duplicator):" + sectionList.size() + "\t");
        return sb.toString();
    }

    public List<TSSection> getSectionsBytableId(Integer tableId) {
        return tableidSection.get(tableId);
    }

    public void dumpSections(boolean withDetail) {
        int sectionListSize = sectionList.size();
        for (int i = 0; i < sectionListSize; i++) {
            TSSection section = sectionList.get(i);
            dumpSection(section, withDetail);
        }
    }

    public void dumpSection(TSSection section, boolean withDetail) {
        if (section == null) {
            return;
        }

        log.info(section.briefTimeInfo());

        if (section.getTableName() == null) {
            log.error("Not transfer over\t" + section);
            return;
        }

        if (withDetail) {
            StringBuffer sb = new StringBuffer(section.getTableName() + "\n");
            if (section != null) {
                sb.append(section.dumpTextResult());
                sb.append(section.dumpSectionData());
                sb.append(section.dumpSectionRawPacket());
            }
            log.info(sb.toString());
        }
    }

    public void deleteSection(TSSection section) {
        sectionList.remove(section);
        List<TSSection> tableIdSections = tableidSection.get(section.getTable_id());
        if (tableIdSections != null) {
            tableIdSections.remove(section);
        }

        section.dispose();
    }

    public void dispose() {
        pidSection.clear();
        tableidSection.clear();

        for (TSSection sec : sectionList) {
            sec.dispose();
        }
        sectionList.clear();
    }

    public void stop() {
        sectionManagerThread.setStop();
    }
}
