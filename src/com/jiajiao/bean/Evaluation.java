package com.jiajiao.bean;

//评价表
public class Evaluation {
	private int eId;
	private Integer teacherId;//教员编号
	private Integer memberId;//学员用户编号
	private Integer toId;//接单编号
	private Integer oId;//订单编号
	private Integer score;//满意度
	private String evaluationContent;//评价内容
	private String evaluationTime;//评价时间

	public String getTeacherName() {
		return TeacherName;
	}

	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	private String orderCode;
	private String TeacherName;
	
	public int getEId() {
		return eId;
	}
	public void setEId(int id) {
		eId = id;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getToId() {
		return toId;
	}
	public void setToId(Integer toId) {
		this.toId = toId;
	}
	public Integer getOId() {
		return oId;
	}
	public void setOId(Integer id) {
		oId = id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getEvaluationContent() {
		return evaluationContent;
	}
	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}
	public String getEvaluationTime() {
		return evaluationTime;
	}
	public void setEvaluationTime(String evaluationTime) {
		this.evaluationTime = evaluationTime;
	}
	
	

}
