package com.jiajiao.bean;

//教员接单表
public class TakeOrders {
	private Integer toId;// 编号
	private Integer oId;// 家教需求订单编号

	public Integer getToId() {
		return toId;
	}

	public void setToId(Integer toId) {
		this.toId = toId;
	}

	public Integer getoId() {
		return oId;
	}

	public void setoId(Integer oId) {
		this.oId = oId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Integer getContactGender() {
		return contactGender;
	}

	public void setContactGender(Integer contactGender) {
		this.contactGender = contactGender;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTakeStatus() {
		return takeStatus;
	}

	public void setTakeStatus(Integer takeStatus) {
		this.takeStatus = takeStatus;
	}

	public Integer getCourcePrice() {
		return courcePrice;
	}

	public void setCourcePrice(Integer courcePrice) {
		this.courcePrice = courcePrice;
	}

	private String orderCode;// 家教需求订单编号
	private String contactName;// 家长姓名
	private Integer contactGender;// 家长性别
	private String courseName;// 授课科目
	private Integer areaId;// 区域
	private String address;// 地址
	private Integer teacherId;// 教员编号
	private String teacherName;// 教员姓名
	private Integer memberId;// 学员编号
	private String takeTime;// 接单时间
	private Integer courcePrice;// 支付金额
	private Integer takeStatus;// 接单状态
	private String remark;// 备注



	@Override
	public String toString() {
		return "TakeOrders [address=" + address + ", areaId=" + areaId
				+ ", contactGender=" + contactGender + ", contactName="
				+ contactName + ", courcePrice=" + courcePrice
				+ ", courseName=" + courseName + ", memberId=" + memberId
				+ ", oId=" + oId + ", orderCode=" + orderCode + ", remark="
				+ remark + ", takeStatus=" + takeStatus + ", takeTime="
				+ takeTime + ", teacherId=" + teacherId + ", teacherName="
				+ teacherName + ", toId=" + toId + "]";
	}

}
