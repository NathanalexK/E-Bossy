<%@page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Sexe" %>
<%@ page import="com.project.ebossy.model.Niveau" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.project.ebossy.model.Professeur" %>
<%--<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%
    List<Sexe> sexeList = ((List<Sexe>) request.getAttribute("sexeList"));
    Map<String,String> erreurs = request.getAttribute("erreurs") != null ? ( (Map<String, String>) request.getAttribute("erreurs")) : new HashMap<>();
    String success = ((String) request.getAttribute("success"));
    Professeur professeur = request.getAttribute("professeur") != null ? ((Professeur) request.getAttribute("professeur")) : new Professeur();
%>

<div class="container my-2">
    <div class="card card-body">
        <div class="card-title">
            <h2>Formulaire d'ajout de Professeur</h2>
        </div>


        <%--        <c:if test="${not empty errorMessage}">--%>
        <%--            <div class="alert alert-danger">--%>
        <%--                <p>${errorMessage}</p>--%>
        <%--            </div>--%>
        <%--        </c:if>--%>

        <%
            if(success != null)
            {
        %>
        <div class="alert alert-success">
            <%=success%>
        </div>
        <%
            }
        %>

        <%
            if (!erreurs.isEmpty())
            {
        %>
        <div class="alert alert-danger">
            Erreur lors de l'inscription
        </div>
        <%
            }
        %>

        <form action="save" method="post">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="nom_in">Nom</label>
                    <input type="text" class="form-control" id="nom_in" placeholder="" name="nom" value="<%=professeur.getNom() != null ? professeur.getNom() : ""%>">
                    <small class="text-danger"><%=erreurs.get("nom")!=null ? erreurs.get("nom") : ""%></small>
                </div>
                <div class="form-group col-md-6">
                    <label for="inputPassword4">Prenom</label>
                    <input type="text" class="form-control" id="inputPassword4" placeholder="" name="prenom" value="<%=professeur.getPrenom() != null ? professeur.getPrenom() : ""%>">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-9">
                    <label for="date_in">Date de Naissance</label>
                    <input type="date" class="form-control" id="date_in" name="dateNaissance" value="<%=professeur.getDateNaissance()%>" required>
                    <small class="text-danger"><%=erreurs.get("dateNaissance")!=null ? erreurs.get("dateNaissance") : ""%></small>

                </div>

                <div class="form-group col-md-3">
                    <label for="sexe_in">Sexe</label>
                    <select name="idSexe" class="form-control" id="sexe_in">
                        <%
                            for (Sexe sexe : sexeList) {
                        %>
                        <option value="<%=sexe.getId()%>" <%=professeur.getIdSexe() != null ? (professeur.getIdSexe().getId().equals(sexe.getId()) ? "selected" : "") : ""%>>
                            <%=sexe.getTypeSexe() %>
                        </option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="debut_in">Date de debut du carriere:</label>
                <input type="date" class="form-control" id="debut_in" name="debutCarriere" value="<%=professeur.getDebutCarriere()%>" required>
            </div>

            <div class="form-group">
                <label for="inputAddress2">Addresse</label>
                <input type="text" class="form-control" id="inputAddress2" placeholder="LOT IPN 20A" name="adresse" value="<%=professeur.getAdresse() != null ? professeur.getAdresse() : ""%>">
            </div>
            <div class="form-group">
                <label for="contact_in">Contact</label>
                <input type="text" class="form-control" id="contact_in" name="contact" value="<%=professeur.getConctact() != null ? professeur.getConctact() : ""%>">
            </div>
            <div class="form-group">
                <label for="email_in">Email:</label>
                <input type="email" class="form-control" id="email_in" name="email" value="<%=professeur.getEmail() != null ? professeur.getEmail() : ""%>">
                <small class="text-danger"><%=erreurs.get("email")!=null ? erreurs.get("email") : ""%></small>

            </div>

            <div class="form-group">
                <label for="identifiant_in">Creer un identifiant unique:</label>
                <small>(A utiliser pour se connecter)</small>
                <input type="text" class="form-control" id="identifiant_in" name="identifiant" value="<%=professeur.getIdentifiant() != null ? professeur.getIdentifiant() : ""%>">
                <small class="text-danger"><%=erreurs.get("identifiant")!=null ? erreurs.get("identifiant") : ""%></small>

            </div>

            <div class="form-row">
                <div class="form-group col-6">
                    <label for="password_in">Mot de passe</label>
                    <input type="password" class="form-control password" id="password_in" name="mdp">
                    <small class="text-danger"><%=erreurs.get("mdp")!=null ? erreurs.get("mdp") : ""%></small>

                </div>

                <div class="form-group col-6">
                    <label for="password_in2">Confirmer votre mot de passe</label>
                    <input type="password" class="form-control password" id="password_in2">
                </div>

                <div class="form-group text-right">
                    <input type="checkbox" id="showpwd"> Afficher le mot de passe
                </div>
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
    });

    document.addEventListener("DOMContentLoaded", () => {
        const checkbox = document.querySelector('#showpwd');
        document.querySelectorAll(".password").forEach(input => {
            input.type = checkbox.checked ? 'text' : 'password';
        })

        checkbox.addEventListener('change', () => {
            document.querySelectorAll(".password").forEach(input => {
                input.type = checkbox.checked ? 'text' : 'password';
            })
        })
    });
</script>
