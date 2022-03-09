package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

/**
 * Servlet implementation class servletEmprunter
 */
@WebServlet("/servletEmprunter")
public class servletEmprunter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletEmprunter() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String docAEmprunter = request.getParameter("nomDocAEmprunter");
		
		int numDocAEmprunter = Integer.parseInt(docAEmprunter);
		
		Document d = Mediatheque.getInstance().getDocument(numDocAEmprunter);	
		Utilisateur u = (Utilisateur) request.getSession().getAttribute("utilisateur");
        
        try {
			d.emprunt(u);
		} catch (Exception e) {
			System.err.println("Erreur lors de l'emprunt : " + e);
		}

	    RequestDispatcher disp = request.getRequestDispatcher("./vueJSP/accueilAbonne.jsp");
	    disp.forward(request, response);
	}

}
