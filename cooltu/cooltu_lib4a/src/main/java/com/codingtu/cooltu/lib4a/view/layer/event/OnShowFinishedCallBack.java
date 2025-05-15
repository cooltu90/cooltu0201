package com.codingtu.cooltu.lib4a.view.layer.event;

public interface OnShowFinishedCallBack extends EventCallBack {
    @Override
    default void callBack(Event event) {
        if (event.type == EventType.SHOW_FINISHED) {
            onShowFinished();
        }
    }

    void onShowFinished();
}
