<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.PeriodeEcolage" %>
<%@ page import="com.project.ebossy.model.EleveAnneeScolaire" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    List<PeriodeEcolage> periodeEcolageList = ((List<PeriodeEcolage>) request.getAttribute("periodeEcolages"));
    EleveAnneeScolaire idEleve = ((EleveAnneeScolaire) request.getAttribute("eleveId"));
%>


<div class="py-2 text-center">
    <h2>Effectuez une paiement de scolarite </h2>
</div>

<form action="save" method="post">
    <div class="form-group">
        <label for="periode">Periode d'ecolage:</label>
        <select name="idperiode" id="periode" class="form-control">
            <%
                for(PeriodeEcolage periodeEcolage : periodeEcolageList)
                {
            %>
                <option value="<%=periodeEcolage.getId()%>"><%=periodeEcolage.getNom()%></option>
            <%
                }
            %>

        </select>
    </div>

    <div class="form-group">
        <label for="datePayement_in">Date de payement:</label>
        <input type="date" class="form-control" name="datePayement" id="datePayement_in" required>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var today = new Date();
            var yyyy = today.getFullYear();
            var mm = String(today.getMonth() + 1).padStart(2, '0'); // Months are zero-based
            var dd = String(today.getDate()).padStart(2, '0');
            var todayFormatted = yyyy + '-' + mm + '-' + dd;
            document.getElementById('datePayement_in').value = todayFormatted;
        });
    </script>

    <div class="form-group">
        <input type="hidden" class="form-control" name="eleveId" id="eleveId_in" value="<%= idEleve.getId() %>" required>
    </div>




    <button type="submit" class="btn btn-primary">Ajouter</button>


</form>