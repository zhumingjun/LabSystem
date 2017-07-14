<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>	
<%@ page language="java" import="com.lab.lsystem.util.*" %>	

<!-- 增加学生界面 -->

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
			<td class="lesta-150">论文题目：</td>
			<td class="lestb">
				${paperDomain.title}
			</td>
			<td class="lesta-150">论文类型：</td>
			<td class="lestb">
				${cusfun:getNameByValueAndType(paperDomain.type,"8102")}
			</td>
		</tr>
		<tr>
			<td class="lesta-150">第一作者身份：</td>
			<td class="lestb">
			   ${cusfun:getNameByValueAndType(paperDomain.firstIdentity,"8105")}
			</td>
			<td class="lesta-150">第一作者：</td>
			<td class="lestb">
				   ${paperDomain.firstName}
			</td>
		</tr>
		<tr>
			<td class="lesta-150">第二作者身份：</td>
			<td class="lestb">
			    ${cusfun:getNameByValueAndType(paperDomain.secondIdentity,"8105")}
			</td>
			<td class="lesta-150">第二作者：</td>
			<td class="lestb">
				   ${paperDomain.secondName}
			</td>
		</tr>
		<tr>
			<td class="lesta-150">通信作者：</td>
			 <td class="lestb">
				   ${correspondName}
			</td>
		</tr>
		<tr>
			<td class="lesta-150">其他作者：</td>
			<td class="lestb">
				${paperDomain.otherAuthors}
			</td>
			<td class="lesta-150">发表日期：</td>
			<td class="lestb">
					<fmt:formatDate value="${paperDomain.publishDate }" type="date"/>
			</td>
		</tr> 
		<tr>
			<td class="lesta-150">期刊名称：</td>
			<td class="lestb">
				${paperDomain.journalTitle}
			</td>
			<td class="lesta-150">刊物级别：</td>
			<td class="lestb">
				${cusfun:getNameByValueAndType(paperDomain.journalLevel,"8103")}
			</td>
		</tr> 
		<tr>
			<td class="lesta-150">项目来源：</td>
			<td class="lestb">
				${paperDomain.projectSource}
			</td>
			<td class="lesta-150">一级学科：</td>
			<td class="lestb">
				${cusfun:getNameByValueAndType(paperDomain.discipline,"8104")}
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