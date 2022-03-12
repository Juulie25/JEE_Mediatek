package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

/**
 * Servlet implementation class servletGereSession
 */
@WebServlet("/servletGereSession")
public class servletLancement extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("persistance.MediathequeData");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("**Lancement du serveur**");
        super.init();
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletLancement() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher d = request.getRequestDispatcher("./vueJSP/formulaire.jsp");
        d.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String pseudo = request.getParameter("pseudo");
        String motdepasse = request.getParameter("motdepasse");
        
        System.out.println(pseudo + " - " + motdepasse);
        
        Utilisateur utilisateur = (Utilisateur) Mediatheque.getInstance().getUser(pseudo, motdepasse);
        
        
        if(utilisateur == null) {
        	RequestDispatcher d = request.getRequestDispatcher("./vueJSP/formulaire.jsp");
            d.forward(request, response);
        }else {
        	response.sendRedirect("servletFormulaire");
        	
            HttpSession session = request.getSession(true);
            session.setAttribute("utilisateur", utilisateur);
        }
    }

}