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
        <%
            if(!readonly){
        %>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
            Ajouter une Annee Scolaire
        </button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ajouterEvenement">
            Planifier un Evenement
        </button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ajouterExamen">
            Planifier un Examen
        </button>
        <%
            }
        %>
    </div>
</div>

<div class="mt-5">
    <div class="d-flex justify-content-between">
    <h4>En Cours:
    </h4>

    <h4>
        <%=encours.size()%>
    </h4>
    </div>
    <hr>
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
                    <button class="btn _btn-link mr-2 voir-detail">Voir details</button>

                    <form action="/calendrier/delete" method="post"
                          onsubmit="confirmSubmission(event, '<%=evenement.getLibelle()%>')">
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
            <div class="_details mt-3">
                <%=evenement.getDescription() != null ? evenement.getDescription() : "Aucun detail"%>
            </div>

        </div>

        <%
            }
        %>

    </div>
</div>


<div class="mt-5">
    <div class="d-flex justify-content-between">
    <h4 class="py-2">A Venir:
    </h4>
        <h4><%=avenir.size()%></>
    </div>
    <hr>
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
                    <button class="btn _btn-link mr-2 voir-detail">Voir details</button>

                    <form action="/calendrier/delete" method="post"
                          onsubmit="confirmSubmission(event, '<%=evenement.getLibelle()%>')">
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
            <div class="_details mt-3">
                <%=evenement.getDescription() != null ? evenement.getDescription() : "Aucun detail"%>
            </div>
        </div>

        <%
            }
        %>

    </div>
</div>


<div class="mt-5">
    <div class="d-flex justify-content-between">
    <h4 class="h4">Terminé: </h4>

    <h4><%=fini.size()%></h4>
    </div>

    <hr>
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
                    <button class="btn _btn-link mr-2 voir-detail">Voir details</button>

                    <form action="/calendrier/delete" method="post"
                          onsubmit="confirmSubmission(event, '<%=evenement.getLibelle()%>')">
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

            <div class="_details mt-3">
                <%=evenement.getDescription() != null ? evenement.getDescription() : "Aucun detail"%>
            </div>
        </div>
        <%
            }
        %>

    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        document.querySelectorAll("._details").forEach(details => {
            details.style.display = "none";
        })

        document.querySelectorAll(".voir-detail").forEach(link => {
            link.addEventListener("click", (event) => {
                const div = link.closest(".list-group-item")

                const detail = div.querySelector("._details");
                if (detail.style.display.toLowerCase() === 'none') {
                    detail.style.display = "block";
                    link.innerHTML = "Masquer details"
                } else {
                    detail.style.display = "none";
                    link.innerHTML = "Voir detail";
                }
            })

        })
    })

</script>