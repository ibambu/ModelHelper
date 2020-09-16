package com.snal.dao;

import com.snal.beans.TSchoolEntryScore;

public interface TSchoolEntryScoreDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TSchoolEntryScore record);

    int insertSelective(TSchoolEntryScore record);

    TSchoolEntryScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TSchoolEntryScore record);

    int updateByPrimaryKey(TSchoolEntryScore record);
}