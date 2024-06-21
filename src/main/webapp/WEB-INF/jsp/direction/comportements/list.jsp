<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Comportements" %>
<%@ page import="com.project.ebossy.model.Classe" %>
<%@ page import="com.project.ebossy.view.ComportementsEleve" %>
<%
    List<Classe> classeList = (List<Classe>) request.getAttribute("classeList");
    List<Comportements> comportementsList = (List<Comportements>) request.getAttribute("comportementsList");
    List<ComportementsEleve> eleveList = (List<ComportementsEleve>) request.getAttribute("eleveList");
    Classe selectedClasse = (Classe) request.getAttribute("selectedClasse");
    int num = 0; // Initialisation de la variable num
%>

<div class="d-flex w-100 justify-content-between py-2">
    <h2>Comportements Eleves par Classe</h2>

    <div class="d-flex">
        <div class="align-items-center px-2 pt-1 row">
            <form action="">
                <select name="comportement" id="comportement_in" class="form-control">
                    <% for (Comportements comp : comportementsList) { %>
                    <option value="<%=comp.getId()%>"
                            <%=selectedComportements != null && comp.getId().equals(selectedComportements.getId()) ? "selected" : ""%>>
                            <%=comp.getNom()%>
                    </option>
                    <% } %>
                </select>
                <select name="classe" id="classe_in" class="form-control">
                    <% for (Classe c : classeList) { %>
                    <option value="<%=c.getId()%>"
                            <%=selectedClasse != null && c.getId().equals(selectedClasse.getId()) ? "selected" : ""%>>
                            <%=c.getNomClasse()%>
                    </option>
                    <% } %>
                </select>
            </form>
        </div>
    </div>
</div>

<div class="table-responsive card container-fluid">
    <input type="hidden" name="idClasse" value="<%=selectedClasse != null ? selectedClasse.getId() : ""%>">

    <table class="table table-striped table-borderless text-center">
        <thead>
        <tr>
            <th>#</th>
            <th>Nom et Prenom</th>
            <th>Id Classe</th>
            <th>Comportements</th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <% if (eleveList != null) { %>
            println("Tsisy eleve");
            <% for (ComportementsEleve eleve : eleveList) {
                num++;
            %>
            <tr>
                <td><%=num%></td>
                <td class="text-left"><%=eleve.getIdEleve()%></td>
                <td><%=eleve.getIdClasse()%></td>
                <td><%=eleve.getIdComportements()%></td>
                <td>
                    <a href="/eleve/information?id=<%=eleve.getIdEleve()%>">Voir informations</a>
                </td>
            </tr>
            <% } %>
        <% } %>
        </tbody>
    </table>
</div>
