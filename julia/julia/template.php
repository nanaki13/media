

 <!DOCTYPE html>
<html>
<head>
<title><?php echo $page->get_title() ?></title>
<?php echo $page->echoCss() ?>
<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet"> 


</head>
<body>

<div id="root" class="root">

<?php  $page->echoBody() ?>
</div>
<?php 
if(isset($_SESSION['logged']))
  	require 'logout.php';
 
//   	echo 'not logged';
//   }
   ?>
<?php echo $page->echoJs() ?>
</body>
</html>