<?php 
class Page implements PageI
{
	private $title;
	private $haveMenu = true;
	private $haveBackground = true;
	private $menuItems = array (
     		array("ref" => "index.php?page=gravure", "name" => "gravure" ),
		array("ref" => "index.php?page=peinture", "name" => "peinture" ),
		array("ref" => "index.php?page=autre", "name" => "autre" )
	);
	private $cssItems = array (
     array("ref" => "rsc/css/main.css"));
	

 	public function getTitle()
    {
	return $this->title;
    }
	public function haveMenu()
    {
	return $this->haveMenu;
    }

	public function echoBody()
    {
	if($this->haveMenu){
		$menuItems = $this->menuItems;
		require('menu.php');	
	}
	if($this->haveBackground){
		require('background.php');	
	}
	
    }
	public function echoCss()
    {
	
		$cssItems = $this->cssItems;
		require('css.php');	

	
    }


    public function __construct($config)
    {
	$configPage = $config[$_GET['page']];
 	$this->title =$configPage['haveMenu'];
	$this->title =$configPage['haveBackground'];
	$this->title =$configPage['title'];
    }


	public function buildContent(){
		$page = $this;
		include ('template.php');
		} 
}

?>

