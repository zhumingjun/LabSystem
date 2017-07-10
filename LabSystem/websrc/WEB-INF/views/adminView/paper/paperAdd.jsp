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

<form id="paperFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/paper/save" method="post">
		<input type="hidden" id="typeId" name="type" value=""/>
		<input type="hidden" id="firstIdentityId" name="firstIdentity" value=""/>
		<input type="hidden" id="firstAuthorId" name="firstAuthor" value=""/>
		<input type="hidden" id="secondAuthorId" name="secondAuthor" value=""/>
		<input type="hidden" id="secondIdentifyId" name="secondIdentify" value=""/>
		<input type="hidden" id="corrspondAuthorId" name="corrspondAuthor" value=""/>
		<input type="hidden" id="journalLevelId" name="journalLevel" value=""/>
		<input type="hidden" id="disciplineId" name="discipline" value=""/>
	<table>	
		<tr>
			<td class="lesta-150">论文题目：</td>
			<td class="lestb">
				<input type="text" id="title" name="title" class="input_text_a" placeholder="请输入论文标题">
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
			    <select id="firstIdentity_select_add_id" class="select_style" onchange="getFirstIdentify(this.value)">
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
			    <select id="secondIdentity_select_add_id" class="select_style" onchange="getSecondIdentify(this.value)">
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
			    <select id="correspondIdentity_select_add_id" class="select_style" onchange="getCorrspondIdentify(this.value)">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${authorItem }" var="authorDomain">
						<option value="${authorDomain.value }">${authorDomain.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">通信作者：</td>
			<td class="lestb">
				    <select id="corrspondAuthor_select_add_id" class="select_style">
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
				<input type="text" id="otherAuthors" name="otherAuthors" class="input_text_a" placeholder="请输入其他作者" />
			</td>
			<td class="lesta-150">发表日期：</td>
			<td class="lestb">
				<input type="text" name="publishDate" class="Wdate" readonly="readonly" placeholder="发表日期" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/> 
			</td>
		</tr> 
		<tr>
			<td class="lesta-150">期刊名称：</td>
			<td class="lestb">
				<input type="text" id="journalTitle" name="journalTitle" class="input_text_a" placeholder="请输入期刊名称" />
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
				<input type="text" id="projectSource" name="projectSource" class="input_text_a" placeholder="请输入项目来源" />
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


$("#saveButton").click(function(){
	var form = $("#paperFormId");
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

	//下拉框选择后给隐藏域赋值
	$("#type_select_add_id").change(function(){
		var type_id=$(this).children('option:selected').val();
		$("#typeId").val(type_id);
	});
	

	//下拉框选择后给隐藏域赋值
	$("#firstAuthor_select_add_id").change(function(){
		var firstAuthor_id=$(this).children('option:selected').val();
		$("#firstAuthorId").val(firstAuthor_id);
	});
	

	//下拉框选择后给隐藏域赋值
	$("#firstIdentity_select_add_id").change(function(){
		var firstIdentity_id=$(this).children('option:selected').val();
		$("#firstIdentityId").val(firstIdentity_id);
	});
	//下拉框选择后给隐藏域赋值
	$("#secondAuthor_select_add_id").change(function(){
		var secondAuthor_id=$(this).children('option:selected').val();
		$("#secondAuthorId").val(secondAuthor_id);
	});
	

	//下拉框选择后给隐藏域赋值
	$("#secondIdentity_select_add_id").change(function(){
		var secondIdentity_id=$(this).children('option:selected').val();
		$("#secondIdentityId").val(secondIdentity_id);
	});
	//下拉框选择后给隐藏域赋值
	$("#corrspondAuthor_select_add_id").change(function(){
		var corrspondAuthor_id=$(this).children('option:selected').val();
		$("#corrspondAuthorId").val(corrspondAuthor_id);
	});
	//下拉框选择后给隐藏域赋值
	$("#journalLevel_select_add_id").change(function(){
		var journalLevel_id=$(this).children('option:selected').val();
		$("#journalLevelId").val(journalLevel_id);
	});
	//下拉框选择后给隐藏域赋值
	$("#discipline_select_add_id").change(function(){
		var discipline_id=$(this).children('option:selected').val();
		$("#disciplineId").val(discipline_id);
	});
	
	
	//选择第一作者身份，得到相应人选
	function getFirstIdentify(identify_value)
	{
    	$.ajax({
			url:'${pageContext.request.contextPath}/admin/paper/getFirstIdentify?identify_value='+identify_value,
			type:"post",
			error:function(e){
			},
			success:function(data){
				var json = new Function("return" + data)();
 				var major_select=$("#firstAuthor_select_add_id");
				major_select.empty();
				major_select.append('<option value="">'+"选择"+'</option>');
				for(var i=0;i<json.length;i++){
					major_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
				} 
			}
		});
	}
    	//选择第二作者身份，得到相应人选
    	function getSecondIdentify(identify_value)
    	{
        	$.ajax({
    			url:'${pageContext.request.contextPath}/admin/paper/getFirstIdentify?identify_value='+identify_value,
    			type:"post",
    			error:function(e){
    			},
    			success:function(data){
    				var json = new Function("return" + data)();
     				var major_select=$("#secondAuthor_select_add_id");
    				major_select.empty();
    				major_select.append('<option value="">'+"选择"+'</option>');
    				for(var i=0;i<json.length;i++){
    					major_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
    				} 
    			}
    		});
    	}
        	//选择通信作者身份，得到相应人选
        	function getCorrspondIdentify(identify_value)
        	{
            	$.ajax({
        			url:'${pageContext.request.contextPath}/admin/paper/getFirstIdentify?identify_value='+identify_value,
        			type:"post",
        			error:function(e){
        			},
        			success:function(data){
        				var json = new Function("return" + data)();
         				var major_select=$("#correspondAuthor_select_add_id");
        				major_select.empty();
        				major_select.append('<option value="">'+"选择"+'</option>');
        				for(var i=0;i<json.length;i++){
        					major_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
        				} 
        			}
        	});
        	}

    	
	

</script>