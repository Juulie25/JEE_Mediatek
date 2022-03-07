<%@ page import="mediatek2022.Utilisateur" %>

<%
	Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
	String pseudo = utilisateur.name(); 
	
%>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Ajout de document Mediatek2022</title>
  <link rel="stylesheet" href="css/styleAjoutDoc.css">
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
	
	
    <h1 class="txt"><u>Ajout de document à la Mediatek</u></h1>

    <form action="./servletAjoutDoc" method="post">
        <div id="formulaire">
		<p class = "txt">Quel type de document voulez vous ajouter ?</p>
		
			<div>
			  <input type="radio" id="caseDVD" name="groupe1" value="1">
			  <label for="caseDVD">DVD</label>
			
			  <input type="radio" id="caseCD" name="groupe1" value="2">
			  <label for="caseCD">CD</label>

			
			  <input type="radio" id="caseLivre" name="groupe1" value="3">
			  <label for="caseLivre">Livre</label>
			</div>		
		
		
            <div class="form-group">
                <label for="labelTitre" class="txt">Titre</label>
                <input class="form-control" id="titre" name="titre" placeholder="Entrez le titre" required>
            
            </div>

            <div class="form-group">
                <label for="labelAuteur" class="txt">Auteur</label>
                <input class="form-control" id="auteur" name="auteur"  placeholder="Entrez l'auteur du document" required>
            
            </div>
            
			 <p class = "txt">Est-ce pour adulte ?</p>           
			<div>
			  <input type="radio" id="oui" name="groupe2" value="oui">
			  <label for="oui">Oui</label>
			
			  <input type="radio" id="non" name="groupe2" value="non">
			  <label for="non">Non</label>

			</div>		         
		
            <button type="Ajouter" class="btn btn-primary" id="btnAjout" >Ajouter</button>
        </div>
    </form>
</body>
</html>