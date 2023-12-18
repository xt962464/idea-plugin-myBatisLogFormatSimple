package com.alan.plugins.MyBatisLogFormatter.utils;

import com.github.vertical_blank.sqlformatter.SqlFormatter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlUtils {

    private static final String preparing = "Preparing: ";
    private static final String parameters = "Parameters: ";

    public static final String sqlParametersPattern = "(,\\s){0,}(.*?)\\((Integer|int|Long|long|Float|float|String|char|Char|Date|LocalDateTime|localDateTime|LocalDate|localDate|Timestamp)\\)";

    public static final String sqlPlaceholder = "#占位符";

    public static String[] splitMybatisLog(String mybatisLog) {
        if (StringUtils.isBlank(mybatisLog)) {
            return null;
        }
        String[] logSplitArr = mybatisLog.split("\n");
        String sqlLog = "";
        String paramsLog = "";
        if (logSplitArr.length > 2) {
            for (String row : logSplitArr) {
                if (row.contains(preparing)) {
                    sqlLog = row.split(preparing)[1];
                } else if (row.contains(parameters)) {
                    String[] splitArr = row.split(parameters);
                    if (splitArr.length == 2) {
                        paramsLog = splitArr[1];
                    }
                }
            }
        } else if (logSplitArr.length == 2) {
            sqlLog = logSplitArr[0].split(preparing)[1];
            String[] splitArr = logSplitArr[1].split(parameters);
            if (splitArr.length == 2) {
                paramsLog = splitArr[1];
            }
        } else if (logSplitArr.length == 1) {
            if (logSplitArr[0].contains(preparing)) {
                sqlLog = logSplitArr[0].split(preparing)[1];
            }
        }
        return new String[]{sqlLog, paramsLog};
    }

    public static String formatMybatisLog(String mybatisLog) {
        String[] mybatisLogArray = splitMybatisLog(mybatisLog);
        if (mybatisLogArray == null || mybatisLogArray.length == 0) {
            return "";
        }
        String sql = mybatisLogArray[0];
        String parameters = "";
        if (mybatisLogArray.length == 2) {
            parameters = mybatisLogArray[1];
        }
        if (StringUtils.isNotBlank(parameters)) {
            // 替换？占位符
            sql = sql.replace("?", sqlPlaceholder);
            // 匹配参数
            Pattern p = Pattern.compile(sqlParametersPattern);
            Matcher m = p.matcher(parameters);
            while (m.find()) {
                String value = m.group(2);
                String type = m.group(3);
                switch (type) {
                    case "String":
                    case "string":
                    case "Char":
                    case "char":
                    case "Date":
                    case "date":
                    case "LocalDateTime":
                    case "localDateTime":
                    case "LocalDate":
                    case "localDate":
                    case "Timestamp":
                    case "timestamp":
                        value = String.format("'%s'", value);
                        break;
                }
                sql = sql.replaceFirst(sqlPlaceholder, value);
            }
        }
        return sql;
    }


    /**
     * sql压缩
     * @param text sql
     */
    public static String compressSql(String text) {
        // 移除多余的空格和换行
        String compressedMySQL = text.replaceAll("\\s+", " ");
        // 移除单行注释
        compressedMySQL = compressedMySQL.replaceAll("--[^\\n]*", "");
        // 移除多行注释
        compressedMySQL = compressedMySQL.replaceAll("/\\*.*?\\*/", "");
        return compressedMySQL.trim();
    }

    /**
     * sql美化
     * @param text sql
     */
    public static String beautifySql(String text) {
        return SqlFormatter.format(text);
    }
}
