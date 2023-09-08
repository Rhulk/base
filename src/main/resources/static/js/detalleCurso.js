
/*
	Funcionalidad para rotar un icono en el menu cada vez que se le pincha.
	Agregamos o quitamos la clase al elemento segun la necesidad.
*/
function rotarIcono(id){
	
	var classRotate = document.getElementById(id).classList.contains( 'fa-rotate-180' );
	
	if (classRotate){
		document.getElementById(id).classList.remove('fa-rotate-180');
		desplegarMenu(id);
	}else{
		document.getElementById(id).classList.add('fa-rotate-180');
		plegarMenu(id);
	}
	
};
/*
	Funcionalidad para ocultar los sub item del menu principal

*/
function plegarMenu(id){
	
	var items2 = document.querySelectorAll('.'+id);
	console.log(id);
	console.log(items2.length);
	for (var i = 0; i < items2.length; i++) {
		items2[i].classList.add('ocultarNavb');
    }

}
/*
	Funcionalidad para mostrar los sub items del menu principal
*/
function desplegarMenu(id){
	var items2 = document.querySelectorAll('.'+id);
	console.log(items2.length);
	for (var i = 0; i < items2.length; i++) {
		items2[i].classList.remove('ocultarNavb');
    }

}

/*
	Funcionalidad para minimizar el detalle del recurso o maximizarlo.
*/

function showHide(){

	var classHeigt = document.getElementById('idframe').classList.contains( 'heigtCero' );

	if (classHeigt){
		document.getElementById('idframe').classList.remove('heigtCero');
		document.getElementById('idframe').classList.add('heigtseiscientos');
		document.getElementById('idshowlink').innerText = 'hide';
	} else {
		document.getElementById('idframe').classList.add('heigtCero');
		document.getElementById('idframe').classList.remove('heigtseiscientos');
		document.getElementById('idshowlink').innerText = 'show';
	}
	
	rotarIcono('idshow');
}
/*
	Funcionalidad para minimizar el detalle del apunte
*/

function showHideApunte(){
	var classHeigt = document.getElementById('main__discover').classList.contains( 'heigtCientoCincuenta' );
	
	if (classHeigt){
		document.getElementById('main__discover').classList.remove('heigtCientoCincuenta');
		document.getElementById('textarea').classList.add('heightDoscientos');
		document.getElementById('textarea').classList.remove('heigtCero');
		document.getElementById('idshowlinkApu').innerText = 'hide';
	}else{
		document.getElementById('main__discover').classList.add('heigtCientoCincuenta');
		document.getElementById('textarea').classList.remove('heightDoscientos');
		document.getElementById('textarea').classList.add('heigtCero');
		document.getElementById('idshowlinkApu').innerText = 'show';
	}
	
}