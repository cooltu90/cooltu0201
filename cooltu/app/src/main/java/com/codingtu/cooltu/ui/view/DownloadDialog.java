package com.codingtu.cooltu.ui.view;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.tools.InflateTool;
import com.codingtu.cooltu.lib4a.tools.MobileTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.layer.Layer;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.tools.StringTool;

public class DownloadDialog implements OnDestroy {
    private View inflate;
    private Activity act;
    private Layer rlv;
    private TextView progressTv;
    private View progressView;
    private TextView sizeTv;
    private TextView speedTv;
    private TextView restTimeTv;
    private int totalWidth;
    private long lastTime;
    private long lastSize;
    private String title;

    public DownloadDialog(Activity act) {
        this.act = act;
        this.totalWidth = MobileTool.dpToPx(228.68f);
        this.rlv = new Layer(this.act);
        this.rlv.setHiddenWhenBackClick(false);
        this.rlv.setHiddenWhenShadowClick(false);
        ViewTool.addToAct(this.act, this.rlv);
        ViewTool.gone(this.rlv);
        this.inflate = InflateTool.inflate(this.act, R.layout.dialog_download);
        this.rlv.addView(this.inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
        ViewTool.inRelativeCenter(this.inflate);

        progressTv = this.inflate.findViewById(R.id.progressTv);
        progressView = this.inflate.findViewById(R.id.progressView);
        sizeTv = this.inflate.findViewById(R.id.sizeTv);
        speedTv = this.inflate.findViewById(R.id.speedTv);
        restTimeTv = this.inflate.findViewById(R.id.restTimeTv);

        setProgress(-1, -1);
    }

    @Override
    public void destroy() {
        this.act = null;
        this.rlv = null;
        this.inflate = null;
        this.progressTv = null;
        this.speedTv = null;
        this.progressView = null;
        this.sizeTv = null;
        this.restTimeTv = null;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setProgress(long totalSize, long currentSize) {
        setSize(totalSize, currentSize);
        setProgressView(totalSize, currentSize);
        setProgressTv(totalSize, currentSize);
        setSpeedTv(totalSize, currentSize);
    }

    private void setSpeedTv(long totalSize, long currentSize) {

        if (totalSize <= 0 || currentSize <= 0) {
            ViewTool.setText(speedTv, "速度 --/s");
            ViewTool.setText(restTimeTv, "剩余时间 00:00:00");
            return;
        }

        long nowTime = System.currentTimeMillis();
        double v = (currentSize - lastSize) * 1000d / (nowTime - lastTime);
        if (v < 0) {
            v = 0d;
        }
        ViewTool.setText(speedTv, "速度 " + getSizeStr((long) v) + "/s");
        lastSize = currentSize;
        lastTime = nowTime;

        if (totalSize == currentSize) {
            ViewTool.setText(restTimeTv, "剩余时间 00:00:00");
        } else if (v > 0) {
            long second = (long) ((totalSize - currentSize) / v);
            if (second <= 0) {
                second = 1;
            }
            long minute = 0;
            long hour = 0;
            if (second >= 60) {
                minute = second / 60;
                second = second - minute * 60;
            }

            if (minute >= 60) {
                hour = minute / 60;
                minute = minute - hour * 60;
            }
            ViewTool.setText(restTimeTv, "剩余时间 " + StringTool.formatNumber(hour, 2) + ":" + StringTool.formatNumber(minute, 2) + ":" + StringTool.formatNumber(second, 2));
        }
    }

    private void setProgressTv(long totalSize, long currentSize) {
        if (totalSize <= 0 || currentSize <= 0) {
            setProgressTv("0.00");
            return;
        }

        if (totalSize == currentSize) {
            setProgressTv("100");
        } else {
            String s = StringTool.formatDouble(currentSize * 100d / totalSize, 2, false);
            setProgressTv(s);
        }

    }

    private void setProgressTv(String progress) {
        ViewTool.setText(progressTv, title + " " + progress + "%");
    }

    private void setProgressView(long totalSize, long currentSize) {
        if (totalSize <= 0 || currentSize <= 0) {
            ViewTool.setW(progressView, 0);
            return;
        }
        if (totalSize == currentSize) {
            ViewTool.setW(progressView, ViewTool.MATCH_PARENT);
        } else {
            int w = (int) (totalWidth * currentSize / totalSize);
            ViewTool.setW(progressView, w);
        }
    }

    private void setSize(long totalSize, long currentSize) {
        if (totalSize < 0 || currentSize < 0) {
            ViewTool.setText(sizeTv, "-- / --");
        } else {
            ViewTool.setText(sizeTv, getSizeStr(currentSize) + " / " + getSizeStr(totalSize));
        }
    }

    private String getSizeStr(long size) {
        double dSize = size / 1024d;
        if (dSize < 1024) {
            return StringTool.formatDouble(dSize, 2, true) + "KB";
        }

        dSize /= 1024d;
        if (dSize < 1024) {
            return StringTool.formatDouble(dSize, 2, true) + "MB";
        }

        dSize /= 1024d;
        if (dSize < 1024) {
            return StringTool.formatDouble(dSize, 2, true) + "GB";
        }

        dSize /= 1024d;
        return StringTool.formatDouble(dSize, 2, true) + "TB";
    }

    public void show() {
        this.rlv.show();
    }

    public void start() {
        lastSize = 0;
        lastTime = System.currentTimeMillis();
        setProgress(-1, -1);
    }

    public void hidden() {
        this.rlv.hidden();
    }
}
