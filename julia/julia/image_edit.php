

<div id="gallery_cont" class="gallery_cont">
	
	
	
	<script type="text/javascript">var gal =[];
<?php	foreach($local as $media){?>
			gal.push(<?php echo $media->toJson() ?>);

		</script>
<?php
}
$i =0;


?><div  class="gallery_inner_cont"><?php
$col_prev = -1;
foreach($local as $media){
	$col = $media->get_column();
	$row = $media->get_row();
	if($col_prev != -1 && $col_prev != $col){
			echo '</div>';
		?><div  class="gallery_inner_cont"><?php
	}
	$col_prev = $col;
		
?>
		<div class="gallery" id="gal_div_<?php echo $i ?>">
			<img class="gallery_image" id="gal_<?php echo $i ?>" src="/rsc/<?php echo $media->get_path() ?>"/>
		
			
			<div class="topright_background">
			
			</div>
			<div class="label"><?php echo  $media->get_id().'<br/>'. $media->get_title().'<br/>'.$media->get_date().'<br/>'.$media->get_dimension().'<br/>'.$media->get_description()?></div>
			
		
		</div>
		
		<?php

$i++;
}

?>
</div>	
</div>




