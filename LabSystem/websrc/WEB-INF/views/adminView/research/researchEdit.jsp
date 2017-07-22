<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改科研界面 -->

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
	.edit_pic{
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

<form id="researchEditFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/research/save" method="post">
		<input type="hidden" id="id" name="id" value="${researchDomain.id }"/>
		<input type="hidden" id="principalId" name="principal.id" value="${researchDomain.principal.id }"/>
		<input type="hidden" id="projectLevelId" name="projectLevel" value="${researchDomain.projectLevel }"/>
		<input type="hidden" id="projectStateId" name="state" value="${researchDomain.state }"/>
		<input type="hidden" id="projectTypeId" name="projectType" value="${researchDomain.projectType }"/>
	<table>
		<tr>
			<td class="lesta-150">项目流水编号：</td>
			<td class="lestb">
				<input type="text" id="projectNumber" name="projectNumber" class="input_text_a" ignore="ignore" value="${researchDomain.projectNumber }"/>
			</td>
			<td class="lesta-150">项目名称：</td>
			<td class="lestb">
				<input type="text" id="projectName" name="projectName" class="input_text_a" ignore="ignore" value="${researchDomain.projectName }" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">负责人：</td>
			<td class="lestb">
				<select id="principal_select_edit_id" class="select_style">
					<option value="${researchDomain.principal.name }" selected="selected">选择</option>
					<c:forEach items="${teachers }" var="teachersDomain">
						<option value="${teachersDomain.id }">${teachersDomain.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">负责人联系方式：</td>
			<td class="lestb">
				<input type="text" id="phone" name="phone" class="input_text_a" ignore="ignore" value="${researchDomain.phone }" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">负责人邮箱：</td>
			<td class="lestb">
				<input type="text" id="email" name="email" class="input_text_a" ignore="ignore" value="${researchDomain.email }" />
			</td>
			<td class="lesta-150">项目级别：</td>
			<td class="lestb">
				<select id="projectLevel_select_edit_id" class="select_style">
					<option value="${researchDomain.projectLevel }" selected="selected">选择</option>
					<c:forEach items="${levelList }" var="levelDomain">
						<option value="${levelDomain.value }">${levelDomain.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">立项日期：</td>
			<td class="lestb">
				<input type="text" name="projectDate" class="Wdate" readonly="readonly" value="<fmt:formatDate value="${researchDomain.projectDate }" type="date"/>" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/> 
			</td>
			<td class="lesta-150">计划完成日期：</td>
			<td class="lestb">
				<input type="text" name="planDate" class="Wdate" readonly="readonly" value="<fmt:formatDate value="${researchDomain.planDate }" type="date"/>" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/> 
		    </td>
		</tr>
		<tr>
			<td class="lesta-150">开始日期：</td>
			<td class="lestb">
				<input type="text" name="startDate" class="Wdate" readonly="readonly" value="<fmt:formatDate value="${researchDomain.startDate }" type="date"/>" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/> 
			</td>
			<tr>
			<td class="lesta-150">结项日期：</td>
			<td class="lestb">
				<input type="text" name="endDate" class="Wdate" readonly="readonly" value="<fmt:formatDate value="${researchDomain.endDate }" type="date"/>" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/> 
			</td>
		</tr>
		<tr>
			<td class="lesta-150">立项总金费：</td>
			<td class="lestb">
				<input type="text" id="allFund" name="allFund" class="input_text_a" ignore="ignore" value="${researchDomain.allFund }"  />
			</td>
			<td class="lesta-150">拨款经费：</td>
			<td class="lestb">
				<input type="text" id="giveFund" name="giveFund" class="input_text_a" ignore="ignore" value="${researchDomain.giveFund }"  />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">财务账号：</td>
			<td class="lestb">
				<input type="text" id="finAccount" name="finAccount" class="input_text_a" ignore="ignore" value="${researchDomain.finAccount }"  />
			</td>	
				<td class="lesta-150">项目状态：</td>
			<td class="lestb">
				<select id="projectState_select_edit_id" class="select_style">
					<option value="${researchDomain.state }" selected="selected">选择</option>
					<c:forEach items="${stateList }" var="stateDomain">
						<option value="${stateDomain.value }">${stateDomain.name}</option>
					</c:forEach>
				</select>
			</td>	
		</tr>
		<tr>
			<td class="lesta-150">项目类型：</td>
			<td class="lestb">
				<select id="projectType_select_edit_id" class="select_style">
					<option value="${researchDomain.projectType }" selected="selected">选择</option>
					<c:forEach items="${typeList }" var="typeDomain">
						<option value="${typeDomain.value }">${typeDomain.name}</option>
					</c:forEach>
				</select>
			</td>	
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 300px;" value="确定"/>
</form>

<script>
$(function(){
	$("#principal_select_edit_id option[value='${researchDomain.principal.id}']").attr("selected",true);
	$("#projectLevel_select_edit_id option[value='${researchDomain.projectLevel}']").attr("selected",true);
	$("#projectState_select_edit_id option[value='${researchDomain.state}']").attr("selected",true);
	$("#projectType_select_edit_id option[value='${researchDomain.projectType}']").attr("selected",true);

});
$("#saveButton").click(function(){
		
		var form = $("#researchEditFormId");
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
	
//下拉框选择后给隐藏域赋值
$("#principal_select_edit_id").change(function(){
	var principal_id=$(this).children('option:selected').val();
	$("#principalId").val(principal_id);
});

$("#projectLevel_select_edit_id").change(function(){
	var projectLevel_id=$(this).children('option:selected').val();
	$("#projectLevelId").val(projectLevel_id);
});
$("#projectState_select_edit_id").change(function(){
	var projectState_id=$(this).children('option:selected').val();
	$("#projectStateId").val(projectState_id);
});

$("#projectType_select_edit_id").change(function(){
	var projectType_id=$(this).children('option:selected').val();
	$("#projectTypeId").val(projectType_id);
});
</script>