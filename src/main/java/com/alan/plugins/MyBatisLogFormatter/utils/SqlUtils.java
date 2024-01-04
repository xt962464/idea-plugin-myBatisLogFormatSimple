package com.alan.plugins.MyBatisLogFormatter.utils;

import com.github.vertical_blank.sqlformatter.SqlFormatter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlUtils {

    private static final String PREPARING_KEY = "Preparing: ";
    private static final String PARAMETERS_KEY = "Parameters: ";

    public static final String sqlParametersPattern = "(,\\s){0,}(null)(,\\s){0,}|(,\\s){0,}(.*?)\\((Integer|int|Long|long|Float|float|String|char|Char|Date|LocalDateTime|localDateTime|LocalDate|localDate|Timestamp|Boolean|boolean)\\)(,\\s){0,}";

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
                if (row.contains(PREPARING_KEY)) {
                    sqlLog = row.split(PREPARING_KEY)[1];
                } else if (row.contains(PARAMETERS_KEY)) {
                    String[] splitArr = row.split(PARAMETERS_KEY);
                    if (splitArr.length == 2) {
                        paramsLog = splitArr[1];
                    }
                }
            }
        } else if (logSplitArr.length == 2) {
            sqlLog = logSplitArr[0].split(PREPARING_KEY)[1];
            String[] splitArr = logSplitArr[1].split(PARAMETERS_KEY);
            if (splitArr.length == 2) {
                paramsLog = splitArr[1];
            }
        } else if (logSplitArr.length == 1) {
            if (logSplitArr[0].contains(PREPARING_KEY)) {
                sqlLog = logSplitArr[0].split(PREPARING_KEY)[1];
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
            parameters = parameters.replace(PARAMETERS_KEY, "");
            // 替换？占位符
            sql = sql.replace("?", sqlPlaceholder);
            // 匹配参数
            Pattern p = Pattern.compile(sqlParametersPattern);
            Matcher m = p.matcher(parameters);
            while (m.find()) {
                String group = m.group();
                String group1 = m.group(1);
                String nullStr = m.group(2);
                String splitStr1 = m.group(3);
                String splitStr2 = m.group(4);
                String value = m.group(5);
                String type = m.group(6);
                if ("null".equalsIgnoreCase(nullStr)){
                    value = "null";
                    type = "null";
                } else if (" (String), ".equalsIgnoreCase(group) ||
                        "(String), ".equalsIgnoreCase(group) || "(String),".equalsIgnoreCase(group) || "(String)".equalsIgnoreCase(group) ||
                        " (String),".equalsIgnoreCase(group) || " (String)".equalsIgnoreCase(group) || " (String) ".equalsIgnoreCase(group)){
                    value = "''";
                    type = "String";
                } else {
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
                }
//                System.out.println(String.format("type:%s, value:%s", type, value));
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
