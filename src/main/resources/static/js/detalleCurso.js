let maxApartado;
let pag =1;


$(document).ready(function() {

	maxApartado = document.querySelectorAll('.'+'apartado').length;
	pag =1;
	// Recuperamos el numero de apartados iniciales
	console.log('Numero de apartados: '+document.querySelectorAll('.'+'apartado').length);
	
	//console.log('Apartado inicial: '+document.querySelectorAll('.'+'apartado')[0].textContent);
	console.log('Apartado id: '+document.querySelectorAll('.'+'apartado')[0].id);
	console.log('Apartado titulo: '+document.querySelectorAll('.'+'apartado')[0].title);
	
} );


/*
	Funcionalidad que nos permite cargar un apartado desde el paginado de los recursos.
	id el la posición del array -1
*/
function goToApartado(id){
	// identificamos el div del apartado al que vamos
	var idDiv = document.querySelectorAll('.'+'apartado')[id-1].id;
	seleccionarApartado(idDiv);
	
	
	// hay que quitarle la clase ocultarNavb

	// cargamos el recurso
	var recurso = document.querySelectorAll('.'+'apartado')[id-1].title;
	cargarRecurso(recurso);
}


/*
	Funcionalidad para rotar un icono en el menu cada vez que se le pincha.
	Agregamos o quitamos la clase al elemento segun la necesidad.
*/
function rotarIcono(id){
	
	var classRotate = document.getElementById(id).classList.contains( 'fa-rotate-180' );
	
	console.log(document.getElementById(id).length);
	
	if (classRotate){
		document.getElementById(id).classList.remove('fa-rotate-180');
		desplegarMenu(id);
	}else{
		document.getElementById(id).classList.add('fa-rotate-180');
		plegarMenu(id);
	}
	
};
/*
	Funcionalidad de rotar icono desde otro elemento
*/
function rotarIconoExt(id){
	id='rotar_'+id;
	var classRotate = document.getElementById(id).classList.contains( 'fa-rotate-180' );
	
	console.log(document.getElementById(id).length);
	
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

/*
	Funcionalidad para cargar el recurso seleccionado
*/
function cargarRecurso(url){

	document.getElementById('idframe').setAttribute('src', url);

}

/*
	Funcionalidad para resaltar el apartadado clicado
*/
function seleccionarApartado(id){

	desSelected()
	document.getElementById(id).classList.add('selectApartado');
	
	// listado de apartados y recupero la posición con el id de su posición.
	var items2 = document.querySelectorAll('.'+'apartado');
	for (var i = 0; i < items2.length; i++) {
		console.log(items2[i].id+' '+id);
		
		if (items2[i].id === id){
			pag=i;
			i=items2.length;
			document.getElementById('pag').textContent=pag+1;
		}
		
    }	

}

function desSelected(){
	var items2 = document.querySelectorAll('.'+'selectApartado');
	console.log(items2.length);
	for (var i = 0; i < items2.length; i++) {
		items2[i].classList.remove('selectApartado');
    }

}

function nextPag(){
	/*selectApartado  document.querySelectorAll('.'+'apartado')[0].id*/
	if(pag<maxApartado){
		pag=pag+1;	
		
	}

	active_btn();
	document.getElementById('pag').textContent=pag;
	desSelected();

	//seleccionarApartado((document.querySelectorAll('.'+'apartado')[pag-1].id));
	
	document.getElementById(document.querySelectorAll('.'+'apartado')[pag-1].id).classList.add('selectApartado');
	console.log(document.getElementById(document.querySelectorAll('.'+'recurso')[pag-1].id));
	

	
	cargarRecurso(document.querySelectorAll('.'+'recurso')[pag-1].id);
}

function backPag(){
	if(pag>=2){
		pag=pag-1;
		
	}
	active_btn();
	document.getElementById('pag').textContent=pag;
	desSelected();
	//seleccionarApartado(document.querySelectorAll('.'+'apartado')[pag-1].id);
	document.getElementById(document.querySelectorAll('.'+'apartado')[pag-1].id).classList.add('selectApartado');
	
	cargarRecurso(document.querySelectorAll('.'+'recurso')[pag-1].id);
}


function active_btn (){

	if(pag===1){
		
		document.getElementById('back_btn').setAttribute('class', 'opacityOn');
		document.getElementById('next_btn').setAttribute('class', 'opacityOff');
	}else{
		if (pag===maxApartado){
			document.getElementById('next_btn').setAttribute('class', 'opacityOn');
			document.getElementById('back_btn').setAttribute('class', 'opacityOff');
			
		}else{
			document.getElementById('back_btn').setAttribute('class', 'opacityOff');
			document.getElementById('next_btn').setAttribute('class', 'opacityOff');
			
		}
	}

}

