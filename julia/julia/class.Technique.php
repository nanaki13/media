<?php 
class Technique extends O
{
  private $name;
  private $code;
	
  public function get_name(){
  	return $this->name ;
  }
  
  public function name($name){
  	 $this->name = $name;;
  }
  public function get_code(){
  	return $this->code ;
  }
  
  public function code($code){
  	$this->code = $code;
  }
}

?>

