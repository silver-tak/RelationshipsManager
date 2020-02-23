package com.silvertak.relationshipsmanager.library;

public class StringLib {
    public static boolean isEmpty(String strValue)
    {
        if(strValue == null || strValue.length() == 0)
            return true;
        else
            return false;
    }

    public static String getOnlyNumber(String strValue)
    {
        return strValue.replaceAll("[^0-9]", "");
    }

    public static String getGroupName(String strText, String strTerm)
    {
        if(StringLib.isEmpty(strText))
            return strTerm;
        else return strText;
    }
}
