package com.jiajiao.service.impl;

import com.jiajiao.bean.Evaluation;
import com.jiajiao.dao.EvaluationDao;
import com.jiajiao.dao.MemberDao;
import com.jiajiao.dao.TeacherDao;
import com.jiajiao.service.EvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("evaluationService")
@Transactional
public class EvaluationServiceImpl implements EvaluationService {
    @Resource
    private EvaluationDao evaluationDao;

    @Override
    public Map<String, Object> evaluate(Map<String, Object> params) {
        return Collections.emptyMap();
    }

    @Override
    public List<Evaluation> findByMemberId(int memberId) {
        return evaluationDao.findByMemberId(memberId);
    }

    @Override
    public int getEvaluationById(int id,int oId) {
        return evaluationDao.getEvaluationById(id,oId);
    }
}
