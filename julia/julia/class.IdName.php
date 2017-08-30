<?php 
class IdName extends O
{
  private $id;
  private $name;
	
  public function get_id(){
  	return $this->id ;
  }
  
  public function id($id){
  	 $this->id = $id;;
  }
  public function get_name(){
  	return $this->name ;
  }
  
  public function name($name){
  	$this->name = $name;
  }

}

?>

