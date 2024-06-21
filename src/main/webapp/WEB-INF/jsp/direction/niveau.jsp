<%@ page import="com.project.ebossy.model.Niveau" %>
<%@ page import="java.util.List" %>
<%
    List<Niveau> niveauList = ((List<Niveau>) request.getAttribute("niveauList"));
%>

<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
    Ajouter un Niveau
</button>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ajouter un Niveau</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="save" method="post">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="numero_in">Numero:</label>
                        <input type="number" name="numero" id="numero_in" class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="nomNiveau_in">Nom du Niveau:</label>
                        <input type="text" name="nom" id="nomNiveau_in" class="form-control">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </div>
            </form>

        </div>
    </div>
</div>


<br>
<br>

<div class="text-center py-2">
    <h1>Liste des niveaux</h1>
</div>

<div class="table-responsive card">
    <table class="table table-striped table-borderless">
        <thead>
        <tr>
            <th>Numero</th>
            <th>Nom</th>
            <th>Action</th>
        </tr>
        </thead>

        <tbody>
        <%
            for (Niveau niveau : niveauList) {
        %>
        <tr>
            <td><%=niveau.getNumero()%>
            </td>
            <td><%=niveau.getNomNiveau()%>
            </td>
            <td>
                <%--                <a onclick="deleteNiveau(<%=niveau.getId()%>)"><button class="btn btn-danger">Supprimer</button></a>--%>
                <form action="delete" method="post" onsubmit="confirmDelete()">
                    <input type="hidden" value="<%=niveau.getId()%>" name="idNiveau">
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

<script>
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('.modal-body input').val(recipient)
    })

    function confirmDelete() {
        return confirm("Etes-vous sur de supprimer cette niveau?");
    }

    function deleteNiveau(idNiveau) {
        if (confirm("Voulez-vous vraiment supprimer cette niveau?")) {
            fetch(`/niveau/del/` + idNiveau, {
                method: 'DELETE'
            })
                .then(response => {
                    if (response.ok) window.location.reload();
                    else console.error(response)
                }).catch(error => {
                console.error(error)
            })
        }
    }


</script>




