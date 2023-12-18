package com.alan.plugins.MyBatisLogFormatter.utils;

import com.intellij.openapi.ide.CopyPasteManager;
import org.apache.commons.lang3.StringUtils;

import java.awt.datatransfer.StringSelection;
import java.util.Timer;
import java.util.TimerTask;

public class Utils {

    /**
     * 复制
     *
     * @param text 内容
     */
    public static void copy(String text) {
        if (StringUtils.isNotBlank(text)) {
            CopyPasteManager.getInstance().setContents(new StringSelection(text));
        }
    }

    /**
     * 定时器
     *
     * @param second 秒
     */
    public static void timerTask(int second, Runnable runnable) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, second * 1000L);
    }


}
