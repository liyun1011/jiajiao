package com.jiajiao.dao.impl;

import com.jiajiao.bean.Evaluation;
import com.jiajiao.dao.EvaluationDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Repository("evaluationDao")
public class EvaluationDaoImpl implements EvaluationDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Evaluation> findByMemberId(int memberId) {

        String sql = "SELECT a.orderCode,c.teacherName,b.`teacherId`,IFNULL(b.score,0) score,IFNULL(b.evaluationContent,'') evaluationContent,b.evaluationTime\n" +
                "FROM t_orders a\n" +
                "LEFT JOIN t_evaluation b ON a.oId = b.`oId` \n" +
                "LEFT JOIN `t_takeorders` d ON d.oId = a.oId \n" +
                "LEFT JOIN `t_teacher` c ON c.`teacherId` = d.`teacherId`\n" +
                "WHERE a.orderStatus = 25 AND a.publisher1 = ?\n" +
                "ORDER BY a.oId DESC ";

        try {
            RowMapper<Evaluation> rowMapper = new BeanPropertyRowMapper<Evaluation>(
                    Evaluation.class);

            List<Evaluation> evaluationList = jdbcTemplate.query(sql, rowMapper,memberId);

            return evaluationList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getEvaluationById(int id,int oId) {
        String sql = "select count(*) from t_evaluation where memberId=? and oId = ?";
        int evaluation = jdbcTemplate.queryForObject(sql, Integer.class,new Object[]{id,oId});
        return evaluation;
    }

    @Override
    public List<Evaluation> findByTeacherId(int teacherId) {
        String sql = "select * from t_evaluation where teacherId = ?";
        RowMapper<Evaluation> rowMapper = new BeanPropertyRowMapper<Evaluation>(
                Evaluation.class);
        return jdbcTemplate.query(sql, rowMapper, teacherId);
    }
}
