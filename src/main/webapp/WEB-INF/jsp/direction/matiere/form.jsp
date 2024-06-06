<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Niveau" %><%
    List<Niveau> niveauList = ((List<Niveau>) request.getAttribute("niveauList"));
%>

<div class="text-center py-2">
    <h2>Ajouter une Matiere</h2>
</div>

<form action="save" method="post">
<div class="card p-4">
    <div class="form-group">
        <label for="nom_in">Nom de la matiere:</label>
        <input type="text" name="nom" class="form-control" id="nom_in" required>
    </div>

    <table class="table table-borderless">
        <thead><tr>
            <th>Niveau</th>
            <th>Ajouter?</th>
            <th>Coefficient</th>
            <th>Volume Horaire</th>
        </tr></thead>

        <tbody>
        <%
            for(Niveau niveau : niveauList)
            {
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

    <button type="submit" class="btn btn-primary ">Ajouter</button>
</div>
</form>


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
</script>