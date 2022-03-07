<%@ page import="servlets.servletGereSession" %>
<%@ page import="mediatek2022.Utilisateur" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.*" %>
<%@ page import="mediatek2022.Document" %>
<%@ page import="mediatek2022.Mediatheque" %>



<%
	Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
	String pseudo = utilisateur.name(); 
	
	List<Document> documents = Mediatheque.getInstance().consulterDocuments();
	
	String sb="";
	
	for(Document d: documents){
		sb+=d;
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
                <p class="titreDiv">Ajouter un document </p>
                
                <form action="./servletFormulaire" method ="post"><button type="submit" class="btn btn-primary">Ajouter</button></form>
                
           </div>
           <div class="container">
                <p class="titreDiv">Documents de la Mediatek</p>
                <table id="listeDocs"> 
                    <tr><th>ID Document</th><th>Type de document</th><th>Titre du document</th><th>Auteur</th><th>Etat</th></tr>
					<%int j = 0;%>
					<% for(int i = 0; i < documents.size()*6; i = i+6){ %> 
					 <% if(documents.get(j).disponible()) { %> 
					 	<tr><th><%=sb.split("/ ")[i]%></th><th><%= sb.split("/ ")[i+1]%></th><th><%=sb.split("/ ")[i+2]%></th><th><%= sb.split("/ ")[i+3]%></th><td><button type="button" class="btn btn-success" disabled>Disponible</button></td></tr>
					 	<%j++; %>
					 <%}else{%>
					 	<tr><th><%=sb.split("/ ")[i]%></th><th><%= sb.split("/ ")[i+1]%></th><th><%=sb.split("/ ")[i+2]%></th><th><%= sb.split("/ ")[i+3]%></th><td><button type="button" class="btn btn-danger" disabled>Emprunté</button></td></tr>
					 	<%j++; %>
					 <%} %>
					 
					<%}%> 


                </table>
           </div>		   
    </body>
</html>