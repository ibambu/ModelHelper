package com.snal.main;

import com.snal.beans.TSchoolEntryScore;
import com.snal.util.excel.BigExcelUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolMain {

    private static Map<String, String> areaMap = new HashMap<>();

    public static void main(String[] args) {
        /**
         * 初始化地区
         */
        initAreaCode();

        String dataFile = "D:\\lcwork\\codes\\tools\\school-tool\\ModelHelper\\民办学校录取分数线.xlsx";
        BigExcelUtil bigExlUtil = new BigExcelUtil();
        int[] minColumns = {13};//13 列
        Map<String, List<List<String>>> metadata = bigExlUtil.readExcelData(dataFile, 0, 0, minColumns);
        List<List<String>> sheetData = metadata.get("0");//第一个工作表格

        List<TSchoolEntryScore> tSchoolEntryScoreList = readTSchoolEntryScore(sheetData);
        tSchoolEntryScoreList.stream().forEach(p->{
            makeInsertSql(p);
        });

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
    }
    private static List<TSchoolEntryScore> readTSchoolEntryScore(List<List<String>> sheetData) {
        List<TSchoolEntryScore> tSchoolEntryScoreList = new ArrayList<>();
        sheetData.stream().forEach(rowData -> {
            List<TSchoolEntryScore> tSchoolEntryScores = createPrivateEntryScore(rowData);
            tSchoolEntryScoreList.addAll(tSchoolEntryScores);
        });
        return tSchoolEntryScoreList;
    }

    /**
     * 民办学校录取分数线
     *
     * @param rowData
     * @return
     */
    private static List<TSchoolEntryScore> createPrivateEntryScore(List<String> rowData) {
        List<TSchoolEntryScore> resultList = new ArrayList<>();
        String areaName = rowData.get(6);
        if ("老三区".equals(areaName)) {
            String areaCode1 = areaMap.get("越秀区");
            resultList.add(createEntryScore(areaCode1, rowData));

            String areaCode2 = areaMap.get("荔湾区");
            resultList.add(createEntryScore(areaCode2, rowData));

            String areaCode3 = areaMap.get("海珠区");
            resultList.add(createEntryScore(areaCode3, rowData));
        } else {
            String areaCode = areaMap.get(areaName);
            resultList.add(createEntryScore(areaCode, rowData));
        }
        return resultList;
    }

    private static TSchoolEntryScore createEntryScore(String areaCode, List<String> rowData) {
        //最低分数线没有的区表示该学校不在该区招生。
        if (rowData.get(7) == null || rowData.get(7).trim().length() == 0) {
            return null;
        }
        TSchoolEntryScore tSchoolEntryScore = new TSchoolEntryScore();
        tSchoolEntryScore.setEnrollYear(Integer.parseInt(rowData.get(0)));
        tSchoolEntryScore.setBatchNo(Byte.parseByte(rowData.get(2)));//批次编号
        tSchoolEntryScore.setStudentType(0);//不限户籍
        tSchoolEntryScore.setSchoolId(Integer.parseInt(rowData.get(5)));//学校编号

        tSchoolEntryScore.setAreaCode(areaCode);
        tSchoolEntryScore.setMinScore(Integer.parseInt(rowData.get(8)));//最低分数
        tSchoolEntryScore.setMinScoreSeq(Integer.parseInt(rowData.get(9)));//最低分时速同分序号
        tSchoolEntryScore.setLastStudentSeq(Integer.parseInt(rowData.get(10)));//末位考生志愿序号
        tSchoolEntryScore.setLastStudentScore(Integer.parseInt(rowData.get(11)));//末位考生分数
        tSchoolEntryScore.setLastStudentSameSeq(Integer.parseInt(rowData.get(12)));//末位考生同分序号
        tSchoolEntryScore.setMaxEntrySeq(Integer.parseInt(rowData.get(13)));//最大志愿序号
        return tSchoolEntryScore;
    }


    private static String makeInsertSql(TSchoolEntryScore tSchoolEntryScore) {
        StringBuilder sqlBuffer = new StringBuilder();
        String s1 = "    insert into t_school_entry_score (school_id, enroll_year, enroll_number, \n" +
                "      student_type, min_score, score_enrolled, \n" +
                "      create_time, batch_no, min_score_seq, \n" +
                "      last_student_score, last_student_seq, last_student_same_seq, \n" +
                "      max_entry_seq, area_code) values (";
        sqlBuffer.append(s1);
        sqlBuffer.append(tSchoolEntryScore.getSchoolId()).append(",")
                .append(tSchoolEntryScore.getEnrollYear()).append(",")
                .append(tSchoolEntryScore.getEnrollNumber()).append(",")
                .append(tSchoolEntryScore.getStudentType()).append(",")
                .append(tSchoolEntryScore.getMinScore()).append(",")
                .append(tSchoolEntryScore.getScoreEnrolled()).append(",")
                .append("'").append(tSchoolEntryScore.getCreateTime()).append("',")
                .append(tSchoolEntryScore.getBatchNo()).append(",")
                .append(tSchoolEntryScore.getMinScoreSeq()).append(",")
                .append(tSchoolEntryScore.getLastStudentScore()).append(",")
                .append(tSchoolEntryScore.getLastStudentSeq()).append(",")
                .append(tSchoolEntryScore.getLastStudentSameSeq()).append(",")
                .append(tSchoolEntryScore.getMaxEntrySeq()).append(",")
                .append("'").append(tSchoolEntryScore.getAreaCode()).append("'").append(");\n");

        System.out.println(sqlBuffer.toString());
        return sqlBuffer.toString();


    }
}
