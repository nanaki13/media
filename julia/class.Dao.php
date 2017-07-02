<?php 

class Dao{
	private $db;
	
	public function __construct($db){
		$this->db = $db;		
	}
	
	public  function updatePosition($img){
		$sql = "update oeuvre_position set row = ".$img->get_row().",column = ".$img->get_column()." where oeuvre_key = ".$img->get_id();
		$this->db->exec($sql);
	}
	public function save($img){
		$sql = "update oeuvre set 
				title = '".sql_format($img->get_title())."'
				,description = '".sql_format($img->get_description())."'
				,date = '".sql_format($img->get_date())."' 
				,dimension = '".sql_format($img->get_dimension())."' 		
				where id = ".$img->get_id();
		//echo $sql;
		$this->db->exec($sql);
		$getSameSql = "select * from oeuvre_position where row = ".$img->get_row()." and column = ".$img->get_column();
		$mePositionSql = "select * from oeuvre_position where oeuvre_key = ".$img->get_id();
		$result = $this->db->query($getSameSql);
		if($res = $result->fetchArray(SQLITE3_ASSOC)){
			$imageGallerySame  = new ImageGallery($res);
			$result2 = $this->db->query($mePositionSql);
			$imageGalleryMe  = new ImageGallery($result2->fetchArray(SQLITE3_ASSOC));
			$imageGallerySame->row($imageGalleryMe->get_row());
			$imageGallerySame->column($imageGalleryMe->get_column());
			$this->updatePosition($imageGallerySame);
		}
		$this->updatePosition($img);
	}
	
	public function getOeuvres($theme){
		$sql = "select * from oeuvre
		join theme on oeuvre.theme_key = theme.id
		join image on image.id = oeuvre.image_key
		join image_path on image.id = image_path.image_key and width=1200
		left join oeuvre_position on oeuvre_position.oeuvre_key = oeuvre.id
		where theme.name='".sql_format($theme)."' order by column,row";
		$ret= array();
		$result2 = $this->db->query($sql);
		
		
		while($res = $result2->fetchArray(SQLITE3_ASSOC)){
		
			$imageGallery  = new ImageGallery($res);
		
			
			$ret[]=$imageGallery;
		
		
		}	
		$result2->finalize();
		return $ret;
	}
	
	public function getAllOeuvres(){
		$sql = "select * from oeuvre
		join theme on oeuvre.theme_key = theme.id
		join image on image.id = oeuvre.image_key
		join image_path on image.id = image_path.image_key and width=1200
		left join oeuvre_position on oeuvre_position.oeuvre_key = oeuvre.id";
		$ret= array();
		$result2 = $this->db->query($sql);
	
	
		while($res = $result2->fetchArray(SQLITE3_ASSOC)){
	
			$imageGallery  = new ImageGallery($res);
	
				
			$ret[]=$imageGallery;
	
	
		}
		$result2->finalize();
		return $ret;
	}
	
	public function getBasePageConfig($page,$parent){
		$result = null;
		
		try{
			$sql = "select * from page_config where name = '".sql_format( $page)."' and parent ".equalOrNullSQL($parent);
			
			$result = $this->db->query($sql);
			$havRes = false;
			if($res = $result->fetchArray(SQLITE3_ASSOC)){
				$havRes = true;
				$config =
				array(
						"name" => $page,
						"haveMenu" => $res['haveMenu'],
						"haveBackground" =>  $page == 'accueil',
						"haveSubMenu" =>  $res['haveSubMenu'],
						"title" => $res['title'].' - '.$res['subTitle'],
						"cssItems" => array ( ),
						"javascript" => array ( ),
						"haveTitleSection" => $res['title'],
						"titleSection" => array ("title" => $res['title'], "subTitle" => $res['subTitle'])
				);
				return $config;
			}else{
				return false;
			}	
		}finally{ 
			if(isset($result)){
				$result->finalize();
			}
		}
		
	}
}
?>
