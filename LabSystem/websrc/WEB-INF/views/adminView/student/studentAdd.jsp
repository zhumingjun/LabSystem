<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加教师界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/webuploader/webuploader.css" />

<script>
	var BASE_URL='${pageContext.request.contextPath}/admin/';
</script>

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
	<input type="hidden" id="gradeId" name="grade" value=""/>
	<table>	
		<tr>
			<td class="lesta-150">学号：</td>
			<td class="lestb">
				<input type="text" id="stuCode" name="stuCode" class="input_text_a" placeholder="请输入学号">
			</td>
			<td rowspan="4" colspan="2">
				<input type="hidden" id="headImg" name="headImg" value=""/>
				<div id="filePicker" class="filePicker">选择图片</div>
				<div class="add_pic" id="add_pic">
					<img id="head_img" src="${pageContext.request.contextPath}/resources/images/touxiang.png" width="140px;" height="150px;" style="border-radius:5px;"/>
				</div>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">姓名：</td>
			<td class="lestb">
				<input type="text" id="studentname" name="name" class="input_text_a" placeholder="请输入姓名">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">性别：</td>
			<td class="lestb">
				<input type="radio" name="sex" value="0" checked="checked"/>男
				<input type="radio" name="sex" value="1" />女
			</td>
		</tr>
		<tr>
			<td class="lesta-150">出生日期：</td>
			<td class="lestb">
				<input type="text" name="birthday" class="Wdate" readonly="readonly" placeholder="出生日期" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/> 
			</td>

		</tr>
		<tr>
			<td class="lesta-150">研究生年级：</td>
			<td class="lestb">
				<select id="grade_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${grade }" var="grade">
						<option value="${grade.id }">${grade.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">手机号码：</td>
			<td class="lestb">
				<input type="text" id="studentphoneNumber" name="phoneNumber" class="input_text_a" ignore="ignore" placeholder="请输入手机号码" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">银行卡号：</td>
			<td class="lestb">
				<input type="text" id="finAccount" name="finAccount" class="input_text_a" ignore="ignore" placeholder="请输入财务账号"/>
			</td>
			<td class="lesta-150">邮箱：</td>
			<td class="lestb">
				<input type="text" id="studentemail" name="email" class="input_text_a" ignore="ignore" placeholder="请输入邮箱"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">身份证：</td>
			<td class="lestb">
				<input type="text" id="studentidNumber" name="idNumber" class="input_text_a" placeholder="请输入身份证号" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">家庭住址：</td>
			<td class="lestb">
				<input type="text" id="studenthomeAddress" name="homeAddress" class="input_text_a" placeholder="请输入家庭住址" />
			</td>
			<td class="lesta-150">入学年份：</td>
			<td class="lestb">
				<input type="text" id="entryYear" name="entryYear" class="input_text_a" placeholder="请输入入职年份" />
			</td>
		</tr> 
		
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 300px;" value="确定"/>
</form>

<script>

 	$("#saveButton").click(function(){
		
		
		var form = $("#studentFormId");
		form.ajaxSubmit(function(result){
			if(result=='success'){
		
				parent.layer.msg('添加成功', {
					offset: ['260px'],
				        time: 1500//1.5s后自动关闭
				    });
				//关闭当前新增页面页
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭    
			}else{
				layer.msg('新增失败',{
					offset: ['260px']
				});
			}
		});
	
	});


/*	//表单验证
	$.Tipmsg.r=null;
	
	var showmsg=function(msg,obj){
		console.info(msg); 
		layer.tips(msg, obj);
	};
	
	$("#studentFormId").Validform({
		tiptype:function(msg,o){
			console.info(msg); 
			showmsg(msg,o.obj[0]);
		}
	});

*/
	//下拉框选择后给隐藏域赋值
	$("#grade_select_add_id").change(function(){
		var grade_id=$(this).children('option:selected').val();
		$("#gradeId").val(grade_id);
	});
	
</script>