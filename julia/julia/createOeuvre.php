<?php
$fv = array( 'name' =>  $_POST['title']);
$id = $dao->create('image',$fv,'id','name');
$fv = array( 
		'image_key' =>  $id,
		'height' =>  -1,
		'width' =>  1200,
		'path' =>  str_replace ( "/rsc" , "" ,$_POST['image_path']  )
);
$dao->create('image_path',$fv);
$fv = array(
'title'	=> $_POST['title'],
'tech_code'	=> $_POST['technique'],
'date'	=> $_POST['date'],
'description'	=> $_POST['desciption'],
'theme_key'	=> $_POST['theme'],
'image_key'	=> $id,
'dimension'	=> $_POST['dimension']
		);
$id = $dao->create('oeuvre',$fv,'id','title');
$fv = array(
		'oeuvre_key'	=> $id,
		'row'	=> 1,
		'column'	=> 1
);
$id = $dao->create('oeuvre_position',$fv);
