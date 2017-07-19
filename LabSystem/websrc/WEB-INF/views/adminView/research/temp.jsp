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
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/validform/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validform.js"></script>
 --%>
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

<form id="studentFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/student/save" method="post">
		<input type="hidden" id="gradeId" name="grade" value=""/>
	<table>	
		<tr>
			<td class="lesta-150">学号：</td>
			<td class="lestb">
				<input type="text" id="stuCode" name="stuCode" class="input_text_a" placeholder="请输入学号">
			</td>
<%-- 			<td rowspan="4" colspan="2">
				<input type="hidden" id="headImg" name="headImg" value=""/>
				<div id="filePicker" class="filePicker">选择图片</div>
				<div class="add_pic" id="add_pic">
					<img id="head_img" src="${pageContext.request.contextPath}/resources/images/touxiang.png" width="140px;" height="150px;" style="border-radius:5px;"/>
				</div>
			</td> --%>
		</tr>
<%-- 		<tr>
			<td class="lesta-150">姓名：</td>
			<td class="lestb">
				<input type="text" id="stuname" name="name" class="input_text_a" placeholder="请输入姓名">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">性别：</td>
			<td class="lestb">
				<input type="radio" name="sex" value="0" checked="checked"/>男
				<input type="radio" name="sex" value="1" />女
			</td>
		</tr>
		<tr>
			<td class="lesta-150">出生日期：</td>
			<td class="lestb">
				<input type="text" name="birthday" class="Wdate" readonly="readonly" placeholder="出生日期" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/> 
			</td>

		</tr>
		<tr>
			<td class="lesta-150">研究生年级：</td>
			<td class="lestb">
				<select id="grade_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${gradeItem }" var="gradedomain">
						<option value="${gradedomain.value }">${gradedomain.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">手机号码：</td>
			<td class="lestb">
				<input type="text" id="studentphoneNumber" name="phoneNumber" class="input_text_a" ignore="ignore" placeholder="请输入手机号码" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">银行卡号：</td>
			<td class="lestb">
				<input type="text" id="bankCard" name="bankCard" class="input_text_a" ignore="ignore" placeholder="请输入财务账号"/>
			</td>
			<td class="lesta-150">邮箱：</td>
			<td class="lestb">
				<input type="text" id="studentemail" name="email" class="input_text_a" ignore="ignore" placeholder="请输入邮箱"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">身份证：</td>
			<td class="lestb">
				<input type="text" id="studentidNumber" name="idNumber" class="input_text_a" placeholder="请输入身份证号" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">家庭住址：</td>
			<td class="lestb">
				<input type="text" id="studenthomeAddress" name="homeAddress" class="input_text_a" placeholder="请输入家庭住址" />
			</td>
			<td class="lesta-150">入学年份：</td>
			<td class="lestb">
				<input type="text" id="entryYear" name="entryYear" class="input_text_a" placeholder="请输入入职年份" />
			</td>
		</tr> 
		<tr>
			<td class="lesta-150">家庭联系人：</td>
			<td class="lestb">
				<input type="text" id="contactPerson" name="contactPerson" class="input_text_a" placeholder="请输入家庭联系人" />
			</td>
			<td class="lesta-150">家庭联系方式：</td>
			<td class="lestb">
				<input type="text" id="familyContact" name="familyContact" class="input_text_a" placeholder="请输入家庭联系方式" />
			</td>
		</tr>  --%>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 300px;" value="确定"/>
</form>

<script>

 /*	$("#saveButton").click(function(){
		
		
		var form = $("#studentFormId");
		form.ajaxSubmit(function(result){
			if(result=='success'){
		
				parent.layer.msg('添加成功', {
					offset: ['260px'],
				        time: 1500//1.5s后自动关闭
				    });
				//关闭当前新增页面页
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭    
			}else{
				layer.msg('新增失败',{
					offset: ['260px']
				});
			}
		});
	
	});
*/




/* 	//表单验证
	$.Tipmsg.r=null;
	
	var showmsg=function(msg,obj){
		console.info(msg); 
		layer.tips(msg, obj);
	};
	
	$("#studentFormId").Validform({
		tiptype:function(msg,o){
			console.info(msg); 
			showmsg(msg,o.obj[0]);
		}
	}); */


	//下拉框选择后给隐藏域赋值
	$("#grade_select_add_id").change(function(){
		var grade_id=$(this).children('option:selected').val();
		$("#gradeId").val(grade_id);
	});
	
	
	
	
/*
// 图片上传demo
	jQuery(function() {
	    var $ = jQuery,
	    
	    	$add_pic = $('#add_pic'),
	    	$img=$('#head_img'),
	    	
	        // 优化retina, 在retina下这个值是2
	        ratio = window.devicePixelRatio || 1,

	        // 缩略图大小
	        thumbnailWidth = 140 * ratio,
	        thumbnailHeight = 150 * ratio,

	        // Web Uploader实例
	        uploader;

	    // 初始化Web Uploader
	    uploader = WebUploader.create({

	        // swf文件路径
	        swf: '${pageContext.request.contextPath}/resources/js/webuploader/dist/Uploader.swf',

	        // 文件接收服务端。
	        server: BASE_URL+'/student/uploaderImg',

	        // 选择文件的按钮。可选。
	        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	        pick: {
	        	id: '#filePicker',
	        	multiple: false
	        },
	        
	        //只需上传一个
	        fileNumLimit: 1,
	        
	        //文件大小限制
	        fileSingleSizeLimit: 2 * 1024*1024 ,

	        // 只允许选择文件，可选。
	        accept: {
	            title: 'Images',
	            extensions: 'gif,jpg,jpeg,bmp,png',
	            mimeTypes: 'image/*'
	        }
	    });
	    
	    //文件被加入队列前
	    uploader.on( 'beforeFileQueued', function( file ) {
	    	//如果已有文件，重置队列
	    	if(file.size<2 * 1024*1024){
	    		uploader.reset();
	    	}
	    	
	    });

	    // 当有文件添加进来的时候
	    uploader.on( 'fileQueued', function( file ) {
	    	//缩略图
	        uploader.makeThumb( file, function( error, src ) {
	            if ( error ) {
	            	$img.replaceWith('<span>不能预览</span>');
	                return;
	            }

	            $img.attr( 'src', src );
	        }, thumbnailWidth, thumbnailHeight );
	        
	    });
	    
	    uploader.on('error', function(handler) {
	    	
			if(handler=="Q_TYPE_DENIED"){
				layer.msg("类型不正确！");
			}
			if(handler=="F_EXCEED_SIZE"){
				layer.msg("请上传2M以下的图片！");
			}
		});

	    // 文件上传过程中创建进度条实时显示。
	    uploader.on( 'uploadProgress', function( file, percentage ) {
	        
	    });

	    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
	    uploader.on( 'uploadSuccess', function( file, data ) {
	    	layer.msg("上传成功");
	    	$("#headImg").val(data._raw);
	    	
			var form = $("#studentFormId");
			form.ajaxSubmit(function(result){
				if(result=='success'){

					parent.layer.msg('成功', {
						offset: ['260px'],
	     		        time: 1500//1.5s后自动关闭
	     		    });
					//关闭当前新增页面页
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭    
				}else{
					layer.msg('新增失败',{
						offset: ['260px']
					});
				}
			});
	    });

	    // 文件上传失败，现实上传出错。
	    uploader.on( 'uploadError', function( file ) {
	    	layer.msg("上传出错");
	    });

	    // 完成上传完了，成功或者失败，先删除进度条。
	    uploader.on( 'uploadComplete', function( file ) {
	    });
	    
	    
	   $("#saveButton").click(function(){

			var stuIdVal=$("#stuCode").val();		//学号
			var stunameVal=$("#stuname").val();	//姓名
			//var classIdVal=$("#classId").val();	//班级
			
			if(stuIdVal==null||stuIdVal==''){
				layer.tips('学号不能为空', '#stuCode');
				return;
			}

			if(stunameVal==null||stunameVal==''){
				layer.tips('姓名不能为空', '#stuname');
				return;
			}
			
//			if(classIdVal==null||classIdVal==''){
//				layer.tips('班级不能为空', '#class_select_add_id');
//				return;
//			}
			
			//如果有文件，则上传，成功后
			if(uploader.getFiles()[0]!=null){
			   	uploader.options.formData={stuCode:stuIdVal}; 
		    	uploader.upload();
		    	return;
			}
			
			var form = $("#studentFormId");
			form.ajaxSubmit(function(result){
				if(result=='success'){

					parent.layer.msg('成功', {
						offset: ['260px'],
	     		        time: 1500//1.5s后自动关闭
	     		    });
					//关闭当前新增页面页
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭    
				}else{
					layer.msg('失败',{
						offset: ['260px']
					});
				}
			});
	    	
	    });
	});
	*/
</script>