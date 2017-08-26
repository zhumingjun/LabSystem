<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改教师界面 -->

<script>
	var BASE_URL='${pageContext.request.contextPath}/admin/';
</script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/webuploader/webuploader.css" />

<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/validform/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validform.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/webuploader/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/webuploader/csystem/userInfoHeadUpload.js"></script>

<style>
	.add_pic{
	    width: 140px;
	    height: 150px;
	    border: 1px dashed #ccc;
	    float: right;
	    margin-right: 10px;
	}
	.filePicker {
		float:left;
	    margin-left: 60px;
	    margin-top: 60px;
	}
	
</style>

<form id="teacherFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/teacher/save" method="post">
	<input type="hidden" id="id" name="id" value="${teacherDomain.id }"/>
	<input type="hidden" id="jobTitleId" name="jobTitle" value="${teacherDomain.jobTitle }"/>
	<input type="hidden" id="mentorStatusId" name="mentorStatus" value="${teacherDomain.mentorStatus }"/>
	<table>
		<tr>
			<td class="lesta-150">工号：</td>
			<td class="lestb">
				<input type="text" id="workCode" name="workCode" class="input_text_a" placeholder="请输入工号" value="${teacherDomain.workCode }">
			</td>
			<td rowspan="4" colspan="2">
				<input type="hidden" id="headImg" name="headImg" />
				<div id="filePicker" class="filePicker">选择图片</div>
				<div class="add_pic" id="add_pic">
					<c:if test="${teacherDomain.headImg==null||teacherDomain.headImg=='' }">
						<img id="head_img" src="${pageContext.request.contextPath}/resources/images/touxiang.png" width="140px;" height="150px;" style="border-radius:5px;"/>
					</c:if>
					<c:if test="${teacherDomain.headImg!=null&&teacherDomain.headImg!='' }">
						<img id="head_img" src="${headImgPath}/${teacherDomain.workCode }/${teacherDomain.headImg }" width="140px;" height="150px;" style="border-radius:5px;"/>
					</c:if>
				</div>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">姓名：</td>
			<td class="lestb">
				<input type="text" id="teachername" name="name" class="input_text_a" placeholder="请输入姓名" value="${teacherDomain.name }">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">性别：</td>
			<td class="lestb">
				<c:if test="${teacherDomain.sex==0 }">
					<input type="radio" name="sex" value="0" checked="checked"/>男
					<input type="radio" name="sex" value="1" />女
				</c:if>
				<c:if test="${teacherDomain.sex==1 }">
					<input type="radio" name="sex" value="0" />男
					<input type="radio" name="sex" value="1" checked="checked"/>女
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">出生日期：</td>
			<td class="lestb">
				<input type="text" name="birthday" class="Wdate" readonly="readonly"  value="<fmt:formatDate value="${teacherDomain.birthday }" type="date"/>" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">教师职称：</td>
			<td class="lestb">
				<select id="jobTitle_select_edit_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${jobTitle }" var="jobTitleDomain">
						<option value="${jobTitleDomain.id }">${jobTitleDomain.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">手机号码：</td>
			<td class="lestb">
				<input type="text" id="teacherphoneNumber" name="phoneNumber" class="input_text_a" ignore="ignore" placeholder="请输入手机号码" value="${teacherDomain.phoneNumber }"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">财务账号：</td>
			<td class="lestb">
				<input type="text" id="finAccount" name="finAccount" class="input_text_a" ignore="ignore" placeholder="请输入财务账号" value="${teacherDomain.finAccount }"/>
			</td>
			<td class="lesta-150">邮箱：</td>
			<td class="lestb">
				<input type="text" id="teacheremail" name="email" class="input_text_a" ignore="ignore" placeholder="请输入邮箱" value="${teacherDomain.email }"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">身份证号：</td>
			<td class="lestb">
				<input type="text" id="teacheridNumber" name="idNumber" class="input_text_a" placeholder="请输入身份证号" value="${teacherDomain.idNumber }"/>
			</td>
			<td class="lesta-150">导师身份：</td>
			<td class="lestb">
				<select id="mentorStatus_select_edit_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${mentorStatus }" var="mentorStatusDomain">
						<option value="${mentorStatusDomain.value }">${mentorStatusDomain.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">办公地址：</td>
			<td class="lestb">
				<input type="text" id="teacherhomeAddress" name="homeAddress" class="input_text_a" placeholder="请输入家庭住址" value="${teacherDomain.homeAddress }"/>
			</td>
			<td class="lesta-150">入职年份：</td>
			<td class="lestb">
				<input type="text" id="entryYear" name="entryYear" class="input_text_a" placeholder="请输入入职年份" value="${teacherDomain.entryYear }"/>
			</td>
		</tr> 
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 300px;" value="确定"/>
</form>

<script>

	$(function(){
		$("#jobTitle_select_edit_id option[value='${teacherDomain.jobTitle}']").attr("selected",true);
		$("#mentorStatus_select_edit_id option[value='${teacherDomain.mentorStatus}']").attr("selected",true);
	});

	//下拉框选择后给隐藏域赋值
	$("#jobTitle_select_edit_id").change(function(){
		var jobTitle_id=$(this).children('option:selected').val();
		$("#jobTitleId").val(jobTitle_id);
	});
	//下拉框选择后给隐藏域赋值
	$("#mentorStatus_select_edit_id").change(function(){
		var mentorStatus_id=$(this).children('option:selected').val();
		$("#mentorStatusId").val(mentorStatus_id);
	});
	$("#saveButton").click(function(){
		
		var form = $("#teacherFormId");
		form.ajaxSubmit(function(result){
			if(result=='success'){

				parent.layer.msg('修改成功', {
					offset: ['260px'],
     		        time: 1500//1.5s后自动关闭
     		    });
				//关闭当前新增页面页
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭    
			}else{
				layer.msg('修改失败',{
					offset: ['260px'],
	     		    time: 1500
	     		});
			}
		});
		
	}); 

</script>