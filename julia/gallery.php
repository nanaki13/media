

<div id="gallery_cont" class="gallery_cont">
	
	<div  class="gallery_inner_cont">
	
	<script type="text/javascript">var gal =[];	</script>
<?php
$i =0;

foreach($local as $media){
?>
		<div class="gallery">
			<img class="gallery_image" id="gal_<?php echo $i ?>" src="/rsc/<?php echo $media->get_path() ?>"/>
			<div class="topright"><div class="in"><?php echo $media->get_title().'<br/>'.$media->get_date().'<br/>'.$media->get_dimension().'<br/>'.$media->get_description()?></div></div>
		</div>
		<script type="text/javascript">
			gal.push(<?php echo $media->toJson() ?>);

		</script>

	
<?php
$i++;
}

?>
	</div>
</div>




