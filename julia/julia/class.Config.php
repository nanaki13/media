<?php 

class Config{
  private $media;
  private $media2;
  private $gallery ;
  private $config ;
  private $page;
  private $parent;
  public function __construct($page,$subPage){
	    
    if(isset( $subPage)){
      $this->parent = $page;
      $this->page = $subPage;
    }else{
		$this->page = $page;
    }
	    
    $db = new SQLite3("julia.db",SQLITE3_OPEN_READWRITE);
	    
    $havRes = $this->findPage($db);
	
	
	if(!$havRes){
  		throw new Exception('404');
	}
}

	
		
	
	
	function getConfig(){
		return $this->config;
	}
	public function findPage($db){

		try{
			
			
			$this->page = str_replace("-"," ", sql_format( $this->page));

			$dao = new Dao($db);
			
			if(getPage() == 'update_image'){
				$img = new ImageGallery($_POST);
				$dao->save($img);
				header('Location: /'.$_POST['page_red']);
				$_GET['page'] = $_POST['page_red'];
				$this->page = str_replace("-"," ", sql_format( $_GET['page']));
			}elseif(getPage() == 'upload_form'){
				require 'upload.php';
			}
			$this->config = $dao->getBasePageConfig($this->page,$this->parent);
			
			$havRes = false;
			if($this->config != false){
				
				if($this->page == "login"){
					
					if(!isLogged() && isset($_POST["login"])){
						$loginName = $_POST["login"];
						$pass = $_POST["password"];
						
						$result2 = $db->query("select name, password from user where name = '".strtolower($loginName)."' and password = '".$pass."'" );
						if($res = $result2->fetchArray(SQLITE3_ASSOC)){
							
							$user = new User($res);
							
							$_SESSION['logged'] = true;
							header('Location: index.php');
						
						}else{
							echo 'mauvais user ou mdp';
						}
					}elseif (isLogged() && isset($_POST["logout"])){
						unset($_SESSION['logged']);
						header('Location: index.php');
					}
					
				}elseif($this->page == "creation_theme"){
					if(isset($_POST['name'])){
						$dao->createTheme($_POST['name']);
					}	
				}elseif($this->page == "admin"){
					$this->config['subMenuItems'] = array(
							array("ref" => "/images", "name" => "images"),
							array("ref" => "/upload", "name" => "upload"),
							array("ref" => "/creation_theme", "name" => "création de theme")
							
					);
				}elseif($this->page == "createArtFromPath"){
					$themes = $dao->getAllThemes();
					$this->config['themes'] = $themes;
				}
				if($this->page == "images"){
					$all_media = $dao->getAllOeuvres();	
					$this->config['all_media_map'] = [];
					foreach ($all_media as $m){
						
						$this->config['all_media_map'][$m->get_path()] = true;
					}
				}
				if($this->config['haveGallery']){
					
					$this->config['gallery'] = $dao->getOeuvres($this->page);
				
				}
				if($this->config['haveMenu']){
					$this->config['menuItems']  = array(array("ref" => "/accueil", "name" => "Acceuil","img_path" => "/rsc/img/fleur_1.jpg" ));
					$result2 = $db->query("select * from theme ");
					while($res = $result2->fetchArray(SQLITE3_ASSOC)){
						$this->config['menuItems'][] = array("ref" => "/".str_replace(" ","-",$res['name'])."", "name" => ucfirst($res['name']) );
					}
					$result2->finalize();
					$this->config['menuItems'][] = array("ref" => "/apropos", "name" => "A propos" );
						
					$this->config['menuItems'][] =array("ref" => "/contact", "name" => "Contact" );
					if(isLogged()){
						$this->config['menuItems'][] =array("ref" => "/admin", "name" => "admin" );

					}
				}
				
		
				$result2 = $db->query("select * from page_css where page = '". $this->page."' or page = 'DEFAULT'");
				while($res2 = $result2->fetchArray(SQLITE3_ASSOC)){
					$this->config['cssItems'][] = array("ref" => "/rsc/css/".$res2['css']);
		
				}
				$result2->finalize();
				$result2 = $db->query("select * from page_js where page = '". $this->page."'or page = 'DEFAULT' order by order_js");
				while($res2 = $result2->fetchArray(SQLITE3_ASSOC)){
					$this->config['javascript'][] = array("ref" => "/rsc/js/".$res2['js']);
		
				}
				$result2->finalize();
		
			}

			return $this->config != false;
			 
			//print_r($this->config);
		}finally{
			if(isset($db)){
				$db->close();
			}
			 
		}
	}
}
?>
