<%@page pageEncoding="UTF-8" %>
<%@ page import="com.project.ebossy.model.Salle" %>
<%@ page import="java.util.List" %>
<%
%>


<form action="/salle/update" method="post" id="updateForm">
    <form action=""></form>
    <div class="d-flex w-100 justify-content-between py-2">
        <h2>Liste des Salles</h2>

        <div class="d-flex ">
            <div class="px-2">
                <table class="table table-bordered">
                    <tr>
                        <td><b>Capacité:</b></td>
                        <td class="bg-light"><%=capacite%>
                        </td>
                        <td><b>Nombres de salles:</b></td>
                        <td class="bg-light"><%=nombre%>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="align-items-center px-2 pt-1">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                    Ajouter une salle
                </button>
            </div>
        </div>
    </div>
    <div id="apply_update" class="fixed-bottom text-center bg-white border-top py-2">
        <button class="btn btn-light" id="undo_update" type="button" onclick="location.reload(true)">Annuler</button>
        <button class="btn btn-primary" type="submit" form="updateForm">Sauveguarder les modifications</button>
    </div>

    <div class="table-responsive card container-fluid">

        <table class="table table-striped table-borderless">
            <thead>
            <tr class="row">
                <th class="col-4">Nom du Salle</th>
                <th class="col-4">Capacité</th>
                <th class="col-4">Action</th>
            </tr>
            </thead>

            <tbody>
            <%
                for (Salle salle : salleList) {
            %>
            <tr class="row">

                <input type="hidden" name="idSalle[]" class="_input" value="<%=salle.getId()%>" disabled>
                <td class="cols-4"><input type="text" name="numero[]" class="form-control disabled_input _input"
                                          value="<%=salle.getNumero()%>" disabled></td>
                <td class="col-4"><input type="number" name="capacite[]" class="form-control _input disabled_input"
                                         value="<%=salle.getCapacite()%>" disabled></td>
                <td class="col-4 text-center">
                    <button class="btn btn-light border _update" type="button">
                        <img src="/assets/icon/modify.svg" width="16px" alt="">
                        Modifier
                    </button>

                    <form id="deleteForm<%=salle.getId()%>" action="delete" method="post"
                          onsubmit="confirmSubmission(event,'<%=salle.getNumero()%>')">
                        <input type="hidden" value="<%=salle.getId()%>" name="idSalle">
                        <button form="deleteForm<%=salle.getId()%>" type="submit" class="btn btn-light border">
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
</form>


<script>
    document.querySelector("#apply_update").style.visibility = "hidden";

    document.querySelectorAll("._input").forEach(input => {
        input.disabled = true;
        // input.classList.remove("disabled_input");
    })

    document.querySelectorAll("._update").forEach(btn => {
        const row = btn.closest("tr");

        btn.addEventListener('click', evt => {
            document.querySelector("#apply_update").style.visibility = "visible";

            row.querySelectorAll("._input").forEach(input => {
                input.disabled = false;
                input.classList.remove("disabled_input");
            })
        })
    })


</script>
