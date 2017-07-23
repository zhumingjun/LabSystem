<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加学生界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/webuploader/webuploader.css" />

<script>
	console.info("${pageContext.request.contextPath}");
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

<form id="projectPersonFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/projectPerson/save" method="post">
		<input type="hidden" id="projectId" name="projectId" value="${projectId }"/>

	<table>	
		<tr>
			<td class="lesta-150">参与人员顺序：</td>
			<td class="lestb">
				<input type="text" id="personOrder" name="personOrder" class="input_text_a" placeholder="请输入参与人员顺序">
			</td>
		</tr>
			<tr>
			<td class="lesta-150">参与人员编号：</td>
			<td class="lestb">
				<input type="text" id="personCode" name="personCode" class="input_text_a" placeholder="请输入参与人员编号">
			</td>
			<td class="lesta-150">作者姓名：</td>
			<td class="lestb">
				<input type="text" id="personName" name="personName" class="input_text_a" placeholder="请输入参与人员姓名"/>
			</td>
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 300px;" value="确定"/>
</form>

<script>


$("#saveButton").click(function(){
	var form = $("#projectPersonFormId");
	form.ajaxSubmit(function(result){
		if(result=='success'){

			parent.layer.msg('添加成功', {
 		        time: 1500//1.5s后自动关闭
 		    });
			//关闭当前新增页面页
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭    
		}else{
			layer.msg('新增失败');
		}
	});
	
});


</script>