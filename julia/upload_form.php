<form class="right" enctype="multipart/form-data" action="upload" method="post">
  <!-- MAX_FILE_SIZE doit précéder le champ input de type file -->
  
  <!-- Le nom de l'élément input détermine le nom dans le tableau $_FILES -->
  Envoyez ce fichier : <input name="userfile" type="file" />
  <input type="submit" value="Envoyer le fichier" />
  <?php
  if(isset($_FILES['userfile'])){
	$uploaddir = './rsc/img/';
	$uploadfile = $uploaddir . basename($_FILES['userfile']['name']);
	
	echo '<pre>';
	if (move_uploaded_file($_FILES['userfile']['tmp_name'], $uploadfile)) {
		echo "Le fichier est valide, et a été téléchargé
           avec succès. Voici plus d'informations :\n";
	} else {
		echo "Attaque potentielle par téléchargement de fichiers.
          Voici plus d'informations :\n";
	}
	
	echo 'Voici quelques informations de débogage :';
	print_r($_FILES);	
	
}
?>
</form>
