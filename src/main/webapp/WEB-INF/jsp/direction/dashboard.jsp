<%@ page import="com.project.ebossy.model.PeriodeNote" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.project.ebossy.model.Matiere" %>
<%@page pageEncoding="UTF-8" %>
<%
    Double moyenneGenerale = ((Double) request.getAttribute("moyenneGenerale"));
    Integer nombresEleves = (Integer) request.getAttribute("nombresEleves");
    List<PeriodeNote> periodeNotes = ((List<PeriodeNote>) request.getAttribute("periodeNoteList"));
    Map<Integer, Double> moyennesMap = (Map<Integer, Double>) request.getAttribute("moyennesGenerales");
    Map<Integer, Integer> sexeMap = (Map<Integer, Integer>) request.getAttribute("sexes");
    Map<Integer, Double> matiereMoyennes = ((Map<Integer, Double>) request.getAttribute("matiereMoyenne"));
    List<Matiere> matiereList = ((List<Matiere>) request.getAttribute("matiereList"));
%>


<div class="d-flex justify-content-between py-2">
    <h2>Dashboard</h2>
</div>
<hr>

<div class="row">
    <div class="col-6">
        <div class="card">
            <div class="card-header font-weight-bold">Moyenne Generale</div>

            <div class="card-body"><%=String.format("%.2f",moyenneGenerale)%> / 20</div>
        </div>
    </div>

    <div class="col-6">
        <div class="card">
            <div class="card-header font-weight-bold">Nombres d'élèves</div>

            <div class="card-body"><%=nombresEleves%></div>
        </div>
    </div>
</div>

<div class="row my-2">
    <div class="col-8">
        <div class="card">
            <canvas id="canvas1"></canvas>
        </div>
    </div>

    <div class="col-4">
        <div class="card">
            <div class="card-header font-weight-bold">
                Moyennes par periodes
            </div>

            <div class="card-body">
                <table class="table table-borderless">
                    <%
                        for(PeriodeNote periodeNote : periodeNotes)
                        {
                    %>
                    <tr>
                        <td><%=periodeNote.getNomPeriode()%></td>
                        <td><%=String.format("%.2f", moyennesMap.get(periodeNote.getId()))%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="row my-2">
    <div class="col-4">
        <div class="card">
            <canvas id="sex-chart"></canvas>
        </div>
    </div>

    <div class="col-8">
        <div class="card">
            <canvas id="matiere-chart"></canvas>
        </div>
    </div>


</div>


<script src="/assets/js/chart.js"></script>
<script src="/assets/js/chartjs-plugin-datalabels.js"></script>
<script>
    let periodes = [];
    let moyennes = [];

    <%
    for(PeriodeNote periodeNote : periodeNotes) {
    %>
    periodes.push("<%=periodeNote.getNomPeriode()%>");
    moyennes.push(<%=moyennesMap.get(periodeNote.getId())%>);
    <%
    }
    %>

    let dataMoyennes = {
        labels: periodes,
        datasets: [{
            data: moyennes,
            label: "Moyenne Generale"
        }]
    }
    let config = {
        type: 'line',
        data: dataMoyennes
    }
    let canvas1 = document.getElementById("canvas1").getContext('2d');
    new Chart(canvas1, config);
</script>

<script>
    let sexeName = ["Garcon", "Fille"];
    let sexeData = [];
    sexeData.push(<%=sexeMap.get(1)%>)
    sexeData.push(<%=sexeMap.get(2)%>)

    let sexeStat = {
        labels: sexeName,
        datasets: [{
            label: "",
            data: sexeData
        }]
    }
    let configSexe = {
        type: 'pie',
        data: sexeStat
    }
    let sexChart = document.getElementById("sex-chart").getContext('2d');
    new Chart(sexChart, configSexe);
</script>

<script>
    let nomMatieres = [];
    let moyenneMatieres = [];

    <%
    for(Matiere matiere : matiereList)
    {
    %>
        nomMatieres.push("<%=matiere.getNomMatiere()%>");
        moyenneMatieres.push(<%=matiereMoyennes.get(matiere.getId())%>)
    <%
    }
    %>

    let matieresData = {
        labels: nomMatieres,
        datasets: [{
            label: "Moyenne par matieres",
            data: moyenneMatieres
        }]
    }
    let matieresConfig = {
        type: 'bar',
        data: matieresData
    }
    let matiereChart = document.getElementById("matiere-chart").getContext('2d');
    new Chart(matiereChart, matieresConfig);

    console.log(nomMatieres);
    console.log(moyenneMatieres)
</script>
