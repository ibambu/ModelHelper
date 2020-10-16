package com.snal.main;

import com.snal.beans.School;
import com.snal.beans.TSchoolEntryScore;
import com.snal.handler.ISchoolDataHandler;
import com.snal.handler.impl.SchoolDataHandler;
import com.snal.util.excel.BigExcelUtil;
import com.snal.util.text.TextUtil;
import com.snal.util.word.WoldUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SchoolMain {

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

    public static void main(String[] args) throws IOException {
        /**
         * 初始化地区
         */
//        initAreaCode();
//        String dataFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/2020年广州市普通高中学校录取分数.xlsx";
//        BigExcelUtil bigExlUtil = new BigExcelUtil();
//        int[] minColumns = {24, 12};//列
//        Map<String, List<List<String>>> metadata = bigExlUtil.readExcelData(dataFile, 0, 1, minColumns);
//        List<List<String>> sheetData1 = metadata.get("0");//第一个工作表格
//        List<List<String>> sheetData2 = metadata.get("1");//第二个工作表格
//        importPubEntryScore(sheetData1);
//        importPrivateEntryScore(sheetData2);

//        String dataFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/2020年广州普通高中学校大全.xlsx";
//        importSchool(dataFile);
//        String dataFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/2020年广州市普通高中学校录取分数.xlsx";
//        importPubEntryScore(dataFile);
//        String descDir = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/学校介绍";
//        String sql = createSchoolDescSql(descDir);
//        System.out.print(sql);

        ISchoolDataHandler handler = new SchoolDataHandler();
        handler.loadSchoolEnrollPaln();
    }
    
    static class Score{
        private Integer score;
        private String name;

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Score(Integer score, String name) {
            this.score = score;
            this.name = name;
        }
        
        
        
    }

    private static void subListTest() {
        List<Score> aa = new ArrayList();
        aa.add(new Score(5,"E"));
        aa.add(new Score(2,"B"));
        aa.add(new Score(4,"D"));
        aa.add(new Score(3,"C"));
        aa.add(new Score(1,"A"));

        aa = aa.stream().sorted(Comparator.comparingInt(p->p.getScore())).collect(Collectors.toList());

        aa.stream().forEach(p->{
            System.out.println(p.getName() +"  "+p.getScore());
        });

    }

    public static void saveToFile(String destUrl) {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        int BUFFER_SIZE = 1024;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        try {
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            fos = new FileOutputStream("/home/luotao/test/aa.jpg");
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fos.flush();
        } catch (IOException e) {
        } catch (ClassCastException e) {
        } finally {
            try {
                fos.close();
                bis.close();
                httpUrl.disconnect();
            } catch (IOException e) {
            } catch (NullPointerException e) {
            }
        }
    }

    private static void importPrivateEntryScore(List<List<String>> sheetData) throws IOException {

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

    private static void importPubEntryScore(List<List<String>> sheetData) throws IOException {
        sheetData.remove(0);
        sheetData.remove(0);
        StringBuilder sqlBuffer = new StringBuilder();

        List<TSchoolEntryScore> tSchoolEntryScoreList = readPubTSchoolEntryScore(sheetData);
        tSchoolEntryScoreList.stream().forEach(p -> {
            String sql = makeInsertSql(p);
            sqlBuffer.append(sql);
        });
        String sqlFileName = "public_school_score_" + System.currentTimeMillis() + ".sql";
        String sqlFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/" + sqlFileName;
        TextUtil.writeToFile(sqlBuffer.toString(), sqlFile);

    }

    private static void importSchool(String dataFile) throws IOException {
        BigExcelUtil bigExlUtil = new BigExcelUtil();
        int[] minColumns = {13};//13 列
        Map<String, List<List<String>>> metadata = bigExlUtil.readExcelData(dataFile, 0, 0, minColumns);
        List<List<String>> sheetData = metadata.get("0");//第一个工作表格

        List<School> schoolList = readSchool(sheetData);
        StringBuilder sqlBuffer = new StringBuilder();
        schoolList.stream().forEach(p -> {
            sqlBuffer.append(makeSchoolInsertSql(p));
        });
        String sqlFile = "/home/luotao/lcwork/lcsvn/中考志愿推荐/志愿填报/02需求管理/test_school1.sql";
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
        school.setBatchNo(rowData.get(4));
        if (rowData.get(6) != null && rowData.get(6).trim().length() > 0) {
            school.setSchoolLevel(Integer.parseInt(rowData.get(6)));
        }
        school.setHeadmaster(rowData.get(8));
        String locationCode = areaMap.get(rowData.get(9));
        school.setLocationCode(locationCode);//所属区域
        school.setAddress(rowData.get(10));//详细地址

        school.setOfficalWebsite(rowData.get(11));//官网
        school.setTelephone(rowData.get(12));//联系电话

        return school;
    }

    private static String makeSchoolInsertSql(School school) {
        StringBuilder sqlbuffer = new StringBuilder();
        sqlbuffer.append("insert into t_school(id,name,batch_no,school_type,school_level,headmaster,address,telephone,official_website,location_code) values (");
        sqlbuffer.append(school.getSchoolId()).append(",")
                .append("'").append((school.getSchoolName())).append("',")
                .append("'").append(school.getBatchNo()).append("',")
                .append(school.getSchoolType()).append(",")
                .append(school.getSchoolLevel()).append(",")
                .append("'").append(school.getHeadmaster()).append("',")
                .append("'").append(school.getAddress()).append("',")
                .append("'").append(school.getTelephone()).append("',")
                .append("'").append(school.getOfficalWebsite()).append("',")
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
        String areaName = rowData.get(7);

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
                if(areaName.equals("全市")){
                    System.out.println("全市"+  rowData.get(4));
                }
                resultList.addAll(createPubEntryScore(areaCode, rowData));
            }
        }

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

    private static String createSchoolDescSql(String filePath) {
        File rootFile = new File(filePath);
        StringBuilder sqlbuffer = new StringBuilder();
        if (rootFile.exists()) {
            File[] docFiles = rootFile.listFiles();
            for (File file : docFiles) {
                String file1 = file.getAbsolutePath();
                String text = WoldUtil.readWord(file1);
                System.out.println(file.getName());
                int schoolId = Integer.parseInt(file.getName().replaceAll(".doc", ""));
                String sql = "update t_school set school_intro ='" + text + "' where id=" + schoolId + ";";
                sqlbuffer.append(sql).append("\n");
            }
        }
        return sqlbuffer.toString();
    }
}
