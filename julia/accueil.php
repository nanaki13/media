<?php 


?>



<?xml version="1.0" encoding="UTF-8"?>

<!-- This Source Code Form is subject to the terms of the Mozilla Public
   - License, v. 2.0. If a copy of the MPL was not distributed with this
   - file, You can obtain one at http://mozilla.org/MPL/2.0/. -->

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Julia Le Corre Artiste</title>

  <!--link rel="stylesheet" type="text/css" media="all" href="chrome://global/skin/" />
  <link rel="stylesheet" type="text/css" media="all" href="chrome://browser/content/contentSearchUI.css" />
  <link rel="stylesheet" type="text/css" media="all" href="chrome://browser/content/newtab/newTab.css" />
  <link rel="stylesheet" type="text/css" media="all" href="chrome://browser/skin/newtab/newTab.css" /-->
</head>
<style type="text/css">
	
.menu{
	padding: 0; 
	margin: 0;
	text-align: center; 

}
.menu_item{
	display: inline;
font-size: 1.5em ;
	padding: 1%; margin: 1%;



}
.menu_item:hover{
	color:gold;



}
.title 	{
	  
	 font-size: 4em ;
	  font-family: eb garamond,serif;
	  text-align: center;
	 
	
	 
}
body{
	background-color:black;
}
.root {
	
    font: 1em 'Open Sans',sans-serif;
	color:white;
	width : 100%;
	background-color:transparent;
}
.image_back {
  	
	width : 33.33%;
	margin:0;
	padding:0;
	vertical-align:top; 
}
.div_back {
  	z-index: -1;
	width : 100%;
	
	position : absolute;
	top : 0;
	left:0;
}
</style>
<body>

<div id="root" class="root">
	<div id="title"  class="title">
		<div class="title_main" >
		Julia Le Corre
		</div>
		<div class="title_sub">
		Artiste
		</div>
	</div>
	<div id="menu"  class="menu">
	<div class="menu_item"><a>Gravure</a></div>
<div class="menu_item"><a>Gravure</a></div><div class="menu_item"><a>Gravure</a></div>
<div class="menu_item"><a>Gravure</a></div>
	</div>	
	<div id="content">
		<div class="div_back" />
		<img class="image_back" src="./rsc/scan328_red.jpg"/><img class="image_back" src="./rsc//scan327_red.jpg"/>
		<img class="image_back" src="./rsc/scan329_red.jpg"/>
		</div>	
	</div>	
</div>
</body>
</html>

