package com.silvertak.relationshipsmanager.define;

public class JsonStringDefine
{
    public static final String CONTACT_INFO = "CONTACT_INFO";
    public static final String CALL_LOG_INFO = "CALL_LOG_INFO";

    public class GROUP
    {
        public static final String ARRAY = "GROUP_ARRAY";
        public static final String DATA = "GROUP_DATA";
        public static final String NAME = "GROUP_NAME";
        public static final String TERM = "GROUP_TERM";
    }

    public class CONTACT
    {
        public static final String ID = "ID";
        public static final String NAME = "CONTACT_NAME";
        public static final String PHONE_NUMBER = "CONTACT_PHONE_NUMBER";
        public static final String PHOTO_ID = "PHOTO_ID";
        public static final String EMAIL = "EMAIL";
    }

    public class CALL_LOG
    {
        public static final String PHONE_NUMBER = "CALL_LOG_PHONE_NUMBER";
        public static final String CALL_TYPE = "CALL_TYPE";
        public static final String CALL_DURATION = "CALL_DURATION";
        public static final String CALL_DATE = "CALL_DATE";
    }
}
