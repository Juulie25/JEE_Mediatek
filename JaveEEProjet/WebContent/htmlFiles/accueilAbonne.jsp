<%@ page import="servlets.servletGereSession" %>
<%@ page import="utilisateur.UtilisateurMediatek" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="document.DocumentsMediatek" %>
<%@ page import="persistance.MediathequeData" %>

<%
	UtilisateurMediatek utilisateur = (UtilisateurMediatek) request.getAttribute("utilisateur");
	String pseudo = utilisateur.name();
	
	ArrayList<DocumentsMediatek> documents = MediathequeData.consulterDocumentsEmprunt(pseudo);
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
                <p class="navbar-text"><%= pseudo%></p>
            </ul>
        </nav>
          <h1>Que voulez-vous faire ? </h1>
            <div class="container">
                <p class="titreDiv">Effectuer un emprunt </p>
                <button type="button" class="btn btn-primary" id="emprunter">Voir tous les documents</button>
           </div>
           <div class="container">
                <p class="titreDiv">Vos emprunts</p>
                <table id="listeDocs">  
                    <tr><th>Type de document</th><th>Titre du document</th><th>Auteur</th><th>Etat</th></tr>
                    <% for(int i = 0; i < documents.size(); i++){ %> 
						<tr><th><%=documents.get(i).getType() %></th><th><%=documents.get(i).getTitre() %></th><th><%= documents.get(i).getAuteur()%></th><td><button type="button" class="btn btn-success">Rendre</button></td></tr>
					<%}%> 
                </table>
           </div>

           <div class="popup" data-popup-id="rendu">
               <div class="popup-content">
                    <h2>Merci pour votre retour</h2>
                    <p>Vous avez rendu le document suivant : </p>
                    <p>La petite sir�ne de Dinsey</p>
                    <button type="button" class="btn cancel" data-dismiss-popup>Fermer</button>
               </div>
           </div>
           <script>
                let btnPopup = document.querySelectorAll("[data-popup-ref]");

                btnPopup.forEach( btn => {
                    btn.addEventListener('click', activePopup );

                    function activePopup(){
                        let popupId = btn.getAttribute('data-popup-ref');
                        let popup = document.querySelector(`[data-popup-id='${popupId}']`);
                        let popupContent = popup.querySelector('.popup-content');
                        let popupClose = popup.querySelectorAll('[data-dismiss-popup]');

                        popupClose.forEach(btn => {
                            btn.addEventListener("click", onPopupClose);
                        });

                        popup.classList.add('active');
                        popupContent.classList.add('active');

                        function onPopupClose(){
                            popup.classList.remove('active');
                            popupContent.classList.remove('active');
                        };
                    }
                });
           </script>
    </body>
</html>