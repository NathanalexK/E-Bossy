        <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ page import="java.util.List" %>
        <%@ page import="com.project.ebossy.model.Professeur" %>
        
        <div class="text-center py-2">
            <h2>Liste des Professeurs</h2>
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
                    for (Professeur professeur : professeurList)
                    {
                %>
                <tr>
                    <td><%=professeur.getNom()%></td>
                    <td><%=professeur.getPrenom()%></td>
                    <td><%=professeur.getDateNaissance()%></td>
                    <td><%=professeur.getAdresse()%></td>
                    <td><%=professeur.getContact()%></td>
                    <td><%=professeur.getEmail()%></td>
                    <td><%= professeur.getIdSexe().getTypeSexe() %></td>
                    <td>
                        <form action="delete" method="post" >
                            <input type="hidden" value="<%=professeur.getId()%>" name="idProfesseur">
                            <button type="submit" class="btn btn-light border">
                                <img src="/assets/icon/close.svg" width="16px" class=""/>
                                Supprimer
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="delete" method="post" >
                            <input type="hidden" value="<%=professeur.getId()%>" name="idProfesseur">
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
        
