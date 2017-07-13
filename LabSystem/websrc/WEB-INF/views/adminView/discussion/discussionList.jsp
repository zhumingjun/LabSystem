<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 研讨列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<div>
<form id="formId" action="${pageContext.request.contextPath}/admin/discussion/discussionSearchList" method="post">
	
	<div class="breadcrumbs" id="discussionListToolbar">
	
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>
	
		<input id="discussionDeleteButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="删除"/>
		<input id="discussionAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
		<input id="discussionQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
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
					<th >研讨时间</th>
					<th >研讨地点</th>
					<th >研讨内容</th>
					<th >参与人员</th>
					<th>操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${discussionList }" var="discussionDomain">
					<tr>
						<td class="center">
						<label> <input type="checkbox" class="ace" value="${discussionDomain.id }"/> <span class="lbl"></span></label>
						</td>
						<td>
						<fmt:formatDate value="${discussionDomain.discussionDate }" type="date"/>
						</td>
						<td>${discussionDomain.location }</td>
						
						<td>${discussionDomain.content }</td>	
						<td>${discussionDomain.participant }</td>
						<td>
							<input type="button" class="btn_list_view" value="查看" onclick="viewdiscussion('${discussionDomain.id }')"/> 
							<input type="button" class="btn_list_update" value="修改" onclick="updatediscussion('${discussionDomain.id }')"/>  
							<input type="button" class="btn_list_delete" value="删除" onclick="deletediscussion('${discussionDomain.id }')"/>
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
	$("#discussionQueryButton").click(function(){
		$("#formId").ajaxSubmit(function(data){
		 	$("#content_page").html(data);
		});
	});

	//新增学生按钮
	$("#discussionAddButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '新增研讨',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['900px' , '600px'],
	        offset: ['60px'],
	        content: '${pageContext.request.contextPath}/admin/discussion/discussionAdd',
	        end: function(){
				//默认加载教师列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});
	
	//list中查看学生按钮
	function viewdiscussion(discussionId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看研讨',
	        shadeClose: true,
	        area : ['700px' , '560px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/discussion/discussionView/'+discussionId
	    });
	}

	//list中修改用户按钮
	function updatediscussion(discussionId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改研讨',
	        shadeClose: true,
	        area : ['900px' , '560px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/discussion/discussionEdit/'+discussionId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	//删除
	function deletediscussion(discussionId)
	{
		//询问框
		layer.confirm('是否确定删除？', {
			offset: ['260px'],
		    btn: ['确定','取消'] //按钮
		}, function(){
	 		//默认加载学生列表
			$.post("${pageContext.request.contextPath}/admin/discussion/delete/"+discussionId, function(result){
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
	$("#discussionDeleteButton").click(function(){
		var checkBoxs=$("table tbody input:checkbox");
		var discussionIds=new Array();
		for(var i=0;i<checkBoxs.length;i++)
		{
			var checkBox=checkBoxs[i];
			if(checkBox.checked){
				discussionIds.push(checkBox.value);
			}
		}
		if(discussionIds.length=='0'){
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
				url : "${pageContext.request.contextPath}/admin/discussion/deletediscussions",
				async: false,
				data : {
					"discussionIds" : discussionIds
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
