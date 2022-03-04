<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="document.Documents" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="persistance.ConnexionBDD" %>
<%@ page import="java.sql.SQLException" %>



<%
	String url = "jdbc:mysql://localhost:3306/mediatek";
	String user = "root";
	String password = "";
	
	ResultSet docs;
	ArrayList<Documents> documents = new ArrayList<>();
	ArrayList<String> reponseTab = new ArrayList<>();
	
	try {
		docs = ConnexionBDD.consulterDocuments(url, user, password);
		documents = ConnexionBDD.listeDocuments(docs);
	} catch (ClassNotFoundException | SQLException e) {
		System.out.println("Erreur : " + e);;
	} 
	

	for(int i = 0; i < documents.size(); i++){
		if(documents.get(i).isEmprunt()) {
			reponseTab.add("<tr><th>"+ documents.get(i).getType()+"</th><th>"+documents.get(i).getTitre()+"</th><th>"+documents.get(i).getAuteur()+"</th><td><button type=\"button\" class=\"btn btn-danger\" disabled>Emprunte</button></td></tr>");
		}
		else {
			reponseTab.add("<tr><th>"+ documents.get(i).getType()+"</th><th>"+documents.get(i).getTitre()+"</th><th>"+documents.get(i).getAuteur()+"</th><td><button type=\"button\" class=\"btn btn-success\" disabled>Disponible</button></td></tr>");
		}
	}
%>

<!doctype html>
<html lang="fr">
    <head>
        <meta charset="utf-8">
        <title>Accueil</title>
        <link rel="stylesheet" href="css/styleAbonne.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <ul class="nav navbar-nav">
              <li><a href="#" class="navbar-text">Mediatek</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <p class="navbar-text">Ozdemir Ilker</p>
            </ul>
          </nav>
          <h1>Que voulez-vous faire ? </h1>
          <div class="container">
                <p class="titreDiv">Ajouter un document </p>
                <button type="button" class="btn btn-primary" id="ajouter"><a href="formulaireAjoutDoc.html">Ajout de document</a></button>
           </div>
           <div class="container">
                <p class="titreDiv">Documents de la Mediatek</p>
                <table id="listeDocs"> 
                    <tr><th>Type de document</th><th>Titre du document</th><th>Auteur</th><th>Etat</th></tr>
                    	<%for(int i=0; i<reponseTab.size(); i++){%>
                    		<% System.out.println(reponseTab.get(i));%>
                    	<%} %>
                </table>
           </div>		   
    </body>
</html>