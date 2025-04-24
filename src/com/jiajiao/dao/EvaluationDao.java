package com.jiajiao.dao;

import com.jiajiao.bean.Evaluation;

import java.util.List;

public interface EvaluationDao {

    public List<Evaluation> findByMemberId(int memberId);

    public int getEvaluationById(int id,int oId);

    List<Evaluation> findByTeacherId(int teacherId);
}
