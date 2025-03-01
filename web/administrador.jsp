<%-- 
    Document   : administrador.jsp
    Created on : 01-mar-2025, 15:37:55
    Author     : adamj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello WorlAAd!</h1>
        <h2><%=session.getAttribute("id")%></h2>
        <h2><%=session.getAttribute("nombre")%></h2>
        <h2><%=session.getAttribute("email")%></h2>
        <h2><%=session.getAttribute("contrasenna")%></h2>
        <h2><%=session.getAttribute("tipo")%></h2>
    </body>
</html>
