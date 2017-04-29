<div id="subMenu"  class="subMenu">
<?php
foreach($subMenuItems as $subMenuItem){
?>
	<div class="subMenuDiv"><a class="subMenuItem" href="<?php echo "/".getPage()."/".$subMenuItem['ref']?>"><?php echo $subMenuItem['name']?></a>
	  <img src="/rsc<?php echo $subMenuItem['img']?>"/>
	</div>
<?php
}
?>
</div>	

