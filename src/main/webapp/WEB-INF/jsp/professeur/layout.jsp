<%@page pageEncoding="UTF-8" %>
<%
  String urlPage = "/WEB-INF/jsp/" + request.getAttribute("page").toString() + ".jsp";
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>E-Bossy</title>
  <link rel="icon" href="../assets/icon/logo.png">
  <link rel="stylesheet" href="../assets/css/bootstrap.css">
  <link rel="stylesheet" href="../assets/css/bootstrap-reboot.css">
  <link rel="stylesheet" href="../assets/css/bootstrap-grid.css">
  <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
<main>
  <div class="py-2 border-bottom">
    <div class="d-flex flex-wrap justify-content-center">
      <div class="col-md-4 align-content-center">
        <b class="">Espace Professeur</b>
      </div>
      <div class="col-md-4 text-center">
        <!--                        <img src="../assets/icon/logo.png" alt="" width="34px" class="mx-2">-->
        <b class="_blue" style="font-size: 24px">E-Bossy</b>
      </div>
      <div class="col-md-4 text-right align-content-center">
        <a href="">Se Deconnecter</a>
      </div>
    </div>
  </div>

  <nav class="py-0 bg-body-tertiary border-bottom text-center sticky-top">
    <div class="py-0">
      <!--                  <a class="navbar-brand" href="#">Hidden brand</a>-->
      <ul class="mb-0">

        <li class="_nav-item btn">
          <a href="" class="_nav-link">
            Mon Compte
          </a>
        </li>

        <li class="dropdown btn _nav-item">
                  <span class="_nav-link dropdown-toggle">
                        Eleves
                  </span>

          <div class="dropdown-menu"
          >
            <div class="dropdown-title">Eleves</div>
            <a href="" class="dropdown-item">Comportements</a>
            <a href="" class="dropdown-item">Absences</a>

          </div>
        </li>


        <li class="dropdown btn _nav-item">
                  <span class="_nav-link dropdown-toggle">
                        Notes
                  </span>

          <div class="dropdown-menu"
          >
            <div class="dropdown-title">Notes</div>
            <a href="/note/form" class="dropdown-item">Ajouter des notes</a>
            <a href="" class="dropdown-item">Consulter les notes</a>
          </div>
        </li>


        <li class="dropdown btn _nav-item">
                              <span class="_nav-link dropdown-toggle">
                                    Communication
                              </span>

          <div class="dropdown-menu"
          >
            <div class="dropdown-title">Communication</div>
            <a href="" class="dropdown-item">Communiqué</a>
            <a href="" class="dropdown-item">Convoquer</a>
            <a href="" class="dropdown-item">Rencontre</a>
            <a href="" class="dropdown-item">Alerte</a>

          </div>
        </li>

        <li class="_nav-item btn">
          <a href="" class="_nav-link">
            Ressources pedagogiques
          </a>
        </li>

        <li class="dropdown btn _nav-item">
                              <span class="_nav-link dropdown-toggle">
                                    Organisations
                              </span>

          <div class="dropdown-menu"
          >
            <div class="dropdown-title">Organisations</div>
            <a href="" class="dropdown-item">Calendrier Scolaire</a>
            <a href="" class="dropdown-item">Emploi du temps</a>

          </div>
        </li>



        <li class="dropdown btn _nav-item">
                              <span class="_nav-link dropdown-toggle">
                                    Autres
                              </span>

          <div class="dropdown-menu"
          >
            <a href="" class="dropdown-item">item 1</a>
            <a href="" class="dropdown-item">item 2</a>
            <a href="" class="dropdown-item">item 3</a>
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
<script src="../assets/js/bootstrap.js"></script>
<script src="../assets/js/bootstrap.bundle.js"></script>

</html>