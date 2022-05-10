<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2022/3/13
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String pathName=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
pageContext.setAttribute("basepath",pathName);

%>

<base href=<%=pathName%>>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
