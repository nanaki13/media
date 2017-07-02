<?php 
class ImageGallery extends O
{
private $id;
private $title;
private $tech_code;
private $date;
private $description;
private $theme_key;
private $image_key;
private $dimension;
private $name;
private $height;
private $width;
private $path;
private $row;
private $column;

public function id($id){
	$this->id = $id;
	}
public function title($title){
	$this->title = $title;
	}
public function tech_code($tech_code){
	$this->tech_code = $tech_code;
	}
public function date($date){
	$this->date = $date;
	}
public function description($description){
	$this->description = $description;
	}
public function theme_key($theme_key){
	$this->theme_key = $theme_key;
	}
public function image_key($image_key){
	$this->image_key = $image_key;
	}
public function dimension($dimension){
	$this->dimension = $dimension;
	}
public function name($name){
	$this->name = $name;
	}
public function height($height){
	$this->height = $height;
	}
public function width($width){
	$this->width = $width;
	}
public function path($path){
	$this->path = $path;
	}
public function row($row){
	$this->row = $row;
}
public function column($column){
	$this->column = $column;
}
public function get_id(){
	return $this->id;
	}
public function get_title(){
	return $this->title;
	}
public function get_tech_code(){
	return $this->tech_code;
	}
public function get_date(){
	return $this->date;
	}
public function get_description(){
	return $this->description;
	}
public function get_theme_key(){
	return $this->theme_key;
	}
public function get_image_key(){
	return $this->image_key;
	}
public function get_dimension(){
	return $this->dimension;
	}
public function get_name(){
	return $this->name;
	}
public function get_height(){
	return $this->height;
	}
public function get_width(){
	return $this->width;
	}
public function get_path(){
	return $this->path;
	}
	public function get_row(){
		return $this->row;
	}
	public function get_column(){
		return $this->column;
	}
	
	public function toJson(){
    return toJson($this);
  }
  }
?>

