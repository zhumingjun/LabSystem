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

<form id="studentFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/paper/save" method="post">
	    <input type="hidden" id="typeId" name="type" value="${paperDomain.type}"/>
		<input type="hidden" id="firstIdentityId" name="firstIdentity" value="${paperDomain.firstIdentity}"/>
		<input type="hidden" id="firstAuthorId" name="firstAuthor" value="${paperDomain.firstAuthor}"/>
		<input type="hidden" id="secondAuthorId" name="secondAuthor" value="${paperDomain.secondAuthor}"/>
		<input type="hidden" id="secondIdentityId" name="secondIdentity" value="${paperDomain.secondIdentity}"/>
		<input type="hidden" id="correspondAuthorId" name="correspondAuthor" value="${paperDomain.correspondIdentity}"/>
		<input type="hidden" id="journalLevelId" name="journalLevel" value="${paperDomain.journalLevel}"/>
		<input type="hidden" id="disciplineId" name="discipline" value="${paperDomain.discipline}"/>
	<table>
		<tr>
			<td class="lesta-150">论文题目：</td>
			<td class="lestb">
				<input type="text" id="title" name="title" class="input_text_a" placeholder="请输入论文标题" value=${paperDomain.title}>
			</td>
			<td class="lesta-150">论文类型：</td>
			<td class="lestb">
				 <select id="type_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>					
					<c:forEach items="${typeItem }" var="typeDomain">
						<option value="${typeDomain.value }">${typeDomain.name}</option>
					</c:forEach>
				  </select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">第一作者身份：</td>
			<td class="lestb">
			    <select id="firstIdentity_select_add_id" class="select_style" onchange="getFirstIdentity(this.value)">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${authorItem }" var="authorDomain">
						<option value="${authorDomain.value }">${authorDomain.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">第一作者：</td>
			<td class="lestb">
				    <select id="firstAuthor_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>					
					<c:forEach items="${teachers }" var="teacherDomain">
						<option value="${teacherDomain.id }">${teacherDomain.name}</option>
					</c:forEach>
				    </select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">第二作者身份：</td>
			<td class="lestb">
			    <select id="secondIdentity_select_add_id" class="select_style" onchange="getSecondIdentity(this.value)">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${authorItem }" var="authorDomain">
						<option value="${authorDomain.value }">${authorDomain.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">第二作者：</td>
			<td class="lestb">
				    <select id="secondAuthor_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>					
					<c:forEach items="${teachers }" var="teacherDomain">
						<option value="${teacherDomain.id }">${teacherDomain.name}</option>
					</c:forEach>
				    </select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">通信作者身份：</td>
			<td class="lestb">
			    <select id="correspondIdentity_select_add_id" class="select_style" onchange="getcorrespondIdentity(this.value)">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${authorItem }" var="authorDomain">
						<option value="${authorDomain.value }">${authorDomain.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">通信作者：</td>
			<td class="lestb">
				    <select id="correspondAuthor_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>					
					<c:forEach items="${teachers }" var="teacherDomain">
						<option value="${teacherDomain.id }">${teacherDomain.name}</option>
					</c:forEach>
				    </select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">其他作者：</td>
			<td class="lestb">
				<input type="text" id="otherAuthors" name="otherAuthors" class="input_text_a" placeholder="请输入其他作者" value="${paperDomain.otherAuthors }"/>
			</td>
			<td class="lesta-150">发表日期：</td>
			<td class="lestb">
				<input type="text" name="publishDate" class="Wdate" readonly="readonly" value="<fmt:formatDate value="${paperDomain.publishDate }" type="date"/>"  placeholder="发表日期" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/> 
			</td>
		</tr> 
		<tr>
			<td class="lesta-150">期刊名称：</td>
			<td class="lestb">
				<input type="text" id="journalTitle" name="journalTitle" class="input_text_a" placeholder="请输入期刊名称" value="${paperDomain.journalTitle }"/>
			</td>
			<td class="lesta-150">刊物级别：</td>
			<td class="lestb">
				 <select id="journalLevel_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>					
					<c:forEach items="${levelItem }" var="levelDomain">
						<option value="${levelDomain.value }">${levelDomain.name}</option>
					</c:forEach>
				  </select>
			</td>
		</tr> 
		<tr>
			<td class="lesta-150">项目来源：</td>
			<td class="lestb">
				<input type="text" id="projectSource" name="projectSource" class="input_text_a" placeholder="请输入项目来源" value="${paperDomain.projectSource }"/>
			</td>
			<td class="lesta-150">一级学科：</td>
			<td class="lestb">
				 <select id="discipline_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>					
					<c:forEach items="${disciplineItem }" var="disciplineDomain">
						<option value="${disciplineDomain.value }">${disciplineDomain.name}</option>
					</c:forEach>
				  </select>
			</td>
		</tr> 
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 300px;" value="确定"/>
</form>

<script>

	$(function(){
		$("#type_select_add_id option[value='${paperDomain.type}']").attr("selected",true);
		$("#firstIdentity_select_add_id option[value='${paperDomain.firstIdentity}']").attr("selected",true);
		$("#firstAuthor_select_add_id option[value='${paperDomain.firstAuthor}']").attr("selected",true);
		$("#secondIdentity_select_add_id option[value='${paperDomain.secondIdentity}']").attr("selected",true);
		$("#secondAuthor_select_add_id option[value='${paperDomain.secondAuthor}']").attr("selected",true);
		$("#correspondIdentity_select_add_id option[value='${paperDomain.correspondIdentity}']").attr("selected",true);
		$("#correspondAuthor_select_add_id option[value='${paperDomain.correspondAuthor}']").attr("selected",true);
		$("#journalLevel_select_add_id option[value='${paperDomain.journalLevel}']").attr("selected",true);
		$("#discipline_select_add_id option[value='${paperDomain.discipline}']").attr("selected",true);
	});
	//下拉框选择后给隐藏域赋值
	$("#grade_select_edit_id").change(function(){
		var grade_id=$(this).children('option:selected').val();
		$("#gradeId").val(grade_id);
	});
	$("#tutor_select_add_id").change(function(){
		var tutor_id=$(this).children('option:selected').val();
		$("#tutorId").val(tutor_id);
	});
	$("#restutor_select_add_id").change(function(){
		var resTutor_id=$(this).children('option:selected').val();
		$("#resTutor").val(resTutor_id);
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