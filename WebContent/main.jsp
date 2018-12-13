<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="./common.jspf" %>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>新闻系统首页</title>
		<link rel="stylesheet" href="${path}/common/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
		<script src="${path}/common/lib/jquery-1.12.4.min.js"></script>
		<script src="${path}/common/lib/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
		<script type="text/javascript" src="${path}/common/lib/Paging/js/paging.js"></script>
		<style>
			* {
				padding: 0;
				margin: 0;
			}
			/*
			  * 外面盒子样式---自己定义
			  */
			
			.page_div {
				margin-top: 20px;
				margin-bottom: 20px;
				font-size: 15px;
				font-family: "microsoft yahei";
				color: #666666;
				margin-right: 10px;
				padding-left: 20px;
				box-sizing: border-box;
			}
			/*
			 * 页数按钮样式
			 */
			
			.page_div a {
				min-width: 30px;
				height: 28px;
				border: 1px solid #dce0e0!important;
				text-align: center;
				margin: 0 4px;
				cursor: pointer;
				line-height: 28px;
				color: #666666;
				font-size: 13px;
				display: inline-block;
			}
			
			#firstPage,
			#lastPage {
				width: 50px;
				color: #0073A9;
				border: 1px solid #0073A9!important;
			}
			
			#prePage,
			#nextPage {
				width: 70px;
				color: #0073A9;
				border: 1px solid #0073A9!important;
			}
			
			.page_div .current {
				background-color: #0073A9;
				border-color: #0073A9;
				color: #FFFFFF;
			}
			
			.totalPages {
				margin: 0 10px;
			}
			
			.totalPages span,
			.totalSize span {
				color: #0073A9;
				margin: 0 5px;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<table class="table">
					<tr>
						<th>编码</th>
						<th>姓名</th>
						<th>年龄</th>
					</tr>
					<c:forEach items="${demoPage.pageList}" var="user">
						 <tr>
							<td>${user.id}</td>
							<td>${user.name}</td>
							<td>${user.age}</td>
						</tr>
					</c:forEach>
				</table>
				
				<div class="row">
					 <div id="page" class="page_div"></div>
				</div>
				<script type="text/javascript">
					//分页
					$("#page").paging({
						pageNo: ${demoPage.thisPage},
						totalPage:${demoPage.totalPage },
						totalSize:${demoPage.totalNum },
						callback: function(num) {
							window.location.href= '${path}/user?command=findUserPage&thisPage='+ num;
						}
					})
				</script>
				
				
				
				
				
				
				
				<hr />
				
				
				
				
				
				
				
				<div class="row">
				一共有【${demoPage.totalPage }】页，
				一共有【${demoPage.totalNum }】条数据
				当前是第【${demoPage.thisPage}】页
				</div>
				<c:if test="${demoPage.thisPage > 1}">
					<a href="${path}/user?command=findUserPage&thisPage=1">首页</a>
				</c:if>
				<c:if test="${demoPage.thisPage > 1}">
					<a href="${path}/user?command=findUserPage&thisPage=${demoPage.thisPage - 1}">上一页</a>
				</c:if>
				<c:if test="${demoPage.thisPage < demoPage.totalPage}">
					<a href="${path}/user?command=findUserPage&thisPage=${demoPage.thisPage + 1}">下一页</a>
				</c:if>
				<c:if test="${demoPage.thisPage < demoPage.totalPage}">
					<a href="${path}/user?command=findUserPage&thisPage=${demoPage.totalPage }">尾页</a>
				</c:if>
			</div>
		</div> 
	</body>
</html>	