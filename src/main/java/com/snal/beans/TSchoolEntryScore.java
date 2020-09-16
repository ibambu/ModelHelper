package com.snal.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * t_school_entry_score
 * @author 
 */
public class TSchoolEntryScore implements Serializable {
    private Integer id;

    /**
     * 学校ID，对应 t_school.id
     */
    private Integer schoolId;

    /**
     * 招生年份，例如 2020 。
     */
    private Integer enrollYear;

    /**
     * 招生人数
     */
    private Integer enrollNumber;

    /**
     * 学生身份类型，1 户籍生  2 非户籍生 
     */
    private Integer studentType;

    /**
     * 最低分数
     */
    private Integer minScore;

    /**
     * 实际录取最低分（已废弃）
     */
    private Integer scoreEnrolled;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 录取批次
     */
    private Byte batchNo;

    /**
     * 最低分数同分序号，是指向该学校投档考生最低分数的同分最大序号。
     */
    private Integer minScoreSeq;

    /**
     * 末位考生分数
     */
    private Integer lastStudentScore;

    /**
     * 末位考生志愿序号，是指向该校投档最后一名考生的志愿序号。
     */
    private Integer lastStudentSeq;

    /**
     * 末位考生同分序号，是指向该学校投档最后一名考生分数的同分序号。
     */
    private Integer lastStudentSameSeq;

    /**
     * 最大志愿序号，是指向该学校投档考生中最大的志愿序号。
     */
    private Integer maxEntrySeq;

    /**
     * 计划招生区域，对应political_location.location_code区县级编码。
     */
    private String areaCode;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getEnrollYear() {
        return enrollYear;
    }

    public void setEnrollYear(Integer enrollYear) {
        this.enrollYear = enrollYear;
    }

    public Integer getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(Integer enrollNumber) {
        this.enrollNumber = enrollNumber;
    }

    public Integer getStudentType() {
        return studentType;
    }

    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public Integer getScoreEnrolled() {
        return scoreEnrolled;
    }

    public void setScoreEnrolled(Integer scoreEnrolled) {
        this.scoreEnrolled = scoreEnrolled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Byte batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getMinScoreSeq() {
        return minScoreSeq;
    }

    public void setMinScoreSeq(Integer minScoreSeq) {
        this.minScoreSeq = minScoreSeq;
    }

    public Integer getLastStudentScore() {
        return lastStudentScore;
    }

    public void setLastStudentScore(Integer lastStudentScore) {
        this.lastStudentScore = lastStudentScore;
    }

    public Integer getLastStudentSeq() {
        return lastStudentSeq;
    }

    public void setLastStudentSeq(Integer lastStudentSeq) {
        this.lastStudentSeq = lastStudentSeq;
    }

    public Integer getLastStudentSameSeq() {
        return lastStudentSameSeq;
    }

    public void setLastStudentSameSeq(Integer lastStudentSameSeq) {
        this.lastStudentSameSeq = lastStudentSameSeq;
    }

    public Integer getMaxEntrySeq() {
        return maxEntrySeq;
    }

    public void setMaxEntrySeq(Integer maxEntrySeq) {
        this.maxEntrySeq = maxEntrySeq;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}