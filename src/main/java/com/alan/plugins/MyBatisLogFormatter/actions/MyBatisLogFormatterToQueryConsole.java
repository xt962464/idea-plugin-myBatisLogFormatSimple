package com.alan.plugins.MyBatisLogFormatter.actions;

import com.alan.plugins.MyBatisLogFormatter.handler.OpenQueryConsoleActionHandler;
import com.alan.plugins.MyBatisLogFormatter.i18n.I18nBundle;
import com.alan.plugins.MyBatisLogFormatter.notice.NotificationHelper;
import com.alan.plugins.MyBatisLogFormatter.utils.SqlUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan
 */
public class MyBatisLogFormatterToQueryConsole extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        if (project == null) {
            return;
        }
        // 获得选中的文本
        String selectedText = event.getRequiredData(CommonDataKeys.EDITOR).getSelectionModel().getSelectedText();
        if (StringUtils.isBlank(selectedText)) {
            NotificationHelper.showWarningNotification(project, I18nBundle.message("label.select.myBatisLog"));
            return;
        }
        String sqlContent = SqlUtils.formatMybatisLog(selectedText);
        if (StringUtils.isBlank(sqlContent)) {
            NotificationHelper.showWarningNotification(project, I18nBundle.message("label.notice.not.sql.statement"));
            return;
        }
        OpenQueryConsoleActionHandler.openQueryConsoleWithClipboardSql(project, SqlUtils.beautifySql(sqlContent));
    }
}
