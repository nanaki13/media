var currentId = $('#current_image');
var currentTitle = $('#current_image_title');
var currentTech = $('#current_image_description');
var root_slider = $('#root_slider');
var slider = $("#slider");
var background_slider = $('#background_slider');
var root = $('#root');
var gallery_cont  = $('#gallery_cont');
var continu = true;
var i = 0;
var dimChange = false;
var timer = null;
function extractDim(dimS){
  
  return dimS.substring(0,dimS.length-2)
}

  



function addEvent(o,jsonMedia,i){
	o.click(function() {
	  // timer =setTimeout(channgeSize,40,o);
  currentId.attr("src",o.attr("src") );
  currentId.gal_id=i;
  //alert(jsonMedia.Mediatitle);
  currentTitle.text(jsonMedia.ImageGallerytitle);
  currentTech.text(jsonMedia.ImageGallerydescription+","+jsonMedia.ImageGallerydate);
   
    root_slider.css("display","block");
    gallery_cont.css("display","none");
    background_slider.height($(window).height());
   
})}

var btnRight = $("#btnRight");
btnRight.click(function(){
	var tmpI = currentId.gal_id;
//	alert(tmpI);
	tmpI++;
	if(tmpI>= gal.length){
		tmpI=0;
	}
	currentId.attr("src","/rsc/"+gal[tmpI].ImageGallerypath );
	currentId.gal_id =tmpI	 ;
	  //alert(jsonMedia.Mediatitle);
	  currentTitle.text(gal[tmpI].ImageGallerytitle);
	    currentTech.text(gal[tmpI].ImageGallerydescription+","+gal[tmpI].ImageGallerydate);
	
	
	
});

var btnLeft = $("#btnLeft");
btnLeft.click(function(){
	var tmpI = currentId.gal_id;
//	alert(tmpI);
	tmpI--;
	if(tmpI< 0){
		tmpI=gal.length-1;
	}
	currentId.attr("src","/rsc/"+gal[tmpI].ImageGallerypath );
	currentId.gal_id =tmpI	 ;
	  //alert(jsonMedia.Mediatitle);
	  currentTitle.text(gal[tmpI].ImageGallerytitle);
	    currentTech.text(gal[tmpI].ImageGallerydescription+","+gal[tmpI].ImageGallerydate);
	
	
	
});
continu = true;
i = 0;
while (continu){
	var img = $('#gal_'+i);
	var jsonMedia = gal[i];
	if(img.length){
		
		addEvent(img,jsonMedia,i);
	}else{
		continu = false;
	}
	
	i++;
}
slider.click(function(){root_slider.css("display","none"); gallery_cont.css("display","block");});



