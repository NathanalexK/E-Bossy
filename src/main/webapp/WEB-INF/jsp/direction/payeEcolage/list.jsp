<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Classe" %>
<%@ page import="com.project.ebossy.view.EleveNonPayeEcolage" %><%
    List<EleveNonPayeEcolage> eleveNonPayeEcolages = ((List<EleveNonPayeEcolage>) request.getAttribute("eleveList"));
    List<Classe> classeList = ((List<Classe>) request.getAttribute("classeList"));

%>
<form action="list" method="post">
    <div class="form-group">
        <label for="classe">Classe:</label>
        <select name="idclasse" id="classe" class="form-control">
            <option value="0">All</option>
            <%
                for(Classe classe : classeList)
                {
            %>
            <option value="<%=classe.getId()%>"><%=classe.getNomClasse()%></option>
            <%
                }
            %>

        </select>
    </div>

    <button type="submit" class="btn btn-primary">Selecter</button>


</form>
<div class="text-center py-2">
    <h2>Liste des eleves qui n'ont pas paye son ecolage</h2>
</div>

<div class="table-responsive card">
    <table class="table table-striped table-borderless">
        <thead><tr>
            <th>Nom</th>
            <th>Prenom</th>
            <th>idClasse</th>
            <th>numero</th>
            <th>idTuteur</th>
        </tr></thead>

        <tbody>
        <%
            for (EleveNonPayeEcolage eleveNonPayeEcolage : eleveNonPayeEcolages)
            {
        %>
        <tr>
            <td><%=eleveNonPayeEcolage.getNom()%></td>
            <td><%=eleveNonPayeEcolage.getPrenom()%></td>
            <td><%=eleveNonPayeEcolage.getIdClasse()%></td>
            <td><%=eleveNonPayeEcolage.getNumero()%></td>
            <td><%=eleveNonPayeEcolage.getIdTuteur()%></td>
        </tr>


        <%
            }
        %>
        </tbody>
    </table>
</div>

