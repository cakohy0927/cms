<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/project/css/table.css">
<script type="text/javascript" src="${ctx}/static/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/project/js/table.js"></script>
<style type="text/css">
</style>
<title>角色列表</title>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#roleList").tableList({
				method: "post",
				url : "${ctx}/menu/getListJson",
				tableId: "#menuList",
				pageSize: 10,
				title:'菜单列表',
				checkbox:true,
				columns:[{
					feild:"id",
					width:'200',
					description : "编号"
				},{
					feild:"name",
					width:'200',
					description : "名称",
				},{
					feild:"path",
					width:'200',
					description : "路径",
				},{
					feild:"operate",
					width:'200',
					description : "操作",
					paramformtter:test()
				}]
			});
		});
		
		function  test() {
			return "<a href=\"javascript:void(0)\">修改</a>";
		}
	</script>
</head>
<body>
		<div class="container-fluid">
			<table id="menuList" class="table table-striped table-bordered table-hover" role-table-url="">
				
			</table>
		</div>
</body>
</html>