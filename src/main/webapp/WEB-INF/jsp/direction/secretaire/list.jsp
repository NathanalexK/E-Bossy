<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Secretaire" %>

<div class="text-center py-2">
    <h2>Liste des Secretaires</h2>
</div>

<div class="table-responsive card">
    <table class="table table-striped table-borderless">
        <thead><tr>
            <th>Nom :</th>
            <th>Prenom :</th>
            <th>Date de Naissance</th>
            <th>Adresse</th>
            <th>Contact</th>
            <th>Email</th>
            <th>Sexe</th>
            <th>Action</th>
        </tr></thead>

        <tbody>
        <%
            for (Secretaire secretaire : secretaireList)
            {
        %>
        <tr>
            <td><%=secretaire.getNom()%></td>
            <td><%=secretaire.getPrenom()%></td>
            <td><%=secretaire.getDateNaissance()%></td>
            <td><%=secretaire.getAdresse()%></td>
            <td><%=secretaire.getContact()%></td>
            <td><%=secretaire.getEmail()%></td>
            <td><%= secretaire.getIdSexe().getTypeSexe() %></td>
            <td>
                <form action="delete" method="post" >
                    <input type="hidden" value="<%=secretaire.getId()%>" name="idSecretaire">
                    <button type="submit" class="btn btn-light border">
                        <img src="/assets/icon/close.svg" width="16px" class=""/>
                        Supprimer
                    </button>
                </form>
            </td>
            <td>
                <form action="#" method="post" >
                    <input type="hidden" value="<%=secretaire.getId()%>" name="idSecretaire">
                    <button type="submit" class="btn btn-light border">
                        <img src="/assets/icon/close.svg" width="16px" class=""/>
                        Modifier
                    </button>
                </form>
            </td>
        </tr>


        <%
            }
        %>
        </tbody>
    </table>
</div>

