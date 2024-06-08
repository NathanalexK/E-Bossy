<%@ page import="com.project.ebossy.model.Classe" %>
<%@ page import="com.project.ebossy.model.MatiereNiveau" %>
<div class="text-center py-2">
    <h2>Liste des Matieres</h2>
</div>

<div class="table-responsive card">
    <table class="table table-striped table-borderless">
        <thead><tr>
            <th>Nom de la matiere</th>
            <th>Classe</th>
<%--            <th>Capacité</th>--%>
<%--            <th>Responsable</th>--%>
            <th>Actions</th>
        </tr></thead>

        <tbody>
        <%
            for (Matiere matiere : matiereList)
            {
        %>
        <tr>
            <td><%=matiere.getNomMatiere()%></td>
            <td>
                <ul>
                <%
                    for(MatiereNiveau mn : matiere.getMatiereNiveaux())
                    {
                %>
                    <li><b><%=mn.getIdNiveau().getNomNiveau()%>: </b> Coef: <%=mn.getCoefficient()%>; <%=mn.getVolumeHoraire()%>h/semaine</li>

                <%
                    }
                %>
                </ul>
            </td>
<%--            <td><%=classe.getIdSalle().getCapacite()%></td>--%>
<%--            <td><%=classe.getIdTitulaire() != null ? classe.getIdTitulaire().getNom() : "Pas de Responsable"%></td>--%>
            <td>
                <form action="delete" method="post" >
                    <input type="hidden" value="<%=matiere.getId()%>" name="idClasse">
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
