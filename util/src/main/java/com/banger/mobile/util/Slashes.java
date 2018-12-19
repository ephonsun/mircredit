
package com.banger.mobile.util;

public class Slashes
{
    /**
     * 方法 replace 可以把源字符串中的指定字符串替换为其它字符串。
     * 
     * @param strSource
     *            替换前的字符串
     * @param strFrom
     *            将被替换的字符串
     * @param strTo
     *            将被替换成的字符串
     * @return 替换后的字符串
     */
    public static String replace(String strSource, String strFrom, String strTo)
    {
        String strDest = "";
        int intFromLen = strFrom.length();
        int intPos;

        while ((intPos = strSource.indexOf(strFrom)) != -1)
        {
            strDest = strDest + strSource.substring(0, intPos);
            strDest = strDest + strTo;
            strSource = strSource.substring(intPos + intFromLen);
        }

        strDest = strDest + strSource;

        return strDest;
    }

    /**
     * 方法 toHtml 可以把源字符串中的不能在网页中正确显示的 字符替换为可以显示的相应字符串。
     * 
     * @param strSource
     *            替换前的字符串
     * @return 替换后的字符串
     */
    public static String toHtml(String strSource)
    {
        char charInter = '\n';
        char charLt = '<';
        char charGt = '>';
        char charQuot = '"';
        char charAmp = '&';
        StringBuffer StrBufReturn = new StringBuffer();

        for (int i = 0; i < strSource.length(); i++)
        {
            if (strSource.charAt(i) == charInter)
            {
                StrBufReturn.append("<BR>");
            }
            else
                if (strSource.charAt(i) == charLt)
                {
                    StrBufReturn.append("<");
                }
                else
                    if (strSource.charAt(i) == charGt)
                    {
                        StrBufReturn.append(">");
                    }
                    else
                        if (strSource.charAt(i) == charQuot)
                        {
                            StrBufReturn.append("");
                        }
                        else
                            if (strSource.charAt(i) == charAmp)
                            {
                                StrBufReturn.append("&");
                            }
                            else
                            {
                                StrBufReturn.append(strSource.charAt(i));
                            }
        }

        return StrBufReturn.toString();
    }

    /**
     * 方法 ToHtml 为方法 toHtml 的重载。 目的为了兼容规范与前期设计时已使用过此方法。
     * 
     * @param strSource
     *            替换前的字符串
     * @return 替换后的字符串
     */
    public static String ToHtml(String strSource)
    {
        return toHtml(strSource);
    }

    /**
     * 方法 toText 可以把源字符串中的在网页中显示时的 特殊字符替换为其在数据库中本来的字符。
     * 
     * @param strSource
     *            替换前的字符串
     * @return 替换后的字符串
     */
    public static String toText(String strSource)
    {
        String[] strFromArray = new String[5];
        strFromArray[0] = ";";
        strFromArray[1] = "<br>";
        strFromArray[2] = "(";
        strFromArray[3] = ")";
        strFromArray[4] = "%";

        // strFromArray[0] = "&";
        // strFromArray[1] = """;
        // strFromArray[2] = ">";
        // strFromArray[3] = "<";
        String[] strToArray = new String[5];
        strToArray[0] = "";
        strToArray[1] = "";
        strToArray[2] = "(";
        strToArray[3] = ")";
        strToArray[4] = "%";

        // strToArray[0] = "&";
        // strToArray[1] = "\"";
        // strToArray[2] = ">";
        // strToArray[3] = "<";
        for (int i = 0; i < 0; i++)
        {
            String strFrom = strFromArray[i];
            String strTo = strToArray[i];
            String strDest = "";
            int intFromLen = strFrom.length();
            int intPos;

            while ((intPos = strSource.indexOf(strFrom)) != -1)
            {
                strDest = strDest + strSource.substring(0, intPos);
                strDest = strDest + strTo;
                strSource = strSource.substring(intPos + intFromLen);
            }

            strDest = strDest + strSource;
            strSource = strDest;
        }

        return strSource;
    }

    /**
     * 添加斜杠
     * 
     * @param inputStr
     *            添加前的字符串
     * @return 添加后的字符串
     */
    public static String addSlashes(String inputStr)
    {
        if (inputStr == null) { return ""; }

        inputStr = replace(inputStr, "'", "''");

        return inputStr;
    }

    /**
     * 移除斜杠
     * 
     * @param inputStr
     *            移除前的字符串
     * @return 移除后的字符串
     */
    public static String removeSlashes(String inputStr)
    {
        while (inputStr.indexOf("''") != -1)
            inputStr = replace(inputStr, "''", "'");

        return inputStr;
    }

    /**
     * 把文字中的回车替换成<br>
     * 
     * @param strSource
     *            替换前的字符串
     * @return 替换后的字符串
     */
    public static String replaceReturn(String strSource)
    {
        char charInter = '\n';
        StringBuffer StrBufReturn = new StringBuffer();

        for (int i = 0; i < strSource.length(); i++)
        {
            if (strSource.charAt(i) == charInter)
            {
                StrBufReturn.append("<BR>");
            }
            else
            {
                StrBufReturn.append(strSource.charAt(i));
            }
        }

        return StrBufReturn.toString();
    }

    /**
     * 把文字中的回车替换成<br>
     * 
     * @param strSource
     *            替换前的字符串
     * @return 替换后的字符串
     */
    public static String replaceReturn(String strSource, boolean tohtml)
    {
        if (tohtml)
        {
            // strSource = replaceReturn(strSource);
            strSource = replace(strSource, "\n", "\n<BR>");
        }
        else
        {
            strSource = replace(strSource, "<br>", "\n");
            strSource = replace(strSource, "<BR>", "\n");
            strSource = replace(strSource, "\n\n", "\n");
        }

        return strSource;
    }

    /**
     * 把文字中的回车替换成<br>
     * 
     * @param strSource
     *            替换前的字符串
     * @return 替换后的字符串
     */
    public static String replaceSpace(String strSource, boolean tohtml)
    {
        if (tohtml)
        {
            strSource = replace(strSource, " ", " ");
            strSource = replace(strSource, "\t", " ");
        }
        else
        {
            strSource = replace(strSource, " ", " ");
        }

        return strSource;
    }
}
