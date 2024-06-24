<%@ page import="com.project.ebossy.model.Comportements" %>



<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
    Ajouter un Comportement
</button>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ajouter un Comportement</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="save" method="post">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="nom_in">Nom:</label>
                        <input type="text" name="nom" id="nom_in" class="form-control">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </div>
            </form>

        </div>
    </div>
</div>
