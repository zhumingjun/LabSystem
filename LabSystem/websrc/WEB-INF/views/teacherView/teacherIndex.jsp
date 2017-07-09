<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.cb.csystem.util.*" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>

<!DOCTYPE HTML>
<html>
	<body>
	<div style="width: 70%;margin: 0 auto;" >
		<div class="codeView docs-example">
			<span style="margin-bottom: 20px;font-size: 26px;">教师基本信息</span>
			<table id="teacherinfoId" class="table table-border table-bordered radius table-font">
				<thead>
 					<tr>
						<th style="width: 15%;"></th>
						<th style="border-left:0px;"></th>
						<th style="border-left:0px;width: 15%;"></th>
						<th style="border-left:0px;"></th>
					</tr> 
				</thead>
				<tbody>
					<tr>
						<th>工号：</th><td>${teacherDomain.workCode }</td>
					<th>头像：</th>
					<td>
			                <div>		
									<c:if test="${teacherDomain.headImg==null||teacherDomain.headImg=='' }">
										<img id="head_img" src="${pageContext.request.contextPath}/resources/images/touxiang.png" width="140px;" height="150px;" style="border-radius:5px;"/>
									</c:if>
									<c:if test="${teacherDomain.headImg!=null&&teacherDomain.headImg!='' }">
										<img id="head_img" src="${headImgPath}/${teacherDomain.workCode}/${teacherDomain.headImg }" width="140px;" height="150px;" style="border-radius:5px;"/>
									</c:if>
				            </div>
			       </td>						
					</tr>
					<tr>
						<th>姓名：</th><td>${teacherDomain.name }</td>
						<th>教师职称：</th><td>${cusfun:getNameByValueAndType(teacherDomain.jobTitle,"8001")}</td>
					</tr>
					<tr>
						<th>性别：</th><td>${cusfun:getNameByValueAndType(teacherDomain.sex,"8002")}</td>
						<th>手机号：</th><td class="lestb" style="width:150px;">${teacherDomain.phoneNumber }</td>
					</tr>
					<tr>
						<th>出生日期：</th><td><fmt:formatDate value="${teacherDomain.birthday }" type="date"/></td>
						<th>财务账号:</th><td class="lestb" style="width:150px;">${teacherDomain.finAccount }</td>
					</tr>
					<tr>
						
						
					</tr>
					<tr>
						<th>身份证号：</th><td>${teacherDomain.idNumber }</td>
						<th>邮箱：</th><td>${teacherDomain.email }</td>
					</tr>
					<tr>
						<th>家庭住址：</th><td>${teacherDomain.homeAddress }</td>
						<th>入职年份：</th><td>${teacherDomain.entryYear }</td>	
					</tr>			
				</tbody>
			</table>
		</div>

	</div>



	<footer class="footer mt-20">
		<div class="container">
			<nav class="footer-nav">
				<a href="#">关于LabSystem</a>
				<span class="pipe">|</span>
				<a href="#">软件著作权</a>
				<span class="pipe">|</span>
				<a href="#">联系我</a>
			</nav>
			<p>Copyright &copy;2017 zmj All Rights Reserved. <br>
			未经允许，禁止转载、抄袭、镜像<br>
		</div>
	</footer>
	
	<script>
	
	</script>
	</body>
</html>
