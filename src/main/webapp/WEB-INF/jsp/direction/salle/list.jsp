<%@page pageEncoding="UTF-8" %>
<%@ page import="com.project.ebossy.model.Salle" %>
<%@ page import="java.util.List" %>
<%
%>


<%--<form action="/salle/update" method="post" id="updateForm">--%>
<%--    <form action=""></form>--%>
    <div class="d-flex w-100 justify-content-between py-2">
        <h2>Liste des Salles</h2>

        <div class="d-flex ">
            <div class="px-2">
                <table class="table table-bordered">
                    <tr>
                        <td><b>Capacité:</b></td>
                        <td class="bg-light"><%=capacite != null ? capacite : 0%>
                        </td>
                        <td><b>Nombres de salles:</b></td>
                        <td class="bg-light"><%=nombre%>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="align-items-center px-2 pt-1">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                    Ajouter
                </button>

                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateModal">
                    Modifier
                </button>
            </div>
        </div>
    </div>

    <div class="table-responsive card container-fluid">

        <table class="table table-striped table-borderless text-center">
            <thead>
            <tr>
                <th class="col-4">Nom du Salle</th>
                <th class="col-4">Capacité</th>
                <th class="col-4">Action</th>
            </tr>
            </thead>

            <tbody>
            <%
                for (Salle salle : salleList) {
            %>
            <tr>

                <input type="hidden" name="idSalle[]" class="_input" value="<%=salle.getId()%>" disabled>
                <td class="cols-4"> <%=salle.getNumero()%> </td>
                <td class="col-4"><%=salle.getCapacite()%></td>
                <td class="col-4 text-center">

                    <form id="deleteForm<%=salle.getId()%>" action="delete" method="post"
                          onsubmit="confirmSubmission(event,'<%=salle.getNumero()%>')">
                        <input type="hidden" value="<%=salle.getId()%>" name="idSalle">
                        <button type="submit" class="btn btn-light border" form="deleteForm<%=salle.getId()%>">
                            <img src="/assets/icon/close.svg" width="16px" class=""/>
                            Supprimer
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
<%--</form>--%>


<%@include file="update.jsp"%>

<script>
    // document.querySelector("#apply_update").style.visibility = "hidden";

    document.querySelectorAll("._input").forEach(input => {
        input.disabled = true;
        // input.classList.remove("disabled_input");
    })

    document.querySelectorAll("._update").forEach(btn => {
        const row = btn.closest("tr");

        btn.addEventListener('click', evt => {
            // document.querySelector("#apply_update").style.visibility = "visible";

            row.querySelectorAll("._input").forEach(input => {
                input.disabled = false;
                input.classList.remove("disabled_input");
            })
        })
    })


</script>
