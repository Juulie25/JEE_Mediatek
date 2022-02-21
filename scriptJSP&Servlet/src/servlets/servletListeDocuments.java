package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import document.Documents;
import persistance.ConnexionBDD;

/**
 * Servlet implementation class servletListeDocuments
 */
@WebServlet("/servletListeDocuments")
public class servletListeDocuments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletListeDocuments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:mysql://localhost:3306/mediatek";
		String user = "root";
		String password = "";
		
		ResultSet docs;
		ArrayList<Documents> documents = new ArrayList<>(); 
		
		try {
			docs = ConnexionBDD.consulterDocuments(url, user, password);
			documents = ConnexionBDD.listeDocuments(docs);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erreur : " + e);;
		} 
		
		request.setAttribute("listeDocuments", documents);
		RequestDispatcher d = request.getRequestDispatcher("../WebContent/htmlFiles/accueilBibliothecaire.jsp");
		d.forward(request, response);
	}

}
