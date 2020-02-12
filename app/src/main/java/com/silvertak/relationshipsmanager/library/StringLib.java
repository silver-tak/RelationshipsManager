package com.silvertak.relationshipsmanager.library;

public class StringLib {
    public static boolean isEmpty(String strValue)
    {
        if(strValue == null || strValue.length() == 0)
            return true;
        else
            return false;
    }
}
