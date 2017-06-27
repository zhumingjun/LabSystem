<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 学生列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<div>
<form id="formId" action="${pageContext.request.contextPath}/admin/teacher/teacherSearchList" method="post">
	
	<div class="breadcrumbs" id="studentListToolbar">
	
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>
	
		<input id="teacherDeleteButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="删除"/>
		<input id="teacherAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
		<input id="teacherQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
	</div>
	<div class="breadcrumbs">
	
		
	</div>
	<div class="table-responsive">
		<table id="sample-table-2" class="table table-striped table-bordered table-hover" style="table-layout:fixed;">
			<thead>
				<tr>
					<th class="center" style="width: 60px;">
						<label> <input id="theadCheckbox" type="checkbox" class="ace" /> <span class="lbl"></span></label>
					</th>
					<th >工号</th>
					<th >姓名</th>
					<th >性别</th>
					<th>职称</th>
					<th >入职年份</th>
					<th>操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${teacherList }" var="teacherDomain">
					<tr>
						<td class="center">
						<label> <input type="checkbox" class="ace" value="${teacherDomain.id }"/> <span class="lbl"></span></label>
						</td>
						<td>${teacherDomain.workCode }</td>
						<td>${teacherDomain.name }</td>
						<td>${cusfun:getNameByValueAndType(teacherDomain.sex,"8002")}</td>
						<td>${cusfun:getNameByValueAndType(teacherDomain.jobTitle,"8001")}</td>
						<td>${teacherDomain.entryYear }</td>	
						<td>
							<input type="button" class="btn_list_view" value="查看" onclick="viewteacher('${teacherDomain.id }')"/> 
							<input type="button" class="btn_list_update" value="修改" onclick="updateteacher('${teacherDomain.id }')"/>  
							<input type="button" class="btn_list_delete" value="删除" onclick="deleteteacher('${teacherDomain.id }')"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="pageId"><tags:paged /></div>
	</div>
</form>
</div>

