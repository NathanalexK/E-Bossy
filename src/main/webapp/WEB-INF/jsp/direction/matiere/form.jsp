<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Niveau" %>
<%@ page import="com.project.ebossy.model.Matiere" %>
<%
    List<Niveau> niveauList = ((List<Niveau>) request.getAttribute("niveauList"));
    List<Matiere> matiereList = ((List<Matiere>) request.getAttribute("matiereList"));
%>

<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
    Ajouter une Matiere
</button>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ajouter un Niveau</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="save" method="post">
                <div class=" p-4">
                    <div class="form-group">
                        <label for="nom_in">Nom de la matiere:</label>
                        <input type="text" name="nom" class="form-control" id="nom_in" required>
                    </div>

                    <table class="table table-borderless">
                        <thead>
                        <tr>
                            <th>Niveau</th>
                            <th>Ajouter?</th>
                            <th>Coefficient</th>
                            <th>Volume Horaire</th>
                        </tr>
                        </thead>

                        <tbody>
                        <%
                            for (Niveau niveau : niveauList) {
                        %>
                        <tr>
                            <td>
                                <input type="hidden" name="idNiveau[]" class="_input" value="<%=niveau.getId()%>">
                                <%=niveau.getNomNiveau()%>
                            </td>
                            <td><input type="checkbox" class="form-check _check"></td>
                            <td><input type="number" class="form-control _input" name="coefficient[]" disabled></td>
                            <td><input type="number" class="form-control _input" name="horaire[]" disabled></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>

                    </table>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </div>
            </form>

        </div>
    </div>
</div>

<%--<div class="text-center py-2">--%>
<%--    <h2>Ajouter une Matiere</h2>--%>
<%--</div>--%>

<%--<form action="save" method="post">--%>
<%--    <div class="card p-4">--%>
<%--        <div class="form-group">--%>
<%--            <label for="nom_in">Nom de la matiere:</label>--%>
<%--            <input type="text" name="nom" class="form-control" id="nom_in" required>--%>
<%--        </div>--%>

<%--        <table class="table table-borderless">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>Niveau</th>--%>
<%--                <th>Ajouter</th>--%>
<%--                <th>Coefficient</th>--%>
<%--                <th>Volume Horaire</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>

<%--            <tbody>--%>
<%--            <%--%>
<%--                for (Niveau niveau : niveauList) {--%>
<%--            %>--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <input type="hidden" name="idNiveau[]" class="_input" value="<%=niveau.getId()%>">--%>
<%--                    <%=niveau.getNomNiveau()%>--%>
<%--                </td>--%>
<%--                <td><input type="checkbox" class="form-check _check"></td>--%>
<%--                <td><input type="number" class="form-control _input" name="coefficient[]" disabled></td>--%>
<%--                <td><input type="number" class="form-control _input" name="horaire[]" disabled></td>--%>
<%--            </tr>--%>
<%--            <%--%>
<%--                }--%>
<%--            %>--%>
<%--            </tbody>--%>

<%--        </table>--%>

<%--        <button type="submit" class="btn btn-primary ">Ajouter</button>--%>
<%--    </div>--%>
<%--</form>--%>

<%@include file="list.jsp"%>


<script>
    document.querySelectorAll("._check")
        .forEach(checkbox => {
            const row = checkbox.closest('tr');

            row.querySelectorAll("._input").forEach(input => {
                input.disabled = !checkbox.checked
                input.required = checkbox.checked

            });
            // input.readOnly = !checkbox.checked;
            // console.log("a")
            checkbox.addEventListener('change', () => {
                // input.readOnly = !checkbox.checked;
                row.querySelectorAll("._input").forEach(input => {
                    input.disabled = !checkbox.checked
                    input.required = checkbox.checked
                });

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