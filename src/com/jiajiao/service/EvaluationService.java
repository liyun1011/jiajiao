package com.jiajiao.service;


import com.jiajiao.bean.Evaluation;

import java.util.List;
import java.util.Map;

public interface EvaluationService {

    public Map<String, Object> evaluate(Map<String, Object> params);

    public List<Evaluation> findByMemberId(int memberId);

    public int getEvaluationById(int id,int oId);
}
