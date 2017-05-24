<%
   String rp = request.getContextPath();
   request.getSession().invalidate();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>PicketLink cluster test</title>
   </head>
   <body>
      Goodbye or <a href="<%=rp%>">start again</a>
   </body>
</html>