<%
    List<Salle> salleList = ((List<Salle>) request.getAttribute("salleList"));
%>


<div class="text-center py-2">
    <h1>Ajouter une salle</h1>
</div><div class="tex
    <h1>Ajouter
</div>


<form action="save" method="post">
    <div class="form-group">
        <label for="numero_in">Nom du Salle:</label>
        <input type="text" name="numero" id="numero_in" class="form-control">
    </div>


    <div class="form-group">
        <label for="capacite_in">Capacite:</label>
        <input type="number" name="capacite" id="capacite_in" class="form-control">
    </div>

    <button type="submit" class="btn btn-primary">Ajouter</button>
</form>

<%@ include file="list.jsp"%>

<%--<jsp:include page="direction/salle/list"></jsp:include>--%>