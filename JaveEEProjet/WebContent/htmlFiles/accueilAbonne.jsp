<%@ page import="servlets.servletGereSession" %>
<%@ page import="utilisateur.UtilisateurMediatek" %>
<%@ page import="java.util.*" %>
<%@ page import="document.DocumentsMediatek" %>
<%@ page import="persistance.MediathequeData" %>
<%@ page import="mediatek2022.Document" %>
<%@ page import="mediatek2022.Mediatheque" %>


<%
	UtilisateurMediatek utilisateur = (UtilisateurMediatek) session.getAttribute("utilisateur");
	String pseudo = utilisateur.name(); 
	
	ArrayList<DocumentsMediatek> documents = MediathequeData.consulterDocumentsEmprunt(pseudo);
	List<Document> documentsDispo = Mediatheque.getInstance().tousLesDocumentsDisponibles();
	
	String sb="";
	
	
	for(Document d: documentsDispo){
		
		sb+=d;
	}
	
%>
<!doctype html>
<html lang="fr">
    <head>
        <meta charset="utf-8">
        <title>Accueil</title>
        <link rel="stylesheet" href="css/styleAbonne.css">
        <script src="js/popUp.js"></script>
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
                <p class="navbar-text"><%= pseudo%></p>
            </ul>
        </nav>

          <h1>Que voulez-vous faire ? </h1>
            <div class="container">
                <p class="titreDiv">Effectuer un emprunt </p>
                <table id="listeDocs">  
					<tr><th>ID Document</th><th>Type de document</th><th>Titre du document</th><th>Auteur</th><th>Etat</th></tr>
                    <% for(int i = 0; i < documentsDispo.size()*6; i = i+6){ %> 
                    	<tr><th><%=sb.split("/ ")[i]%></th><th><%= sb.split("/ ")[i+1]%></th><th><%=sb.split("/ ")[i+2]%></th><th><%= sb.split("/ ")[i+3]%></th><td><form action="./servletRendre" method="post"><button type="button" class="btn btn-primary">Emprunter</button></form></td></tr>
					<%}%> 					
							
                </table>
           </div>
           <div class="container">
                <p class="titreDiv">Vos emprunts</p>
                <table id="listeDocs">  
                    <tr><th>ID Document</th><th>Type de document</th><th>Titre du document</th><th>Auteur</th><th>Etat</th></tr>
                    <% for(int i = 0; i < documents.size(); i++){ %> 
						<tr><th><%=documents.get(i).getIdDoc()%></th><th><%= documents.get(i).getType()%></th><th><%=documents.get(i).getTitre() %></th><th><%= documents.get(i).getAuteur()%></th><td><form action="./servletRendre" method="post"><input id="hidden" style="display&#58;none" name=<%DocumentsMediatek d = documents.get(i); %> type="hidden" ><button type="button" class="btn btn-success">Rendre</button></form></td></tr>
					<%}%> 
                </table>
           </div>
    </body>
</html>