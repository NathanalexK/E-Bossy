<%@page pageEncoding="UTF-8" %>
<%@ page import="com.project.ebossy.model.PeriodeNote" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.project.ebossy.model.Matiere" %>
<%@ page import="com.project.ebossy.model.Note" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.MatiereProf" %><%
    List<MatiereProf> matiereProfList = ((List<MatiereProf>) request.getAttribute("matiereProfList"));
    List<PeriodeNote> periodeNoteList = ((List<PeriodeNote>) request.getAttribute("periodeNoteList"));
    Map<Matiere, Map<PeriodeNote, Note>> bulletinMap = ((Map<Matiere, Map<PeriodeNote, Note>>) request.getAttribute("bulletinMap"));
%>

<div class="d-flex justify-content-between py-2">
    <h2>Bulletin de l'élève</h2>
    <div>
        <a href=""><button class="btn btn-light">Retour</button></a>
        <button class="btn btn-primary">Modifier les informations</button>
    </div>
</div>


<div class="table-responsive card container-fluid">

    <table class="table table-striped table-borderless text-center">
        <thead>
        <tr>
            <th>Matieres</th>
            <th>Coef</th>
            <%
                for (PeriodeNote periodeNote : periodeNoteList) {
                    out.print("<th>" + periodeNote.getNomPeriode() + "</th>");
                }
            %>
        </tr>
        </thead>
        <tbody>
        <%
            for (Map.Entry<Matiere, Map<PeriodeNote, Note>> entry : bulletinMap.entrySet()) {
                String rowKey = entry.getKey().getNomMatiere();
                Map<PeriodeNote, Note> innerMap = entry.getValue();
        %>
        <tr>
            <td><%= rowKey %></td>
            <td><%=1%></td>
            <%
                for (PeriodeNote periodeNote: innerMap.keySet()) {
                    String cellValue = innerMap.get(periodeNote) != null ? innerMap.get(periodeNote).getNote().toString() : "Pas de note";
                    if (cellValue == null) {
                        cellValue = "-";
                    }
                    out.print("<td>" + cellValue + "</td>");
                }
            %>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>


</div>

<%--<table>--%>
<%--    <%--%>
<%--        for(Map.Entry<PeriodeNote, Map<Matiere, Note>> entry : bulletinMap.entrySet()) {--%>
<%--    %>--%>
<%--    <tr>--%>
<%--        <%=entry.getKey().getNomPeriode()%>--%>
<%--        <%--%>
<%--            for(Map.Entry<Matiere, Note> matiereNoteEntry : entry.getValue().entrySet()) {--%>

<%--        %>--%>
<%--            <td><%=matiereNoteEntry.getValue() != null ? matiereNoteEntry.getValue().getNote() : 0%></td>--%>
<%--        <%--%>
<%--            }--%>
<%--        %>--%>
<%--    </tr>--%>

<%--    <%--%>
<%--        }--%>
<%--    %>--%>

<%--</table>--%>