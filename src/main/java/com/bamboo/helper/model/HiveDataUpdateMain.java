/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.helper.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author csandy
 */
public class HiveDataUpdateMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String tableName = args[0];
        String sDay = args[1];
        String eDay = args[2];
        List<String> sqlTemplate = readTxtFileToList(tableName + ".txt", false);
        SimpleDateFormat formatDay = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatMon = new SimpleDateFormat("yyyyMM");

        Calendar startDay = Calendar.getInstance();
        startDay.setTime(formatDay.parse(sDay));

        Calendar endDay = Calendar.getInstance();
        endDay.setTime(formatDay.parse(eDay));

        Calendar endMon = Calendar.getInstance();
        endMon.setTime(formatDay.parse(eDay));
        
        while (endDay.after(startDay) || endDay.equals(startDay)) {
            
        }
    }

    /**
     * 读取文本文件内容
     *
     * @param filename
     * @param doDistinct 是否要去掉重复行
     * @return
     */
    public static List<String> readTxtFileToList(String filename, boolean doDistinct) {
        List dslist = new ArrayList(1500);
        try {
            FileInputStream instream = new FileInputStream(filename);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(instream, "UTF-8"), 50 * 1024 * 1024);
            String readline = null;
            while ((readline = bufreader.readLine()) != null) {
                if (!doDistinct) {
                    dslist.add(readline);
                } else if (!dslist.contains(readline)) {
                    dslist.add(readline);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dslist;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static void writeToFile(String script, String filename) throws IOException {
        BufferedWriter bufwriter = null;
        try {
            OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(filename), "UTF-8");
            bufwriter = new BufferedWriter(writerStream);
            bufwriter.write(script);
            bufwriter.newLine();
            bufwriter.close();
            System.out.println("输出结果：" + filename);
        } catch (IOException e) {
            System.out.println("[FAIL]输出建表语句失败！\n");
        } finally {
            if (bufwriter != null) {
                bufwriter.close();
            }
        }
    }
}
