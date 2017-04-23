<div id="menu"  class="menu">

<?php
foreach($menuItems as $menuItem){
?>
	
	<div class="menu_item_div"><a class="menu_item" href="<?php echo $menuItem['ref']?>"><?php echo $menuItem['name']?></a></div>
<?php
}
?>

</div>	

