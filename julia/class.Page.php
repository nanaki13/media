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
	if(!$this->login) warp('div','id=left');
	if($this->haveTitleSection){
		$local = $this->titleSection;
		require('title.php');	
	}
	if($this->haveMenu){
		$menuItems = $this->menuItems;
		require('menu.php');	
	}
	if(!$this->login) endwarp('div');
	if(!$this->login && $this->haveBackground) warp('div','id=right');
	if($this->haveSubMenu){
		$subMenuItems = $this->subMenuItems;
		require('subMenu.php');		
	}
	if($this->haveBackground){
		require('background.php');	
	}
	if( isset($this->gallery)){
		$local = $this->gallery;
		require('gallery.php');	
		require('slider.php');
	}
	if(!$this->login && $this->haveBackground) endwarp('div');
	
	if( isset($this->login) && $this->login){
		
		require('login.php');
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


    public function __construct($config)
    {
	c($this,$config);
    }


	public function buildContent(){
		$page = $this;
		include ('template.php');
		} 
}

?>

