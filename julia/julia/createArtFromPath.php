	<div class="right">
	
	<form class="margtop inlineblock" method="post" action="/admin/createOeuvre">
	<?php 
	
foreach ($get_method as $name){
	$get_met = 'get_'.$name;
	
	?>
	
		<label for="<?php echo $name?>"><?php echo $name?></label><input type="text" value="" name="<?php echo $name?>"/><br/>
	<?php 
}
	?>
		<input type="text" value="<?php echo getPage() ?>" name="page_red" hidden="true"/>
		<input type="text" value="<?php echo $path ?>" name="image_path" hidden="true"/>
		<input type="submit" value="OK"/>
	 	<label for="theme">theme</label><select name="theme">
	
	 <?php 
	
	 foreach ($themes as $theme){
	 	
	 ?> <option value="<?php echo $theme->get_id()?>"><?php echo $theme->get_name()?></option>
	 <?php 
}
?>
		</select> 
			 	<label for="technique">technique : </label><select name="technique">
	
	 <?php 
	 foreach ($techniques as $technique){
	 	
	 ?> <option value="<?php echo $technique->get_code()?>"><?php echo $technique->get_name()?></option>
	 <?php 
}
?>
		</select>
	</form>
	 <img  class="vignetteDroite" src="<?php echo $path; ?>" />	
	</div>
