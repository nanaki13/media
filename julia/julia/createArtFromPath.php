	<div class="right">
	
	<form class="margtop inlineblock" method="post" action="/admin/createOeuvre">
	<?php 
	
foreach ($get_method as $name){
	$get_met = 'get_'.$name;
	$abso = str_replace("./","/", $_GET['path']);
	?>
	
	<label for="<?php echo $name?>"><?php echo $name?></label><input type="text" value="" name="<?php echo $name?>"/></br>
	<?php 
}
	?>
	<input type="text" value="<?php echo getPage() ?>" name="page_red" hidden="true"/>
	<input type="submit" value="OK"/>
	 <select name="theme">
	
	 <?php 
	
	 foreach ($themes as $theme){
	 	
	 ?> <option value="<?php echo $theme->get_id()?>"><?php echo $theme->get_name()?></option>
	 <?php 
}
?>

</select> 
	</form>
	 <img  class="vignetteDroite" src="<?php echo $abso; ?>" />	
	</div>
