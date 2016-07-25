<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>检测进度查询</title>
	<style type="text/css">
		table.hovertable {
			font-family: verdana,arial,sans-serif;
			font-size:11px;
			color:#333333;
			border-width: 1px;
			border-color: #999999;
			border-collapse: collapse;
		}
		table.hovertable th {
			background-color:#c3dde0;
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #a9c6c9;
		}
		table.hovertable tr {
			background-color:#d4e3e5;
		}
		table.hovertable td {
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #a9c6c9;
		}
	</style>
</head>
<body style="font-size:50px;">
	<table class="hovertable" style="width:100%;">
		<tr style="font-size:50px;">
			<th colspan="2" align="center">委托检测进度查询</th>
		</tr>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" style="font-size:40px;">
			<td>委托单号</td><td><%=request.getParameter("commissionCode") %></td>
		</tr>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" style="font-size:40px;">
			<td>委托单位名称</td><td><%=request.getParameter("customerName") %></td>
		</tr>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" style="font-size:40px;">
			<td>器具名称：</td><td><%=request.getParameter("applianceName") %></td>
		</tr>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" style="font-size:40px;">
			<td>委托日期</td><td><%=request.getParameter("commissionDate")%></td>
		</tr>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" style="font-size:40px;">
			<td>器具数量</td><td><%=request.getParameter("quantity") %></td>
		</tr>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" style="font-size:40px;">
			<td>检测进度</td><td><%=request.getParameter("status") %></td>
		</tr>
	</table>
	<br/>
	<p style="font-size:40px;">注：本查询结果仅供客户参考，最终解释权归常州市计量测试技术研究所所有！</p>
</body>
</html>