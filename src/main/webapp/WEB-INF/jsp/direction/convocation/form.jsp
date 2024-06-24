<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Classe" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    List<Classe> classeList = (List<Classe>) request.getAttribute("classeList");
%>

<div class="py-2 text-center">
    <h2> </h2>
</div>

<h2>Formulaire de Convocation d'eleve</h2>

<form action="save" method="post">
    <div class="form-group">
        <label for="classe">Classe:</label>
        <select name="idclasse" id="classe" class="form-control">
            <option value="">Selectionner une classe</option>
            <% for(Classe classe : classeList) { %>
            <option value="<%= classe.getId() %>"><%= classe.getNomClasse() %></option>
            <% } %>
        </select>
    </div>
    <div class="form-group">
        <label for="dateConvocation_in">Date de Convocation:</label>
        <input type="date" class="form-control" name="dateConvocation" id="dateConvocation_in" required>
    </div>
    <div class="form-group">
        <label for="motif_in">Motif :</label>
        <textarea id="motif_in" class="form-control" name="motif" rows="4" cols="50" required></textarea>
    </div>

    <h3>Liste des eleves</h3>

    <table id="eleveTable">
        <thead>
        <tr>
            <th>Nom</th>
            <th>Prenom</th>
        </tr>
        </thead>
        <tbody>
        <!-- Les élèves seront chargés ici par AJAX -->
        </tbody>
    </table>

    <input type="hidden" id="selectedEleves" name="selectedEleves">

    <br>
    <input type="submit" value="Generer la Convocation">
</form>


<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
    var selectedEleves = [];

    $(document).ready(function() {
        var today = new Date().toISOString().split('T')[0];
        $('#dateConvocation_in').val(today);
        $("#classe").change(function() {
            var classeId = $(this).val();
            if (classeId) {
                $.ajax({
                    url: "/convocation/eleves",
                    type: "GET",
                    data: { classeId: classeId },
                    success: function(data) {
                        var tbody = $("#eleveTable tbody");
                        tbody.empty();
                        $.each(data, function(i, eleve) {
                            tbody.append(
                                "<tr data-eleve-id='" + eleve.id + "' onclick='selectEleve(this)'>" +
                                "<td>" + eleve.nom + "</td>" +
                                "<td>" + eleve.prenom + "</td>" +
                                "</tr>"
                            );
                        });
                    },
                    error: function(xhr, status, error) {
                        console.error("Erreur lors du chargement des élèves :", error);
                    }
                });
            }
        });

        $("form").submit(function() {
            $("#selectedEleves").val(selectedEleves.join(","));
        });
    });

    function selectEleve(row) {
        var eleveId = $(row).data("eleve-id");
        if ($(row).hasClass("selected")) {
            $(row).removeClass("selected");
            selectedEleves = selectedEleves.filter(id => id !== eleveId);
        } else {
            $(row).addClass("selected");
            selectedEleves.push(eleveId);
        }
        $("#selectedEleves").val(selectedEleves.join(","));
    }
</script>


