package com.yesway.common_lib.eventbus.event;

import com.yesway.common_lib.eventbus.Event;

public class SyncInfoEvent extends Event<Object> {
    public SyncInfoEvent(int code, Object data) {
        super(code, data);
    }

    public SyncInfoEvent(int code) {
        super(code);
    }
}
