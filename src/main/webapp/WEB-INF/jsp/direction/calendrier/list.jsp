<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>

<div class="py-4">
    <h2>Calendrier Scolaire</h2>
</div>

<div class="list-group">
    <%
        for(CalendrierScolaire evenement : evenementList)
        {
            LocalDateTime debut = evenement.getDateDebut();
            LocalDateTime fin = evenement.getDateFin();
    %>
    <div class="list-group-item my-2 border <%=evenement.getTypeEvenement() == 0 ? "bg2" : "bg1"%>">
        <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1 font-weight-bold"><%=evenement.getLibelle()%></h5>
            <small><%=evenement.getStatus().getNom()%></small>
        </div>
<%--        <small><%=evenement.getStatus().getNom()%></small>--%>
        <p class="mb-1"><u>Date debut:</u> <%=debut.getDayOfMonth()%> <%=debut.getMonth()%> <%=debut.getYear()%>  a  <%=debut.getHour()%>:<%=debut.getMinute()%></p>
        <p class="mb-1"><u>Date Fin:</u> <%=fin.getDayOfMonth()%> <%=fin.getMonth()%> <%=fin.getYear()%> a  <%=fin.getHour()%>:<%=fin.getMinute()%></p>
    </div>
    <%
        }
    %>

</div>