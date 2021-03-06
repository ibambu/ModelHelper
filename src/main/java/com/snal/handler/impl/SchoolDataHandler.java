/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snal.handler.impl;

import com.snal.beans.TSchoolEnrollPlan;
import com.snal.beans.TSchoolEntryScore;
import com.snal.handler.ISchoolDataHandler;
import com.snal.util.excel.BigExcelUtil;
import com.snal.util.text.TextUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luotao
 */
public class SchoolDataHandler implements ISchoolDataHandler {

    private static Map<String, String> areaMap = new HashMap<>();
    private static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String[] ALL_AREAS = {
        "440105",
        "440117",
        "440118",
        "440115",
        "440113",
        "440103",
        "440112",
        "440106",
        "440104",
        "440114",
        "440111"};

    @Override
    public void loadSchoolEntryScore() {
        try {
            /*
            * 初始化地区
             */
            initAreaCode();
            String dataFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/2020年广州市普通高中学校录取分数.xlsx";
            BigExcelUtil bigExlUtil = new BigExcelUtil();
            int[] minColumns = {24, 12};//列
            Map<String, List<List<String>>> metadata = bigExlUtil.readExcelData(dataFile, 0, 1, minColumns);
            List<List<String>> sheetData1 = metadata.get("0");//第一个工作表格
            List<List<String>> sheetData2 = metadata.get("1");//第二个工作表格
            importPubEntryScore(sheetData1);
            importPrivateEntryScore(sheetData2);
        } catch (IOException ex) {
            Logger.getLogger(SchoolDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void loadSchool() {

    }

    private static void initAreaCode() {
        areaMap.put("海珠区", "440105");
        areaMap.put("从化区", "440117");
        areaMap.put("增城区", "440118");
        areaMap.put("南沙区", "440115");
        areaMap.put("番禺区", "440113");
        areaMap.put("荔湾区", "440103");
        areaMap.put("黄埔区", "440112");
        areaMap.put("天河区", "440106");
        areaMap.put("越秀区", "440104");
        areaMap.put("花都区", "440114");
        areaMap.put("白云区", "440111");
        areaMap.put("全市", "ALL");

        areaMap.put("南海区", "440605");
        areaMap.put("清城区", "441802");
        areaMap.put("博罗县", "441322");
    }

    private void importPrivateEntryScore(List<List<String>> sheetData) throws IOException {

        StringBuilder sqlBuffer = new StringBuilder();
        sheetData.remove(0);
        List<TSchoolEntryScore> tSchoolEntryScoreList = readTSchoolEntryScore(sheetData);
        tSchoolEntryScoreList.stream().forEach(p -> {
            String sql = makeInsertSql(p);
            sqlBuffer.append(sql);
        });
        String sqlFileName = "private_school_score_" + System.currentTimeMillis() + ".sql";
        String sqlFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/" + sqlFileName;
        TextUtil.writeToFile(sqlBuffer.toString(), sqlFile);

    }

    private void importPubEntryScore(List<List<String>> sheetData) throws IOException {
        sheetData.remove(0);
        sheetData.remove(0);
        StringBuilder sqlBuffer = new StringBuilder();

        List<TSchoolEntryScore> tSchoolEntryScoreList = readPubTSchoolEntryScore(sheetData);
        tSchoolEntryScoreList.stream().forEach(p -> {
            String sql = makeInsertSql(p);
            sqlBuffer.append(sql);
        });
        String sqlFileName = "pub_school_score_" + System.currentTimeMillis() + ".sql";
        String sqlFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/" + sqlFileName;
        TextUtil.writeToFile(sqlBuffer.toString(), sqlFile);

    }

    /**
     * 读取学校分数线
     *
     * @param sheetData
     * @return
     */
    private static List<TSchoolEntryScore> readTSchoolEntryScore(List<List<String>> sheetData) {
        List<TSchoolEntryScore> tSchoolEntryScoreList = new ArrayList<>();
        sheetData.stream().forEach(rowData -> {
            List<TSchoolEntryScore> tSchoolEntryScores = createPrivateEntryScore(rowData, 0);
            tSchoolEntryScoreList.addAll(tSchoolEntryScores);
        });
        System.out.println("总记录数目：" + tSchoolEntryScoreList.size());
        return tSchoolEntryScoreList;
    }

    private static List<TSchoolEntryScore> readPubTSchoolEntryScore(List<List<String>> sheetData) {
        List<TSchoolEntryScore> tSchoolEntryScoreList = new ArrayList<>();
        sheetData.stream().forEach(rowData -> {
            List<TSchoolEntryScore> tSchoolEntryScores = createPubSchoolEntryScore(rowData);
            tSchoolEntryScoreList.addAll(tSchoolEntryScores);
        });
        System.out.println("总记录数目：" + tSchoolEntryScoreList.size());
        return tSchoolEntryScoreList;
    }

    private static List<TSchoolEnrollPlan> readSchoolEnrollPlan(List<List<String>> sheetData) {
        List<TSchoolEnrollPlan> tSchoolEntryScoreList = new ArrayList<>();
        sheetData.stream().forEach(rowData -> {
            List<TSchoolEnrollPlan> tSchoolEntryScores = createSchoolEntryPlan(rowData);
            tSchoolEntryScoreList.addAll(tSchoolEntryScores);
        });
        System.out.println("总记录数目：" + tSchoolEntryScoreList.size());
        return tSchoolEntryScoreList;
    }

    /**
     * 民办学校录取分数线
     *
     * @param rowData
     * @return
     */
    private static List<TSchoolEntryScore> createPrivateEntryScore(List<String> rowData, int studentType) {
        List<TSchoolEntryScore> resultList = new ArrayList<>();
        String areaName = rowData.get(6);
        if ("老三区".equals(areaName)) {
            String areaCode1 = areaMap.get("越秀区");
            resultList.add(createEntryScore(areaCode1, studentType, rowData));

            String areaCode2 = areaMap.get("荔湾区");
            resultList.add(createEntryScore(areaCode2, studentType, rowData));

            String areaCode3 = areaMap.get("海珠区");
            resultList.add(createEntryScore(areaCode3, studentType, rowData));
        } else {
            String areaCode = areaMap.get(areaName);
            resultList.add(createEntryScore(areaCode, studentType, rowData));
        }
        return resultList;
    }

    private static List<TSchoolEntryScore> createPubSchoolEntryScore(List<String> rowData) {
        List<TSchoolEntryScore> resultList = new ArrayList<>();
        String areaName = rowData.get(6);

        String outAreaMinScore = rowData.get(18);
        boolean isLimitOutArea = outAreaMinScore != null && outAreaMinScore.trim().length() > 0;
        if (isLimitOutArea && "全市".equals(areaName)) {
            String schoolAreaCode = areaMap.get(rowData.get(6));
            for (String areaCode : ALL_AREAS) {
                boolean isLocal = areaCode.equals(schoolAreaCode);
                if (isLocal) {
                    resultList.addAll(createPubEntryScore(areaCode, rowData));
                } else {
                    resultList.addAll(createPubOutAreaEntryScore(areaCode, rowData));
                }
            }
        } else {
            if ("老三区".equals(areaName)) {
                String areaCode1 = areaMap.get("越秀区");
                resultList.addAll(createPubEntryScore(areaCode1, rowData));

                String areaCode2 = areaMap.get("荔湾区");
                resultList.addAll(createPubEntryScore(areaCode2, rowData));

                String areaCode3 = areaMap.get("海珠区");
                resultList.addAll(createPubEntryScore(areaCode3, rowData));
            } else {
                String areaCode = areaMap.get(areaName);
                resultList.addAll(createPubEntryScore(areaCode, rowData));
            }
        }

        return resultList;
    }

    private static List<TSchoolEnrollPlan> createSchoolEntryPlan(List<String> rowData) {
        List<TSchoolEnrollPlan> resultList = new ArrayList<>();

        TSchoolEnrollPlan tSchoolEnrollPlan = new TSchoolEnrollPlan();
        tSchoolEnrollPlan.setSchoolId(Integer.parseInt(rowData.get(0)));//学校ID
        tSchoolEnrollPlan.setYear(Integer.parseInt(rowData.get(2)));//年份
        tSchoolEnrollPlan.setEnrollType(Integer.parseInt(rowData.get(3)));//录取类别
        tSchoolEnrollPlan.setPlanLocationNames(rowData.get(5));//招生区域
        tSchoolEnrollPlan.setBatchName(rowData.get(6));//招生批次
        tSchoolEnrollPlan.setEnrollNumber(Integer.parseInt(rowData.get(7)));//招生人数
        tSchoolEnrollPlan.setAccommodationNumber(Integer.parseInt(rowData.get(8)));//宿位个数
        tSchoolEnrollPlan.setAccommodationNumberDesc(rowData.get(9));//宿位个数描述

        resultList.add(tSchoolEnrollPlan);
        return resultList;
    }

    private static List<TSchoolEntryScore> createPubOutAreaEntryScore(String areaCode, List<String> rowData) {
        List<TSchoolEntryScore> entryScoreList = new ArrayList<>();

        TSchoolEntryScore tSchoolEntryScore1 = new TSchoolEntryScore();//户籍生
        TSchoolEntryScore tSchoolEntryScore2 = new TSchoolEntryScore();//非户籍生

        tSchoolEntryScore1.setEnrollYear(Integer.parseInt(rowData.get(0)));
        tSchoolEntryScore1.setBatchNo(Byte.parseByte(rowData.get(2)));//批次编号
        tSchoolEntryScore1.setStudentType(1);//户籍生
        tSchoolEntryScore1.setSchoolId(Integer.parseInt(rowData.get(4)));//学校编号
        tSchoolEntryScore1.setSchoolLocationCode(areaMap.get(rowData.get(6)));//学校区域
        tSchoolEntryScore1.setCreateTime(new Date());

        tSchoolEntryScore2.setEnrollYear(Integer.parseInt(rowData.get(0)));
        tSchoolEntryScore2.setBatchNo(Byte.parseByte(rowData.get(2)));//批次编号
        tSchoolEntryScore2.setStudentType(2);//非户籍生
        tSchoolEntryScore2.setSchoolId(Integer.parseInt(rowData.get(4)));//学校编号
        tSchoolEntryScore2.setSchoolLocationCode(areaMap.get(rowData.get(6)));//学校区域
        tSchoolEntryScore2.setCreateTime(new Date());

        tSchoolEntryScore1.setAreaCode(areaCode);
        tSchoolEntryScore1.setMinScore(Integer.parseInt(rowData.get(18)));//最低分数
        tSchoolEntryScore1.setMinScoreSeq(Integer.parseInt(rowData.get(19)));//最低分时速同分序号
        tSchoolEntryScore1.setLastStudentSeq(Integer.parseInt(rowData.get(20)));//末位考生志愿序号
        tSchoolEntryScore1.setLastStudentScore(Integer.parseInt(rowData.get(21)));//末位考生分数
        tSchoolEntryScore1.setLastStudentSameSeq(Integer.parseInt(rowData.get(22)));//末位考生同分序号
        tSchoolEntryScore1.setMaxEntrySeq(Integer.parseInt(rowData.get(23)));//最大志愿序号
        tSchoolEntryScore1.setScoreEnrolled(tSchoolEntryScore1.getLastStudentScore());

        tSchoolEntryScore2.setAreaCode(areaCode);
        tSchoolEntryScore2.setMinScore(Integer.parseInt(rowData.get(18)));//最低分数
        tSchoolEntryScore2.setMinScoreSeq(Integer.parseInt(rowData.get(19)));//最低分时速同分序号
        tSchoolEntryScore2.setLastStudentSeq(Integer.parseInt(rowData.get(20)));//末位考生志愿序号
        tSchoolEntryScore2.setLastStudentScore(Integer.parseInt(rowData.get(21)));//末位考生分数
        tSchoolEntryScore2.setLastStudentSameSeq(Integer.parseInt(rowData.get(22)));//末位考生同分序号
        tSchoolEntryScore2.setMaxEntrySeq(Integer.parseInt(rowData.get(23)));//最大志愿序号
        tSchoolEntryScore2.setScoreEnrolled(tSchoolEntryScore1.getLastStudentScore());

        entryScoreList.add(tSchoolEntryScore1);
        entryScoreList.add(tSchoolEntryScore2);
        return entryScoreList;
    }

    private static List<TSchoolEntryScore> createPubEntryScore(String areaCode, List<String> rowData) {
        //最低分数线没有的区表示该学校不在该区招生。
        List<TSchoolEntryScore> entryScoreList = new ArrayList<>();

        TSchoolEntryScore tSchoolEntryScore1 = new TSchoolEntryScore();//户籍生
        TSchoolEntryScore tSchoolEntryScore2 = new TSchoolEntryScore();//非户籍生

        tSchoolEntryScore1.setEnrollYear(Integer.parseInt(rowData.get(0)));
        tSchoolEntryScore1.setBatchNo(Byte.parseByte(rowData.get(2)));//批次编号
        tSchoolEntryScore1.setStudentType(1);//户籍生
        tSchoolEntryScore1.setSchoolId(Integer.parseInt(rowData.get(4)));//学校编号
        tSchoolEntryScore1.setSchoolLocationCode(areaMap.get(rowData.get(6)));//学校区域
        tSchoolEntryScore1.setCreateTime(new Date());

        tSchoolEntryScore2.setEnrollYear(Integer.parseInt(rowData.get(0)));
        tSchoolEntryScore2.setBatchNo(Byte.parseByte(rowData.get(2)));//批次编号
        tSchoolEntryScore2.setStudentType(2);//非户籍生
        tSchoolEntryScore2.setSchoolId(Integer.parseInt(rowData.get(4)));//学校编号
        tSchoolEntryScore2.setSchoolLocationCode(areaMap.get(rowData.get(6)));//学校区域
        tSchoolEntryScore2.setCreateTime(new Date());

        tSchoolEntryScore1.setAreaCode(areaCode);
        tSchoolEntryScore1.setMinScore(Integer.parseInt(rowData.get(8)));//最低分数
        tSchoolEntryScore1.setMinScoreSeq(Integer.parseInt(rowData.get(9)));//最低分时速同分序号
        tSchoolEntryScore1.setLastStudentSeq(Integer.parseInt(rowData.get(10)));//末位考生志愿序号
        tSchoolEntryScore1.setLastStudentScore(Integer.parseInt(rowData.get(11)));//末位考生分数
        tSchoolEntryScore1.setLastStudentSameSeq(Integer.parseInt(rowData.get(12)));//末位考生同分序号
        tSchoolEntryScore1.setMaxEntrySeq(Integer.parseInt(rowData.get(23)));//最大志愿序号
        tSchoolEntryScore1.setScoreEnrolled(tSchoolEntryScore1.getLastStudentScore());

        tSchoolEntryScore2.setAreaCode(areaCode);
        tSchoolEntryScore2.setMinScore(Integer.parseInt(rowData.get(13)));//最低分数
        tSchoolEntryScore2.setMinScoreSeq(Integer.parseInt(rowData.get(14)));//最低分时速同分序号
        tSchoolEntryScore2.setLastStudentSeq(Integer.parseInt(rowData.get(15)));//末位考生志愿序号
        tSchoolEntryScore2.setLastStudentScore(Integer.parseInt(rowData.get(16)));//末位考生分数
        tSchoolEntryScore2.setLastStudentSameSeq(Integer.parseInt(rowData.get(17)));//末位考生同分序号
        tSchoolEntryScore2.setMaxEntrySeq(Integer.parseInt(rowData.get(23)));//最大志愿序号
        tSchoolEntryScore2.setScoreEnrolled(tSchoolEntryScore2.getLastStudentScore());

        entryScoreList.add(tSchoolEntryScore1);
        entryScoreList.add(tSchoolEntryScore2);
        return entryScoreList;
    }

    private static TSchoolEntryScore createEntryScore(String areaCode, int studentType, List<String> rowData) {
        //最低分数线没有的区表示该学校不在该区招生。
        if (rowData.get(7) == null || rowData.get(7).trim().length() == 0) {
            return null;
        }
        TSchoolEntryScore tSchoolEntryScore = new TSchoolEntryScore();
        tSchoolEntryScore.setEnrollYear(Integer.parseInt(rowData.get(0)));
        tSchoolEntryScore.setBatchNo(Byte.parseByte(rowData.get(2)));//批次编号
        tSchoolEntryScore.setStudentType(studentType);//不限户籍
        tSchoolEntryScore.setSchoolId(Integer.parseInt(rowData.get(5)));//学校编号

        tSchoolEntryScore.setAreaCode(areaCode);
        tSchoolEntryScore.setMinScore(Integer.parseInt(rowData.get(7)));//最低分数
        tSchoolEntryScore.setMinScoreSeq(Integer.parseInt(rowData.get(8)));//最低分时速同分序号
        tSchoolEntryScore.setLastStudentSeq(Integer.parseInt(rowData.get(9)));//末位考生志愿序号
        tSchoolEntryScore.setLastStudentScore(Integer.parseInt(rowData.get(10)));//末位考生分数
        tSchoolEntryScore.setLastStudentSameSeq(Integer.parseInt(rowData.get(11)));//末位考生同分序号
        tSchoolEntryScore.setMaxEntrySeq(Integer.parseInt(rowData.get(12)));//最大志愿序号
        tSchoolEntryScore.setScoreEnrolled(tSchoolEntryScore.getLastStudentScore());

        tSchoolEntryScore.setCreateTime(new Date());
        return tSchoolEntryScore;
    }

    private static String makeInsertSql(TSchoolEntryScore tSchoolEntryScore) {
        if (tSchoolEntryScore == null) {
            return "";
        }
        StringBuilder sqlBuffer = new StringBuilder();
        String s1 = "insert into t_school_entry_score (school_id, enroll_year, enroll_number, "
                + "student_type, min_score, score_enrolled, "
                + "create_time, batch_no, min_score_seq, "
                + "last_student_score, last_student_seq, last_student_same_seq, "
                + "max_entry_seq, area_code) values (";
        sqlBuffer.append(s1);
        sqlBuffer.append(tSchoolEntryScore.getSchoolId()).append(",")
                .append(tSchoolEntryScore.getEnrollYear()).append(",")
                .append(tSchoolEntryScore.getEnrollNumber()).append(",")
                .append(tSchoolEntryScore.getStudentType()).append(",")
                .append(tSchoolEntryScore.getMinScore()).append(",")
                .append(tSchoolEntryScore.getScoreEnrolled()).append(",")
                .append("'").append(dataFormat.format(tSchoolEntryScore.getCreateTime())).append("',")
                .append(tSchoolEntryScore.getBatchNo()).append(",")
                .append(tSchoolEntryScore.getMinScoreSeq()).append(",")
                .append(tSchoolEntryScore.getLastStudentScore()).append(",")
                .append(tSchoolEntryScore.getLastStudentSeq()).append(",")
                .append(tSchoolEntryScore.getLastStudentSameSeq()).append(",")
                .append(tSchoolEntryScore.getMaxEntrySeq()).append(",")
                .append("'").append(tSchoolEntryScore.getAreaCode()).append("'").append(");\n");
        return sqlBuffer.toString();

    }
    
    

    @Override
    public void loadSchoolEnrollPaln() {
        try {
            /*
            * 初始化地区
             */
            initAreaCode();
            String dataFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/广州市学校招生计划.xlsx";
            BigExcelUtil bigExlUtil = new BigExcelUtil();
            int[] minColumns = {10, 0};//列
            Map<String, List<List<String>>> metadata = bigExlUtil.readExcelData(dataFile, 0, 0, minColumns);
            List<List<String>> sheetData1 = metadata.get("0");//第一个工作表格
            importSchoolEnrollPaln(sheetData1);
        } catch (IOException ex) {
            Logger.getLogger(SchoolDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void importSchoolEnrollPaln(List<List<String>> sheetData) throws IOException {
        sheetData.remove(0);
        StringBuilder sqlBuffer = new StringBuilder();

        List<TSchoolEnrollPlan> tSchoolEntryScoreList = readSchoolEnrollPlan(sheetData);
        tSchoolEntryScoreList.stream().forEach(p -> {
            String sql = makeInsertEnrollPlanSql(p);
            sqlBuffer.append(sql);
        });
        String sqlFileName = "pub_school_enroll_plan_" + System.currentTimeMillis() + ".sql";
        String sqlFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/" + sqlFileName;
        TextUtil.writeToFile(sqlBuffer.toString(), sqlFile);
    }
    
    private String makeInsertEnrollPlanSql(TSchoolEnrollPlan tSchoolEnrollPlan){
          if (tSchoolEnrollPlan == null) {
            return "";
        }
        StringBuilder sqlBuffer = new StringBuilder();
        String s1 = "insert into t_school_enroll_plan (school_id, year, enroll_type, "
                + "enroll_number, accommodation_number, plan_location_names, accommodation_number_desc,"
                + "batch_name) values (";
        sqlBuffer.append(s1);
        sqlBuffer.append(tSchoolEnrollPlan.getSchoolId()).append(",")
                .append(tSchoolEnrollPlan.getYear()).append(",")
                .append(tSchoolEnrollPlan.getEnrollType()).append(",")
                .append(tSchoolEnrollPlan.getEnrollNumber()).append(",")
                .append(tSchoolEnrollPlan.getAccommodationNumber()).append(",")
                .append("'").append(tSchoolEnrollPlan.getPlanLocationNames()).append("',")
                .append("'").append(tSchoolEnrollPlan.getAccommodationNumberDesc()).append("',")
                .append("'").append(tSchoolEnrollPlan.getBatchName()).append("');\n");
        return sqlBuffer.toString();
    }
}
