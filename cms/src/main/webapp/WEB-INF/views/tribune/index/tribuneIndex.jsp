<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib uri="http://curtainContain/tag" prefix="curtain"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.css.map">
<script type="text/javascript" src="${ctx}/static/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<title>bbs首页</title>
<style type="text/css">
	.menu {
		float: left;
		margin: 5px 0px 5px 10px;
	}
	
	.logincss {
		float: right;
		color: #ddd;
		margin: 5px 5px 5px 0;
	}
	
	.menu a:HOVER, .logincss a:HOVER {
		text-decoration: none;
		color: white;
	}
	
	a:HOVER a:LINK, .logincss a:LINK {
		text-decoration: none;
		color: white;
	}
	
	a {
		margin-left: 5px;
		color: white;
	}
	
	#sddm {
		margin: 0 auto;
		padding: 0;
		z-index: 30;
		width: auto;
		height: 23px;
	}
	
	#sddm li {
		margin: 0;
		padding: 0;
		list-style: none;
		float: left;
		font: bold 12px arial
	}
	
	#sddm li a {
		display: block;
		margin: 0 1px 0 0;
		padding: 4px 10px;
		text-align: center;
		text-decoration: none
	}
	
	#sddm li a:hover {
		background: #49A3FF
	}
	
	#sddm div {
		position: absolute;
		visibility: hidden;
		margin: 0;
		padding: 0;
	}
	
	#sddm div a {
		position: relative;
		display: block;
		margin: 0;
		padding: 5px 10px;
		width: auto;
		white-space: nowrap;
		text-align: left;
		text-decoration: none;
		background: #EAEBD8;
		color: #2875DE;
		font: 12px arial
	}
	
	#sddm div a:hover {
		background: #49A3FF;
		color: #FFF
	}
</style>
<script type="text/javascript">
	var timeout = 500;
	var closetimer = 0;
	var ddmenuitem = 0;
	// open hidden layer
	function mopen(id) {
		// cancel close timer
		mcancelclosetime();
		// close old layer
		if (ddmenuitem)
			ddmenuitem.style.visibility = 'hidden';
		// get new layer and show it
		ddmenuitem = document.getElementById(id);
		ddmenuitem.style.visibility = 'visible';
	}
	// close showed layer
	function mclose() {
		if (ddmenuitem)
			ddmenuitem.style.visibility = 'hidden';
	}
	// go close timer
	function mclosetime() {
		closetimer = window.setTimeout(mclose, timeout);
	}

	// cancel close timer
	function mcancelclosetime() {
		if (closetimer) {
			window.clearTimeout(closetimer);
			closetimer = null;
		}
	}
	// close layer when click-out
	document.onclick = mclose;
</script>
</head>
<body>
	<div class="container">
		<div class="row" style="background-color:#15a230">
			<span class="menu">
				<ul id="sddm">
					<li>
						<a href="#" onmouseover="mopen('m1')" onmouseout="mclosetime()">首页</a>
						<div id="m1" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
							<a href="#">HTML DropDown</a>
							<a href="#">DHTML DropDown menu</a>
							<a href="#">JavaScript DropDown</a>
							<a href="#">DropDown Menu</a>
							<a href="#">CSS DropDown</a>
						</div>
					</li>
					<li><a href="#" onmouseover="mopen('m2')" onmouseout="mclosetime()">开源项目</a>
						<div id="m2" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
						<a href="#">ASP Dropdown</a>
						<a href="#">Pulldown menu</a>
						<a href="#">AJAX dropdown</a>
						<a href="#">DIV dropdown</a>
						</div>
					</li>
					<li><a href="#" onmouseover="mopen('m3')" onmouseout="mclosetime()">代码</a>
						<div id="m3" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
						<a href="#">Visa Credit Card</a>
						<a href="#">Paypal</a>
						</div>
					</li>
					<li><a href="#" onmouseover="mopen('m4')" onmouseout="mclosetime()">博客</a>
						<div id="m4" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
						<a href="#">Download Help File</a>
						<a href="#">Read online</a>
						</div>
					</li>
					<li><a href="#" onmouseover="mopen('m5')" onmouseout="mclosetime()">资讯</a>
						<div id="m5" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
						<a href="#">E-mail</a>
						<a href="#">Submit Request Form</a>
						<a href="http://www.lanrentuku.com/" target="_blank">lanrentuku.com</a>
						</div>
					</li>
				</ul>
			</span>
			<span class="logincss">
				当前访客身份：游客
				<a href="javascript:void(0)">[ 登录</a>
				|
				<a href="javascript:void(0)">加入落幕  ]</a>
				
			</span>
		</div>
		<div class="row">
			这是下面的内容
		</div>
	</div>
</body>
</html>