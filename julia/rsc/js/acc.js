var imgs = [];
//var prev = imgs[imgs.lenght - 1];
var first = true;
$('#div_back').find('img').each(function(){
	
//	if(first){
//		 $(this).css("display","block");
//	}
//	else{
//		first = false;
//	}
	imgs.unshift($(this));	

	
});

//window.setTimeout(swap,0,imgs,0)
swap(imgs,0);
first = true;
function swap(imgs,ind){
//	alert('coucou')
	imgs[ind].fadeOut(1000 );
	imgs[(ind + 1) % imgs.length  ].fadeIn(1000 );
	ind++;
	if(ind == imgs.length){
		ind = 0;
	}
	window.setTimeout(swap,4000,imgs,ind);
}
//$(document).ready	(function() {
//	 alert('Le chargement de la page web est terminé');
//	});
//window load

//img.click(function(){  img.fadeOut(500, function() {
//	img.attr("src","/rsc/img/Le bouquet.jpg");
//	img.fadeIn(500);
//})});

//
//img.click(function(){
//	
//	img.animate(
//	function() {
//		img.attr("src","/rsc/img/Le bouquet.jpg");
//		
//	}
//	,1500)
//	});
//img.css('transition','opacity 1s ease-in-out');