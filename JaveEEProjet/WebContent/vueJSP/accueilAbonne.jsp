<%@ page import="servlets.servletGereSession" %>
<%@ page import="mediatek2022.Utilisateur" %>
<%@ page import="java.util.*" %>
<%@ page import="mediatek2022.Document" %>
<%@ page import="mediatek2022.Mediatheque" %>

<%
	Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
	String pseudo = utilisateur.name(); 
	
	List<Document> documentsEmprunt = Mediatheque.getInstance().consulterDocumentsEmprunt(pseudo);
	List<Document> documentsDispo = Mediatheque.getInstance().tousLesDocumentsDisponibles();
	
	String sb="";
	String st="";
	
	
	for(Document d: documentsDispo){
		sb+=d;
	}
	
	for(Document d : documentsEmprunt){
		st+=d; 
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
 	       <ul class="nav navbar-nav navbar-left">
              <li> <p class="navbar-text">Mediatek </p></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
	           	<form action="./servletLogOut" method="post">
	           		<p style="text-align: center;" ><%=pseudo %><p>
	    			<button type="submit button" class="btn btn-light">Deconnexion</button>
				</form>
            </ul>
		</nav>

          <h1>Que voulez-vous faire ? </h1>
            <div class="container">
                <p class="titreDiv">Effectuer un emprunt </p>
                <table id="listeDocs">  
					<tr><th>ID Document</th><th>Type de document</th><th>Titre du document</th><th>Auteur</th><th>Etat</th></tr>
                    <% for(int i = 0; i < documentsDispo.size()*6; i +=6){ %> 
                    	<tr><th><%=sb.split("/ ")[i]%></th><th><%= sb.split("/ ")[i+1]%></th><th><%=sb.split("/ ")[i+2]%></th><th><%= sb.split("/ ")[i+3]%></th><td><form action="./servletEmprunter" method="post"><input id="hidden" style="display&#58;none" name="nomDocAEmprunter" value=<%= sb.split("/ ")[i] %> type="hidden" ><button type="submit button" class="btn btn-primary">Emprunter</button></form></td></tr>
					<%}%> 							
                </table>
           </div>
           <div class="container">
                <p class="titreDiv">Vos emprunts</p>
                <table id="listeDocs">  
                    <tr><th>ID Document</th><th>Type de document</th><th>Titre du document</th><th>Auteur</th><th>Etat</th></tr>
					<% for(int i = 0; i < documentsEmprunt.size()*6; i = i+6){ %> 
                    	<tr><th><%=st.split("/ ")[i]%></th><th><%= st.split("/ ")[i+1]%></th><th><%=st.split("/ ")[i+2]%></th><th><%= st.split("/ ")[i+3]%></th><td><form action="./servletRendre" method="post"><input id="hidden" style="display&#58;none" name="nomDocARendre" value=<%= st.split("/ ")[i] %> type="hidden" ><button type="submit button" class="btn btn-success">Rendre</button></form></td></tr>
					<%}%> 	 
                </table>
           </div>
    </body>
</html>