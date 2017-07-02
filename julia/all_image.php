
<div id="gallery_cont" class="gallery_cont">	
<?php 
	
$imgs = glob("./rsc/img/*.jpg");

foreach ($imgs as $img){
	
 ?>
 <div class="gallery_all" >

	<img class="gallery_image<?php 
	
	if( isset($all_media_map[ str_replace("./rsc","", $img)]))  echo "_used"; else  echo ""?>" src="<?php echo $img; ?>" />	 
	<?php echo $img; ?>
	</div>
 <?php
		
	}
?>
</div>