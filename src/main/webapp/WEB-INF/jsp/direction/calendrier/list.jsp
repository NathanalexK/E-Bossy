<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="javax.swing.text.DateFormatter" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@page pageEncoding="UTF-8" %>
<%
    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM", Locale.FRANCE);
%>
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
    <h4>En Cours: <%=encours.size()%></h4>
    <div class="list-group">
        <%
            if (encours.isEmpty()) {
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

                <div>
                    <form action="/calendrier/delete" method="post" onsubmit="confirmSubmission(event, '<%=evenement.getLibelle()%>')">
                        <input type="hidden" name="typeEvent" value="<%=evenement.getTypeEvenement()%>">
                        <input type="hidden" name="idEvent" value="<%=evenement.getIdEvent()%>">
                        <button class="btn _btn-link" type="submit">Supprimer</button>
                    </form>
                </div>
            </div>

            <p class="mb-1"><u>Date debut:</u> <%=debut.getDayOfMonth()%> <%=debut.format(df)%> <%=debut.getYear()%>
                a  <%=debut.getHour()%>:<%=debut.getMinute()%>
            </p>
            <p class="mb-1"><u>Date Fin:</u> <%=fin.getDayOfMonth()%> <%=fin.format(df)%> <%=fin.getYear()%>
                a  <%=fin.getHour()%>:<%=fin.getMinute()%>
            </p>

        </div>
        <%
            }
        %>

    </div>
</div>


<div class="mt-5">
    <h4 class="py-2">A Venir: <%=avenir.size()%></h4>
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
                <div>
                    <form action="/calendrier/delete" method="post"  onsubmit="confirmSubmission(event, '<%=evenement.getLibelle()%>')">
                        <input type="hidden" name="typeEvent" value="<%=evenement.getTypeEvenement()%>">
                        <input type="hidden" name="idEvent" value="<%=evenement.getIdEvent()%>">
                        <button class="btn _btn-link" type="submit">Supprimer</button>
                    </form>
                </div>
            </div>
            <small>Dans <%=evenement.getDateDiff()%> jours</small>
            <%--        <small><%=evenement.getStatus().getNom()%></small>--%>
            <p class="mb-1"><u>Date debut:</u> <%=debut.getDayOfMonth()%> <%=debut.format(df)%> <%=debut.getYear()%>
                a  <%=debut.getHour()%>:<%=debut.getMinute()%>
            </p>
            <p class="mb-1"><u>Date Fin:</u> <%=fin.getDayOfMonth()%> <%=fin.format(df)%> <%=fin.getYear()%>
                a  <%=fin.getHour()%>:<%=fin.getMinute()%>
            </p>
        </div>
        <%
            }
        %>

    </div>
</div>


<div class="mt-5">
    <h4 class="h4">Terminé: <%=fini.size()%></h4>
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
                <div>
                    <form action="/calendrier/delete" method="post"  onsubmit="confirmSubmission(event, '<%=evenement.getLibelle()%>')">
                        <input type="hidden" name="typeEvent" value="<%=evenement.getTypeEvenement()%>">
                        <input type="hidden" name="idEvent" value="<%=evenement.getIdEvent()%>">
                        <button class="btn _btn-link" type="submit">Supprimer</button>
                    </form>
                </div>
            </div>
            <%--        <small><%=evenement.getStatus().getNom()%></small>--%>
            <p class="mb-1"><u>Date debut:</u> <%=debut.getDayOfMonth()%> <%=debut.format(df)%> <%=debut.getYear()%>
                a  <%=debut.getHour()%>:<%=debut.getMinute()%>
            </p>
            <p class="mb-1"><u>Date Fin:</u> <%=fin.getDayOfMonth()%> <%=fin.format(df)%> <%=fin.getYear()%>
                a  <%=fin.getHour()%>:<%=fin.getMinute()%>
            </p>
        </div>
        <%
            }
        %>

    </div>
</div>

<script>

</script>