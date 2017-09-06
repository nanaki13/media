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
	public  function createPosition($img){
	
		$sql = "insert into oeuvre_position (oeuvre_key,row,column) values (".$img->get_id().",".$img->get_row().",".$img->get_column().")";
		$this->db->exec($sql);
	}
	public function save($img){
		$sql = "update oeuvre set 
				title = '".sql_format($img->get_title())."'
				,description = '".sql_format($img->get_description())."'
				,date = '".sql_format($img->get_date())."' 
				,dimension = '".sql_format($img->get_dimension())."' 		
				where id = ".$img->get_id();
	
		$this->db->exec($sql);
		if($img->get_row() != '' && $img->get_column() != ''){
			$getSameSql = "select * from oeuvre_position where row = ".$img->get_row()." and column = ".$img->get_column()." and oeuvre_key <> ".$img->get_id();
			$mePositionSql = "select * from oeuvre_position where oeuvre_key = ".$img->get_id();
			$imageGallerySame = null;
			$imageGalleryMe = null;
			$result2 = $this->db->query($mePositionSql);
			if($res = $result2->fetchArray(SQLITE3_ASSOC)){
				$imageGalleryMe  = new ImageGallery($res	);
				$this->updatePosition($img);
			}else{
				$this->createPosition($img);
			}
			$result = $this->db->query($getSameSql);
			if($imageGalleryMe != null && $res = $result->fetchArray(SQLITE3_ASSOC)){
				$imageGallerySame  = new ImageGallery($res);
				$imageGallerySame->id($res['oeuvre_key']);
				$imageGallerySame->row($imageGalleryMe->get_row());
				$imageGallerySame->column($imageGalleryMe->get_column());
				$this->updatePosition($imageGallerySame);
				
			}	
			
			
		}
		
		
	}
	//return the theme id or -1 if the theme exists
	public  function createTheme($theme){
		$sql = "select id from theme where name = '".sql_format($theme)."'";
		$result2 = $this->db->query($sql);
		if($res = $result2->fetchArray(SQLITE3_ASSOC)){
			return -1;
		}else{
			$sql = "insert into theme (name) values ('".sql_format($theme)."')";
			$this->db->exec($sql);

			$sql = "select id from theme where name = '".sql_format($theme)."'";
			$result2 = $this->db->query($sql);
			if($res = $result2->fetchArray(SQLITE3_ASSOC)){
				return $res['id'];
			}		
			
		}
	}
	public function getOeuvres($theme){
		$sql = "select 
				oeuvre.id	,
	oeuvre.title	,
	oeuvre.tech_code	,
	oeuvre.date	,
	oeuvre.description	,
	oeuvre.theme_key	,
	oeuvre.image_key	,
	oeuvre.dimension ,
	theme.name,
	image_path.path,
				row,column
				
				from oeuvre
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
		$sql = "select oeuvre.id	,
	oeuvre.title	,
	oeuvre.tech_code,
	oeuvre.date	,
	oeuvre.description	,
	oeuvre.theme_key	,
	oeuvre.image_key	,
	oeuvre.dimension ,
	theme.name,
	image_path.path,
				row,column
				
				from oeuvre
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
	
	public function getAllThemes(){
		$sql = "select * from theme";
		$ret= array();
		$result2 = $this->db->query($sql);
	
	
		while($res = $result2->fetchArray(SQLITE3_ASSOC)){
	
			$imageGallery  = new Theme($res);
			
	
			$ret[]=$imageGallery;
	
	
		}
		$result2->finalize();
		return $ret;
	}
	
	public function getAllTechnique(){
		$sql = "select * from technique";
		$ret= array();
		$result2 = $this->db->query($sql);
	
	
		while($res = $result2->fetchArray(SQLITE3_ASSOC)){
	
			$imageGallery  = new Theme($res);
				
	
			$ret[]=$imageGallery;
	
	
		}
		$result2->finalize();
		return $ret;
	}
	
	public function getAll($table){
		$sql = "select * from ".$table;
		$ret= array();
		$result2 = $this->db->query($sql);
	
	
		while($res = $result2->fetchArray(SQLITE3_ASSOC)){
			$class = ucfirst($table);
			$imageGallery  = new $class($res);
			
	
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
						"haveGallery" =>  $res['haveGallery'],
						"haveSubMenu" =>  $res['haveSubMenu'],
						"title" => $res['title']. (isset($res['subTitle']) ? ' - '.$res['subTitle'] : ''),
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
	
	public function create($table,&$fields_values,$field_return = NULL,$on = NULL){
		foreach ($fields_values as $key => $value){
			$fields_values[$key] = sql_format($value);
		}
		$insert = Dao::build_insert($table,$fields_values);
		$sql = $insert;
		$this->db->exec($sql);
		if(isset($field_return)){
			$sql = "select ".$field_return." from ".$table." where ".$on." = '".$fields_values[$on] ."'";
			$result2 = $this->db->query($sql);
			if($res = $result2->fetchArray(SQLITE3_ASSOC)){
				return $res[$field_return];
			}
		}
		
	}
	
	public static function build_insert($table,$fields_values){
		
		$sql  = "insert into ".$table."('".implode(array_keys($fields_values),"','")."') values ('".implode($fields_values,"','")."')";
		echo $sql;
		return $sql;
	}
	
}
?>
