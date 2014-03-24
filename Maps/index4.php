<html>
  <head>
    <script type='text/javascript' src='https://www.google.com/jsapi'></script>
    <script type='text/javascript'>
     google.load('visualization', '1', {'packages': ['geochart']});
     google.setOnLoadCallback(drawMarkersMap);

      function drawMarkersMap() {
      var data = google.visualization.arrayToDataTable([
        ['Region',   'Population', 'Area'],
        ['Milan',     1324110,    181.76],
        ['italie, Naples',    959574,     117.27],
        ['Turin',     907563,     130.17],
        ['Palermo',   655875,     158.9],
        ['Genoa',     607906,     243.60],
        ['Bologna',   380181,     140.7],
        ['Florence',  371282,     102.41],
        ['Fiumicino', 67370,      213.44],
        ['Anzio',     52192,      43.43],
        ['Ciampino',  38262,      11111],
        ['Groningen',  38262,     1111],
        ['Rotterdam',  38262,      111111],
        ['Holwierde',  38262,      1111],
        ['Groningen, Spijk',  38262,      1111111],
        ['Eindhoven',  38262,      1111111111],
        ['Amsterdam',  38262,      11111],
        ['Almere',  38262,      11111]
      ]);

      var options = {
        displayMode: 'markers',
		colorAxis: {colors: ['#FFAEAE', '#FF0000']}
      };

      var chart = new google.visualization.GeoChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    };
    </script>
  </head>
  <body>
    <div id='chart_div' style='width: 900px; height: 500px;'></div>
  </body>
</html>