<%@ page import="com.project.ebossy.model.AnneeScolaire" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String urlPage = "/WEB-INF/jsp/" + request.getAttribute("page").toString() + ".jsp";
    AnneeScolaire anneeScolaire = ((AnneeScolaire) request.getSession().getAttribute("anneeScolaire"));
    List<AnneeScolaire> anneeScolaires = ((List<AnneeScolaire>) request.getAttribute("anneeScolaireList")) != null? ((List<AnneeScolaire>) request.getAttribute("anneeScolaireList")) : new ArrayList<>();
    System.out.println(anneeScolaire.getNom());
//    System.out.println(anneeScolaire.getNom());
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-Bossy</title>
    <link rel="icon" href="/assets/icon/logo.png">
    <link rel="stylesheet" href="../assets/css/bootstrap.css">
    <link rel="stylesheet" href="../assets/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="../assets/css/bootstrap-grid.css">
    <link rel="stylesheet" href="../assets/css/style.css">
    <script src="/assets/js/angular.min.js"></script>
    <script>
        const app = angular.module("app", [])
    </script>
</head>
<body>
<main>
    <div class="py-2 border-bottom">
        <div class="d-flex flex-wrap justify-content-around">
            <div class="align-content-center align-items-end">
                <b class="">Espace Direction</b>
            </div>
            <div class="">
                <!--                        <img src="../assets/icon/logo.png" alt="" width="34px" class="mx-2">-->
                <b class="_blue" style="font-size: 24px">E-Bossy</b>
            </div>
            <div class="text-right align-content-center">
                <a href="/logout">Se Deconnecter</a>
            </div>
        </div>
    </div>

    <nav class="py-0 bg-body-tertiary border-bottom text-center sticky-top shadow-sm">
        <div class="py-0">
            <!--                  <a class="navbar-brand" href="#">Hidden brand</a>-->
            <ul class="mb-0">

                <li class="_nav-item btn">
                    <a href="" class="_nav-link">
                        Dashboard
                    </a>
                </li>

                <li class="dropdown btn _nav-item">
                              <span class="_nav-link dropdown-toggle">
                                    <%=anneeScolaire.getNom()%>
                              </span>

                    <div class="dropdown-menu"
                    >
                        <div class="dropdown-title">Annee Scolaire</div>
                        <%
                            for(AnneeScolaire as : anneeScolaires){
                        %>
                                <a href="/anneeScolaire/set?id=<%=as.getId()%>" class="dropdown-item"><%=as.getNom()%></a>
                        <%
                            }
                        %>
                    </div>
                </li>

                <li class="dropdown btn _nav-item">
                              <span class="_nav-link dropdown-toggle">
                                    Eleves
                              </span>

                    <div class="dropdown-menu"
                    >
                        <div class="dropdown-title">Eleves</div>
                        <a href="/eleve/critere-list" class="dropdown-item">Liste des élèves</a>
                        <a href="/eleve/list" class="dropdown-item">Eleves par classe</a>
                        <a href="/eleve/form" class="dropdown-item">Inscrire un élève</a>
                        <a href="" class="dropdown-item">Bulletin de note</a>
                        <a href="" class="dropdown-item">Absences</a>
                        <a href="" class="dropdown-item">Comportements</a>
                    </div>
                </li>

                <li class="dropdown btn _nav-item">
                              <span class="_nav-link dropdown-toggle">
                                    Pesonnels
                              </span>

                    <div class="dropdown-menu"
                    >
                        <div class="dropdown-title">Personnels</div>

                        <a href="" class="dropdown-item">Secretaires</a>
                        <a href="/prof/form" class="dropdown-item">Professeurs</a>
                    </div>
                </li>

                <li class="dropdown btn _nav-item">
                              <span class="_nav-link dropdown-toggle">
                                    Organisations
                              </span>

                    <div class="dropdown-menu"
                    >
                        <%--                        <a href="" class="dropdown-item">Niveau Scolaire</a>--%>
                        <div class="dropdown-title">Organisations</div>

                        <a href="/classe/form" class="dropdown-item">Classe</a>
                        <a href="/salle/form" class="dropdown-item">Salle</a>
                        <a href="/niveau/form" class="dropdown-item">Niveau</a>
                        <a href="/matiere/form" class="dropdown-item">Matière</a>
                        <a href="/matiereProf/form" class="dropdown-item">Matière - Profs</a>
                        <%--                        <a href="/periodeExamen/form" class="dropdown-item">Periode d'Examen</a>--%>
                        <a href="/calendrier/form" class="dropdown-item">Calendrier Scolaire</a>

                        <a href="" class="dropdown-item">Emploi du temps</a>

                    </div>
                </li>


                <li class="dropdown btn _nav-item">
                              <span class="_nav-link dropdown-toggle">
                                    Communication
                              </span>

                    <div class="dropdown-menu">
                        <div class="dropdown-title">Communication</div>
                        <a href="" class="dropdown-item">Communiqué</a>
                        <a href="/convocation/form" class="dropdown-item">Convoquer</a>
                        <a href="" class="dropdown-item">Rencontre</a>
                        <a href="" class="dropdown-item">Confirmation Absence</a>
                        <a href="" class="dropdown-item">Alerte</a>
                    </div>
                </li>

                <li class="_nav-item btn">
                    <a href="" class="_nav-link">
                        Etablissement
                    </a>
                </li>

                <li class="dropdown btn _nav-item">
                              <span class="_nav-link dropdown-toggle">
                                    Ecolages
                              </span>

                    <div class="dropdown-menu"
                    >
                        <a href="/payeEcolage/form" class="dropdown-item">Prix</a>
                        <a href="" class="dropdown-item">Ecolage non payé </a>

                    </div>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container py-5">
        <jsp:include page="<%=urlPage%>"></jsp:include>
    </div>


</main>

</body>
<script src="/assets/js/jquery-3.7.1.min.js"></script>
<script src="../assets/js/bootstrap.js"></script>

<script src="../assets/js/bootstrap.bundle.js"></script>

<script>
    function confirmSubmission(event, nom) {
        if (!confirm('Êtes-vous sûr de vouloir supprimer  ' + nom + " ?")) {
            event.preventDefault();
        }
    }

    function rollback() {
        window.location.reload();
    }
</script>

<!-- Include jQuery -->
<%--<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>--%>
<!-- Include Bootstrap JS -->
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>--%>


</html>