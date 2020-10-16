package com.snal.beans;

import java.io.Serializable;

/**
 * (TSchoolEnrollPlan)实体类
 *
 * @author luotao
 * @since 2020-10-16 14:07:32
 */
public class TSchoolEnrollPlan implements Serializable {
    private static final long serialVersionUID = 330808242819029426L;
    
    private Integer id;
    
    private Integer schoolId;
    /**
    * 招生年份
    */
    private Integer year;
    /**
    * 录取类别：1 公办  2 民办
    */
    private Integer enrollType;
    /**
    * 参考字典表数据：DICT_HIGHT_SCHOOL_BATCH_NO
    */
    private Integer batchNo;
    /**
    * 招生人数
    */
    private Integer enrollNumber;
    /**
    * 宿位
    */
    private Integer accommodationNumber;
    /**
    * 计划招生区域，为空表示面向全市招生。
    */
    private String panLocationCode;
    /**
    * 招生区域，可能泛指多个区域，例如老三区
    */
    private String planLocationNames;
    /**
    * 录取批次名称
    */
    private String batchName;
    
    private String accommodationNumberDesc;

    public String getAccommodationNumberDesc() {
        return accommodationNumberDesc;
    }

    public void setAccommodationNumberDesc(String accommodationNumberDesc) {
        this.accommodationNumberDesc = accommodationNumberDesc;
    }
    
    


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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getEnrollType() {
        return enrollType;
    }

    public void setEnrollType(Integer enrollType) {
        this.enrollType = enrollType;
    }

    public Integer getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Integer batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(Integer enrollNumber) {
        this.enrollNumber = enrollNumber;
    }

    public Integer getAccommodationNumber() {
        return accommodationNumber;
    }

    public void setAccommodationNumber(Integer accommodationNumber) {
        this.accommodationNumber = accommodationNumber;
    }

    public String getPanLocationCode() {
        return panLocationCode;
    }

    public void setPanLocationCode(String panLocationCode) {
        this.panLocationCode = panLocationCode;
    }

    public String getPlanLocationNames() {
        return planLocationNames;
    }

    public void setPlanLocationNames(String planLocationNames) {
        this.planLocationNames = planLocationNames;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

}