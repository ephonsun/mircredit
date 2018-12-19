/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-10-18
 */
package com.banger.mobile.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanme
 * @version $Id: HanziUtil.java,v 0.1 2012-10-18 下午5:23:30 Administrator Exp $
 */
public class HanziUtil {
    private static final String[] HANZI_NUMBER       = { "一", "二", "三", "四", "五", "六", "七", "八",
            "九"                                     };
    private static final String[] HANZI_RATE_NUMBER  = { "十", "百", "千", "万" };
    private static final int[]    ARABIC_NUMBER      = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private static final int[]    ARABIC_RATE_NUMBER = { 10, 100, 1000, 10000 };

    public static int compare(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return 0;
        }

        if (s1 != null && s2 == null) {
            return 1;
        }

        if (s1 == null && s2 != null) {
            return -1;
        }

        if (s1.equals(s2)) {
            return 0;
        }

        List<String> p1 = spiltPart(s1);
        List<String> p2 = spiltPart(s2);
        int max = p1.size() > p2.size() ? p1.size() : p2.size();
        for (int i = 0; i < max; i++) {
            if (i >= p1.size()) {
                return -1;
            }

            if (i >= p2.size()) {
                return 1;
            }

            String t1 = p1.get(i);
            String t2 = p2.get(i);
            if (t1.equals(t2)) {
                continue;
            }
            String tn1 = parseHanziNumber(t1);
            String tn2 = parseHanziNumber(t2);
            if (tn1 != null && tn2 != null) {
                int tni1 = hanzi2arabic(tn1);
                int tni2 = hanzi2arabic(tn2);
                if (tni1 > tni2) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                if (t1.compareTo(t2) > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

        return 0;
    }

    private static List<String> spiltPart(String s) {
        List<String> r = new ArrayList<String>();
        String temp = null;
        boolean isNumber = false;
        for (int i = 0; i < s.length(); i++) {
            String ns = s.substring(i, i + 1);
            boolean stillNumber = false;
            if (containStr(HANZI_NUMBER, ns) != -1 || containStr(HANZI_RATE_NUMBER, ns) != -1) {
                stillNumber = true;
            }

            if (temp == null) {
                temp = ns;
                isNumber = stillNumber;
            } else {
                if (isNumber != stillNumber) {
                    r.add(temp);
                    temp = ns;
                    isNumber = stillNumber;
                } else {
                    temp += ns;
                }
            }
        }
        if (temp != null) {
            r.add(temp);
        }
        return r;
    }

    public static int hanzi2arabic(String s) {
        String temp = "";

        if (s.startsWith(HANZI_RATE_NUMBER[0])) {
            s = HANZI_NUMBER[0] + s;
        }

        int i = 0;
        while (i < s.length()) {
            String a = s.substring(i, i + 1);
            int m = containStr(HANZI_NUMBER, a);
            int n = containStr(HANZI_RATE_NUMBER, a);
            if (n != -1) {
            } else {
                temp += ARABIC_NUMBER[m];
            }
            i++;
        }

        if (temp.length() == 0) {
            return 0;
        }

        int r = Integer.parseInt(temp);
        String last = s.substring(s.length() - 1, s.length());
        int lastN = containStr(HANZI_RATE_NUMBER, last);
        if (lastN != -1) {
            r = r * ARABIC_RATE_NUMBER[lastN];
        }
        return r;
    }

    public static String parseHanziNumber(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        String r = "";
        int i = 0;
        String a = s.substring(i, i + 1);
        int m = containStr(HANZI_NUMBER, a);
        int n = containStr(HANZI_RATE_NUMBER, a);
        while (m != -1 || n != -1) {
            if (m != -1) {
                r += HANZI_NUMBER[m];
            } else {
                r += HANZI_RATE_NUMBER[n];
            }
            i++;
            if (i < s.length()) {
                a = s.substring(i, i + 1);
                m = containStr(HANZI_NUMBER, a);
                n = containStr(HANZI_RATE_NUMBER, a);
            } else {
                break;
            }
        }

        if (r == "") {
            return null;
        } else {
            return r;
        }
    }

    private static int containStr(String[] source, String target) {
        if (source == null || source.length == 0 || target == null) {
            return -1;
        }
        for (int i = 0; i < source.length; i++) {
            if (source[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}
