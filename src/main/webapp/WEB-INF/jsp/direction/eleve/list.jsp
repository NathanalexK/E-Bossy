<%@ page import="com.project.ebossy.model.Classe" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.EleveAnneeScolaire" %>
<%@ page import="org.springframework.data.domain.Page" %>
<%@page pageEncoding="UTF-8" %>
<%
    Classe classe = ((Classe) request.getAttribute("classe"));
    List<Classe> classeList = ((List<Classe>) request.getAttribute("classeList"));
    Page<EleveAnneeScolaire> eleveList = ((Page<EleveAnneeScolaire>) request.getAttribute("eleveList"));
    List<EleveAnneeScolaire> pasDeClasseListe = ((List<EleveAnneeScolaire>) request.getAttribute("pasDeClasseList"));
    Integer currentPage = (Integer) request.getAttribute("currentPage");
    Integer totalPages = (Integer) request.getAttribute("totalPages");
    int num = 0;
%>

<div class="fixed-bottom text-center border-top py-2 shadow">
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
        Affecter des élèves (<%=pasDeClasseListe.size()%>)
    </button>
</div>


<div class="d-flex w-100 justify-content-between py-2">
    <h2>Eleves par Classe</h2>

    <div class="d-flex ">
        <div class="align-items-center px-2 pt-1 row">
            <form action="">
                <select name="" id="classe_in" class="form-control">
                    <%
                        for (Classe c : classeList) {
                    %>
                    <option value="<%=c.getId()%>"
                            <%=c.getId().equals(classe.getId()) ? "selected" : ""%>
                    ><%=c.getNomClasse()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </form>
        </div>
    </div>


</div>

<div class="table-responsive card container-fluid">

    <div class="fixed-bottom text-center border-top py-2 _action-div">
        <button type="button" class="btn bg-light" onclick="location.reload(true)">Annuler</button>
        <button type="submit" class="btn btn-primary">Sauveguarder</button>
    </div>

    <input type="hidden" name="idClasse" value="<%=classe.getId()%>">

    <table class="table table-striped table-borderless text-center">
        <thead>
        <tr>
            <th>#</th>
            <th>Nom et prenom</th>
            <th>Date de naissance</th>
            <th>Sexe</th>
            <th></th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <%
            for (EleveAnneeScolaire eleve : eleveList) {

        %>
        <tr>
            <td><%=eleve.getId()%>
            </td>
            <td class="text-left"><%=eleve.getIdEleve().getNom()%> <%=eleve.getIdEleve().getPrenom()%>
            </td>
            <td><%=eleve.getIdEleve().getDateNaissance()%>
            </td>
            <td><%=eleve.getIdEleve().getIdSexe().getTypeSexe().charAt(0)%>
            </td>
            <td>
                <a href="/eleve/information?id=<%=eleve.getIdEleve().getId()%>">Voir informations</a>
            </td>
            <td>
                <a href="/note/bulletin?eleve=<%=eleve.getId()%>">Voir Bulletin</a>
            </td>
            <td>
                <a href="/payeEcolage/form?eleve=<%=eleve.getId()%>">Payer ecolage</a>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>

    </table>

    <div class="row d-flex justify-content-center">
        <div aria-label="Page navigation example " class="bg-transparent">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <%
                    for(int i = 0; i < totalPages; i++){
                %>
                <li class="page-item"><a class="page-link <%=currentPage == i ? "bg-primary text-white" : ""%>" href="?page=<%=i%>"><%=i+1%></a></li>
                <%
                    }
                %>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </div>
    </div>




</div>

<%@include file="affectation.jsp"%>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        const div = document.querySelector("._action-div");
        div.style.display = "none";



        const select = document.getElementById("classe_in")
        console.log(select)
        select.addEventListener("change", () => {
            window.location = "/eleve/list?classe=" + select.value;
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