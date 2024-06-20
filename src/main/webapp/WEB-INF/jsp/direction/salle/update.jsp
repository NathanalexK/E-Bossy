<%@page pageEncoding="UTF-8" %>

<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModal"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modifier Salle</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form action="update" method="post">
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
                                <td class="cols-4"><input type="text" name="numero[]"
                                                          class="form-control disabled_input _input"
                                                          value="<%=salle.getNumero()%>" disabled></td>
                                <td class="col-4"><input type="number" name="capacite[]"
                                                         class="form-control _input disabled_input"
                                                         value="<%=salle.getCapacite()%>" disabled></td>
                                <td class="col-4 text-center">
                                    <button class="btn btn-light border _update" type="button">
                                        <img src="/assets/icon/modify.svg" width="16px" alt="">
                                        Modifier
                                    </button>
                                </td>
                            </tr>


                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                    <%--</form>--%>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        <button type="submit" class="btn btn-primary">Modifier</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>