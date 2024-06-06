<div class="py-2 text-center">
    <h2>Ajouter une nouvelle Annee Scolaire</h2>
</div>

<form action="/anneeScolaire/save">
    <div class="form-group">
        <label for="nom_in">Nom:</label>
        <input type="text" name="nom" id="nom_in" class="form-control" required>
    </div>

    <div class="form-group">
        <label for="dateDebut_in">Date Commencement:</label>
        <input type="date" class="form-control" name="dateDebut" id="dateDebut_in" required>
    </div>

    <div class="form-group">
        <label for="dateFin_in">Date Fin:</label>
        <input type="date" class="form-control" name="dateFin" id="dateFin_in" required>
    </div>

    <button type="submit" class="btn btn-primary">Ajouter</button>


</form>