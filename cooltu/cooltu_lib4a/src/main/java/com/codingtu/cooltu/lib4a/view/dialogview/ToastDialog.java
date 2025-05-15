package com.codingtu.cooltu.lib4a.view.dialogview;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.thread.OnceThread;
import com.codingtu.cooltu.lib4a.tools.HandlerTool;
import com.codingtu.cooltu.lib4a.view.layer.Layer;
import com.codingtu.cooltu.lib4a.view.layer.event.OnHiddenFinishedCallBack;
import com.codingtu.cooltu.lib4a.view.layer.event.OnShowFinishedCallBack;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4a.tools.InflateTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4j.function.OnError;
import com.codingtu.cooltu.lib4j.function.OnFinish;
import com.codingtu.cooltu.lib4j.data.value.TValue;

public final class ToastDialog implements OnDestroy {

    private Activity act;
    private Layer layer;
    private int layout;
    private View inflate;
    private View contentTv;

    public ToastDialog(Activity act) {
        this.act = act;
    }

    @Override
    public void destroy() {
        contentTv = null;
        inflate = null;
        ViewTool.removeFromAct(act, layer);
        if (layer != null)
            layer.destroy();
        layer = null;
        act = null;
    }

    public ToastDialog destroys(Destroys destroys) {
        if (destroys != null)
            destroys.add(this);
        return this;
    }

    public ToastDialog setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public ToastDialog build() {
        return build(false, false);
    }

