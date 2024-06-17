<%@ page import="com.project.ebossy.model.Classe" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Matiere" %>
<%@ page import="com.project.ebossy.model.Professeur" %>
<%@ page import="com.project.ebossy.model.MatiereProf" %>
<%@ page import="java.util.Map" %>
<%@page pageEncoding="UTF-8" %>

<%
    List<Classe> classeList = ((List<Classe>) request.getAttribute("classeList"));
    List<Matiere> matiereList = ((List<Matiere>) request.getAttribute("matiereList"));
    Map<Matiere, Professeur> matiereProfList = ((Map<Matiere, Professeur>) request.getAttribute("matiereProf"));
    List<Professeur> professeurList = ((List<Professeur>) request.getAttribute("profList"));
    Classe classe = ((Classe) request.getAttribute("classe"));
%>


<div class="d-flex w-100 justify-content-between py-2">
    <h2>Professeur par Classe</h2>

    <div class="d-flex ">
        <div class="align-items-center px-2 pt-1 row">
            <form action="">
                <select name="" id="classe_in" class="form-control">
                    <%
                        for(Classe c : classeList)
                        {
                    %>
                    <option value="<%=c.getId()%>"
                        <%=c.getId().equals(classe.getId()) ? "selected" : ""%>
                    ><%=c.getNomClasse()%></option>
                    <%
                        }
                    %>
                </select>
            </form>
        </div>
    </div>
</div>

<div class="table-responsive card container-fluid">

    <form action="save" method="post">
        <div class="fixed-bottom text-center border-top py-2 _action-div">
            <button type="button" class="btn bg-light" onclick="location.reload(true)">Annuler</button>
            <button type="submit" class="btn btn-primary">Sauveguarder</button>
        </div>

        <input type="hidden" name="idClasse" value="<%=classe.getId()%>">

        <table class="table table-striped table-borderless text-center">
            <thead>
            <tr>
                <th class="col-4">Matiere</th>
                <th class="col-4">Professeur</th>
            </tr>
            </thead>

            <tbody>
            <%
                for(Map.Entry<Matiere, Professeur> mp : matiereProfList.entrySet())
                {
            %>
            <tr>
                <td><%=mp.getKey().getNomMatiere()%>
                </td>
                <td>
                    <input type="hidden" name="idMatiere[]" value="<%=mp.getKey().getId()%>">
                    <select name="idProfs[]" id="" class="form-control _select">
                        <option value="-1">Aucun Prof</option>

                        <%
                            for (Professeur professeur : professeurList) {
                        %>
                        <option value="<%=professeur.getId()%>"  <%= mp.getValue() != null ? (mp.getValue().getId().equals(professeur.getId()) ? "selected" : "") : ""%>><%=professeur.getNom()%>  <%=professeur.getPrenom()%>
                        </option>
                        <%
                            }
                        %>

                    </select>

                </td>
            </tr>

            <%
                }
            %>
            </tbody>
        </table>
    </form>
</div>

<script>


    document.addEventListener("DOMContentLoaded", () => {
        const div = document.querySelector("._action-div");
        div.style.display = "none";

        document.querySelectorAll("._select").forEach(select => {
            select.addEventListener('change', evt => {
                div.style.display = "block";
            })
        })

        const select = document.getElementById("classe_in")
        console.log(select)
        select.addEventListener("change", () => {
            window.location = "/matiereProf/form?classe="+select.value;
        })
    })
</script>