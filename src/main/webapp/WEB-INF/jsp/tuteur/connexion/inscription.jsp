<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.Sexe" %><%
    List<Sexe> sexeList = ((List<Sexe>) request.getAttribute("sexeList"));
%>

<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>E-Bossy</title>
    <link rel="icon" href="/assets/icon/logo.png">
    <link rel="stylesheet" href="/assets/css/bootstrap.css">
    <link rel="stylesheet" href="/assets/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="/assets/css/bootstrap-grid.css">
    <link rel="stylesheet" href="/assets/css/style.css">
</head>
<body>

<div class="container my-2">
    <div class="card card-body">
        <div class="text-center _blue py-2">
                <h2 class="font-weight-bold">E-Bossy</h2>
        </div>
        <div class="card-title">
            <h3>Inscription en tant que Tuteur</h3>
        </div>
        <form action="/tuteur/inscription/save" method="post">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="nom_in">Nom</label>
                    <input type="text" class="form-control" id="nom_in" placeholder="" name="nom">
                </div>
                <div class="form-group col-md-6">
                    <label for="inputPassword4">Prenom</label>
                    <input type="text" class="form-control" id="inputPassword4" placeholder="" name="prenom">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-9">
                    <label for="date_in">Date de Naissance</label>
                    <input type="date" class="form-control" id="date_in" name="date" required>
                </div>

                <div class="form-group col-md-3">
                    <label for="sexe_in">Sexe</label>
                    <select name="sexe" class="form-control" id="sexe_in">
                        <%
                            for(Sexe sexe : sexeList)
                            {
                        %>
                        <option value="<%=sexe.getId()%>"><%=sexe.getTypeSexe()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="inputAddress2">Addresse</label>
                <input type="text" class="form-control" id="inputAddress2" placeholder="LOT IPN 20A" name="adresse">
            </div>
            <div class="form-group">
                <label for="contact_in">Contact</label>
                <input type="text" class="form-control" id="contact_in" name="contact">
            </div>
            <div class="form-group">
                <label for="email_in">Email</label>
                <input type="email" class="form-control" id="email_in" name="email">
            </div>
            <div class="form-group">
                <label for="password_in">Mot de passe</label>
                <input type="password" class="form-control password" id="password_in" name="mdp">
            </div>

            <div class="form-group">
                <label for="password_in2">Confirmer votre mot de passe</label>
                <input type="password" class="form-control password" id="password_in2">
            </div>

            <div class="form-group text-right">
                <input type="checkbox" id="showpwd"> Afficher le mot de passe
            </div>


            <button type="submit" class="btn btn-primary">Creer mon compte</button>
        </form>
    </div>
</div>
</body>
</html>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        const checkbox = document.querySelector('#showpwd');
        document.querySelectorAll(".password").forEach(input => {
            input.type = checkbox.checked ? 'text' : 'password';
        })

        checkbox.addEventListener('change', () => {
            document.querySelectorAll(".password").forEach(input => {
                input.type = checkbox.checked ? 'text' : 'password';
            })
        })
    });
</script>
