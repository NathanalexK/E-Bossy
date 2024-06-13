<%@ page import="com.project.ebossy.model.Secretaire" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Sexe" %>
<%
    List<Secretaire> secretaireList = ((List<Secretaire>) request.getAttribute("secretaireList"));
    List<Sexe> sexeList = ((List<Sexe>) request.getAttribute("sexeList"));
%>

<div class="text-center py-2">
    <h1>Ajouter un Secretaire</h1>
</div>

<form action="save" method="post">
    <div class="form-group">
        <label for="nom_in">Nom du Secretaire:</label>
        <input type="text" name="nom" id="nom_in" class="form-control">
    </div>

    <div class="form-group">
        <label for="prenom_in">Prenom du Secretaire:</label>
        <input type="text" name="prenom" id="prenom_in" class="form-control">
    </div>

    <div class="form-group">
        <label for="date_in">Date de Naissance:</label>
        <input type="date" name="dateNaissance" id="date_in" class="form-control">
    </div>

    <div class="form-group">
        <label for="adresse_in">Adresse:</label>
        <input type="text" name="adresse" id="adresse_in" class="form-control">
    </div>

    <div class="form-group">
        <label for="contact_in">contact:</label>
        <input type="text" name="contact" id="contact_in" class="form-control">
    </div>

    <div class="form-group">
        <label for="email_in">Votre Email:</label>
        <input type="email" name="email" id="email_in" class="form-control">
    </div>

    <div class="form-group">
        <label for="mdp_in">Votre Mot de passe:</label>
        <input type="password" name="mdp" id="mdp_in" class="form-control">
    </div>

    <div class="form-group">
        <label for="idSexe_in">Sexe:</label>
        <select name="idSexe" id="idSexe_in" class="form-control">
            <%
                for(Sexe sexe : sexeList)
                {
            %>
            <option value="<%=sexe.getId()%>"><%=sexe.getTypeSexe()%></option>
            <%
                }
            %>

        </select>
    </div>

    <button type="submit" class="btn btn-primary">Ajouter</button>
</form>

<%@ include file="list.jsp"%>