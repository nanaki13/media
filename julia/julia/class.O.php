<?php
class O {
	
	
	public function __construct($data){
		c($this,$data);
	
	}
	
	public function toJson(){
		return toJson($this);
	}
	
	public function toArray(){
		$ar = [];
		foreach ((array) $this as $k=>$v){
			$ar[preg_replace("/[^A-Za-z0-9 ]/",'',str_replace(get_class($this),'',$k))] = $v;
		}
		return $ar;
	}
}