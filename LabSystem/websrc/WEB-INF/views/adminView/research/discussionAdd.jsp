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

<form id="discussionFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/discussion/save" method="post">
		<input type="hidden" id="gradeId" name="grade" value=""/>
		<input type="hidden" id="tutorId" name="tutorDomain.id" value=""/>
		<input type="hidden" id="restutorId" name="restutorDomain.id" value=""/>
	<table>	
		<tr>
			<td class="lesta-150">研讨时间：</td>
			<td class="lestb">
				<input type="text" name="discussionDate" class="Wdate" readonly="readonly" placeholder="研讨日期" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/> 
			</td>
			<td class="lesta-150">研讨地点：</td>
			<td class="lestb">
				<input type="text" id="location" name="location" class="input_text_a" ignore="ignore" placeholder="请输入研讨地点" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">研讨内容：</td>
			<td class="lestb">
				<textarea rows="10" cols="50" name="content" placeholder="请输入研讨内容"></textarea>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">参会人员：</td>
			<td class="lestb">
				<textarea rows="3" cols="50" name="participant" placeholder="请输入参会人员"></textarea>
			</td>		
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 300px;" value="确定"/>
</form>

<script>

$("#saveButton").click(function(){
	var form = $("#discussionFormId");
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