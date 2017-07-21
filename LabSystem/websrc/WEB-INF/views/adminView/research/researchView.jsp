<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>	
<%@ page language="java" import="com.lab.lsystem.util.*" %>	

<!-- 查看界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datePicker/WdatePicker.js"></script>

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

	<table>
		<tr>
			<td class="lesta-150">项目流水编号：</td>
			<td class="lestb">
				<input type="text" id="projectNumber" name="projectNumber" class="input_text_a" ignore="ignore" value="${research.projectNumber }"/>
			</td>
			<td class="lesta-150">项目名称：</td>
			<td class="lestb">
				<input type="text" id="projectName" name="projectName" class="input_text_a" ignore="ignore" value="${research.projectName }" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">负责人：</td>
			<td class="lestb">
			${research.principal.name }
			</td>
			<td class="lesta-150">负责人联系方式：</td>
			<td class="lestb">
				<input type="text" id="phone" name="phone" class="input_text_a" ignore="ignore" value="${research.phone }"  />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">负责人邮箱：</td>
			<td class="lestb">
				<input type="text" id="email" name="email" class="input_text_a" ignore="ignore" value="${research.email }"  />
			</td>
			<td class="lesta-150">项目级别：</td>
			<td class="lestb">
				${research.projectLevel }
			</td>
		</tr>
			<tr>
			<td class="lesta-150">立项日期：</td>
			<td class="lestb">
				<fmt:formatDate value="${researchDomain.projectDate }" type="date"/>
			</td>
			<tr>
			<td class="lesta-150">计划完成日期：</td>
			<td class="lestb">
				<fmt:formatDate value="${researchDomain.planDate }" type="date"/>
			</td>
			</tr>
		<tr>
		</tr>
			<tr>
			<td class="lesta-150">开始日期：</td>
			<td class="lestb">
				<fmt:formatDate value="${researchDomain.stateDate }" type="date"/>
			</td>
			<tr>
			<td class="lesta-150">结项日期：</td>
			<td class="lestb">
				<fmt:formatDate value="${researchDomain.endDate }" type="date"/>
			</td>
			</tr>
		<tr>
			<td class="lesta-150">立项总金费：</td>
			<td class="lestb">
				<input type="text" id="allFund" name="allFund" class="input_text_a" ignore="ignore" value="${researchDomain.allFund }" />
			</td>
			<td class="lesta-150">拨款经费：</td>
			<td class="lestb">
				<input type="text" id="giveFund" name="giveFund" class="input_text_a" ignore="ignore" value="${researchDomain.giveFund }"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">财务账号：</td>
			<td class="lestb">
				<input type="text" id="finAccount" name="finAccount" class="input_text_a" ignore="ignore" value="${researchDomain.finAccount }" />
			</td>	
				<td class="lesta-150">项目状态：</td>
			<td class="lestb">
				${researchDomain.state }
			</td>	
		</tr>
		<tr>
			<td class="lesta-150">项目类型：</td>
			<td class="lestb">
				value="${researchDomain.projectType }"
			</td>	
		</tr>
	</table>

	<input id="closeButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 300px;" value="关闭"/>

<script>
	
	$("#closeButton").click(function(){
		//关闭当前页面
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭     
	});


</script>