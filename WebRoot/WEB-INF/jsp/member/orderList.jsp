<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.jiajiao.bean.Member"%>
<%@page import="com.jiajiao.bean.District"%>
<%@page import="com.jiajiao.bean.MemberOrderTeacher"%>
<%@page import="com.jiajiao.bean.Orders"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	Member member = (Member) session.getAttribute("member");
	if (member == null) {
		response.sendRedirect(basePath);
		return;
	}
%>

<jsp:include page="header.jsp"></jsp:include>



			<!--end一行的左-->
			<div class="r_cell box_r">

				<dl class="tab_nav r_tab_nav">
					<dd>
						<a href="javascript:void(0)" class="tab_light">我的家教订单</a>
					</dd>
				</dl>
				<div class="@*tab_box*@ list_tb">
					<div>
						<table class="list_table hover_table">
							<thead>
								<tr>
									<th>订单编号</th>
									<th>授课科目</th>
									<th>授课教员</th>
									<th>支付金额</th>
									<th>发布时间</th>
									<th>预约状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<%
							List<Orders> orderList = (List<Orders>)request.getAttribute("orderList");
							if(orderList!=null){
								for(int i=0;i<orderList.size();i++){
									Orders order = orderList.get(i);
							%>
									<tr >
										<td>
											
	                                    <%=order.getOrderCode()%>
	                                
										</td>
										<td><%=order.getCourseName() %></td>
										<td data-id="63458" class="">
											<span class="blue_link">
												<a target="_blank" href="../teacher/teacherInfoPage.action?tId=<%=order.getTeacherId() %>">
													<%=order.getTeacherName() %>
												</a>
											</span>
										</td>
										<td><%=order.getCoursePrice() %></td>
										<td>
											<span class="data_2">
	                                    		<%=order.getPublicTime().substring(0,10) %>
	                                    		<p><%=order.getPublicTime().substring(10,18) %></p>
	                                		</span>
										</td>
										<td>
										<%
										int state = order.getOrderStatus();
										if(state==22){ 
										%>
										发布中
										<%}else if(state==23){ %>
										教员申请
										<%}else if(state==24){ %>
										授课中
										<%}else if(state==25){ %>
										已完成
										<%}else if(state==26){ %>
										订单失败
										<%}%>
										</td>
										<td>
											<span class="blue_link">
												<a href="../order/orderInfo.action?oId=<%=order.getoId() %>" target="_blank" class="btndel">查看</a>
	                         	       		</span><br>
											<span id="gtzfBtn" class="blue_link">
												<a href="javascript:void(0)" class="btndel">沟通支付</a>
	                         	       		</span>
										</td>
										<!-- 模态弹窗的结构 -->
										<div id="myModal" class="modal" style="display: none">
											<div class="modal-content">
												<span class="close">&times;</span>
												<h2>教员联系方式</h2>
												<p>电话：<%=order.getContactPhone()%></p>
												<p>微信：<%=order.getWxNumber()%></p>
												<br>
												<h2>付款方式</h2><br>
												<img src="../static/img/wxpay1.jpg" height="350px" width="300px"/>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<img src="../static/img/zfbpay1.jpg" height="350px" width="300px"/>
											</div>
										</div>
									</tr>
							<%
								}
							}
							%>
							</tbody>
						</table>
					</div>
				</div>
			</div>


<script src="../static/js/layer.js" ></script>
<script>
	// 获取模态弹窗
	var modal = document.getElementById("myModal");

	// 获取打开弹窗的按钮
	var btn = document.getElementById("gtzfBtn");

	// 获取 <span> 元素，用于关闭模态弹窗
	var span = document.getElementsByClassName("close")[0];

	// 当用户点击按钮时，打开模态弹窗
	btn.onclick = function() {
		modal.style.display = "block";
	}

	// 当用户点击 <span> (x)，关闭模态弹窗
	span.onclick = function() {
		modal.style.display = "none";
	}

	// 当用户点击模态弹窗外部区域时，关闭模态弹窗
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
</script>

		</div>
		<!--end一行-->
<style>
	/* 模态弹窗的样式 */
	.modal {
		display: none;
		position: fixed;
		z-index: 1;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		overflow: auto;
		background-color: rgb(0,0,0);
		background-color: rgba(0,0,0,0.4);
	}

	.modal-content {
		background-color: #fefefe;
		margin: 15% auto;
		padding: 20px;
		border: 1px solid #888;
		width: 50%;
	}

	.close {
		color: #aaa;
		float: right;
		font-size: 28px;
		font-weight: bold;
	}

	.close:hover,
	.close:focus {
		color: black;
		text-decoration: none;
		cursor: pointer;
	}

</style>
<jsp:include page="../footer.jsp"></jsp:include>