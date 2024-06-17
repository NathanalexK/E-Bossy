<%@ page import="com.project.ebossy.model.Eleve" %>
<%@page pageEncoding="UTF-8" %>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModal"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Selectionner des élèves</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form action="affecter" method="post">
                    <input type="hidden" name="idClasse" value="<%=classe.getId()%>">
                    <div class="table-responsive card container-fluid">

                        <table class="table table-striped table-borderless">
                            <thead>
                            <tr>
                                <th> </th>
                                <th >Nom</th>
                                <th >Prenom</th>
                                <th >Date de naissance</th>
                                <th>Sexe</th>


                            </tr>
                            </thead>

                            <tbody>
                            <%
                                for (EleveAnneeScolaire pasDeClasse : pasDeClasseListe) {
                                    Eleve eleve = pasDeClasse.getIdEleve();
                            %>
                            <tr>

<%--                                <input type="hidden" name="idEleve[]" class="_input" value="<%=eleve.getId()%>" disabled>--%>
                                <td><input type="checkbox" class="checkbox" name="idEleve[]" value="<%=pasDeClasse.getId()%>"></td>
                                <td>
                                    <%=eleve.getNom()%>

                                </td>
                                <td >
                                    <%=eleve.getPrenom()%>
                                </td>
                                <td class="">
                                    <%=eleve.getDateNaissance()%>
                                </td>
                                <td><%=eleve.getIdSexe().getTypeSexe().charAt(0)%></td>
                            </tr>


                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                    <%--</form>--%>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        <button type="submit" id="submitButton" class="btn btn-primary">Affecter</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        const checkboxes = document.querySelectorAll('input[type="checkbox"]');
        const submitButton = document.getElementById('submitButton');

        function checkCheckboxes() {
            let checked = false;
            checkboxes.forEach(function(checkbox) {
                if (checkbox.checked) {
                    checked = true;
                }
            });
            submitButton.disabled = !checked;
        }

        checkboxes.forEach(function(checkbox) {
            checkbox.addEventListener('change', checkCheckboxes);
        });

        // Initial check in case any checkbox is already checked on load
        checkCheckboxes();
    })
</script>