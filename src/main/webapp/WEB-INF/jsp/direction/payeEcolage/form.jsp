<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Classe" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    List<Classe> classeList = ((List<Classe>) request.getAttribute("classeList"));
%>


<div class="py-2 text-center">
    <h2>Effectuez une paiement de scolarite </h2>
</div>

<form action="save">
    <div class="form-group">
        <label for="numero_in">Numero d'eleve:</label>
        <input type="number" name="numero" id="numero_in" class="form-control" required>
    </div>
    
    <div class="form-group">
        <label for="classe">Classe:</label>
        <select name="idclasse" id="classe" class="form-control">
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

    <div class="form-group">
        <label for="datePayement_in">Date de payement:</label>
        <input type="date" class="form-control" name="datePayement" id="datePayement_in" required>
    </div>


    <button type="submit" class="btn btn-primary">Ajouter</button>


</form>