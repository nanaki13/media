

<div class="gallery_cont">
	<div class="current_image_div">



<img id="current_image" class="current_image" src="/rsc/<?php echo $local[0]->get_path() ?>"/>
<div id="current_image_title" class="legend_image"><?php 
	echo $local[0]->get_title() 
?></div>
<div id="current_image_annee" class="legend_image"><?php 
	echo $local[0]->get_date() 
?></div>
<div id="current_image_technique" class="legend_image"><?php 
	echo $local[0]->get_description() 
?></div>

</div>

	<div class="gallery_right_cont">
<script type="text/javascript">var gal =[];	</script>
<?php
$i =0;

foreach($local as $media){
?>
		<div class="gallery"><img class="gallery_image" id="gal_<?php echo $i ?>" src="/rsc/<?php echo $media->get_path() ?>"/></div>
		<script type="text/javascript">
		gal.push(<?php echo $media->toJson() ?>);

</script>

	
<?php
$i++;
}

?>
	</div>
</div>



