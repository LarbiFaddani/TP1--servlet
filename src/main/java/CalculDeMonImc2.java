

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;


@WebServlet("/CalculDeMonImc2")
public class CalculDeMonImc2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CalculDeMonImc2() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String tailleSt = request.getParameter("taille");
            String poidsSt = request.getParameter("poids");

            double taille = Double.parseDouble(tailleSt);
            double poids = Double.parseDouble(poidsSt);
            Imc imcObj = new Imc(taille, poids);
            double imc = imcObj.calcul();
            
            HttpSession session = request.getSession();
            session.setAttribute("taille",taille);
            session.setAttribute("poids",poids);
            session.setAttribute("imc",imc);

            out.println("Le IMC est  :" + imc);
            out.println("<p><a href='TableauDeBord2'>Voir le tableau de bord numero 2</a></p>");
        } catch (NumberFormatException | NullPointerException e) {
            out.println("Erreur : Veuillez entrer des valeurs valides pour le poids et la taille.");
        }
    }
	

}
