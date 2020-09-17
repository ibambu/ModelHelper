package com.snal.main;

import com.snal.beans.School;
import com.snal.beans.TSchoolEntryScore;
import com.snal.util.excel.BigExcelUtil;
import com.snal.util.text.TextUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolMain {

    private static Map<String, String> areaMap = new HashMap<>();
    private static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws IOException {
        /**
         * 初始化地区
         */
        initAreaCode();
//        String dataFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/民办学校录取分数线.xlsx";
//        importPrivateEntryScore(dataFile);

//        String dataFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/2020年广州普通高中学校大全.xlsx";
//        importSchool(dataFile);

        String dataFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/公办学校录取分数线.xlsx";
        importPubEntryScore(dataFile);

    }

    private static void importPrivateEntryScore(String dataFile) throws IOException {
        BigExcelUtil bigExlUtil = new BigExcelUtil();
        int[] minColumns = {12};//13 列
        Map<String, List<List<String>>> metadata = bigExlUtil.readExcelData(dataFile, 0, 0, minColumns);
        List<List<String>> sheetData = metadata.get("0");//第一个工作表格

        StringBuilder sqlBuffer = new StringBuilder();

        List<TSchoolEntryScore> tSchoolEntryScoreList = readTSchoolEntryScore(sheetData);
        tSchoolEntryScoreList.stream().forEach(p -> {
            String sql = makeInsertSql(p);
            sqlBuffer.append(sql);
        });
        String sqlFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/test_school_score.sql";
        TextUtil.writeToFile(sqlBuffer.toString(), sqlFile);

    }
    private static void importPubEntryScore(String dataFile) throws IOException {
        BigExcelUtil bigExlUtil = new BigExcelUtil();
        int[] minColumns = {12};//13 列
        Map<String, List<List<String>>> metadata = bigExlUtil.readExcelData(dataFile, 0, 0, minColumns);
        List<List<String>> sheetData = metadata.get("0");//第一个工作表格

        StringBuilder sqlBuffer = new StringBuilder();

        List<TSchoolEntryScore> tSchoolEntryScoreList = readPubTSchoolEntryScore(sheetData);
        tSchoolEntryScoreList.stream().forEach(p -> {
            String sql = makeInsertSql(p);
            sqlBuffer.append(sql);
        });
        String sqlFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/test_school_score.sql";
        TextUtil.writeToFile(sqlBuffer.toString(), sqlFile);

    }
    
    private static void importSchool(String dataFile) throws IOException {
        BigExcelUtil bigExlUtil = new BigExcelUtil();
        int[] minColumns = {12};//13 列
        Map<String, List<List<String>>> metadata = bigExlUtil.readExcelData(dataFile, 0, 0, minColumns);
        List<List<String>> sheetData = metadata.get("0");//第一个工作表格

        List<School> schoolList = readSchool(sheetData);
        StringBuilder sqlBuffer = new StringBuilder();
        schoolList.stream().forEach(p -> {
            sqlBuffer.append(makeSchoolInsertSql(p));
        });
        String sqlFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/test_school.sql";
        TextUtil.writeToFile(sqlBuffer.toString(), sqlFile);
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

    /**
     * 读取学校
     */
    private static List<School> readSchool(List<List<String>> sheetData) {
        List<School> schoolList = new ArrayList<>();
        sheetData.remove(0);
        sheetData.stream().forEach(rowData -> {
            School school = createSchool(rowData);
            schoolList.add(school);
        });
        System.out.println("总记录数目：" + schoolList.size());
        return schoolList;
    }

    private static School createSchool(List<String> rowData) {
        School school = new School();
        school.setSchoolId(Integer.parseInt(rowData.get(0)));//学校ID
        school.setSchoolName(rowData.get(1));//学校名称
        school.setSchoolType(Integer.parseInt(rowData.get(2)));//学校性质

        String locationCode = areaMap.get(rowData.get(7));
        school.setLocationCode(locationCode);

        school.setAddress(rowData.get(8));

        return school;
    }

    private static String makeSchoolInsertSql(School school) {
        StringBuilder sqlbuffer = new StringBuilder();
        sqlbuffer.append("insert into t_school(id,name,school_type,address,location_code) values (");
        sqlbuffer.append(school.getSchoolId()).append(",")
                .append("'").append((school.getSchoolName())).append("',")
                .append(school.getSchoolType()).append(",")
                .append("'").append(school.getAddress()).append("',")
                .append("'").append(school.getLocationCode()).append("'")
                .append(");\n");
        return sqlbuffer.toString();
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
        return resultList;
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
        tSchoolEntryScore1.setCreateTime(new Date());

        tSchoolEntryScore2.setEnrollYear(Integer.parseInt(rowData.get(0)));
        tSchoolEntryScore2.setBatchNo(Byte.parseByte(rowData.get(2)));//批次编号
        tSchoolEntryScore2.setStudentType(2);//户籍生
        tSchoolEntryScore2.setSchoolId(Integer.parseInt(rowData.get(4)));//学校编号
        tSchoolEntryScore2.setCreateTime(new Date());

        tSchoolEntryScore1.setAreaCode(areaCode);
        tSchoolEntryScore1.setMinScore(Integer.parseInt(rowData.get(7)));//最低分数
        tSchoolEntryScore1.setMinScoreSeq(Integer.parseInt(rowData.get(8)));//最低分时速同分序号
        tSchoolEntryScore1.setLastStudentSeq(Integer.parseInt(rowData.get(9)));//末位考生志愿序号
        tSchoolEntryScore1.setLastStudentScore(Integer.parseInt(rowData.get(10)));//末位考生分数
        tSchoolEntryScore1.setLastStudentSameSeq(Integer.parseInt(rowData.get(11)));//末位考生同分序号
        tSchoolEntryScore1.setMaxEntrySeq(Integer.parseInt(rowData.get(17)));//最大志愿序号
        tSchoolEntryScore1.setScoreEnrolled(tSchoolEntryScore1.getLastStudentScore());

        tSchoolEntryScore2.setAreaCode(areaCode);
        tSchoolEntryScore2.setMinScore(Integer.parseInt(rowData.get(12)));//最低分数
        tSchoolEntryScore2.setMinScoreSeq(Integer.parseInt(rowData.get(13)));//最低分时速同分序号
        tSchoolEntryScore2.setLastStudentSeq(Integer.parseInt(rowData.get(14)));//末位考生志愿序号
        tSchoolEntryScore2.setLastStudentScore(Integer.parseInt(rowData.get(15)));//末位考生分数
        tSchoolEntryScore2.setLastStudentSameSeq(Integer.parseInt(rowData.get(16)));//末位考生同分序号
        tSchoolEntryScore2.setMaxEntrySeq(Integer.parseInt(rowData.get(17)));//最大志愿序号
        tSchoolEntryScore2.setScoreEnrolled(tSchoolEntryScore1.getLastStudentScore());

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
}
