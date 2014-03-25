<html>
  <head>
    <script type='text/javascript' src='https://www.google.com/jsapi'></script>
    <script type='text/javascript'>
     google.load('visualization', '1', {'packages': ['geochart']});
     google.setOnLoadCallback(drawRegionsMap);

      function drawRegionsMap() {
        var data = google.visualization.arrayToDataTable([
          ['Country', 'Flu1', 'Flu2'],
          ['Germany', 200, 100],
          ['United States', 300, 150],
          ['Brazil', 400, 200],
          ['Canada', 500, 250],
          ['France', 600, 300],
          ['RU', 700, 350]
        ]);

        var options = {
		colorAxis: {colors: ['#FFAEAE', '#FF0000']}
		};

        var chart = new google.visualization.GeoChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    };
    </script>
  </head>
  <body>
    <div id="chart_div" style="width: 100%; height: 700px;"></div>
  </body>
</html>