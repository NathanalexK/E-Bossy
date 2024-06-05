<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Classe" %><%
//    List<Classe> classeList;
%>

<div class="text-center py-2">
    <h2>Liste des Classes</h2>
</div>

<div class="table-responsive card">
    <table class="table table-striped table-borderless">
        <thead><tr>
            <th>Nom du Classe</th>
            <th>Nom du Salle:</th>
            <th>Capacité</th>
            <th>Responsable</th>
            <th>Actions</th>
        </tr></thead>

        <tbody>
        <%
            for (Classe classe : classeList)
            {
        %>
        <tr>
            <td><%=classe.getNomClasse()%></td>
            <td><%=classe.getIdSalle().getNumero()%></td>
            <td><%=classe.getIdSalle().getCapacite()%></td>
            <td><%=classe.getIdTitulaire()%></td>
            <td>
                <form action="delete" method="post" >
                    <input type="hidden" value="<%=classe.getId()%>" name="idClasse">
                    <button type="submit" class="btn btn-light border">
                        <img src="/assets/icon/close.svg" width="16px" class=""/>
                        Supprimer
                    </button>
                </form>
            </td>
        </tr>


        <%
            }
        %>
        </tbody>
    </table>
</div>

