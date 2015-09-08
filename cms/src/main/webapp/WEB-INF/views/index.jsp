<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery-easyui/plugins/jquery.validatebox.js"></script>
    <script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core-3.5.min.js"></script>
	<title>后台管理</title>
	<script type="text/javascript">
		window.setInterval('showTime()', 1000);
		function showTime() {
			var enabled = 0;
			today = new Date();
			var day;
			var date;
			if (today.getDay() == 0)
				day = "星期日";
			if (today.getDay() == 1)
				day = "星期一";
			if (today.getDay() == 2)
				day = "星期二";
			if (today.getDay() == 3)
				day = "星期三";
			if (today.getDay() == 4)
				day = "星期四";
			if (today.getDay() == 5)
				day = "星期五";
			if (today.getDay() == 6)
				day = "星期六";
			var minntes = today.getMinutes() <= 9 ? ("0" + today.getMinutes()): today.getMinutes();
			var second = today.getSeconds() <= 9 ? ("0" + today.getSeconds()) : today.getSeconds();
			date = (today.getYear() + 1900) + "年" + (today.getMonth() + 1) + "月"
					+ today.getDate() + "日 " + day +" "+ today.getHours() + ":" + minntes + ":" + second;
			document.getElementById("time").innerHTML = date;
		}
		$(document).ready(function(){
			showTime();
		});
		var setting = {
			view: {
				selectedMulti: false
			},
			data: {
				key: {
					title:"t"
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: zTreeOnClick
			}
		};
		function zTreeOnClick(event, treeId, treeNode) {
			var href = treeNode.uri;
			if(href != "" && href != "javascript:void(0)" && href != '${ctx}'){
				//$("#main_frame").attr('src',href);
				addTab(treeNode.name, href,treeNode.id);
				var width = document.body.offsetWidth;
				var height = document.body.offsetHeight;
				var leftWidth = $("#left_menu").width();
				var rightWidth = $("#right").width();
				var top_height = $("#top_menu").height();
				$("#main_frame").css("width",(width -leftWidth - rightWidth)+"px").css("height",(height-top_height)+"px");
			}
		};
		var zNodes = ${list};
	
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
		function addTab(title, url,id){
			if ($('#main').tabs('exists', title)){
				$('#main').tabs('select', title);
			} else {
				var content = '<iframe id="'+id+'" scrolling="auto" frameborder="0"  src="${ctx}'+url+'" style="width:100%;height:100%;"></iframe>';
				$('#main').tabs('add',{
					title:title,
					content:content,
					closable:true
				});
				var width = document.body.offsetWidth;
				var height = document.body.offsetHeight;
				var leftWidth = $("#left_menu").width();
				var rightWidth = 50;
				var top_height = $("#top_menu").height();
				$("#"+id).css("width",(width -leftWidth - rightWidth)+"px").css("height",(height-top_height - 100)+"px");
			}
		}
	</script>
	<style type="text/css">
		.mask {
		    position: absolute;
		    left: 0;
		    top: 0;
		    width: 100%;
		    height: 100%;
		    filter: alpha(opacity = 40);
		    opacity: 0.40;
		    font-size: 1px;
		    text-align:center;
		    overflow: hidden;
		    background: #ccc;
		}
		.mask-msg {
			filter: alpha(opacity = 0);
			opacity: 0;
		}
	</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" id="top_menu" style="height:60px;background:#B3DFDA;padding:10px">
		<%-- 欢迎[<shiro:principal/>]登录 --%>
		<span style="float:right;">
			<span>欢迎您：${user.realName}</span>
			&nbsp;&nbsp;&nbsp;
			当前时间：<span id="time"></span>
			<span><a href="${ctx}/logout">退出</a></span>
		</span>
		
	</div>
	<div data-options="region:'west',split:true" id="left_menu" style="width:260px;">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	<div id="right" data-options="region:'east',split:true,collapsed:true,title:'快捷入口'" style="width:500px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:35px;background:#A9FACD;padding:10px;text-align:center;">south region</div>
	<div data-options="region:'center'" id="main" class="easyui-tabs">
		<!-- <iframe id="main_frame" name="main_frame" src="" frameborder="0" width="100%" height="500px;"></iframe> -->
	</div>
</body>
</html>