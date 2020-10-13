package com.yesway.common_lib.eventbus.event;

import com.yesway.api_lib.moudel.common.entity.User;
import com.yesway.common_lib.eventbus.Event;

public class UserEvent extends Event<User> {
    public UserEvent(int code) {
        super(code);
    }

    public UserEvent(int code, User data) {
        super(code, data);
    }
}
