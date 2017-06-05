<?php
class User extends O{
	private $name;
	private $password;
	
	public function get_name(){
		return $this->name;
	}
	public function name($name){
		$this->name = $name;
	}
	
	public function get_password(){
		return $this->password;
	}
	public function password($password){
		$this->password = $password;
	}
	
}
?>