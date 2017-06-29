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

<form id="studentFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/student/save" method="post">
	<input type="hidden" id="id" name="id" value="${studentDomain.id }"/>
	<input type="hidden" id="stuCode" name="stuCode" value="${studentDomain.stuCode }"/>
	<input type="hidden" id="gradeId" name="grade" value="${studentDomain.grade }"/>
	<table>
		<tr>
			<td class="lesta-150">学号：</td>
			<td class="lestb">
				${studentDomain.stuCode }
			</td>
			<td rowspan="4" colspan="2">
				<input type="hidden" id="headImg" name="headImg" />
				<div id="filePicker" class="filePicker">选择图片</div>
				<div class="add_pic" id="add_pic">
						<img id="head_img" src="${pageContext.request.contextPath}/resources/images/touxiang.png" width="140px;" height="150px;" style="border-radius:5px;"/>
				</div>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">姓名：</td>
			<td class="lestb">
				<input type="text" id="studentname" name="name" class="input_text_a" placeholder="请输入姓名" value="${studentDomain.name }">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">性别：</td>
			<td class="lestb">
				<c:if test="${studentDomain.sex==0 }">
					<input type="radio" name="sex" value="0" checked="checked"/>男
					<input type="radio" name="sex" value="1" />女
				</c:if>
				<c:if test="${studentDomain.sex==1 }">
					<input type="radio" name="sex" value="0" />男
					<input type="radio" name="sex" value="1" checked="checked"/>女
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">出生日期：</td>
			<td class="lestb">
				<input type="text" name="birthday" class="Wdate" readonly="readonly"  value="<fmt:formatDate value="${studentDomain.birthday }" type="date"/>" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">学生年级：</td>
			<td class="lestb">
				<select id="jobTitle_select_edit_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${grade }" var="grade">
						<option value="${grade.id }">${grade.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">手机号码：</td>
			<td class="lestb">
				<input type="text" id="studentphoneNumber" name="phoneNumber" class="input_text_a" ignore="ignore" placeholder="请输入手机号码" value="${studentDomain.phoneNumber }"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">银行卡号：</td>
			<td class="lestb">
				<input type="text" id="bankCard" name="bankCard" class="input_text_a" ignore="ignore" placeholder="请输入银行卡号" value="${studentDomain.bankCard }"/>
			</td>
			<td class="lesta-150">邮箱：</td>
			<td class="lestb">
				<input type="text" id="studentemail" name="email" class="input_text_a" ignore="ignore" placeholder="请输入邮箱" value="${studentDomain.email }"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">身份证：</td>
			<td class="lestb">
				<input type="text" id="studentidNumber" name="idNumber" class="input_text_a" placeholder="请输入身份证号" value="${studentDomain.idNumber }"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">家庭住址：</td>
			<td class="lestb">
				<input type="text" id="studenthomeAddress" name="homeAddress" class="input_text_a" placeholder="请输入家庭住址" value="${studentDomain.homeAddress }"/>
			</td>
			<td class="lesta-150">入学年份：</td>
			<td class="lestb">
				<input type="text" id="entryYear" name="entryYear" class="input_text_a" placeholder="请输入入学年份" value="${studentDomain.entryYear }"/>
			</td>
		</tr> 
		<tr>
			<td class="lesta-150">家庭联系人：</td>
			<td class="lestb">
				<input type="text" id="contactPerson" name="contactPerson" class="input_text_a" placeholder="请输入家庭联系人" value="${studentDomain.contactPerson }"/>
			</td>
			<td class="lesta-150">家庭联系方式：</td>
			<td class="lestb">
				<input type="text" id="familyContact" name="familyContact" class="input_text_a" placeholder="请输入家庭联系方式" value="${studentDomain.familyContact }"/>
			</td>
		</tr> 
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 300px;" value="确定"/>
</form>

<script>

	$(function(){
		$("#grade_select_edit_id option[value='${studentDomain.grade}']").attr("selected",true);
	});

	//下拉框选择后给隐藏域赋值
	$("#grade_select_edit_id").change(function(){
		var grade_id=$(this).children('option:selected').val();
		$("#gradeId").val(grade_id);
	});
	
	$("#saveButton").click(function(){
		
		var form = $("#studentFormId");
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