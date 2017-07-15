<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 论文作者列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<div>
<form id="formId" action="${pageContext.request.contextPath}/admin/paperAuthor/paperAuthorSearchList" method="post">
	<input type="hidden" id="paperId" value="${paperId }"/>
	<div class="breadcrumbs" id="paperAuthorListToolbar">
	
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>
	
		<input id="paperAuthorDeleteButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="删除"/>
		<input id="paperAuthorAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
		<input id="paperAuthorQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
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
					<th >作者顺序</th>
					<th >作者姓名</th>
					<th >作者编号</th>
					<th >贡献率</th>
					<th>操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${paperAuthorAuthorList }" var="paperAuthorAuthorDomain">
					<tr>
						<td class="center">
						<label> <input type="checkbox" class="ace" value="${paperAuthorAuthorDomain.id }"/> <span class="lbl"></span></label>
						</td>
						<td>${paperAuthorAuthorDomain.authorOrder }</td>
						<td>${paperAuthorAuthorDomain.authorName }</td>
						<td>${paperAuthorAuthorDomain.authorCode }</td>
						<td>${paperAuthorAuthorDomain.contributionRate }</td>
						
						<td>
							<input type="button" class="btn_list_view" value="查看" onclick="viewpaperAuthor('${paperAuthorDomain.id }')"/> 
							<input type="button" class="btn_list_update" value="修改" onclick="updatepaperAuthor('${paperAuthorDomain.id }')"/>  
							<input type="button" class="btn_list_delete" value="编辑" onclick="deletepaperAuthor('${paperAuthorDomain.id }')"/>
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
	$("#paperAuthorQueryButton").click(function(){
		$("#formId").ajaxSubmit(function(data){
		 	$("#content_page").html(data);
		});
	});

	//新增学生按钮
	$("#paperAuthorAddButton").click(function(){
		var paperId=$("#paperId").val;
	    parent.layer.open({
	        type: 2,
	        title: '新增论文作者',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['750px' , '500px'],
	        offset: ['60px'],
	        content: '${pageContext.request.contextPath}/admin/paperAuthor/paperAuthorAdd/'+paperId,
	        end: function(){
				//默认加载教师列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});
	
	//list中查看学生按钮
	function viewpaperAuthor(paperAuthorId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看论文作者',
	        shadeClose: true,
	        area : ['700px' , '560px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/paperAuthor/paperAuthorView/'+paperAuthorId
	    });
	}

	//list中修改用户按钮
	function updatepaperAuthor(paperAuthorId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改论文作者',
	        shadeClose: true,
	        area : ['900px' , '560px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/paperAuthor/paperAuthorEdit/'+paperAuthorId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	//删除
	function deletepaperAuthor(paperAuthorId)
	{
	 		//默认加载学生列表
			$.post("${pageContext.request.contextPath}/admin/paperAuthor/paperAuthorAuthorList/"+paperAuthorId, function(result){
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
	}
	
	
	//多选删除
	$("#paperAuthorDeleteButton").click(function(){
		var checkBoxs=$("table tbody input:checkbox");
		var paperAuthorIds=new Array();
		for(var i=0;i<checkBoxs.length;i++)
		{
			var checkBox=checkBoxs[i];
			if(checkBox.checked){
				paperAuthorIds.push(checkBox.value);
			}
		}
		if(paperAuthorIds.length=='0'){
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
				url : "${pageContext.request.contextPath}/admin/paperAuthor/deletepaperAuthors",
				async: false,
				data : {
					"paperAuthorIds" : paperAuthorIds
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
