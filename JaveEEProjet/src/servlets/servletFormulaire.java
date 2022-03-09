package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.Utilisateur;
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher d;
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		if(user.isBibliothecaire()) {
			
			d = request.getRequestDispatcher("./vueJSP/accueilBibliothecaire.jsp");
			d.forward(request, response);
		}else {
			request.setAttribute("utilisateur",user);

			d = request.getRequestDispatcher("./vueJSP/accueilAbonne.jsp");
			d.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("servletAjoutDoc");
	}
}
