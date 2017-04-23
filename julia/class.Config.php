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
	    
	
	  
	try{
	$this->page = str_replace("-"," ", sql_format( $this->page));
	    $sql = "select * from page_config where name = '".sql_format( $this->page)."' and parent ".equalOrNullSQL($this->parent);
	//  echo $sql;
	  $result = $db->query($sql);
	  $havRes = false;
	    while($res = $result->fetchArray(SQLITE3_ASSOC)){ 
		$havRes = true;
		$this->config = 
		array(
			"haveMenu" => $res['haveMenu'], 
			"haveBackground" =>  $this->page == 'accueil',
			"haveSubMenu" =>  $res['haveSubMenu'],
			"title" => $res['title'].' - '.$res['subTitle'],
			"cssItems" => array ( ),
			"javascript" => array ( ),
			"haveTitleSection" => $res['title'],
			"titleSection" => array ("title" => $res['title'], "subTitle" => $res['subTitle'])
		);
		if( $this->page != "accueil" && !isset($this->parent)){
		    $result2 = $db->query("select technique.code tech_code,image.name image_name,theme.name theme_name from technique 
		      join technique_theme on technique.code = technique_theme.tech_code 
		      join image on image.id = image_key
		      join theme on theme.id = technique_theme.theme_key where technique.name = '". $this->page."'");
		      $this->config['subMenuItems']= array();
		    while($res = $result2->fetchArray(SQLITE3_ASSOC)){ 

		      $this->config['subMenuItems'][]=array("ref"=>str_replace(" ","-", $res['theme_name']),"name"=>$res['theme_name'],"img"=>replaceSpace($res['image_name']));
		      
		    }
		    $result2->finalize();

		}else{
		$sql = "select * from oeuvre 
		join theme on oeuvre.theme_key = theme.id 
		join image on image.id = oeuvre.image_key  
		join image_path on image.id = image_path.image_key and width=1200
		where theme.name='".$this->page."'";
		$this->config['gallery']= array();
		$result2 = $db->query($sql);
		     

		while($res = $result2->fetchArray(SQLITE3_ASSOC)){ 
		   
		      $imageGallery  = new ImageGallery();
		      
		      c($imageGallery,$res);
		      $this->config['gallery'][]=$imageGallery;
		      $this->config['javascript'] ;
		      
		    }
		}
		$this->config['menuItems']  = array(array("ref" => "/accueil", "name" => "Acceuil" ));
		  $result2 = $db->query("select * from technique ");
		while($res = $result2->fetchArray(SQLITE3_ASSOC)){ 
		    $this->config['menuItems'][] = array("ref" => "/".$res['name']."", "name" => ucfirst($res['name']) );
		}
		$result2->finalize();
		$this->config['menuItems'][] = array("ref" => "/apropos", "name" => "A propos" );
			    
		$this->config['menuItems'][] =array("ref" => "/contact", "name" => "Contact" );
		    
		$result2 = $db->query("select * from page_css where page = '". $this->page."' or page = 'DEFAULT'");
		while($res2 = $result2->fetchArray(SQLITE3_ASSOC)){ 
		    $this->config['cssItems'][] = array("ref" => "/rsc/css/".$res2['css']);
		
		}
		$result2->finalize();
		$result2 = $db->query("select * from page_js where page = '". $this->page."' order by order_js");
		while($res2 = $result2->fetchArray(SQLITE3_ASSOC)){ 
		    $this->config['javascript'][] = array("ref" => "/rsc/js/".$res2['js']);
		
		}
		$result2->finalize();
		
	    }
	    $result->finalize();
	  
	//print_r($this->config);
	}finally{
	  if(isset($db)){
	    $db->close();
	  }
	  
	}
	
	if(!$havRes){
	  throw new Exception('404');
	}
    }

	
		
	
	
	function getConfig(){
		return $this->config;
	}
}
?>
