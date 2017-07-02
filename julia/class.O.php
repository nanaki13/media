<?php
class O {
	
	
	public function __construct($data){
		c($this,$data);
	
	}
	
	public function toJson(){
		return toJson($this);
	}
}