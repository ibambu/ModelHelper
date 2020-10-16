/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snal.beans;

/**
 *
 * @author luotao
 */
public class School {
    private int schoolId;
    private String schoolName;
    
    private int schoolType;
    
    private String batchNo;
    private int schoolLevel;
    private String headmaster;
    private String locationCode;
    private String address;
    private String officalWebsite;
    private String telephone;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(int schoolLevel) {
        this.schoolLevel = schoolLevel;
    }

    public String getHeadmaster() {
        return headmaster;
    }

    public void setHeadmaster(String headmaster) {
        this.headmaster = headmaster;
    }

    public String getOfficalWebsite() {
        return officalWebsite;
    }

    public void setOfficalWebsite(String officalWebsite) {
        this.officalWebsite = officalWebsite;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    
    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(int schoolType) {
        this.schoolType = schoolType;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
    
    
    
}
