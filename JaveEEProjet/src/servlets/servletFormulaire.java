package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilisateur.UtilisateurMediatek;
/**
 * Servlet implementation class sevletFormulaire
 */

@WebServlet("/servletFormulaire")
public class servletFormulaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("persistance.MediathequeData");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("**chargement de la servlet demo**");
        super.init();
    }   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletFormulaire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher d;
		request.setAttribute("utilisateur", request.getSession().getAttribute("utilisateur"));
		
		UtilisateurMediatek utilisateur = (UtilisateurMediatek) request.getAttribute("utilisateur");
		
		if(utilisateur.isBibliothecaire()) {
			d = request.getRequestDispatcher("./htmlFiles/accueilBibliothecaire.jsp");
			d.forward(request, response);
		}else {
			d = request.getRequestDispatcher("./htmlFiles/accueilAbonne.jsp");
			d.forward(request, response);
		}
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
