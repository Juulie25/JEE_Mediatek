<%@ page import="servlets.servletGereSession" %>
<%@ page import="utilisateur.UtilisateurMediatek" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="document.DocumentsMediatek" %>
<%@ page import="persistance.MediathequeData" %>


<%
	UtilisateurMediatek utilisateur = (UtilisateurMediatek) request.getAttribute("utilisateur");
	String pseudo = utilisateur.name();
	
	ArrayList<DocumentsMediatek> documents = MediathequeData.consulterDocuments();
	
%>

<!doctype html>
<html lang="fr">
    <head>
        <meta charset="utf-8">
        <title>Accueil</title>
        <link rel="stylesheet" href="css/styleBibliothécaire.css">
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
                <p class="navbar-text"><%=pseudo %></p>
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
					<% for(int i = 0; i < documents.size(); i++){ %> 
						<% if(documents.get(i).disponible()) { %> 
					 		<tr><th><%=documents.get(i).getType() %></th><th><%=documents.get(i).getTitre() %></th><th><%= documents.get(i).getAuteur()%></th><td><button type="button" class="btn btn-success" disabled>Disponible</button></td></tr>
					 	<%}else{%>
					 		<tr><th><%=documents.get(i).getType() %></th><th><%=documents.get(i).getTitre() %></th><th><%= documents.get(i).getAuteur()%></th><td><button type="button" class="btn btn-danger" disabled>Emprunté</button></td></tr>
					 	<%} %>
					<%}%> 
                </table>
           </div>		   
    </body>
</html>