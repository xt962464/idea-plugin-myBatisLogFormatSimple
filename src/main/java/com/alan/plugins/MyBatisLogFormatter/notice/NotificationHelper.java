package com.alan.plugins.MyBatisLogFormatter.notice;

import com.alan.plugins.MyBatisLogFormatter.i18n.I18nBundle;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;

/**
 * @author Alan
 */
public class NotificationHelper {
    /**
     * 显示通知消息
     *
     * @param project 当前的 Project 对象，可以为 null
     * @param content 通知的内容
     */
    public static void showInfoNotification(Project project, String content) {
        showNotification(project, I18nBundle.message("label.notice.type.info"), content, NotificationType.INFORMATION);
    }

    /**
     * 显示通知消息
     *
     * @param project 当前的 Project 对象，可以为 null
     * @param title   通知的标题
     * @param content 通知的内容
     */
    public static void showInfoNotification(Project project, String title, String content) {
        showNotification(project, title, content, NotificationType.INFORMATION);
    }

    /**
     * 显示警告通知消息
     *
     * @param project 当前的 Project 对象，可以为 null
     * @param title   通知的标题
     * @param content 通知的内容
     */
    public static void showWarningNotification(Project project, String title, String content) {
        showNotification(project, title, content, NotificationType.WARNING);
    }

    /**
     * 显示警告通知消息
     *
     * @param project 当前的 Project 对象，可以为 null
     * @param content 通知的内容
     */
    public static void showWarningNotification(Project project, String content) {
        showNotification(project, I18nBundle.message("label.notice.type.warning"), content, NotificationType.WARNING);
    }

    /**
     * 显示通知消息
     *
     * @param project 当前的 Project 对象，可以为 null
     * @param content 通知的内容
     */
    public static void showErrorNotification(Project project, String content) {
        showNotification(project, I18nBundle.message("label.notice.type.error"), content, NotificationType.ERROR);
    }


    /**
     * 显示通知消息
     *
     * @param project 当前的 Project 对象，可以为 null
     * @param title   通知的标题
     * @param content 通知的内容
     */
    public static void showErrorNotification(Project project, String title, String content) {
        showNotification(project, title, content, NotificationType.ERROR);
    }

    /**
     * 显示通知消息
     *
     * @param project 当前的 Project 对象，可以为 null
     * @param title   通知的标题
     * @param content 通知的内容
     * @param type    通知的类型（信息、警告、错误）
     */
    private static void showNotification(Project project, String title, String content, NotificationType type) {
        // 获取通知分组
        Notification notification = NotificationGroupManager.getInstance()
                // 自定义通知组名称
                .getNotificationGroup("MyBatisLogFormatter.Notifications")
                .createNotification(title, content, type);

        // 在指定的项目上下文中通知
        notification.notify(project);
    }
}
