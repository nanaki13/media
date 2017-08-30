
<div id="gallery_cont" class="gallery_cont">	
<?php 
	
$imgs = glob("./rsc/img/*.jpg");

foreach ($imgs as $img){
	$abso = str_replace("./","/", $img);
	$isUsed =isset($all_media_map[ str_replace("./rsc","", $img)]);
 ?>
	<div class="gallery_all" >
<?php 	if (!$isUsed){ ?>
		<a href="/admin/createArtFromPath?path=<?php echo $abso; ?>">
<?php }?>
		<img class="gallery_image<?php 
	
	if($isUsed)  echo "_used"; else  echo ""?>" src="<?php echo $abso; ?>" />	 
	<?php echo $abso; ?>
<?php 	if (!$isUsed){ ?>
</a>
<?php }?>	
	</div>
 <?php
		
	}
?>
</div>