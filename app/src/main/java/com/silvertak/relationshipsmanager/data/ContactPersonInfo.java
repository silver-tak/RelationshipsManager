package com.silvertak.relationshipsmanager.data;

public class ContactPersonInfo {

    private String strName = "";
    private String strLastContactDate = "";
    private String strPhoneNumber = "";

    public ContactPersonInfo(String name, String lastContactDate, String phoneNumber)
    {
        this.strName = name;
        this.strLastContactDate = lastContactDate;
        this.strPhoneNumber = phoneNumber;
    }

    public String getName() {
        return strName;
    }

    public String getLastContactDate() {
        return strLastContactDate;
    }

    public String getPhoneNumber() {
        return strPhoneNumber;
    }
}
