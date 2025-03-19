

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/TableauDeBord2")
public class TableauDeBord2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public TableauDeBord2() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("html/text");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		Double taille = (Double) session.getAttribute("taille");
		Double poids = (Double) session.getAttribute("poids");
		Double imc = (Double) session.getAttribute("imc");
		
		out.println("<h2>Tableau de Bord 2</h2>");
        if (poids != null) {
            out.println("<p>Poids : " + poids + " kg</p>");
            out.println("<p>Taille : " + taille + " m</p>");
            out.println("<p>IMC : " + imc + "</p>");
        } else {
            out.println("<p>Aucune donnée en session.</p>");
        }

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
