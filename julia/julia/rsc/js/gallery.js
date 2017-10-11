var currentId = $('#current_image');
var currentTitle = $('#current_image_title');
var currentTech = $('#slider_description');
var root_slider = $('#root_slider');
var slider = $("#slider");
var background_slider = $('#background_slider');
var root = $('#root');
var gallery_cont  = $('#gallery_cont');

var continu = true;
var i = 0;
var dimChange = false;
var timer = null;
var position = 0;
function extractDim(dimS){
  
  return dimS.substring(0,dimS.length-2)
}


function mediaHtml(media){
	
	return '<span class="italic upper">'+media.title+'</span>'+"<br/>"+media.date+"<br/>"+media.dimension+'<br/>'+media.description;
	
}



function addEvent(o,div,jsonMedia,i){
	div.click(function() {
	  // timer =setTimeout(channgeSize,40,o);
  currentId.attr("src",o.attr("src") );
  currentId.gal_id=i;
  //alert(jsonMedia.Mediatitle);
//  currentTitle.text(jsonMedia.title);
  currentTech.html(mediaHtml(jsonMedia));
   position = $(window).scrollTop(); 
    console.log("position get : "+position)
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
	currentId.attr("src","/rsc/"+gal[tmpI].path );
	currentId.gal_id =tmpI	 ;

	currentTech.html(mediaHtml(gal[tmpI]));
	
	
	
});

var btnLeft = $("#btnLeft");
btnLeft.click(function(){
	var tmpI = currentId.gal_id;
	
	tmpI--;
	if(tmpI< 0){
		tmpI=gal.length-1;
	}
	currentId.attr("src","/rsc/"+gal[tmpI].path );
	currentId.gal_id =tmpI	 ;
	  //alert(jsonMedia.Mediatitle);
//	  currentTitle.text(gal[tmpI].title);
	    currentTech.html(mediaHtml(gal[tmpI]));
	
	
	
});
continu = true;
i = 0;
while (continu && eventEnable){
	var img = $('#gal_'+i);
	var div = $('#gal_div_'+i);
	var jsonMedia = gal[i];
	if(img.length){
		addEvent(img,div,jsonMedia,i);
	}else{
		continu = false;
	}
	
	i++;
}
if(!eventEnable){
	$( ".label" ).css( "display", "block" );
}
slider.click(function(){
    root_slider.css("display","none");
    gallery_cont.css("display","block");
    console.log(position);
    $(window).scrollTop(position); 
    
});
$( window ).resize(function() {
  background_slider.height($(window).height());
});



