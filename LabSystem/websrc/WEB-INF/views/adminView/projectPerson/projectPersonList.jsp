<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 论文列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<div>
<form id="formId" action="${pageContext.request.contextPath}/admin/projectPerson/projectPersonSearchList" method="post">
	
	<div class="breadcrumbs" id="projectPersonListToolbar">
	
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>
	
		<input id="projectPersonDeleteButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="删除"/>
		<input id="projectPersonAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
		<input id="projectPersonQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
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
					<th >人员顺序</th>
					<th >人员姓名</th>
					<th >人员编号</th>

					<th>操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${projectPersonList }" var="projectPersonDomain">
					<tr>
						<td class="center">
						<label> <input type="checkbox" class="ace" value="${projectPersonDomain.id }"/> <span class="lbl"></span></label>
						</td>
						<td>${projectPersonDomain.personOrder}</td>
						<td>${projectPersonDomain.personName }</td>
						<td>${projectPersonDomain.personCode }</td>
						<td>
							<input type="button" class="btn_list_view" value="查看" onclick="viewprojectPerson('${projectPersonDomain.id }')"/> 
							<input type="button" class="btn_list_update" value="修改" onclick="updateprojectPerson('${projectPersonDomain.id }')"/>  
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

	//查询
	$("#projectPersonQueryButton").click(function(){
		$("#formId").ajaxSubmit(function(data){
		 	$("#content_page").html(data);
		});
	});

	//新增学生按钮
	$("#projectPersonAddButton").click(function(){
		var projectId="${projectId}";
	    parent.layer.open({
	        type: 2,
	        title: '新增参与人员',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['750px' , '500px'],
	        offset: ['60px'],
	        content: '${pageContext.request.contextPath}/admin/projectPerson/projectPersonAdd/'+projectId,
	        end: function(){
				//默认加载教师列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});
	
	//list中查看学生按钮
	function viewprojectPerson(projectPersonId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看论文',
	        shadeClose: true,
	        area : ['700px' , '560px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/projectPerson/projectPersonView/'+projectPersonId
	    });
	}

	//list中修改用户按钮
	function updateprojectPerson(projectPersonId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改论文',
	        shadeClose: true,
	        area : ['900px' , '560px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/projectPerson/projectPersonEdit/'+projectPersonId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	//编辑
	function editprojectPerson(projectPersonId)
	{
		parent.layer.open({
	        type: 2,
	        title: '编辑作者',
	        shadeClose: true,
	        area : ['1200px' , '600px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/projectPersonAuthor/projectPersonAuthorList/'+projectPersonId,
	        end: function () {
                location.reload();
            }
	    });
	}
	
	
	//多选删除
	$("#projectPersonDeleteButton").click(function(){
		var checkBoxs=$("table tbody input:checkbox");
		var projectPersonIds=new Array();
		for(var i=0;i<checkBoxs.length;i++)
		{
			var checkBox=checkBoxs[i];
			if(checkBox.checked){
				projectPersonIds.push(checkBox.value);
			}
		}
		if(projectPersonIds.length=='0'){
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
				url : "${pageContext.request.contextPath}/admin/projectPerson/deleteprojectPersons",
				async: false,
				data : {
					"projectPersonIds" : projectPersonIds
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
	
	
	//点击表格标题栏，选中所有checkbox框
	$('table th input:checkbox').on('click' , function(){
		
		var that=this;		
 		$(this).closest('table').find('tr > td:first-child input:checkbox').each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});

	
</script>
