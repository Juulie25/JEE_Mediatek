<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Connexion Mediatek2022</title>
  <link rel="stylesheet" href="css/style.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
  <script src="script.js"></script>
</head>
<body>
    <h1 class="txt" style="text-decoration: underline;">Bienvenue à la Mediatek</h1>
    <p class="txt">Pour accéder à  votre espace personnel, merci de vous connecter.</p>

    <form action="./servletGereSession" method ="post">
        <div id="formulaire">
            <div class="form-group">
                <label for="labelPseudo" class="txt">Pseudo</label>
                <input type="text" class="form-control" id="pseudo" name="pseudo" placeholder="Entrez votre pseudo" required>
            
            </div>
            <div class="form-group">
                <label for="labelMotdepasse" class="txt">Mot de passe</label>
                <input type="password" class="form-control" id="motdepasse" name="motdepasse" placeholder="Mot de Passe" required>
            </div>
           
            <button type="submit" class="btn btn-primary">Connexion</button>
        </div>
    </form>
    <p class="txt">La bibliothèque Mediatek vous accueille du lundi au vendredi de 9h à  19h, et le samedi de 10h à 17h.</p>
</body>
</html>