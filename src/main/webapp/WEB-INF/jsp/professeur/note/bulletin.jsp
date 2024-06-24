<%@page pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.ebossy.model.*" %>
<%@ page import="java.math.BigDecimal" %>
<%
    List<MatiereProf> matiereProfList = ((List<MatiereProf>) request.getAttribute("matiereProfList"));
    List<PeriodeNote> periodeNoteList = ((List<PeriodeNote>) request.getAttribute("periodeNoteList"));
    Map<Matiere, Map<PeriodeNote, Note>> bulletinMap = ((Map<Matiere, Map<PeriodeNote, Note>>) request.getAttribute("bulletinMap"));
    Map<Matiere, Integer> coefMap = ((Map<Matiere, Integer>) request.getAttribute("coefMap"));
    Map<Integer, Map<String, Object>> rangMap = (Map<Integer, Map<String, Object>>) request.getAttribute("rangMap");
    Map<Integer, Map<String, Object>> statMap = (Map<Integer, Map<String, Object>>) request.getAttribute("statMap");
    EleveAnneeScolaire eas = ((EleveAnneeScolaire) request.getAttribute("eas"));
    Eleve eleve = eas.getIdEleve();
%>
<%--<%=statMap%>--%>
<script>
    let moyennes = [];
    let moyennesMax = [];
    let moyennesMin = [];
    let moyennesClasse = [];
    let periodesNotes = [];
</script>
<div class="d-flex justify-content-between py-2">
    <h2>Bulletin de l'élève</h2>
    <div>
        <a href="">
            <button class="btn btn-light">Retour</button>
        </a>
        <button class="btn btn-primary" id="download">Modifier les informations</button>
    </div>
</div>

<div id="content">
    <table class="table table-borderless">
        <tr>
            <td><b>Nom:</b></td>
            <td><%=eleve.getNom()%>
            </td>
        </tr>

        <tr>
            <td><b>Prenom:</b></td>
            <td><%=eleve.getPrenom()%>
            </td>
        </tr>

        <tr>
            <td><b>Classe:</b></td>
            <td><%=eas.getIdClasse().getNomClasse()%>
            </td>
        </tr>

        <tr>
            <td><b>Année Scolaire:</b></td>
            <td><%=eas.getIdAnneeScolaire().getNom()%>
            </td>
        </tr>
    </table>

    <div class="table-responsive card container-fluid" id="content">


        <table class="table table-striped table-borderless text-center">
            <thead>
            <tr>
                <th>Matieres</th>
                <th>Coef</th>
                <%
                    for (PeriodeNote periodeNote : periodeNoteList) {
                        out.print("<th>" + periodeNote.getNomPeriode() + "</th>");
                    }
                %>
            </tr>
            </thead>
            <tbody>
            <%
                for (Map.Entry<Matiere, Map<PeriodeNote, Note>> entry : bulletinMap.entrySet()) {
                    String rowKey = entry.getKey().getNomMatiere();
                    Map<PeriodeNote, Note> innerMap = entry.getValue();
            %>
            <tr>
                <td><%= rowKey %>
                </td>
                <td><%=coefMap.get(entry.getKey())%>
                </td>
                <%
                    for (PeriodeNote periodeNote : innerMap.keySet()) {
                        String cellValue = innerMap.get(periodeNote) != null ? innerMap.get(periodeNote).getNote().toString() : "-";
                        if (cellValue == null) {
                            cellValue = "-";
                        }
                        out.print("<td>" + cellValue + "</td>");
                    }
                %>
            </tr>

            <%
                }
            %>

            <tr>
                <td><b>Moyenne</b></td>
                <td></td>
                <%
                    for (PeriodeNote periodeNote : periodeNoteList) {
                        Map<String, Object> map = rangMap.get(periodeNote.getId());
                        String value = map != null ? String.format("%.2f", ((BigDecimal) map.get("moyenne"))) : "";
                %>
                <script>
                    moyennes.push(<%=value%>)
                    periodesNotes.push("<%=periodeNote.getNomPeriode()%>")
                </script>
                <td><b><%=map != null ? String.format("%.2f", ((BigDecimal) map.get("moyenne"))) + " / 20" : "-"%>
                </>
                </td>
                <%
                    }
                %>
            </tr>

            <tr>
                <td><b>Max</b></td>
                <td></td>
                <%
                    for (PeriodeNote periodeNote : periodeNoteList) {
                        Map<String, Object> map = statMap.get(periodeNote.getId());
                        String value = map != null ? String.format("%.2f", ((BigDecimal) map.get("max"))) : "";
                %>
                <script>
                    moyennesMax.push(<%=value%>)
                </script>
                <td><%=map != null ? String.format("%.2f", ((BigDecimal) map.get("max"))) + " / 20" : "-"%>

                </td>
                <%
                    }
                %>
            </tr>

            <tr>
                <td><b>Min</b></td>
                <td></td>
                <%
                    for (PeriodeNote periodeNote : periodeNoteList) {
                        Map<String, Object> map = statMap.get(periodeNote.getId());
                        String value = map != null ? String.format("%.2f", ((BigDecimal) map.get("min"))) : "";
                %>
                <script>
                    moyennesMin.push(<%=value%>)
                </script>
                <td><%=map != null ? String.format("%.2f", ((BigDecimal) map.get("min"))) + " / 20" : "-"%>
                </td>
                <%
                    }
                %>
            </tr>

            <tr>
                <td><b>Classe</b></td>
                <td></td>
                <%
                    for (PeriodeNote periodeNote : periodeNoteList) {
                        Map<String, Object> map = statMap.get(periodeNote.getId());
                        String value = map != null ? String.format("%.2f", ((BigDecimal) map.get("avg"))) : "";
                %>
                <script>
                    moyennesClasse.push(<%=value%>)
                </script>
                <td><%=map != null ? String.format("%.2f", ((BigDecimal) map.get("avg"))) + " / 20" : "-"%>
                </td>
                <%
                    }
                %>
            </tr>

            <tr>
                <td><b>Rang</b></td>
                <td></td>
                <%
                    for (PeriodeNote periodeNote : periodeNoteList) {
                        Map<String, Object> map = rangMap.get(periodeNote.getId());
                %>
                <td><i><%=map != null ? map.get("rank") + "e / " + map.get("participant") + " élèves" : "-"%>
                </>
                </td>
                <%
                    }
                %>
            </tr>

            </tbody>


        </table>

    </div>

    <div class="d-flex py-4 justify-content-between">
        <h2 class="h2">Graphique des Notes</h2>

        <div>
            Mode de vue:
            <select id="type-graph" class="form-control" onchange="changeGraph()">
                <option value="bar">Evolution Annuelle</option>
                <option value="radar">Profil de l'élève</option>

            </select>
        </div>
    </div>
    <div id="graphic-container" class="px-5 mx-5 border">
        <canvas id="chart" style="background-color: #ffffff">

        </canvas>
    </div>

    <div class="d-flex pt-5">
        <h2>Graphique des moyennes</h2>
    </div>
    <hr>

    <div class="px-5 mx-5 border">
        <canvas id="moyenne-chart">

        </canvas>
    </div>
