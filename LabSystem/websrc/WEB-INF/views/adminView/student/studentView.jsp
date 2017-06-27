<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>	
<%@ page language="java" import="com.cb.csystem.util.*" %>	

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
			<td class="lesta-150">学号：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.stuId }
			</td>
			<td rowspan="4" colspan="2">
				<div class="add_pic" id="add_pic">
					<c:if test="${studentDomain.headImg==null||studentDomain.headImg=='' }">
						<img id="head_img" src="${pageContext.request.contextPath}/resources/images/touxiang.png" width="140px;" height="150px;" style="border-radius:5px;"/>
					</c:if>
					<c:if test="${studentDomain.headImg!=null&&studentDomain.headImg!='' }">
						<img id="head_img" src="${headImgPath}/${studentDomain.stuId }/${studentDomain.headImg }" width="140px;" height="150px;" style="border-radius:5px;"/>
					</c:if>
				</div>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">姓名：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.name }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">性别：</td>
			<td class="lestb" style="width:150px;">
				${cusfun:getNameByValueAndType(studentDomain.sex,"8002")}
			</td>
		</tr>
		<tr>
			<td class="lesta-150">出生日期：</td>
			<td class="lestb" style="width:150px;">
				<fmt:formatDate value="${studentDomain.birthday }" type="date"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">政治面貌：</td>
			<td class="lestb" style="width:150px;">
				${cusfun:getNameByValueAndType(studentDomain.politicalStatus,"8001")}
			</td>
			<td class="lesta-150">手机号码：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.cellphone }
			</td>
		</tr>
		<tr>
 			<td class="lesta-150">年级：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.classDomain.grade.grade }
			</td>
			<td class="lesta-150">QQ：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.email }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">学院：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.classDomain.major.college.name }
			</td>
			<td class="lesta-150">宿舍号：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.dormitory }
			</td>
		</tr>
		<tr>
 			<td class="lesta-150">专业：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.classDomain.major.name }
			</td>
			<td class="lesta-150">民族：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.nationality }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">班级：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.classDomain.name }
			</td>
			<td class="lesta-150">身份证号：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.IDnumber }
			</td>
		</tr>
		<tr>
 			<td class="lesta-150">教学班级：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.teachClass }
			</td> 
			<td class="lesta-150">籍贯：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.nativePlace }
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