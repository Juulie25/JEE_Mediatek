package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.Mediatheque;
import utilisateur.UtilisateurMediatek;

/**
 * Servlet implementation class servletGereSession
 */
@WebServlet("/servletGereSession")
public class servletGereSession extends HttpServlet {
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
    public servletGereSession() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher d = request.getRequestDispatcher("./htmlFiles/formulaire.jsp");
        d.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       String pseudo = request.getParameter("pseudo");
        String motdepasse = request.getParameter("motdepasse");
        
        UtilisateurMediatek utilisateur = (UtilisateurMediatek) Mediatheque.getInstance().getUser(pseudo, motdepasse);
        
        if(utilisateur == null) {
        	RequestDispatcher d = request.getRequestDispatcher("./htmlFiles/formulaire.jsp");
            d.forward(request, response);
        }else {
        	response.sendRedirect("servletFormulaire");
        	request.getSession().setAttribute("utilisateur", utilisateur);
        }
    }

}