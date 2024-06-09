<%@page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Sexe" %>
<%
    List<Sexe> sexeList = ((List<Sexe>) request.getAttribute("sexeList"));
%>

<div class="container my-2">
    <div class="card card-body">
        <div class="card-title">
            <h2>Inscrire un élève</h2>
        </div>
        <form action="/tuteur/inscription/save" method="post">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="nom_in">Nom</label>
                    <input type="text" class="form-control" id="nom_in" placeholder="" name="nom">
                </div>
                <div class="form-group col-md-6">
                    <label for="inputPassword4">Prenom</label>
                    <input type="text" class="form-control" id="inputPassword4" placeholder="" name="prenom">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-9">
                    <label for="date_in">Date de Naissance</label>
                    <input type="date" class="form-control" id="date_in" name="date" required>
                </div>

                <div class="form-group col-md-3">
                    <label for="sexe_in">Sexe</label>
                    <select name="sexe" class="form-control" id="sexe_in">
                        <%
                            for (Sexe sexe : sexeList) {
                        %>
                        <option value="<%=sexe.getId()%>"><%=sexe.getTypeSexe()%>
                        </option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="inputAddress2">Addresse</label>
                <input type="text" class="form-control" id="inputAddress2" placeholder="LOT IPN 20A" name="adresse">
            </div>
            <div class="form-group">
                <label for="contact_in">Contact</label>
                <input type="text" class="form-control" id="contact_in" name="contact">
            </div>
            <div class="form-group">
                <label for="parent_in">Parent</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="parent_in" name="contact">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" data-toggle="modal" data-target="#exampleModal">Rechercher Parent</button>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="email_in">Creer un identifiant unique:</label>
                <input type="text" class="form-control" id="email_in" name="identifiant">
            </div>
            <div class="form-group">
                <label for="password_in">Mot de passe</label>
                <input type="password" class="form-control password" id="password_in" name="mdp">
            </div>

            <div class="form-group">
                <label for="password_in2">Confirmer votre mot de passe</label>
                <input type="password" class="form-control password" id="password_in2">
            </div>

            <div class="form-group text-right">
                <input type="checkbox" id="showpwd"> Afficher le mot de passe
            </div>


            <button type="submit" class="btn btn-primary">Inscrire</button>
        </form>
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Rechercher un parent</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="save" method="post">

                <div class="modal-body">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </div>
            </form>

        </div>
    </div>
</div>

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
