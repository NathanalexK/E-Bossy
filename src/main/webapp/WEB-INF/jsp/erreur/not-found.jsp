<%@page pageEncoding="UTF-8" %>
<%
    String message = request.getAttribute("message").toString();
%>
<div class="row">
    <div class="col-md-12 d-flex flex-column justify-content-center align-items-center vh-10">
        <h1>Probleme!</h1>
        <p class="text-center"><%=message%></p>
        <%--        <a href="#">Back To Home</a>--%>
    </div>
</div>