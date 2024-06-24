<%@page pageEncoding="UTF-8" %>
<%
    String message = ((String) request.getAttribute("error"));
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.css">
    <link rel="stylesheet" href="/assets/css/style.css">

    <style>
        html {
            height: 100%;
        }

        body {
            display: flex;
            height: 100%;
            width: 100%;
            justify-content: center;
            align-content: center;
            align-items: center;
        }

        .top {
            position: absolute;
            display: flex;
            align-items: center;
            justify-content: center;
            top: -20%;
            left: 5%;
            height: 100px;
            border-radius: 5px;
            box-shadow: 0px 5px 15px 5px rgba(0, 98, 156, 0.24);

            font-size: 2rem;
            color: white;

            background-color: #00629c;
            width: 90%;
        }

        .middle {
            position: relative;
            padding: 40px 35px !important;
            background-color: rgb(255, 255, 255);
        }
    </style>
</head>
<body>


<div class="row border p-4 middle shadow rounded">
    <div style="width: 450px">
        <div class="text-center h4">
            Creer une nouvelle Annee Scolaire
        </div>

        <small>Votre etablissement ne dispose pas encore d'annee scolaire</small>

        <%
            if(message != null)
            {
        %>
        <div class="alert alert-danger"> <%=message%></div>
        <%
            }
        %>

        <form action="/anneeScolaire/save" method="post">
            <div class="form-group">
                <label for="input-nom">Nom:</label>
                <input
                        type="text"
                        id="input-nom"
                        class="form-control"
                        name="nom"
                        placeholder="Oct 2023 - Juil 2024"
                >
            </div>

            <div class="form-group">
                <label for="input-debut">Date debut: </label>
                <input
                        type="date"
                        id="input-debut"
                        class="form-control"
                        name="dateDebut"
                >
            </div>

            <div class="form-group">
                <label for="input-fin">Date Fin: </label>
                <input
                        type="date"
                        id="input-fin"
                        class="form-control"
                        name="dateFin"
                >
            </div>

            <div class="text-center pt-3">
                <button type="submit" class="btn btn-primary">
                    C'est Parti!
                </button>
                <div>
                    ou
                </div>

                <a href="/logout">Se deconnecter</a>
            </div>
        </form>
    </div>
</div>

<div
        style="
        position: fixed;
        z-index: -10;
        top: 200px;
        left: 500px;
        filter: blur(120px);
        background-color: #00629c;
        width: 300px;
        height: 200px;
"
>

</div>

<div
        style="
        position: fixed;
        z-index: -12;
        top: 500px;
        left: 1100px;
        filter: blur(150px);
        background-color: rgba(0,98,156,0.72);
        width: 300px;
        height: 300px;
"
>
</div>
</body>
</html>