<script type="text/javascript">

	$("#sample-table-2 thead tr th img").click(function(){
		
		var sortValueVal=$(this)[0].id.split("_")[1];
		var sortModeVal=$(this)[0].id.split("_")[2];
		$("#sortValue").val(sortValueVal);
		if(sortModeVal=='asc'){
			$("#sortMode").val('desc');
		}else{
			$("#sortMode").val('asc');
		}
		
 		//默认加载学生列表
		$("#formId").ajaxSubmit(function(data){
    	 	$("#content_page").html(data);
		}); 
	});

	//使下拉框默认选择
	$(function(){
		$("#grade_select_id option[value='${gradeId}']").attr("selected",true);
		$("#college_select_id option[value='${collegeId}']").attr("selected",true);
		$("#major_select_id option[value='${majorId}']").attr("selected",true);
		$("#class_select_id option[value='${classId}']").attr("selected",true);
	});
	
 	//下拉框选择后给隐藏域赋值
 	$("#class_select_id").change(function(){
		var classIdVal=$(this).children('option:selected').val();
		$("#classId").val(classIdVal);
	});
	$("#major_select_id").change(function(){
		var majorIdVal=$(this).children('option:selected').val();
		$("#majorId").val(majorIdVal);
	});
	$("#college_select_id").change(function(){
		var collegeIdVal=$(this).children('option:selected').val();
		$("#collegeId").val(collegeIdVal);
	});
	$("#grade_select_id").change(function(){
		var gradeIdVal=$(this).children('option:selected').val();
		$("#gradeId").val(gradeIdVal);
	});
	
	//查询
	$("#studentQueryButton").click(function(){
		$("#formId").ajaxSubmit(function(data){
		 	$("#content_page").html(data);
		});
	});

	//新增学生按钮
	$("#teacherAddButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '新增教师',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['1000px' , '560px'],
	        offset: ['60px'],
	        content: '${pageContext.request.contextPath}/admin/teacher/teacherAdd',
	        end: function(){
				//默认加载教师列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});
	
	//list中查看教师按钮
	function viewteacher(teacherId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看教师',
	        shadeClose: true,
	        area : ['700px' , '560px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/teacher/teacherView/'+teacherId
	    });
	}

	//list中修改用户按钮
	function updatestudent(studentId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改学生',
	        shadeClose: true,
	        area : ['700px' , '560px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/student/studentEdit/'+studentId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	//删除
	function deletestudent(studentId)
	{
		//询问框
		layer.confirm('是否确定删除？', {
			offset: ['260px'],
		    btn: ['确定','取消'] //按钮
		}, function(){
	 		//默认加载学生列表
			$.post("${pageContext.request.contextPath}/admin/student/delete/"+studentId, function(result){
				if(result=='success'){
					//默认加载学生列表
		        	$("#formId").ajaxSubmit(function(data){
		        	 	$("#content_page").html(data);
		    		});
					parent.layer.msg('删除成功', {
						offset: ['260px'],
	     		        time: 1500//1.5s后自动关闭
	     		    });
				}else{
					layer.msg('删除失败');
				}
			});
		}, function(){
			
		});
		
	}
	
	
	//多选删除
	$("#studentDeleteButton").click(function(){
		var checkBoxs=$("table tbody input:checkbox");
		var studentIds=new Array();
		for(var i=0;i<checkBoxs.length;i++)
		{
			var checkBox=checkBoxs[i];
			if(checkBox.checked){
				studentIds.push(checkBox.value);
			}
		}
		if(studentIds.length=='0'){
			layer.msg('请至少选择一个',{
				offset: ['260px']
			});
			return;
		}
		
		//询问框
		layer.confirm('是否确定删除这些学生？', {
			offset: ['260px'],
		    btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
				url : "${pageContext.request.contextPath}/admin/student/deleteStudents",
				async: false,
				data : {
					"studentIds" : studentIds
				},
				dataType : "text",
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					layer.msg('删除失败',{
						offset: ['260px']
					});
                },
				success : function(result) {
					if(result=='success'){
						//默认加载用户列表
			        	$("#formId").ajaxSubmit(function(data){
			        	 	$("#content_page").html(data);
			    		});
						parent.layer.msg('删除成功', {
							offset: ['260px'],
		     		        time: 1500//1.5s后自动关闭
		     		    });
					}else{
						layer.msg('删除失败',{
							offset: ['260px']
						});
					}
				}
			});
			
		}, function(){
			
		});
	});
	
	//excel导入
	$("#studentExcelToDBButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '从excel中导入学生信息',
	        shadeClose: true,
	        area : ['700px' , '500px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/student/studentExcelView',
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});
	
	//导出信息
	$("#studentDBToExcelButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '导出学生信息',
	        shadeClose: true,
	        area : ['700px' , '500px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/student/studentDBToExcelView'
	    });
	});
	
	//点击表格标题栏，选中所有checkbox框
	$('table th input:checkbox').on('click' , function(){
		
		var that=this;		
 		$(this).closest('table').find('tr > td:first-child input:checkbox').each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});

	//选择学院，得到专业
	function getMajor(college_id)
	{
    	$.ajax({
			url:'${pageContext.request.contextPath}/admin/major/getMajorByCollege?college_id='+college_id,
			type:"post",
			error:function(e){
			},
			success:function(data){
				var json = new Function("return" + data)();
 				var major_select=$("#major_select_id");
				major_select.empty();
				major_select.append('<option value="">'+"全部"+'</option>');
				for(var i=0;i<json.length;i++){
					major_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
				} 
			}
		});
	}
	
	//选择专业，得到班级
	function getClass(major_id)
	{
    	$.ajax({
			url:'${pageContext.request.contextPath}/admin/class/getClassByMajor?major_id='+major_id,
			type:"post",
			error:function(e){
			},
			success:function(data){
				var json = new Function("return" + data)();
 				var class_select=$("#class_select_id");
				class_select.empty();
				class_select.append('<option value="">'+"全部"+'</option>');
				for(var i=0;i<json.length;i++){
					class_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
				} 
			}
		});
	}
</script>
