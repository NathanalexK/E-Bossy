<%
    List<Salle> salleList = ((List<Salle>) request.getAttribute("salleList"));
    Long capacite = (Long)request.getAttribute("capacite");
    Long nombre = (Long)request.getAttribute("nombre");
%>

<%--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">--%>
<%--    Ajouter une salle--%>
<%--</button>--%>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ajouter une Salle</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="save" method="post">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="numero_in">Nom du Salle:</label>
                        <input type="text" name="numero" id="numero_in" class="form-control">
                    </div>


                    <div class="form-group">
                        <label for="capacite_in">Capacite:</label>
                        <input type="number" name="capacite" id="capacite_in" class="form-control">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </div>
            </form>

        </div>
    </div>
</div>


<%--<div class="text-center py-2">--%>
<%--    <h1>Ajouter une salle</h1>--%>
<%--</div>--%>


<%--<form action="save" method="post">--%>
<%--    <div class="form-group">--%>
<%--        <label for="numero_in">Nom du Salle:</label>--%>
<%--        <input type="text" name="numero" id="numero_in" class="form-control">--%>
<%--    </div>--%>


<%--    <div class="form-group">--%>
<%--        <label for="capacite_in">Capacite:</label>--%>
<%--        <input type="number" name="capacite" id="capacite_in" class="form-control">--%>
<%--    </div>--%>

<%--    <button type="submit" class="btn btn-primary">Ajouter</button>--%>
<%--</form>--%>

<%@ include file="list.jsp"%>


<%--<jsp:include page="direction/salle/list"></jsp:include>--%>