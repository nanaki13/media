<?php 
class Media
{
  private $title;
  private $technique;
  private $annee;
  private $dimension;
  private $src;
  private $category;
	
  public function category($category){
      $this->category = $category;
  }
  public function get_category(){
      return $this->category;
  }
  public function technique($technique){
      $this->technique = $technique;
  }
  public function get_technique(){
      return $this->technique;
  }
  public function title($title){
      $this->title = $title;
  }
  public function get_title()
  {
    return $this->title;
  }
  public function annee($anne){
      $this->annee = $anne;
  }
   public function get_annee()
  {
    return $this->annee;
  }
  public function dimension($dimension){
      $this->dimension = $dimension;
  }
   public function get_dimension()
  {
    return $this->dimension;
  }
   public function src($src){
      $this->src = $src;
  }
   public function get_src()
  {
    return $this->src;
  }
  public function __construct($data){
     c($this,$data);
  
  }
  
  public function toJson(){
    return toJson($this);
  }

}

?>

