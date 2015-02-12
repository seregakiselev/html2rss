<%@ page import="util.Misc" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String worksheet = Misc.norm(request.getParameter("worksheet"));
%>
<html>
  <head>
    <jsp:include page="head.jsp"/>
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <jsp:include page="<%=worksheet%>"/>
  </body>
</html>