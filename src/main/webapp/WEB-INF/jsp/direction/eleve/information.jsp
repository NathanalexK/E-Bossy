<%@page pageEncoding="UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.util.UtilDate" %>
<%@ page import="com.project.ebossy.model.*" %>
<%
//    EleveAnneeScolaire eleveAnneeScolaire = ((EleveAnneeScolaire) request.getAttribute("eleve"));
    Eleve eleve = ((Eleve) request.getAttribute("eleve"));
    Tuteur tuteur = eleve.getIdTuteur();
    List<EleveAnneeScolaire> parcours = ((List<EleveAnneeScolaire>) request.getAttribute("parcours"));
    Classe classe = ((Classe) request.getAttribute("classe"));
    Niveau niveau = ((Niveau) request.getAttribute("niveau"));
    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM", Locale.FRANCE);
%>

<div class="d-flex justify-content-between py-2">
    <h2>Fiche de l'élève</h2>
    <div>
<%--        <a href="/eleve/list?classe=<%=parcours.get(0).getIdClasse().getId()%>"><button class="btn btn-light">Retour</button></a>--%>
        <button class="btn btn-primary">Modifier les informations</button>
    </div>
</div>



<div class="table-responsive card container-fluid shadow">

<%--    <div class="py-5 text-center">--%>
<%--        <img class="profil" src="${pageContext.request.contextPath}/uploads/image/eleve/0.jpg" alt="" width="200px">--%>
<%--    </div>--%>


    <table class="table table-borderless">
        <tbody>
        <tr>
            <td class="font-weight-bold">Nom</td>
            <td><%=eleve.getNom()%></td>
        </tr>

        <tr>
            <td class="font-weight-bold">Prenom(s)</td>
            <td>
                <input
                        type="text"
                        name="prenom"
                        class="form-control disabled_input"
                        value="<%=eleve.getPrenom()%>"
                >
            </td>
        </tr>

        <tr>
            <td class="font-weight-bold">Date de naissance</td>
            <td><%=eleve.getDateNaissance().getDayOfMonth()%> <%=df.format(eleve.getDateNaissance())%> <%=eleve.getDateNaissance().getYear()%>
            (<%=UtilDate.calculAge(eleve.getDateNaissance())%> ans)
            </td>
        </tr>

        <tr>
            <td class="font-weight-bold">Sexe</td>
            <td><%=eleve.getIdSexe().getTypeSexe().charAt(0)%></td>
        </tr>

        <tr>
            <td class="font-weight-bold">Classe</td>
            <td><%=classe != null ? classe.getNomClasse() : "Pas de classe (niveau: " + niveau.getNomNiveau() + ")"%></td>
        </tr>

        <tr>
            <td class="font-weight-bold">Identifiant</td>
            <td><%=eleve.getIdentifiant()%></td>
        </tr>

        <tr>
            <td class="font-weight-bold">Tuteur</td>
            <td><%=tuteur.getNom()%> <%=tuteur.getPrenom()%></td>
        </tr>

        <tr>
            <td class="font-weight-bold">Adresse</td>
            <td><%=tuteur.getAdresse()%></td>
        </tr>





        <tr>
            <td class="font-weight-bold">Email Tuteur:</td>
            <td><%=tuteur.getEmail()%></td>
        </tr>

        <tr>
            <td class="font-weight-bold">Contact Tuteur:</td>
            <td><%=tuteur.getConctact()%></td>
        </tr>
        </tbody>



    </table>


</div>