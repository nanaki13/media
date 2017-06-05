<div id="subMenu"  class="subMenu">
<?php
foreach($subMenuItems as $subMenuItem){
?>
	<div class="subMenuDiv"><a class="subMenuItem" href="<?php echo "/".getPage()."/".$subMenuItem['ref']?>"><div class="sub_menu_title"><?php echo $subMenuItem['name']?></div>
	  <img src="/rsc<?php echo $subMenuItem['img']?>"/></a>
	</div>
<?php
}
?>
</div>	

