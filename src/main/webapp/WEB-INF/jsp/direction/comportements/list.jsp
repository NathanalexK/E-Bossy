<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Comportements" %>
<%@ page import="com.project.ebossy.model.Classe" %>
<%@ page import="com.project.ebossy.model.Eleve" %>
<%@ page import="com.project.ebossy.view.ComportementsEleve" %>
<%
    List<Classe> classeList = (List<Classe>) request.getAttribute("classeList");
    List<Comportements> comportementsList = (List<Comportements>) request.getAttribute("comportementsList");
    List<ComportementsEleve> eleveList = (List<ComportementsEleve>) request.getAttribute("eleveList");
    List<Eleve> eleveL = (List<Eleve>) request.getAttribute("eleveL");
    int num = 0; // Initialisation de la variable num
%>

<div class="d-flex w-100 justify-content-between py-2">
    <h2>Comportements Eleves par Classe</h2>
    <div class="d-flex">
        <div class="align-items-center px-2 pt-1 row">
            <form id="filterForm">
                <select name="comportement" id="comportement_in" class="form-control" onchange="filterTable()">
                    <option value="">Tous les comportements</option>
                    <% for (Comportements comp : comportementsList) { %>
                    <option value="<%=comp.getId()%>"><%=comp.getNom()%></option>
                    <% } %>
                </select>
                <select name="classe" id="classe_in" class="form-control" onchange="filterTable()">
                    <option value="">Toutes les classes</option>
                    <% for (Classe c : classeList) { %>
                    <option value="<%=c.getId()%>"><%=c.getNomClasse()%></option>
                    <% } %>
                </select>
            </form>
        </div>
    </div>
</div>

<table class="table table-striped table-borderless text-center" id="eleveTable">
    <thead>
    <tr>
        <th>#</th>
        <th>Nom et Prenom</th>
        <th>Classe</th>
        <th>Comportements</th>
        <th>Action</th>
    </tr>
    </thead>

    <tbody>
    <% if (eleveList != null) { %>
        <% for (ComportementsEleve eleve : eleveList) {
            num++;
            Eleve currentEleve = null;
            for (Eleve e : eleveL) {
                if (e.getId().equals(eleve.getIdEleve())) {
                    currentEleve = e;
                    break;
                }
            }
            Classe currentClasse = null;
            for (Classe c : classeList) {
                if (c.getId().equals(eleve.getIdClasse())){
                    currentClasse = c;
                    break;
                }
            }
            Comportements currentComp = null;
            for (Comportements comp : comportementsList){
                if(comp.getId().equals(eleve.getComp())){
                    currentComp = comp;
                    break;
                }
            }
        %>
        <tr data-classe-id="<%=currentClasse != null ? currentClasse.getId() : ""%>" data-comp-id="<%=currentComp != null ? currentComp.getId() : ""%>">
            <td><%=num%></td>
            <td class="text-left"><%= currentEleve != null ? currentEleve.getNom() + " " + currentEleve.getPrenom() : "N/A" %></td>
            <td><%= currentClasse != null ? currentClasse.getNomClasse() : "N/A" %></td>
            <td><%= currentComp != null ? currentComp.getNom() : "N/A" %></td>
            <td>
                <button type="button" class="btn btn-primary _update" data-toggle="modal" data-target="#updateModal" data-eleve-id="<%= eleve.getIdEleve() %>">
                    Modifier
                </button>
            </td>
        </tr>
        <% } %>
    <% } %>
    </tbody>
</table>

<!-- Update Modal -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateModalLabel">Modifier le comportement de l'élève</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Formulaire de mise à jour -->
                <form id="updateForm">
                    <input type="hidden" id="eleveId" name="eleveId" value="">
                    <!-- Ajouter les champs nécessaires pour la mise à jour -->
                    <div class="form-group">
                        <label for="newComportement">Nouveau Comportement</label>
                        <select id="newComportement" name="newComportement" class="form-control">
                            <% for (Comportements comp : comportementsList) { %>
                            <option value="<%=comp.getId()%>"><%=comp.getNom()%></option>
                            <% } %>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="button" class="btn btn-primary" onclick="applyUpdate()">Enregistrer</button>
            </div>
        </div>
    </div>
</div>

<script>
function filterTable() {
    var comportementSelect = document.getElementById('comportement_in');
    var classeSelect = document.getElementById('classe_in');
    var comportementFilter = comportementSelect.value;
    var classeFilter = classeSelect.value;

    var table = document.getElementById('eleveTable');
    var rows = table.getElementsByTagName('tr');
    var num = 0;

    // Loop through table rows
    for (var i = 1; i < rows.length; i++) {
        var row = rows[i];
        var idClasse = row.getAttribute('data-classe-id');
        var idComp = row.getAttribute('data-comp-id');

        // Check if row matches filters
        if ((comportementFilter === "" || idComp === comportementFilter) &&
            (classeFilter === "" || idClasse === classeFilter)) {
            row.style.display = '';
            num++;
            row.getElementsByTagName('td')[0].innerText = num; // Update row number
        } else {
            row.style.display = 'none';
        }
    }
}

document.querySelectorAll("._update").forEach(btn => {
    btn.addEventListener('click', evt => {
        var eleveId = btn.getAttribute('data-eleve-id');
        document.getElementById('eleveId').value = eleveId;
        // Vous pouvez ajouter d'autres actions ici si nécessaire
    });
});

function applyUpdate() {
    var updateForm = document.getElementById('updateForm');
    var formData = new FormData(updateForm);

    fetch('/update-eleve-comportement', {
        method: 'POST',
        body: formData
    }).then(response => {
        if (response.ok) {
            location.reload();
        } else {
            alert("Erreur lors de la mise à jour.");
        }
    }).catch(error => {
        console.error('Error:', error);
    });
}
</script>
