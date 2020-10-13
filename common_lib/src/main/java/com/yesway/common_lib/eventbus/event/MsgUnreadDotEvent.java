package com.yesway.common_lib.eventbus.event;

import com.yesway.common_lib.eventbus.Event;

public class MsgUnreadDotEvent extends Event<Integer> {
    public MsgUnreadDotEvent(int code, Integer data) {
        super(code, data);
    }

    public MsgUnreadDotEvent(int code) {
        super(code);
    }
}
