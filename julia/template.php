

 <!DOCTYPE html>
<html>
<head>
<title><?php echo $page->get_title() ?></title>
<?php echo $page->echoCss() ?>



</head>
<body>
<div class="root">
<?php  $page->echoBody() ?>
</div>
<?php echo $page->echoJs() ?>
</body>
</html>
