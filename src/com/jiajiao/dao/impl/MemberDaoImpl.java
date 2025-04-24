package com.jiajiao.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.jiajiao.bean.Evaluation;
import com.jiajiao.bean.Orders;
import com.jiajiao.bean.TakeOrders;
import com.jiajiao.dao.EvaluationDao;
import com.jiajiao.dao.OrdersDao;
import com.jiajiao.dao.TakeOrdersDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jiajiao.bean.Member;
import com.jiajiao.dao.MemberDao;

@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private EvaluationDao evaluationDao;

	@Resource
	private TakeOrdersDao takeOrdersDao;

	@Resource
	private OrdersDao ordersDao;

	@Override
	public int registerMember(String phone, String pwd) {
		String sql = "insert into t_member(phone,password,registTime) value(?,?,now())";
		int rows = jdbcTemplate.update(sql, phone, pwd);
		return rows;
	}

	@Override
	public Member findByPhone(String phone) {

		String sql = " SELECT memberId,name,phone,password,gender,wxNumber,a.districtId,IFNULL(b.district,'') as district,b.district,address,registTime "
				+ " from t_member a LEFT JOIN t_district b   "
				+ " ON a.districtId = b.districtId" + " WHERE a.phone = ? ";

		try {
			RowMapper<Member> rowMapper = new BeanPropertyRowMapper<Member>(
					Member.class);
			Member member = jdbcTemplate.queryForObject(sql, rowMapper, phone);

			return member;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Member login(String phone, String pwd) {
		//HashMap<String,Object> memberMap = new HashMap<>();

		String sql = " SELECT memberId,name,phone,password,gender,wxNumber,a.districtId,IFNULL(b.district,'') as district,address,registTime,isUse "
				+ " from t_member a LEFT JOIN t_district b   "
				+ " ON a.districtId = b.districtId"
				+ " WHERE a.phone = ? AND a.password = ? ";

		try {
			RowMapper<Member> rowMapper = new BeanPropertyRowMapper<Member>(
					Member.class);
			Member member = jdbcTemplate.queryForObject(sql, rowMapper,
					new Object[] { phone, pwd });

			//获取累计发布需求数量
			Integer allPushNum = 0;
			//获取累计选择授课教员数量
			Integer allTeachNum = 0;
			//获取当前发布需求数量
			Integer currentPushNum = 0 ;
			String name = member.getName();
			if (!name.equals("")) {
				String querySql1 = "SELECT COUNT(b.oId) allPushNum\n" +
						"FROM `t_member` a\n" +
						"LEFT JOIN `t_orders` b ON a.memberId = b.publisher1";
				 allPushNum = jdbcTemplate.queryForObject(querySql1, Integer.class);

				String querySql2 = "SELECT COUNT(b.teacherId) allTeachNum\n" +
						"FROM `t_member` a\n" +
						"LEFT JOIN `t_memberorderteacher` b ON a.memberId = b.memberId";
				allTeachNum = jdbcTemplate.queryForObject(querySql2, Integer.class);

				String querySql3 = "SELECT COUNT(b.oId) allPushNum\n" +
						"FROM `t_member` a\n" +
						"LEFT JOIN `t_orders` b ON a.memberId = b.publisher1\n" +
						"WHERE b.orderStatus = 22";
				currentPushNum = jdbcTemplate.queryForObject(querySql3, Integer.class);
			}


			member.setAllPushNum(allPushNum);
			member.setAllTeachNum(allTeachNum);
			member.setCurrentPushNum(currentPushNum);
			return member;

		} catch (Exception e) {

			return null;
		}

	}

	@Override
	public int updateMemberInfo(String name, int gender, String wxNumber,
			int districtId, String address, int memberId) {
		String sql = "update t_member set name=?,gender=?,wxNumber=?,districtId=?,address=? where memberId=?";

		int row = jdbcTemplate.update(sql, new Object[] { name, gender,
				wxNumber, districtId, address, memberId });

		return row;
	}

	@Override
	public int evaluateTeacher(int score, String evaluationContent, String orderCode,Integer teacherId, Integer memberId) {
		int result = 0;

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String currentTime = formatter.format(date);

		String querySql1 = "SELECT oId FROM t_orders WHERE publisher1=? AND orderCode=?";
		Integer oId = jdbcTemplate.queryForObject(querySql1, Integer.class, new Object[]{memberId,orderCode});
		if (oId==null){
			return result;
		}

		String querySql2 = "SELECT teacherId FROM t_takeorders WHERE memberId=? AND oId=?";
		teacherId = jdbcTemplate.queryForObject(querySql2, Integer.class, new Object[]{memberId,oId});
		if (teacherId==null){
			return result;
		}

		//判断是否存在评价信息
		int evception = evaluationDao.getEvaluationById(memberId,oId);
		if (evception==0){
			//新增评价

			String querySql3 = "SELECT toId FROM t_takeorders WHERE memberId=? AND oId=?";
			Integer toId = jdbcTemplate.queryForObject(querySql3, Integer.class, new Object[]{memberId,oId});
			if (toId==null){
				return result;
			}
			String insertSql = "INSERT INTO t_evaluation (teacherId, memberId,toId,oId,score,evaluationContent,evaluationTime) " +
					"VALUES (?,?,?,?,?,?,?)";
			result = jdbcTemplate.update(insertSql, new Object[] { teacherId, memberId,toId,oId, score, evaluationContent,currentTime});
		}else {
			//修改评价
			String updateSql = "update t_evaluation set score=?,evaluationContent=?,evaluationTime=? where memberId=? and oId=?";
			result = jdbcTemplate.update(updateSql,new Object[]{score,evaluationContent,currentTime,memberId,oId});
		}




		return result;
	}

	@Override
	public int changePasswordByMemberId(String password, int memberId) {

		String sql = "update t_member set password=? where memberId=?";

		int row = jdbcTemplate.update(sql, new Object[] { password, memberId });

		return row;
	}

	@Override
	public Member findByMemberId(int memberId) {

		String sql = " SELECT memberId,name,phone,password,gender,wxNumber,a.districtId,IFNULL(b.district,'') as district,b.district,address,registTime "
				+ " from t_member a LEFT JOIN t_district b   "
				+ " ON a.districtId = b.districtId" + " WHERE a.memberId = ? ";

		try {
			RowMapper<Member> rowMapper = new BeanPropertyRowMapper<Member>(
					Member.class);
			Member member = jdbcTemplate.queryForObject(sql, rowMapper,
					memberId);

			return member;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Member> findAllMember() {

		String sql = "SELECT t_member.memberId,name,phone,gender,wxNumber,districtId,address,registTime, "
				+ " isUse,IFNULL(a.orderNum,0) as orderNum,IFNULL(b.appointNum,0) as appointNum "
				+ " FROM t_member  "
				+ " LEFT JOIN ( "
				+ "	SELECT t_orders.publisher1,COUNT(t_orders.publisher1) as orderNum "
				+ "	FROM t_orders "
				+ "	GROUP BY t_orders.publisher1 "
				+ " )a ON a.publisher1=t_member.memberId "
				+ " LEFT JOIN ( "
				+ "	SELECT t_memberorderteacher.memberId,COUNT(t_memberorderteacher.memberId) as appointNum "
				+ "	FROM t_memberorderteacher "
				+ "	GROUP BY t_memberorderteacher.memberId "
				+ " )b ON b.memberId=t_member.memberId "
				+ " ORDER BY registTime DESC ";

		try {
			RowMapper<Member> rowMapper = new BeanPropertyRowMapper<Member>(
					Member.class);

			List<Member> memberList = jdbcTemplate.query(sql, rowMapper);

			return memberList;

		} catch (Exception e) {

			return null;
		}

	}

	@Override
	public int changeMemberUse(int id, int isUse) {
		String sql = "UPDATE t_member SET isUse=? WHERE memberId=?";
		int rows = jdbcTemplate.update(sql, isUse, id);
		return rows;
	}
	
	@Override
	public int countMemberTotal() {
		String sql = "SELECT COUNT(memberId) FROM t_member ";

		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public int countBanMemberTotal() {
		String sql = "SELECT COUNT(memberId) FROM t_member WHERE isUse=0";

		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

}