</div>


<script src="/assets/js/chart.js"></script>
<script src="/assets/js/chartjs-plugin-datalabels.js"></script>
<script>
    let labels = [];
    <%
        for (MatiereProf matiereProf : matiereProfList) {
    %>
    labels.push("<%=matiereProf.getIdMatiere().getNomMatiere().toUpperCase()%>")
    <%
    }
    %>


    let datasets = []


    <%
        for (PeriodeNote periodeNote : periodeNoteList) {
    %>
    content = [];
    <%
            for(MatiereProf matiereProf : matiereProfList) {
                String value = bulletinMap.get(matiereProf.getIdMatiere()) != null ? (
                               bulletinMap.get(matiereProf.getIdMatiere()).get(periodeNote) != null ?
                               bulletinMap.get(matiereProf.getIdMatiere()).get(periodeNote).getNote().toString() :
                               "undefined" ): "undefined";
    %>

    content.push(<%=value%>);
    <%
            }
    %>
    datasets.push({
        label: "<%=periodeNote.getNomPeriode()%>",
        data: content,
        fill: false
    });
    <%
        }
    %>


    const data = {
        labels: labels,
        datasets: datasets,
    };

    const red = "rgb(255, 99, 132)";
    const color = Chart.helpers.color;
    const config = {
        type: "bar",
        title: "Hello",
        data: data,
        options: getOptions(document.getElementById("type-graph").value),
        plugins: [ChartDataLabels]
    };
    var ctx = document.getElementById('chart').getContext('2d');
    var myChart = new Chart(ctx, config);


    function changeGraph() {
        // let canvas = document.createElement("canvas");
        // document.getElementById("graphic-container").appendChild(canvas);
        var chartType = document.getElementById("type-graph").value;
        config.options = getOptions(chartType);
        config.type = chartType;
        myChart.destroy();
        myChart = new Chart(ctx, config)
    }

    function getOptions(type) {
        let options = {};
        if (type === 'bar' || type === 'line') {
            options = {
                scales: {
                    x: {
                        grid: {
                            display: true // Désactiver les grilles de l'axe X
                        }
                    },
                    y: {
                        beginAtZero: true,
                        grid: {
                            display: true // Désactiver les grilles de l'axe Y
                        },
                        max:20
                    },
                },
                plugins: {

                    legend: {
                        labels: {
                            font: {
                                size: 18
                            }
                        },
                        position: 'bottom'
                    },
                    datalabels: {
                        display: true,
                        color: 'black',
                        font: {},
                    },
                },
            };
        } else if (type === 'radar') {
            options = {
                scales: {
                    r: {
                        grid: {
                            display: true // Désactiver les grilles du radar
                        },
                        max: 20
                    }
                },
                plugins: {

                    legend: {
                        labels: {
                            font: {
                                size: 18
                            }
                        },
                        position: 'bottom'
                    },
                    datalabels: {
                        display: true,
                        color: 'black',
                        font: {
                            // weight: 'bold'
                        },
                    },
                },
            };
        }
        return options;
    }

    const moyenneChart = document.getElementById("moyenne-chart").getContext('2d');
    const dataMoyennes = {
        labels: periodesNotes,
        datasets: [{
            data: moyennes,
            label: "Ma moyenne",
            borderColor: "rgb(0,112,192)"
        }, {
            data: moyennesMax,
            borderColor: "rgb(75,192,0)",
            label: "max"
        }, {
            data: moyennesMin,
            label: "min",
            borderColor: "rgb(232,0,0)"
        }, {
            data: moyennesClasse,
            label: "classe",
            fill: true
        }]
    }

    const configMoyenne = {
        type: 'line',
        data: dataMoyennes,
        options: {
            // y: {
            //     max:20,
            //     min: 0
            // }
        }
    }

    var a = new Chart(moyenneChart, configMoyenne)


    console.log(moyennes);
    console.log(periodesNotes)

</script>
