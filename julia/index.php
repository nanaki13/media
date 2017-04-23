<?php 

	
// Afficher les erreurs à l'écran
ini_set('display_errors', 1);
// Enregistrer les erreurs dans un fichier de log
//ini_set('log_errors', 1);
// Nom du fichier qui enregistre les logs (attention aux droits à l'écriture)
//ini_set('error_log', dirname(__file__) . '/log_error_php.txt');
// Afficher les erreurs et les avertissements
// error_reporting(e_all);
include 'autoload.php';
include 'function.php';

//

try{
  $conf = new Config(getPage(),getSubPage());
  $obj1 = new Request($_SERVER);
  $obj1->process($conf->getConfig());
  
}catch(Exception $e){
  if($e->getMessage() == "404"){
      echo "404";
      http_response_code(404);
  }else{
    throw $e;
  }
}

//print_r($_SERVER);
//echo "page = ". $_GET['page']."</br>";
//echo "subPage = ".$_GET['subPage']."</br>";



?>





