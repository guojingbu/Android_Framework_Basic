package com.yesway.common_lib.eventbus;

/**
 * 时间码
 */
public interface EventCode {
    interface Common {
        //-----------------common 100 开始------------------------
        int COMMON_LANGUAGE_CHANGED_CODE = 100;
        int COMMON_SYNC_INFO_EVENT_CODE = 101;
    }

    interface Vehicle {
        //-------------vehicle 200 开始-------------------------
        int VEHICLE_UPDATE_LIST_CODE = 200;
        int ADD_VEHICLE_SUCCESS=201;
        int CHECK_VEHICLE_EVENTCODE = 202;
        int VEHICLE_REALTIME_LOCATION_CODE = 203;
    }

    interface Me {
        //-------------me 300 开始-------------------------
        int UPDATE_USER_INFO_CODE = 300;
        int UPDATE_USER_PHONE_CODE = 301;
    }

    interface Message {
        //-------------me 400 开始-------------------------
        int PUBLIC_SMART_CODE = 400;
        int PUBLIC_CLICK_CODE = 401;
        int MESSAGE_LIST_NOTICE_PUSH_CODE = 402;
        int MESSAGE_UPDATE_UNREAD_DOT = 403;
    }


}

