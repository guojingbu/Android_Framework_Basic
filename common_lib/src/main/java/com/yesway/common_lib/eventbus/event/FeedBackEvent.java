package com.yesway.common_lib.eventbus.event;

import com.yesway.common_lib.eventbus.Event;

public class FeedBackEvent extends Event {
    public FeedBackEvent(int code) {
        super(code);
    }
}
