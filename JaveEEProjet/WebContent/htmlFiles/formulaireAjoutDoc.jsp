
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Bienvenue à la Médiatek</title>
  <link rel="stylesheet" href="css/styleAjoutDoc.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
	
    <h1 class="txt"><u>Ajout de document à la Médiatek</u></h1>

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
		
            <button type="Ajouter" class="btn btn-primary" id="btnAjout" data-popup-ref="ajoutSuccess">Ajouter</button>
        </div>
    </form>

    <div class="popup" data-popup-id="ajoutSuccess">
        <div class="popup-content">
             <h2>Nouveau document </h2>
             <p>Vous avez bien ajouté à la Mediatek: </p>
             <p>Harry Potter JK Rowling</p>
             <button type="button" class="btn cancel" data-dismiss-popup>Fermer</button>
        </div>
    </div>


</body>
</html>