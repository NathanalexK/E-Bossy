<%@ page import="com.project.ebossy.util.Role" %>
<%@page pageEncoding="UTF-8" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="../assets/css/bootstrap.css">
</head>
<body>
    <div class="container">
        <h2>Se Connecter en tant que:</h2>
        <a href="/login/form?role=<%=Role.DIRECTEUR%>" >Directeur</a>
        <a href="/login/form?role=<%=Role.SECRETAIRE%>">Secretaire</a>
        <a href="/login/form?role=<%=Role.PROFESSEUR%>">Professeur</a>
        <a href="/login/form?role=<%=Role.PARENT%>">Parent</a>
        <a href="/login/form?role=<%=Role.ELEVE%>">Eleve</a>
    </div>
</body>
</html>