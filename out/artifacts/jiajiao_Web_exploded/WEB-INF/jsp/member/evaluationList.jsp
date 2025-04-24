<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.jiajiao.bean.Member"%>
<%@page import="com.jiajiao.bean.Evaluation"%>
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
						<a href="javascript:void(0)" class="tab_light">评价授课教员</a>
					</dd>
				</dl>
				<div class="@*tab_box*@ list_tb">
					<div>
						<table class="list_table hover_table">
							<thead>
								<tr>
									<th>订单编号</th>
									<th>授课教员</th>
									<th>满意度</th>
									<th>评价内容</th>
									<th>评价时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<%
							List<Evaluation> evaluationList = (List<Evaluation>)request.getAttribute("evaluationList");
							if(evaluationList!=null){
								for(int i=0;i<evaluationList.size();i++){
									Evaluation evaluation = evaluationList.get(i);
							%>
									<tr >
										<td>
											
	                                    <%=evaluation.getOrderCode()%>
	                                
										</td>
										<td data-id="63458" class="">
											<span class="blue_link">
												<a target="_blank" href="../teacher/teacherInfoPage.action?tId=<%=evaluation.getTeacherId() %>">
													<%=evaluation.getTeacherName() %>
												</a>
											</span>
										</td>
										<td><%=evaluation.getScore() %></td>
										<td>
											<span class="data_2">
												<%=evaluation.getEvaluationContent()%>
	                                		</span>
										</td>
										<td>
											<span class="data_2">
												<% if (evaluation.getEvaluationTime()!=null){%>
	                                    		<%=evaluation.getEvaluationTime().substring(0,10) %>
	                                    		<p><%=evaluation.getEvaluationTime().substring(10,18) %></p>
												<%}%>
	                                		</span>
										</td>
										<td>
											<span id="pingjiaBtn" class="blue_link" >
												<a href="javascript:void(0)" class="btndel">评价</a>
	                         	       		</span>
										</td>
										<!-- 模态弹窗的结构 -->
										<div id="myModal" class="modal" style="display: none">
											<div class="modal-content">
												<span class="close">&times;</span>
												<h2>教员授课评价</h2>
												<input type="text" id="orderCode" name="orderCode" hidden="hidden" value="<%=evaluation.getOrderCode()%>">
												<input type="text" id="memberId" name="memberId" hidden="hidden" value="<%=member.getMemberId() %>">
												<input type="text" id="teacherId" name="teacherId" hidden="hidden" value="<%=evaluation.getTeacherId()%>">
												<p>满意度：</p>
												<input type="number" id="score" class="fill_text"
													   placeholder="" >
												<p>评价内容：</p>
												<input type="text" id="evaluationContent" class="fill_text"
																	   placeholder="">
												<br>

												<input type="button" id="btnSave"
													   class="round_m transition_a login_btn" value="确定评价"
													   style="width: 410px">
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
	var btn = document.getElementById("pingjiaBtn");

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


	$('#btnSave').click(function () {
		var score = $.trim($("#score").val());
		var evaluationContent = $.trim($("#evaluationContent").val());
		var orderCode = $.trim($("#orderCode").val());
		var memberId = $.trim($("#memberId").val());
		var teacherId = $.trim($("#teacherId").val());
		if (orderCode == '') {
			alert('订单编号不能为空');
			return false;
		}

		var param = {
		};
		param.score = score;
		param.evaluationContent = evaluationContent;
		param.orderCode = orderCode;
		param.memberId =  memberId;
		param.teacherId =  0;
		$.post('evaluateTeacher.action', param, function (res) {
			if (res.success) {
				alert(res.message);
				window.location.reload();

			} else {
				alert(res.message);
			}
		});
	})
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
		width: 30%;
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