<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.cb.csystem.util.*" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>

<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		
		<title>学生个人信息</title>
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ui/hui/static/h-ui/css/H-ui.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ui/hui/lib/Hui-iconfont/1.0.7/iconfont.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ui/hui/lib/icheck/icheck.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ui/hui/static/h-ui/css/style.css" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ui/hui/lib/jquery/1.9.1/jquery.min.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ui/hui/lib/layer/2.1/layer.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ui/hui/lib/laypage/1.2/laypage.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ui/hui/lib/My97DatePicker/WdatePicker.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ui/hui/lib/icheck/jquery.icheck.min.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ui/hui/lib/bootstrap-Switch/bootstrapSwitch.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ui/hui/lib/Validform/5.3.2/Validform.min.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ui/hui/lib/Validform/5.3.2/passwordStrength-min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ui/hui/static/h-ui/js/H-ui.js"></script>

		<style>
			.table-font th{font-size:16px;}
			.table-font td{font-size:16px;}
		</style>
	</head>
	<body>
	<header class="navbar-wrapper">
		<div class="navbar navbar-black navbar-fixed-top">
			<div class="container cl">
				<a class="logo navbar-logo hidden-xs" href="${pageContext.request.contextPath}/login">CSystem学生系统</a>
				<span class="logo navbar-slogan hidden-xs"> &middot;  学生详细信息</span>
			</div>
		</div>
	</header>
	<div style="width: 70%;margin: 0 auto;" >
		<div class="codeView docs-example">
			<span style="margin-bottom: 20px;font-size: 26px;">学生基本信息</span>
			<table id="studentinfoId" class="table table-border table-bordered radius table-font">
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
						<th>学号：</th><td>${studentDomain.stuCode }</td>
					<th>头像：</th>
					<td>
			                <div>		
									<c:if test="${studentDomain.headImg==null||studentDomain.headImg=='' }">
										<img id="head_img" src="${pageContext.request.contextPath}/resources/images/touxiang.png" width="140px;" height="150px;" style="border-radius:5px;"/>
									</c:if>
									<c:if test="${studentDomain.headImg!=null&&studentDomain.headImg!='' }">
										<img id="head_img" src="${headImgPath}/${studentDomain.stuCode}/${studentDomain.headImg }" width="140px;" height="150px;" style="border-radius:5px;"/>
									</c:if>
				            </div>
			       </td>						
					</tr>
					<tr>
						<th>姓名：</th><td>${studentDomain.name }</td>
						<th>研究生年级：  <td>${cusfun:getNameByValueAndType(studentDomain.grade,"8100")}</td>
					</tr>
					<tr>
						<th>性别：</th><td>${cusfun:getNameByValueAndType(studentDomain.sex,"8002")}</td>
						<th>手机号：</th><td class="lestb" style="width:150px;">${studentDomain.phoneNumber }</td>
					</tr>
					<tr>
						<th>出生日期：</th><td><fmt:formatDate value="${studentDomain.birthday }" type="date"/></td>
						<th>银行卡号:</th><td class="lestb" style="width:150px;">${studentDomain.bankCard }</td>
					</tr>
					<tr>
						
						
					</tr>
					<tr>
						<th>身份证号：</th><td>${studentDomain.idNumber }</td>
						<th>邮箱：</th><td>${studentDomain.email }</td>
					</tr>
					<tr>
						<th>家庭住址：</th><td>${studentDomain.homeAddress }</td>
						<th>入学年份：</th><td>${studentDomain.entryYear }</td>						
					</tr>
					<tr>
						<th>家庭联系人：</th><td>${studentDomain.contactPerson }</td>
						<th>家庭联系方式：</th><td>${studentDomain.familyContact }</td>
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
