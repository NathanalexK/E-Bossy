<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.project.ebossy.model.*" %>
<%@page pageEncoding="UTF-8" %>
<%
    List<MatiereProf> matiereProfList = ((List<MatiereProf>) request.getAttribute("matiereProfList"));
    List<PeriodeNote> periodeNoteList = ((List<PeriodeNote>) request.getAttribute("periodeNoteList"));
    List<EleveAnneeScolaire> easList = ((List<EleveAnneeScolaire>) request.getAttribute("easList"));
    Map<Eleve, Note> noteMap = ((Map<Eleve, Note>) request.getAttribute("noteMap"));

    MatiereProf selectedMatiereProf = ((MatiereProf) request.getAttribute("selectedMatiereProf"));
    PeriodeNote selectedPeriodeNote = ((PeriodeNote) request.getAttribute("selectedPeriodeNote"));

    double moyenne = ((Double) request.getAttribute("moyenne"));
    int num = 0;
%>
<%--a--%>

<div class="fixed-bottom text-center border-top py-2 shadow bg-white">
    <button type="submit" form="noteForm" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
        Valider
    </button>
</div>


<form action="save" method="post" id="noteForm">
    <div class="d-flex w-100 justify-content-between py-2">
        <h2>Ajouter notes</h2>

        <div class="d-flex">
            <div class="align-items-center px-2 pt-1">
                <%--            <form action="">--%>
                <select name="idPeriode" id="periode_in" class="form-control">
                    <%
                        for (PeriodeNote periode : periodeNoteList) {
                    %>
                    <option value="<%=periode.getId()%>"
                            <%=periode.getId().equals(selectedPeriodeNote.getId()) ? "selected" : ""%>
                    ><%=periode.getNomPeriode()%>
                    </option>
                    <%
                        }
                    %>
                </select>
                <%--            </form>--%>
            </div>
            <div class="align-items-center px-2 pt-1">
                <%--            <form action="">--%>
                <select name="idMatiereProf" id="classe_in" class="form-control">
                    <%
                        for (MatiereProf mp : matiereProfList) {
                            Classe c = mp.getIdClasse();
                    %>
                    <option value="<%=mp.getId()%>"
                            <%=mp.getId().equals(selectedMatiereProf.getId()) ? "selected" : ""%>
                    <%--                            <%=c.getId().equals(classe.getId()) ? "selected" : ""%>--%>
                    ><%=c.getNomClasse()%> - <%=mp.getIdMatiere().getNomMatiere()%>

                    </option>
                    <%
                        }
                    %>
                </select>
                <%--            </form>--%>
            </div>
        </div>


    </div>

    <div>
        <table class="table table-borderless">
            <tr>
                <td><b>Classe:</b></td>
                <td><%=selectedMatiereProf.getIdClasse().getNomClasse()%>
                </td>


            </tr>

            <tr>
                <td><b>Matiere:</b></td>
                <td><%=selectedMatiereProf.getIdMatiere().getNomMatiere()%>
                </td>
            </tr>

            <tr>
                <td><b>Periode:</b></td>
                <td><%=selectedPeriodeNote.getNomPeriode()%> <small>(de <%=selectedPeriodeNote.getDateDebut()%> à <%=selectedPeriodeNote.getDateFin()%>)</small></td>
            </tr>

            <tr>
                <td><b>Nombre d'éleves:</b></td>
                <td><%=noteMap.size()%></td>
            </tr>

            <tr>
                <td><b>Moyenne de classe</b></td>
                <td><%=String.format("%.2f", moyenne)%></td>
            </tr>
        </table>
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
                <th>#</th>
                <th>Nom et prenom</th>
                <th class="col-2">Note</th>
                <th>Appreciation</th>
            </tr>
            </thead>

            <tbody>
            <%
                for (EleveAnneeScolaire eleve : easList) {
                    Note note = noteMap.get(eleve.getIdEleve());
                    note = note != null ? note : new Note();
                    num++;
            %>
            <tr>
                <input type="hidden" name="idNote[]" value="<%=note.getId() != null ? note.getId() : ""%>">
                <input type="hidden" name="idEleve[]" value="<%=eleve.getIdEleve().getId()%>">
                <td><%=eleve.getId()%>
                </td>
                <td class="text-left"><%=eleve.getIdEleve().getNom()%> <%=eleve.getIdEleve().getPrenom()%>
                </td>
                </td>
                <td>
                    <input
                            class="form-control"
                            type="number"
                            step="0.25"
                            min="0"
                            max="20"
                            name="note[]"
                            value="<%=note.getNote()%>"
                    >
                </td>
                <td>
                <textArea
                        class="form-control"
                        name="appreciation[]"
                ><%=note.getAppreciation() != null ? note.getAppreciation() : ""%></textArea>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>

        </table>
</form>


<%--</div>--%>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        const div = document.querySelector("._action-div");
        div.style.display = "none";


        const classe = document.getElementById("classe_in");
        const periode = document.getElementById("periode_in");

        classe.addEventListener("change", () => {
            window.location = "/note/form?matiereProf=" + classe.value + "&periodeNote=" + periode.value;
        })

        periode.addEventListener("change", () => {
            window.location = "/note/form?matiereProf=" + classe.value + "&periodeNote=" + periode.value;
        })
    })

    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('.modal-body input').val(recipient)
    })


</script>