    public ToastDialog build(boolean isHiddenWhenBackClick, boolean isHiddenWhenShadowClick) {
        layer = new Layer(act);
        layer.setHiddenWhenBackClick(isHiddenWhenBackClick);
        layer.setHiddenWhenShadowClick(isHiddenWhenShadowClick);
        ViewTool.addToAct(act, layer);
        ViewTool.gone(layer);
        inflate = InflateTool.inflate(act, layout);
        layer.addView(inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
        contentTv = inflate.findViewById(R.id.dialogContentTv);
        ViewTool.inRelativeCenter(inflate);
        return this;
    }

    public void setHiddenWhenBackClick(boolean isHiddenWhenBackClick) {
        layer.setHiddenWhenBackClick(isHiddenWhenBackClick);
    }

    public void setHiddenWhenShadowClick(boolean isHiddenWhenShadowClick) {
        layer.setHiddenWhenShadowClick(isHiddenWhenShadowClick);
    }


    public ToastDialog setContent(String text) {
        ViewTool.setText(contentTv, text);
        return this;
    }

    public String getContent() {
        return ((TextView) contentTv).getText().toString();
    }

    private boolean isShow() {
        return ViewTool.isVisible(layer);
    }

    /**************************************************
     *
     **************************************************/

    public ToastDialogShow show() {
        return new ToastDialogShow(this);
    }

    public static class ToastDialogShow {
        private final ToastDialog toastDialog;
        private OnShowFinishedCallBack onShowFinishedCallBack;

        private ToastDialogShow(ToastDialog toastDialog) {
            this.toastDialog = toastDialog;
        }

        public ToastDialogShow onShowFinished(OnShowFinishedCallBack onShowFinishedCallBack) {
            this.onShowFinishedCallBack = onShowFinishedCallBack;
            return this;
        }

        private void onShowFinishedForCustomer() {
            if (onShowFinishedCallBack != null) {
                onShowFinishedCallBack.onShowFinished();
            }
        }

        public void start() {
            start(new OnShowFinishedCallBack() {
                @Override
                public void onShowFinished() {
                    onShowFinishedForCustomer();
                }
            });
        }

        private void start(OnShowFinishedCallBack onShowFinishedCallBack) {
            toastDialog.layer.show(onShowFinishedCallBack);
        }

        public ToastDialogWhenShowFinishedStartThread whenShowFinishedStartThread(Runnable subRunnable) {
            ToastDialogWhenShowFinishedStartThread startThread = new ToastDialogWhenShowFinishedStartThread(this);
            startThread.subRunnable = subRunnable;
            return startThread;
        }
    }

    public static class ToastDialogWhenShowFinishedStartThread {
        private Runnable subRunnable;
        private OnceThread.MainRunnable mainRunnable;
        private ToastDialogShow toastDialogShow;
        private OnHiddenFinishedCallBack onHiddenFinishedCallBack;

        public ToastDialogWhenShowFinishedStartThread(ToastDialogShow toastDialogShow) {
            this.toastDialogShow = toastDialogShow;
        }

        public ToastDialogWhenShowFinishedStartThread onMainThread(OnceThread.MainRunnable mainRunnable) {
            this.mainRunnable = mainRunnable;
            return this;
        }

        public void start() {
            start(null);
        }

        public void start(OnFinish<Throwable> onFinish) {
            toastDialogShow.start(new OnShowFinishedCallBack() {
                @Override
                public void onShowFinished() {
                    toastDialogShow.onShowFinishedForCustomer();
                    if (subRunnable != null) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                TValue<Throwable> throwable = TValue.obtain();
                                try {
                                    subRunnable.run();
                                } catch (Exception e) {
                                    throwable.value = e;

                                }
                                HandlerTool.getMainHandler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (mainRunnable != null) {
                                            mainRunnable.run(throwable.value);
                                        }
                                        if (onFinish != null)
                                            onFinish.onFinish(throwable.value);
                                    }
                                });
                            }
                        }).start();
                    } else if (onFinish != null) {
                        onFinish.onFinish(null);
                    }
                }
            });
        }

        public ToastDialogHidden hiddenWhenThreadFinished() {
            ToastDialogHidden toastDialogHidden = new ToastDialogHidden(this);
            return toastDialogHidden;
        }

        public ToastDialogThreadFinished onError(OnError onError) {
            ToastDialogThreadFinished x = new ToastDialogThreadFinished(this);
            x.onError = onError;
            return x;
        }

        public ToastDialogThreadFinished onFinish(OnFinish onFinish) {
            ToastDialogThreadFinished x = new ToastDialogThreadFinished(this);
            x.onFinish = onFinish;
            return x;
        }
    }

    public static class ToastDialogThreadFinished {
        private OnFinish onFinish;
        private OnError onError;
        private ToastDialogWhenShowFinishedStartThread toastDialogWhenShowFinishedStartThread;

        public ToastDialogThreadFinished(ToastDialogWhenShowFinishedStartThread toastDialogWhenShowFinishedStartThread) {
            this.toastDialogWhenShowFinishedStartThread = toastDialogWhenShowFinishedStartThread;
        }

        public ToastDialogThreadFinished onFinish(OnFinish onFinish) {
            this.onFinish = onFinish;
            return this;
        }

        public ToastDialogThreadFinished onError(OnError onError) {
            this.onError = onError;
            return this;
        }

        public void start() {
            toastDialogWhenShowFinishedStartThread.start(new OnFinish<Throwable>() {
                @Override
                public void onFinish(Throwable o) {
                    if (o != null) {
                        if (onError != null) {
                            onError.onError(o);
                        }
                    } else {
                        if (onFinish != null)
                            onFinish.onFinish(null);
                    }
                }
            });
        }
    }

    /**************************************************
     *
     **************************************************/
    public ToastDialogHidden hidden() {
        ToastDialogHidden toastDialogHidden = new ToastDialogHidden(this);
        return toastDialogHidden;
    }

    public static class ToastDialogHidden {
        private Long hiddenTime;
        private OnHiddenFinishedCallBack onHiddenFinishedCallBack;
        private ToastDialog toastDialog;
        private ToastDialogWhenShowFinishedStartThread toastDialogWhenShowFinishedStartThread;
        private String content;

        private ToastDialogHidden(ToastDialog toastDialog) {
            this.toastDialog = toastDialog;
        }

        public ToastDialogHidden(ToastDialogWhenShowFinishedStartThread toastDialogWhenShowFinishedStartThread) {
            this.toastDialogWhenShowFinishedStartThread = toastDialogWhenShowFinishedStartThread;
            this.toastDialog = toastDialogWhenShowFinishedStartThread.toastDialogShow.toastDialog;
        }

        public ToastDialogHidden hiddenTime(long time) {
            this.hiddenTime = time;
            return this;
        }

        public ToastDialogHidden onHiddenFinished(OnHiddenFinishedCallBack onHiddenFinishedCallBack) {
            this.onHiddenFinishedCallBack = onHiddenFinishedCallBack;
            return this;
        }

        public void start() {
            if (toastDialogWhenShowFinishedStartThread != null) {
                toastDialogWhenShowFinishedStartThread.start(new OnFinish() {
                    @Override
                    public void onFinish(Object o) {
                        hiddenDeal();
                    }
                });
            } else if (toastDialog.isShow()) {
                hiddenDeal();
            } else {
                toastDialog.show().onShowFinished(new OnShowFinishedCallBack() {
                    @Override
                    public void onShowFinished() {
                        hiddenDeal();
                    }
                }).start();
            }

        }

        private void hiddenDeal() {
            if (content != null)
                toastDialog.setContent(content);
            if (hiddenTime != null) {
                HandlerTool.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hiddenReal();
                    }
                }, hiddenTime);
            } else {
                hiddenReal();
            }
        }

        private void hiddenReal() {
            toastDialog.layer.hidden(onHiddenFinishedCallBack);
        }

        public ToastDialogHidden setContent(String content) {
            this.content = content;
            return this;
        }
    }
}
