<?php 

  function getPage()
  {
    return isset($_GET['page']) && $_GET['page'] != '' ? $_GET['page'] : 'accueil';
  }
  
   function getSubPage()
  {
    return isset($_GET['subPage']) && $_GET['subPage'] != '' ? $_GET['subPage'] : null;
  }
  
  function c($obj,$data){
     foreach($data as $field=>$value){
      $obj->$field($value);
    }
  }
  
  function toJson($obj){
   
    $json = '{';
    $ar = (array) $obj;
     $count= count($ar)	;
     $i = 0;
    foreach($ar as $field=>$value){
      
      $json .=  preg_replace("/[^A-Za-z0-9 ]/",'',$field). ' : "'.$value.'"';
      if($i != $count -1){
	   $json = $json .',';
      }
      $i++;
    }
     $json = $json .'}';
     
    return $json;
  }
  
  function replaceSpace($ch){
     return str_replace(' ', '_', $ch);
  } 
  
  function equalOrNullSQL($t){
    if(isset($t)){
      return " = '".sql_format($t)."'";
    }else{
      return "is null";
    }
  
  }
  
  function sql_format($t){
   return str_replace("'","''",$t);
  
  }
  
  function array2ul($array) {
    $out = "<ul>";
    foreach($array as $key => $elem){
        if(!is_array($elem)){
                $out .= "<li><span>$key:[$elem]</span></li>";
        }
        else $out .= "<li><span>$key</span>".array2ul($elem)."</li>";
    }
    $out .= "</ul>";
    return $out; 
}
?>