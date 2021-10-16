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

import java.util.*;

public class SyntaxFactory {
    /**
     * tag name list map
     */
    private static final Map<Integer, List<String>> tagClassNameMapping = new HashMap<Integer, List<String>>();

    /* name class map */
    private static final Map<String, Class<?>>      classPool           = new HashMap<String, Class<?>>();

    /* class instance map */
    private static final Map<Class<?>, Object>      classInstancePool   = new HashMap<Class<?>, Object>();

    private static synchronized void addTagResource(int tagValue, String resourceName) {
        /* Delete old tag reference */
        for (int _tag : tagClassNameMapping.keySet()) {
            List<String> list = tagClassNameMapping.get(_tag);
            if (list.indexOf(resourceName) != -1) {
                list.remove(resourceName);
                break;
            }
        }

        if (tagClassNameMapping.containsKey(tagValue)) {
            List<String> names = tagClassNameMapping.get(tagValue);
            if (!names.contains(resourceName)) {
                names.add(resourceName);
                Collections.sort(names);
            }
        } else {
            List<String> names = new ArrayList<String>();
            names.add(resourceName);
            tagClassNameMapping.put(tagValue, names);
        }
    }

    /**
     * @param discriptortTag : descriptor tag value, -1 mean is section
     * @param className      : full class name
     */
    public static synchronized void registClassName(int discriptortTag, String className) {
        // valid tag
        if (discriptortTag != -1) {
            addTagResource(discriptortTag, className);
        }

        Class<?> clazz = classPool.get(className);
        /* already has the class with resourceName,remove it first */
        if (clazz != null) {
            classPool.remove(className);
            classInstancePool.remove(clazz);
        }
    }

    public static synchronized Class<?> getClazz(String resourceName) throws ClassNotFoundException {
        if (resourceName == null) {
            return null;
        }
        Class<?> clazz = null;
        clazz = classPool.get(resourceName);
        if (clazz != null) {
            return clazz;
        } else {
            clazz = Class.forName(resourceName);
            classPool.put(resourceName, clazz);
        }

        return clazz;
    }

    public static Object getInstanceByClass(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        Object object = null;
        object = classInstancePool.get(clazz);
        if (object != null) {
            return object;
        }

        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        classInstancePool.put(clazz, object);
        return object;
    }

    public static synchronized List<Class<?>> getClassByTag(int tag) {
        List<Class<?>> clazzs = new ArrayList<Class<?>>();
        List<String>   names  = tagClassNameMapping.get(tag);
        if (names == null) {
            clazzs.add(null);
            return clazzs;
        }

        try {
            for (String resourceName : names) {
                clazzs.add(getClazz(resourceName));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return clazzs;
    }

    public static void resetAll() {
        tagClassNameMapping.clear();
        classPool.clear();
        classInstancePool.clear();
    }
}
