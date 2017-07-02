<?php 
class Page 
{
  private $title;
  private $haveMenu;
  private $haveBackground;
  private $haveTitleSection;
  private $haveSubMenu;
  private $titleSection ;
  private $menuItems ;
  private $subMenuItems ;
  private $cssItems;
  private $gallery;
  private $javascript;
  private $login;
  private $name;
   private $all_media_map;
  
   public function all_media_map($all_media_map){
   	$this->all_media_map = $all_media_map;
   }
   public function get_all_media_map(){
   	return $this->all_media_map;
   }
  public function name($name){
  	$this->name = $name;
  }
  public function get_name(){
  	return $this->name;
  }
  
  public function login($login){
  	$this->login = $login;
  }
  public function get_login(){
  	return $this->login;
  }
  
   public function haveSubMenu($haveSubMenu){
      $this->haveSubMenu = $haveSubMenu;
  }
   public function get_haveSubMenu(){
    return $this->haveSubMenu;
  }
    public function javascript($javascript){
      $this->javascript = $javascript;
  }
   public function get_javascript(){
    return $this->javascript;
  }
   public function haveMenu($haveMenu){
      $this->haveMenu = $haveMenu;
  }
  public function get_haveMenu(){
      return $this->haveMenu;
  }
  public function title($title){
      $this->title = $title;
  }
  public function get_title(){
    return $this->title;
  }
  public function haveBackground($haveBackground){
      $this->haveBackground = $haveBackground;
  }
   public function get_haveBackground(){
    return $this->haveBackground;
  }
  public function haveTitleSection($haveTitleSection){
      $this->haveTitleSection = $haveTitleSection;
  }
   public function get_haveTitleSection(){
    return $this->haveTitleSection;
  }
   public function titleSection($titleSection){
      $this->titleSection = $titleSection;
  }
   public function get_titleSection(){
    return $this->titleSection;
  }
  public function gallery($gallery){
      $this->gallery = $gallery;
  }
   public function get_gallery(){
    return $this->gallery;
  }
  public function cssItems($cssItems){
      $this->cssItems = $cssItems;
  }
  public function get_cssItems(){
    return $this->cssItems;
  }
   public function menuItems($menuItems){
      $this->menuItems = $menuItems;
  }
   public function get_menuItems()
  {
    return $this->menuItems;
  }
  
   public function subMenuItems($subMenuItems){
      $this->subMenuItems = $subMenuItems;
  }
   public function get_subMenuItems()
  {
    return $this->subMenuItems;
  }

	public function echoBody()
    {
		if($this->name == 'image_edit' ||$this->name == 'login'){
			if($this->name == 'login'){
				require('login.php');
			}else{
				require('image_edit.php');
			}
		
		}else{
			warp('div','id=left');
			if($this->haveTitleSection){
				$local = $this->titleSection;
				require('title.php');
			}
			if($this->haveMenu){
				$menuItems = $this->menuItems;
				require('menu.php');
			}
			endwarp('div');
			if( $this->haveBackground) warp('div','class=right');
			if($this->haveSubMenu){
				$subMenuItems = $this->subMenuItems;
				require('subMenu.php');
			}
			if($this->haveBackground){
				require('background.php');
			}
			if($this->name == 'images'){
				$all_media_map = $this->all_media_map;
				require('all_image.php');
			}
			
			if( isset($this->gallery)){
				$local = $this->gallery;
				$get_method = ['title','description','date','dimension','row','column'];
				require('gallery.php');
				require('slider.php');
			}
			if($this->name == 'upload'){
				require('upload_form.php');
			}
			if( $this->haveBackground) endwarp('div');
		}
	


	
    }
	public function echoCss()
    {
	
		$cssItems = $this->cssItems;
		require('css.php');	

	
    }
	public function echoJs()
    {
	
		if( isset($this->javascript)){
			$local = $this->javascript;
			require('js.php');	
		}	

	
    }


    public function __construct($config){
		c($this,$config);
    }


	public function buildContent(){
		$page = $this;
		include ('template.php');
	} 
}

?>

