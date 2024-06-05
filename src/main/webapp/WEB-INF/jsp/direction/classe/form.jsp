<%@ page import="com.project.ebossy.model.Salle" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Niveau" %>
<%@ page import="com.project.ebossy.view.SalleDisponible" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    List<SalleDisponible> salleList = ((List<SalleDisponible>) request.getAttribute("salleList"));
    List<Niveau> niveauList = ((List<Niveau>) request.getAttribute("niveauList"));
    List<Classe> classeList = ((List<Classe>) request.getAttribute("classeList"));
%>


<div class="text-center py-2">
    <h1>Ajouter une Classe</h1>
</div>


<form action="save" method="post">
    <div class="form-group">
        <label for="nom_in">Nom du Classe:</label>
        <input type="text" name="nomClasse" id="nom_in" class="form-control">
    </div>


    <div class="form-group">
        <label for="niveau_in">Niveau:</label>
        <select name="idNiveau" id="niveau_in" class="form-control">
            <%
                for(Niveau niveau : niveauList)
                {
            %>
                <option value="<%=niveau.getId()%>"><%=niveau.getNomNiveau()%></option>
            <%
                }
            %>

        </select>
    </div>

    <div class="form-group">
        <label for="salle_in">Salle:</label>
        <select name="idSalle" id="salle_in" class="form-control" required>
            <%
                for(SalleDisponible salle : salleList)
                {
            %>
                    <option value="<%=salle.getId()%>"><%=salle.getNumero()%></option>
            <%
                }
            %>

        </select>

    </div>

    <div class="form-group">
        <label for="titulaire_in">Responsable de Classe:</label>
        <select name="idProf" id="titulaire_in" class="form-control">
            <option value="1">Pas de Responsable</option>
        </select>
    </div>

    <button type="submit" class="btn btn-primary">Ajouter</button>
</form>

<%@ include file="list.jsp"%>