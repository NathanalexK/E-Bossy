<%@ page import="com.project.ebossy.model.Salle" %>
<%@ page import="java.util.List" %>
<%
%>

<div class="text-center py-2">
    <h2>Liste des Salles</h2>
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
            for (Salle salle : salleList)
                 {
        %>
        <tr>
            <td><%=salle.getNumero()%></td>
            <td><%=salle.getCapacite()%></td>
            <td>
                <form action="delete" method="post" >
                    <input type="hidden" value="<%=salle.getId()%>" name="idSalle">
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



</script>
