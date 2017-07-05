<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改用户界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="userEditFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/user/editSave" method="post">
	<input type="hidden" id="id" name="id" value="${userDomain.id }"/>
	<input type="hidden" id="username" name="username" value="${userDomain.username }"/>
	<input type="hidden" id="password" name="password" value="${userDomain.password }"/>
	<input type="hidden" id="roleId" name="role.id" value="${userDomain.role.id }" />
	<input type="hidden" id="roleAuthority" value="${userDomain.role.authority }"/>
	<table>
		<tr>
			<td class="lesta-150">账号：</td>
			<td class="lestb">
				${userDomain.username }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">密码：</td>
			<td class="lestb">
				******
			</td>
		</tr>
		<tr>
			<td class="lesta-150">角色：</td>
			<td class="lestb">
				${userDomain.role.name }
<%-- 				<select id="role_select_edit_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${roleList }" var="roleDomain">
						<c:if test="${roleDomain.authority!=3 }">
							<option value="${roleDomain.id }_${roleDomain.authority }">${roleDomain.name}</option>
						</c:if>
					</c:forEach>
				</select> 
			</td>
<%-- 			<td class="lesta-150">角色：</td>
			<td class="lestb">
				<select id="role_select_edit_id" class="select_style">
					<c:forEach items="${roleList }" var="roleDomain">
						<option value="${roleDomain.id }">${roleDomain.name}</option>
					</c:forEach>
				</select>
			</td> --%>
		</tr>
		
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>
	
	$("#saveButton").click(function(){
	
		var roleIdVal=$("#roleId").val();
		if(roleIdVal==null||roleIdVal==''){
			layer.tips('请选择角色', '#role_select_edit_id');
			return;
		}
		var form = $("#userEditFormId");
		form.ajaxSubmit(function(result){
			if(result=='success'){

				parent.layer.msg('修改成功', {
     		        time: 1500//1.5s后自动关闭
     		    });
				//关闭当前新增页面页
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭     
				
			}else{
				layer.msg('修改失败');
			}
		});
		
	});

</script>