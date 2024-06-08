<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>
<%@page pageEncoding="UTF-8" %>

<div class="py-2 d-flex justify-content-between">
    <h2>Calendrier Scolaire</h2>

    <div>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
            Ajouter une Annee Scolaire
        </button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ajouterEvenement">
            Planifier un Evenement
        </button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ajouterExamen">
            Planifier un Examen
        </button>
    </div>
</div>

<div class="mt-5">
    <h3>En Cours:</h3>
    <div class="list-group">
        <%
            if(encours.isEmpty())
            {
        %>
            <div class="text-center"><h5>Aucun evenement en cours</h5></div>
        <%
            }
        %>

        <%
            for (CalendrierScolaire evenement : encours) {
                LocalDateTime debut = evenement.getDateDebut();
                LocalDateTime fin = evenement.getDateFin();
        %>
        <div class="list-group-item my-2  <%=evenement.getTypeEvenement() == 0 ? "bg2" : "bg1"%>">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1 font-weight-bold"><%=evenement.getLibelle()%>
                </h5>
                <small><%=evenement.getStatus().getNom()%>
                </small>
            </div>
            <%--        <small><%=evenement.getStatus().getNom()%></small>--%>
            <p class="mb-1"><u>Date debut:</u> <%=debut.getDayOfMonth()%> <%=debut.getMonth()%> <%=debut.getYear()%>
                a  <%=debut.getHour()%>:<%=debut.getMinute()%>
            </p>
            <p class="mb-1"><u>Date Fin:</u> <%=fin.getDayOfMonth()%> <%=fin.getMonth()%> <%=fin.getYear()%>
                a  <%=fin.getHour()%>:<%=fin.getMinute()%>
            </p>
        </div>
        <%
            }
        %>

    </div>
</div>


<div class="mt-5">
    <h3 class="py-2">A Venir:</h3>
    <div class="list-group">
        <%
            for (CalendrierScolaire evenement : avenir) {
                LocalDateTime debut = evenement.getDateDebut();
                LocalDateTime fin = evenement.getDateFin();
        %>
        <div class="list-group-item my-2  <%=evenement.getTypeEvenement() == 0 ? "bg2" : "bg1"%>">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1 font-weight-bold"><%=evenement.getLibelle()%>
                </h5>
                <small><%=evenement.getStatus().getNom()%>
                </small>
            </div>
            <%--        <small><%=evenement.getStatus().getNom()%></small>--%>
            <p class="mb-1"><u>Date debut:</u> <%=debut.getDayOfMonth()%> <%=debut.getMonth()%> <%=debut.getYear()%>
                a  <%=debut.getHour()%>:<%=debut.getMinute()%>
            </p>
            <p class="mb-1"><u>Date Fin:</u> <%=fin.getDayOfMonth()%> <%=fin.getMonth()%> <%=fin.getYear()%>
                a  <%=fin.getHour()%>:<%=fin.getMinute()%>
            </p>
        </div>
        <%
            }
        %>

    </div>
</div>


<div class="mt-5">
    <h3>Terminé:</h3>
    <div class="list-group">
        <%
            for (CalendrierScolaire evenement : fini) {
                LocalDateTime debut = evenement.getDateDebut();
                LocalDateTime fin = evenement.getDateFin();
        %>
        <div class="list-group-item my-2  <%=evenement.getTypeEvenement() == 0 ? "bg2" : "bg1"%>">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1 font-weight-bold"><%=evenement.getLibelle()%>
                </h5>
                <small><%=evenement.getStatus().getNom()%>
                </small>
            </div>
            <%--        <small><%=evenement.getStatus().getNom()%></small>--%>
            <p class="mb-1"><u>Date debut:</u> <%=debut.getDayOfMonth()%> <%=debut.getMonth()%> <%=debut.getYear()%>
                a  <%=debut.getHour()%>:<%=debut.getMinute()%>
            </p>
            <p class="mb-1"><u>Date Fin:</u> <%=fin.getDayOfMonth()%> <%=fin.getMonth()%> <%=fin.getYear()%>
                a  <%=fin.getHour()%>:<%=fin.getMinute()%>
            </p>
        </div>
        <%
            }
        %>

    </div>
</div>