package com.codingtu.cooltu.lib4a.view.layer.event;

public interface OnHiddenFinishedCallBack extends EventCallBack {
    @Override
    default void callBack(Event event) {
        if (event.type == EventType.HIDDEN_FINISHED) {
            onHiddenFinished();
        }
    }

    void onHiddenFinished();
}
