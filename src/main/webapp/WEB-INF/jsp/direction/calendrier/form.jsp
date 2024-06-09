<%@page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.view.CalendrierScolaire" %><%
    List<CalendrierScolaire> avenir = ((List<CalendrierScolaire>) request.getAttribute("avenir"));
    List<CalendrierScolaire> encours = ((List<CalendrierScolaire>) request.getAttribute("encours"));
    List<CalendrierScolaire> fini = ((List<CalendrierScolaire>) request.getAttribute("fini"));

%>

<%--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">--%>
<%--    Ajouter une Annee Scolaire--%>
<%--</button>--%>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ajouter une Annee Scolaire</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="save" method="post">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="nom_in">Nom:</label>
                        <input type="text" name="nom" id="nom_in" class="form-control" placeholder="ex: OCT2023 - JUIL2024" required>
                    </div>

                    <div class="form-group">
                        <label for="dateDebut_in">Date Commencement:</label>
                        <input type="date" class="form-control" name="dateDebut" id="dateDebut_in" required>
                    </div>

                    <div class="form-group">
                        <label for="dateFin_in">Date Fin:</label>
                        <input type="date" class="form-control" name="dateFin" id="dateFin_in" required>
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

<%--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ajouterEvenement">--%>
<%--    Planifier un Evenement--%>
<%--</button>--%>

<div class="modal fade" id="ajouterEvenement" tabindex="-1" role="dialog" aria-labelledby="ajouterEvenenement"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ajouterEvenenement">Planifier un evenement</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="evenement/save" method="post">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="nom_in">Nom:</label>
                        <input type="text" name="nom" id="nom_in" class="form-control" required placeholder="ex: Sortie Scolaire">
                    </div>

                    <div class="form-group">
                        <label for="dateDebut_in">Date Commencement:</label>
                        <input type="datetime-local" class="form-control" name="dateDebut" id="dateDebut_in" required>
                    </div>

                    <div class="form-group">
                        <label for="dateFin_in">Date Fin:</label>
                        <input type="datetime-local" class="form-control" name="dateFin" id="dateFin_in" required>
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

<%--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ajouterExamen">--%>
<%--    Planifier un Examen--%>
<%--</button>--%>

<div class="modal fade" id="ajouterExamen" tabindex="-1" role="dialog" aria-labelledby="ajouterExamen"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ajouterExamen">Planifier un Examen</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="examen/save" method="post">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="nom_in">Nom:</label>
                        <input type="text" name="nom" id="nom_in" class="form-control" placeholder="ex: Examen N°2" required>
                    </div>

                    <div class="form-group">
                        <label for="dateDebut_in">Date Commencement:</label>
                        <input type="date" class="form-control" name="dateDebut" id="dateDebut_in" required>
                    </div>

                    <div class="form-group">
                        <label for="dateFin_in">Date Fin:</label>
                        <input type="date" class="form-control" name="dateFin" id="dateFin_in" required>
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



<%@include file="list.jsp"%>

<script>
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('.modal-body input').val(recipient)
    })
</script>


<%--<form action="/anneeScolaire/save">--%>
<%--    <div class="form-group">--%>
<%--        <label for="nom_in">Nom:</label>--%>
<%--        <input type="text" name="nom" id="nom_in" class="form-control" required>--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--        <label for="dateDebut_in">Date Commencement:</label>--%>
<%--        <input type="date" class="form-control" name="dateDebut" id="dateDebut_in" required>--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--        <label for="dateFin_in">Date Fin:</label>--%>
<%--        <input type="date" class="form-control" name="dateFin" id="dateFin_in" required>--%>
<%--    </div>--%>

<%--    <button type="submit" class="btn btn-primary">Ajouter</button>--%>


<%--</form>--%>