import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

@WebServlet("/CalculDeMonImc")
public class CalculDeMonImc extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CalculDeMonImc() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String tailleSt = request.getParameter("taille");
            String poidsSt = request.getParameter("poids");

            double taille = Double.parseDouble(tailleSt);
            double poids = Double.parseDouble(poidsSt);
            double imc = poids / (taille * taille);

            out.println("Le IMC est  :" + imc);
        } catch (NumberFormatException | NullPointerException e) {
            out.println("Erreur : Veuillez entrer des valeurs valides pour le poids et la taille.");
        }
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

            Cookie cookieTaille = new Cookie("taille", tailleSt);
            Cookie cookiePoids = new Cookie("poids", poidsSt);
            Cookie cookieIMC = new Cookie("imc", String.format(Locale.US, "%.2f", imc));

            int cookieDuration = 24 * 60 * 60;
            cookieTaille.setMaxAge(cookieDuration);
            cookiePoids.setMaxAge(cookieDuration);
            cookieIMC.setMaxAge(cookieDuration);

            response.addCookie(cookieTaille);
            response.addCookie(cookiePoids);
            response.addCookie(cookieIMC);

            out.println("Le IMC est  :" + imc);
            out.println("<p><a href='TableauDeBord'>Voir le tableau de bord</a></p>");
        } catch (NumberFormatException | NullPointerException e) {
            out.println("Erreur : Veuillez entrer des valeurs valides pour le poids et la taille.");
        }
    }
}
