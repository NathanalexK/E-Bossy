<%@ page import="com.project.ebossy.model.Niveau" %>
<%@ page import="java.util.List" %><%
    List<Niveau> niveauList = ((List<Niveau>) request.getAttribute("niveauList"));
%>

<div class="text-center py-2">
    <h1>Ajouter un niveau</h1>
</div>


<form action="save" method="post">
    <div class="form-group">
        <label for="numero_in">Numero:</label>
        <input type="number" name="numero" id="numero_in" class="form-control">
    </div>

    <div class="form-group">
        <label for="nomNiveau_in">Nom du Niveau:</label>
        <input type="text" name="nom" id="nomNiveau_in" class="form-control">
    </div>

    <button type="submit" class="btn btn-primary">Ajouter</button>
</form>

<br>
<br>

<div class="text-center py-2">
    <h1>Liste des niveaux</h1>
</div>

<div class="table-responsive card">
<table class="table table-striped table-borderless">
    <thead><tr>
        <th>Numero</th>
        <th>Nom</th>
        <th>Action</th>
    </tr></thead>

    <tbody>
        <%
            for (Niveau niveau : niveauList)
            {
        %>
        <tr>
            <td><%=niveau.getNumero()%></td>
            <td><%=niveau.getNomNiveau()%></td>
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
    function confirmDelete() {
        return confirm("Etes-vous sur de supprimer cette niveau?");
    }

    function deleteNiveau(idNiveau) {
        if(confirm("Voulez-vous vraiment supprimer cette niveau?")){
            fetch(`/niveau/del/` + idNiveau, {
                method: 'DELETE'
            })
                .then(response => {
                    if(response.ok) window.location.reload();
                    else console.error(response)
                }).catch(error => {
                    console.error(error)
            })
        }
    }
</script>
