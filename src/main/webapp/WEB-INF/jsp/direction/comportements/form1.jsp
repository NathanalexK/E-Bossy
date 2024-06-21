<%@ page import="com.project.ebossy.model.Classe" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" %>
<%
    List<Classe> classeList = (List<Classe>) request.getAttribute("classeList");
    List<Comportements> ComportementsList = (List<Comportements>) request.getAttribute("ComportementsList");
%>

<div class="text-center py-2">
    <h1>Définir Comportement Élève</h1>
</div>

<form action="save1" method="post">
    <div class="form-group">
        <label for="idClasse">Classe:</label>
        <select name="idClasse" id="idClasse_in" class="form-control">
            <%
                if (classeList != null && !classeList.isEmpty()) {
                    for (Classe classe : classeList) {
            %>
                <option value="<%= classe.getId() %>"><%= classe.getNomClasse() %></option>
            <%
                    }
                } else {
                    out.println("classeList is empty or null");
                }
            %>
        </select>
    </div>

    
    
    <button type="submit" class="btn btn-primary">Ajouter</button>
</form>
