<%@ page import="com.project.ebossy.model.Classe" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.EleveAnneeScolaire" %>
<%@page pageEncoding="UTF-8" %>
<%
    List<EleveAnneeScolaire> eleveList = ((List<EleveAnneeScolaire>) request.getAttribute("eleveList"));
    int totalPages = ((int) request.getAttribute("totalPages"));
    int num = 0;
%>




<div class="d-flex w-100 justify-content-between py-2">
    <h2>Eleves par Classe</h2>

    <div class="d-flex ">

    </div>
</div>

<div class="table-responsive card container-fluid">

    <div class="fixed-bottom text-center border-top py-2 _action-div">
        <button type="button" class="btn bg-light" onclick="location.reload(true)">Annuler</button>
        <button type="submit" class="btn btn-primary">Sauveguarder</button>
    </div>

<%--    <input type="hidden" name="idClasse" value="<%=classe.getId()%>">--%>

    <table class="table table-striped table-borderless text-center">
        <thead>
        <tr>
<%--            <th>#</th>--%>
            <th>Nom et prenom</th>
            <th>Date de naissance</th>
            <th>Sexe</th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <%
            for (EleveAnneeScolaire eleve : eleveList) {
                num++;
        %>
        <tr>
<%--            <td><%=num%>--%>
<%--            </td>--%>
            <td class="text-left"><%=eleve.getIdEleve().getNom()%> <%=eleve.getIdEleve().getPrenom()%>
            </td>
            <td><%=eleve.getIdEleve().getDateNaissance()%>
            </td>
            <td><%=eleve.getIdEleve().getIdSexe().getTypeSexe().charAt(0)%>
            </td>
            <td>
                <a href="/eleve/information?id=<%=eleve.getIdEleve().getId()%>">Voir informations</a>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>

    </table>

    <div class="row d-flex justify-content-center">
        <div aria-label="Page navigation example " class="bg-transparent">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <%
                    for(int i = 0; i < totalPages; i++){
                %>
                    <li class="page-item"><a class="page-link" href="?page=<%=i%>"><%=i+1%></a></li>
                <%
                    }
                %>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </div>
    </div>



</div>

