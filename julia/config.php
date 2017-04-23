<?php 

	
 $config = array(
	"accueil" => array(
		"haveMenu" => true, 
		"haveBackground" => true,
		"title" => "Julia Le Corre - Artiste",
		"menuItems" => array (
     		array("ref" => "index.php?page=gravure", "name" => "gravure" ),
		array("ref" => "index.php?page=peinture", "name" => "peinture" ),
		array("ref" => "index.php?page=autre", "name" => "autre" )
		)
	)
);

function getConfig(){
	return $config;
}
?>





