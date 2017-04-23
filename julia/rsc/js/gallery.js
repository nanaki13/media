var currentId = $('#current_image');
var currentTitle = $('#current_image_title');
var currentAnne = $('#current_image_annee');
var currentTech = $('#current_image_technique');
var currentIdDiv = $('#current_image_div');
var continu = true;
var i = 0;
var dimChange = false;
var timer = null;
function extractDim(dimS){
  
  return dimS.substring(0,dimS.length-2)
}
var originWidth = extractDim(currentId.css("width"));
var channgeSize = function(o){
  var width = extractDim(o.css("width"));
  width = width*1.05;
 o.css("z-index",3);
  if(width < 700){
     o.css("width",width);
       timer =setTimeout(channgeSize,40,o);
    
  }else{
    clearTimeout(timer);
  }
  
}
//  currentId.click(function() {
//    currentIdDiv.css("z-index",3);
//    timer =setTimeout(channgeSize,40,currentId);
// })
 currentId.click(function() {
   
   currentId.css("width",originWidth);
  
})


function addEvent(o,jsonMedia){
	o.mouseover(function() {
	  // timer =setTimeout(channgeSize,40,o);
  currentId.attr("src",o.attr("src") );
  //alert(jsonMedia.Mediatitle);
  currentTitle.text(jsonMedia.ImageGallerytitle);
   currentAnne.text(jsonMedia.ImageGallerydate);
    currentTech.text(jsonMedia.ImageGallerydescription);		
    currentId.mouseover(function() {
       continu = true;
       i = 0;
       while (continu){
	var img = $('#gal_'+i);
	var jsonMedia = gal[i];
	if(img.length){
		img.css("display","none");
	}else{
		continu = false;
	}
	
	i++;
}
      
    });
     currentId.mouseleave(function() {
       continu = true;
       i = 0;
       while (continu){
	var img = $('#gal_'+i);
	var jsonMedia = gal[i];
	if(img.length){
		img.css("display","inherit");
	}else{
		continu = false;
	}
	
	i++;
}
      
    });
});	
}

continu = true;
i = 0;
while (continu){
	var img = $('#gal_'+i);
	var jsonMedia = gal[i];
	if(img.length){
		addEvent(img,jsonMedia);
	}else{
		continu = false;
	}
	
	i++;
}



