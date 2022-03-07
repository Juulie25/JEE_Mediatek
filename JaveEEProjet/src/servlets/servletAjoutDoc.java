package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.Mediatheque;

/**
 * Servlet implementation class servletAjoutDoc
 */
@WebServlet("/servletAjoutDoc")
public class servletAjoutDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAjoutDoc() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher d = request.getRequestDispatcher("./htmlFiles/formulaireAjoutDoc.jsp");
        d.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeLivre = request.getParameter("groupe1");
        int type = Integer.parseInt(typeLivre);
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        String forAdult = request.getParameter("groupe2");
        Boolean adulte;
        
        if(forAdult.equals("oui")) {
        	adulte = true;
        }else {
        	adulte = false;
        }      
        
        Mediatheque.getInstance().ajoutDocument(type, titre, auteur, adulte );
        
    	RequestDispatcher d = request.getRequestDispatcher("./htmlFiles/accueilBibliothecaire.jsp");
        d.forward(request, response);   
	}
}
