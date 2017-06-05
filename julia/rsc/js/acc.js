var imgs = [];
var prev = imgs[imgs.lenght - 1];
var first = true;
$('#div_back').find('img').each(function(){
	
	if(first){
		 $(this).css("display","block");
	}
	else{
		first = false;
	}
	imgs.push($(this));	

	
});

window.setTimeout(swap,1000,imgs,0)

function swap(imgs,ind){
	imgs[ind].fadeOut(700);
	imgs[(ind + 1) % imgs.length  ].fadeIn(700);
	ind++;
	if(ind == imgs.length){
		ind = 0;
	}
	window.setTimeout(swap,3000,imgs,ind);
}
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