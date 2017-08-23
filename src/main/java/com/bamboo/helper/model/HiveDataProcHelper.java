/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.helper.model;

import com.snal.beans.Table;
import com.snal.beans.TableCol;
import com.snal.util.text.TextUtil;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author csandy
 */
public class HiveDataProcHelper {

    /**
     * 生成HQL语句模板
     *
     * @param tableName
     * @param tableMap
     * @throws ParseException
     * @throws IOException
     */
    public static void createUpdateSqlTemplate(String tableName, Map<String, Table> tableMap) throws ParseException, IOException {

        Table table = tableMap.get(tableName);

        List partitionCols = Arrays.asList(table.getPartitionCols());

        StringBuilder fieldBuffer = new StringBuilder();
        for (TableCol tableCol : table.getTablecols()) {

            String colName = tableCol.getColumnName();
            if (partitionCols.contains(colName)) {
                continue;
            }
            if (colName.endsWith("CELL_CD")) {
                colName = "LPAD(UPPER(TRIM(" + colName + ")),8,'0')";
            }
            fieldBuffer.append(colName).append(",");
        }
        fieldBuffer.deleteCharAt(fieldBuffer.length() - 1);//删除最后一个“,”。

        StringBuilder sqlBuffer = new StringBuilder();
        if (partitionCols.contains("month") && partitionCols.contains("day")) {
            sqlBuffer.append("perl ~schadm/dssprog/bin/remote_cli.pl ").append(table.getTenantUser()).append(" beeline -e \"USE JCFW;");
            sqlBuffer.append("INSERT OVERWRITE TABLE JCFW.")
                    .append(table.getTableName()).append("_BAK ")
                    .append("PARTITION (branch='#branch',")
                    .append("month=#month").append(",")
                    .append("day=#day").append(") ")
                    .append("SELECT ").append(fieldBuffer.toString())
                    .append(" FROM JCFW.").append(table.getTableName())
                    .append(" WHERE branch='#branch'")
                    .append(" AND month=#month")
                    .append(" AND day=#day").append(";");
            sqlBuffer.append("\"\n");
        } else if (partitionCols.contains("month") && !partitionCols.contains("day")) {
            sqlBuffer.append("perl ~schadm/dssprog/bin/remote_cli.pl ").append(table.getTenantUser()).append(" beeline -e \"use jcfw;");
            sqlBuffer.append("INSERT OVERWRITE TABLE JCFW.")
                    .append(table.getTableName()).append("_BAK ")
                    .append("PARTITION (branch='#branch',")
                    .append("month=#month)")
                    .append("SELECT ").append(fieldBuffer.toString())
                    .append(" FROM JCFW.").append(table.getTableName())
                    .append(" WHERE branch='#branch'")
                    .append(" AND month=#month;");
            sqlBuffer.append("\"\n");

        } else if (partitionCols.contains("branch")
                && !partitionCols.contains("month")
                && !partitionCols.contains("day")) {
            sqlBuffer.append("perl ~schadm/dssprog/bin/remote_cli.pl ").append(table.getTenantUser()).append(" beeline -e \"use jcfw;");
            sqlBuffer.append("INSERT OVERWRITE TABLE JCFW.")
                    .append(table.getTableName()).append("_BAK ")
                    .append("PARTITION (branch='#branch')")
                    .append("SELECT ").append(fieldBuffer.toString())
                    .append(" FROM JCFW.").append(table.getTableName())
                    .append(" WHERE branch='#branch';");
            sqlBuffer.append("\"\n");
        }
        TextUtil.writeToFile(sqlBuffer.toString(), tableName + ".sh");
    }
}
