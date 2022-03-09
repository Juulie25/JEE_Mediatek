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


/**
 * Servlet implementation class servletRendre
 */
@WebServlet("/servletRendre")
public class servletRendre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletRendre() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String docARendre = request.getParameter("nomDocARendre");
		
		int numDocARendre = Integer.parseInt(docARendre);
		
		Document d = Mediatheque.getInstance().getDocument(numDocARendre);
		
		d.retour();
	     
	    RequestDispatcher disp = request.getRequestDispatcher("./vueJSP/accueilAbonne.jsp");
	    disp.forward(request, response);
	}

}
