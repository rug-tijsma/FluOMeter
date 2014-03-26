import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FluOMeterServlet")
public class FluOMeterServlet extends HttpServlet {
	
	private FluOMeterViewer frame;

	public FluOMeterServlet() {
		super();
		this.frame = new FluOMeterViewer();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<script type='text/javascript' src='https://www.google.com/jsapi'></script>");
		out.println("<script type='text/javascript'>");
		out.println("google.load('visualization', '1', {'packages': ['geochart']});");
		out.println("google.setOnLoadCallback(drawMarkersMap);");

		out.println("function drawMarkersMap() {");
		out.println("var data = google.visualization.arrayToDataTable([");
		out.println("['Region',   'Population', 'Area'],");
		out.println("['Milan',     1324110,    181.76],");
		out.println("['italie, Naples',    959574,     117.27],");
		out.println("['Turin',     907563,     130.17],");
		out.println("['Palermo',   655875,     158.9],");
		out.println("['Genoa',     607906,     243.60],");
		out.println("['Bologna',   380181,     140.7],");
		out.println("['Florence',  371282,     102.41],");
		out.println("['Fiumicino', 67370,      213.44],");
		out.println("['Anzio',     52192,      43.43],");
		out.println("['Ciampino',  38262,      11111],");
		out.println("['Groningen',  38262,     1111],");
		out.println("['Rotterdam',  38262,      111111],");
		out.println("['Holwierde',  38262,      1111],");
		out.println("['Groningen, Spijk',  38262,      1111111],");
		out.println("['Eindhoven',  38262,      1111111111],");
		out.println("['Amsterdam',  38262,      11111],");
		out.println("['Almere',  38262,      11111]");
		out.println("]);");
      

		out.println("var options = {");
		out.println("displayMode: 'markers',");
		out.println("colorAxis: {colors: ['#FFAEAE', '#FF0000']}");
		out.println("};");

		out.println("var chart = new google.visualization.GeoChart(document.getElementById('chart_div'));");
		out.println("chart.draw(data, options);");
		out.println("};");
		out.println("</script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id='chart_div' style='width: 1280px; height: 900px;'></div>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
	}
	
	public static void main(String args[]) {
		new FluOMeterServlet();
	}

}