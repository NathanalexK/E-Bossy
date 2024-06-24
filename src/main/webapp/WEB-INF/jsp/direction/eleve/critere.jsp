<%@ page import="com.project.ebossy.model.Classe" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.EleveAnneeScolaire" %>
<%@ page import="com.project.ebossy.model.Sexe" %>
<%@ page import="java.time.LocalDate" %>
<%@page pageEncoding="UTF-8" %>
<%
    List<EleveAnneeScolaire> eleveList = ((List<EleveAnneeScolaire>) request.getAttribute("eleveList"));
    int totalPages = ((int) request.getAttribute("totalPages"));
    int currentPage = ((int) request.getAttribute("currentPage"));

    String nom = request.getAttribute("nom") != null ? (String) request.getAttribute("nom") : "";
    String prenom = request.getAttribute("prenom") != null ? (String) request.getAttribute("prenom") : "";
    String sexe = request.getAttribute("idSexe") != null ? ((Sexe) request.getAttribute("idSexe")).getId().toString() : "";
    String debut = request.getAttribute("dateDebut") != null ? ((LocalDate) request.getAttribute("dateDebut")).toString() : "";
    String fin = request.getAttribute("dateFin") != null ? ((LocalDate) request.getAttribute("dateFin")).toString() : "";


    int num = 0;
%>




<div class="d-flex w-100 justify-content-between py-2">
    <h2>Liste des Eleves</h2>

    <div class="d-flex ">

    </div>
</div>

<div class="py-5">
    <form action="">
        <div class="form-row">
            <div class="form-group col-md-5">
                <label for="nom_in">Nom</label>
                <input type="text" class="form-control filter-input" id="nom_in"  name="nom" value="<%=nom%>">
            </div>
            <div class="form-group col-md-5">
                <label for="input-prenom">Prenom</label>
                <input type="text" class="form-control filter-input" id="input-prenom" placeholder="" name="prenom" value="<%=prenom%>">
            </div>
            <div class="form-group col-md-2">
                <label for="input-sexe">sexe</label>
                <select class="form-control" id="input-sexe" name="idSexe">
                    <option value="" <%=sexe.isBlank() ? "selected" : "" %> >Toutes</option>
                    <option value="1" <%=sexe.equals("1") ? "selected" : ""%>>Garcon</option>
                    <option value="2" <%=sexe.equals("2") ? "selected" : ""%>>Fille</option>
                </select>
            </div>
        </div>

        <div class="form-row">
            <div class="col-2">
                Date de Naissance:

            </div>
            <div class="form-group col-5">
                <input type="date" id="input-debut" class="form-control filter-input" name="dateDebut" value="<%=debut%>">
            </div>

            <div class="form-group col-5">
                <input type="date" id="input-fin" class="form-control filter-input" name="dateFin" value="<%=fin%>">
            </div>
        </div>

        <div class="d-flex justify-content-center p-1">
            <button  class="btn btn-light border mx-1" id="reset-btn">Reinitialiser</button>
            <button type="submit" class="btn btn-primary mx-1">Filtrer</button>
        </div>

    </form>

</div>

<div class="table-responsive card container-fluid">

<%--    <input type="hidden" name="idClasse" value="<%=classe.getId()%>">--%>

    <table class="table table-striped table-borderless text-center">
        <thead>
        <tr>
<%--            <th>#</th>--%>
            <th>Nom et prenom</th>
            <th>Classe</th>
            <th>Date de naissance</th>
            <th>Sexe</th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <%
            for (EleveAnneeScolaire eleve : eleveList) {
                num++;
        %>
        <tr>
<%--            <td><%=num%>--%>
<%--            </td>--%>
            <td class="text-left"><%=eleve.getIdEleve().getNom()%> <%=eleve.getIdEleve().getPrenom()%>
            </td>
             <td>
                 <%=eleve.getIdClasse()  != null? eleve.getIdClasse().getNomClasse() : "-"%>
             </td>
            <td><%=eleve.getIdEleve().getDateNaissance()%>
            </td>
            <td><%=eleve.getIdEleve().getIdSexe().getTypeSexe().charAt(0)%>
            </td>
            <td>
                <a href="/eleve/information?id=<%=eleve.getIdEleve().getId()%>">Voir informations</a>
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
<%--                <li class="page-item"><a class="page-link" href="#">Previous</a></li>--%>
                <%
                    for(int i = 0; i < totalPages; i++){
                %>
                    <form action="/eleve/critere-list" method="get">
                        <input type="hidden" name="page" value="<%=i%>">
                        <input type="hidden" name="nom" value="<%=nom%>">
                        <input type="hidden" name="prenom" value="<%=prenom%>">
                        <input type="hidden" name="idSexe" value="<%=sexe%>">
                        <input type="hidden" name="dateDebut" value="<%=debut%>">
                        <input type="hidden" name="dateFin" value="<%=fin%>">
                        <button type="submit" class="btn border <%=currentPage == i ? "btn-primary" : ""%>"><%=i+1%></button>
                    </form>
                <%
                    }
                %>
<%--                <li class="page-item"><a class="page-link" href="#">Next</a></li>--%>
            </ul>
        </div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        document.getElementById("reset-btn").addEventListener("click", () => {
            console.log("hello")
            document.querySelectorAll(".filter-input").forEach(input => {
                input.value = "";
                // console.log(input.value)
            });
        })
    })

</script>

