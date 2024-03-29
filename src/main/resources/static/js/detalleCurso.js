let maxApartado;
let pag=1;
let apartadoActual;

let pagApu=1;
let cantApu;
let posicionInicial=0;
let video='08f8_eHrarU';

let cursoActual;
let urlHost="http://localhost:8080";



function follow(){



	fetch(urlHost+"/follow/"+document.querySelector('.follow').value)
	.then((respuesta) => respuesta.json())
    .then(
    	function (respuesta){  
			
			if (respuesta.check){
	 			document.getElementById('btn_unfollow').classList.remove('oculto');
				document.getElementById('btn_follow').classList.add('oculto');
			}
		}

     ); 

	
}

function unfollow(){
	
	fetch(urlHost+"/unfollow/"+document.querySelector('.unfollow').value)
	.then((respuesta) => respuesta.json())
    .then(
    	function (respuesta){    
			if (respuesta.check){
				document.getElementById('btn_follow').classList.remove('oculto');
				document.getElementById('btn_unfollow').classList.add('oculto');
			}
		}

     ); 
     
}
function isfollow(){
	cursoActual = document.getElementById('btn_follow').value;
    try {
		fetch(urlHost+"/isfollow/"+document.querySelector('.follow').value)
		.then((respuesta) => respuesta.json())
	    .then(
	    	function (respuesta){  
	    	 	console.log(respuesta.check);
			 	siOno = respuesta.check;
	
			 	
			 	if(respuesta.check){//follow
				    console.log(	document.getElementById('btn_unfollow') );
					document.getElementById('btn_unfollow').classList.remove('oculto');
					var contiene = document.getElementById('btn_follow')
						.classList.contains('oculto'); 
					if(!contiene){//con contiene
						document.getElementById('btn_follow').classList.add('oculto');
					}				 		
			 	}else{//unfollow
					document.getElementById('btn_follow').classList.remove('oculto');
					var contiene = document.getElementById('btn_unfollow')
						.classList.contains( 'oculto' ); 
					if(!contiene){
						document.getElementById('btn_unfollow').classList.add('oculto');
					}		 		
			 	}
			 	return siOno;
			}
			
	
     	);	    
    } catch (error) {
        return error;
    }
}


$(document).ready(function() {

	isfollow();

	document.getElementById('visto').innerText = '¿Visto?';
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
	onYouTubePlayerAPIReady();
	console.log(document.getElementById(document.querySelectorAll('.'+'btn_gua')[0].id).classList);
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
    //alterar el div del aporte filtrado por el id del apartado seleccionado.
    //var apunte = document.getElementById('idapunte');
    console.log('id '+id);
    console.log(id.substr(10, 1));//Apartado
    apartadoActual = id.substr(10, id.length);
    var idapunte ='apunte'+id.substr(10, 1);
    console.log(apartadoActual);
    console.log(document.getElementById(idapunte));
    console.log('Recupero div');
    
    //Opcion b Construir el div aqui.
    //crearDivAporte(); //descartado los svg no se crean
	var idapu = 'idapunte'+ apartadoActual;

    document.querySelectorAll('.'+'apartadoAporte')[0].id = idapu;
    document.getElementById('idapunte'+apartadoActual).classList.remove('oculto');
    
	recuperarNotas(id.substr(10, id.length),posicionInicial);
	isCheching();
}

function recuperarNotas(apartado, pag){	
	console.log(document.getElementById(document.querySelectorAll('.'+'btn_gua')[0].id).classList);
		pagApu=1;
		document.getElementById('pagApu').textContent = pagApu;
        fetch(urlHost+"/apuntes/"+apartado+"/"+pag+"/"+cursoActual)
       .then((apunte) => apunte.json())
       .then(
           function (apunte){  
             	if (apunte.length > 0){
             		var id_btn_guardar = document.querySelectorAll('.'+'btn_gua')[0].id;
             		
					document.getElementById(id_btn_guardar).classList.add('oculto');//no funciona
					
						console.log(document.getElementById(id_btn_guardar).classList);
						console.log(document.getElementById(document.querySelectorAll('.'+'btn_gua')[0].id));
						
					document.getElementById(
						document.querySelectorAll('.'+'btn_alt')[0].id).classList.remove('oculto');
					document.getElementById(
						document.querySelectorAll('.'+'btn_mod')[0].id).classList.remove('oculto');
					document.getElementById(
						document.querySelectorAll('.'+'btn_del')[0].id).classList.remove('oculto');
					document.getElementById('textarea').value = apunte[0].notas;
					
					document.querySelectorAll('.'+'btn_gua')[0].id = 'btn_gua' + apunte[0].id;
					document.querySelectorAll('.'+'btn_alt')[0].id = 'btn_alt' + apunte[0].id;
					document.querySelectorAll('.'+'btn_mod')[0].id = 'btn_mod' + apunte[0].id;
					document.querySelectorAll('.'+'btn_del')[0].id = 'btn_del' + apunte[0].id;
					

				}else{
					document.getElementById('textarea').value = 'Escribe aquí tus apuntes...';
					// ocultamos el btn delete, mod y alta por el de guardar
					
					document.querySelectorAll('.'+'btn_gua')[0].id = 'btn_gua' + apartadoActual;
					document.querySelectorAll('.'+'btn_alt')[0].id = 'btn_alt';
					
					document.querySelectorAll('.'+'btn_mod')[0].id = 'btn_mod';
					document.querySelectorAll('.'+'btn_del')[0].id = 'btn_del';
					
					document.getElementById(
						document.querySelectorAll('.'+'btn_gua')[0].id).classList.remove('oculto');
					console.log(document.getElementById(document.querySelectorAll('.'+'btn_gua')[0].id));
					
					document.getElementById(
						document.querySelectorAll('.'+'btn_alt')[0].id).classList.add('oculto');
					document.getElementById(
						document.querySelectorAll('.'+'btn_mod')[0].id).classList.add('oculto');
					document.getElementById(
						document.querySelectorAll('.'+'btn_del')[0].id).classList.add('oculto');
				}
            }

        );	
        console.log('apartadoActual :'+apartadoActual);
        apartadoActual = apartado;
        
		fetch("http://localhost:8080/apuntes/"+apartado+"/"+cursoActual)
       .then((cantidad) => cantidad.json())
       .then(
           function (cantidad){    
				console.log('cantidad: '+cantidad);
				cantApu= cantidad;
				document.getElementById('pagTotal').textContent = cantidad;			
            }

        );
        console.log(document.getElementById(document.querySelectorAll('.'+'btn_gua')[0].id).classList);              		
}
/*
	Crear nuevo aporte form en blanco previo y activar el btn_guardar activo
*/
function altAporte(id){

	document.querySelectorAll('.'+'btn_gua')[0].id = 'btn_gua';
	document.querySelectorAll('.'+'btn_alt')[0].id = 'btn_alt';
	document.querySelectorAll('.'+'btn_mod')[0].id = 'btn_mod';
	document.querySelectorAll('.'+'btn_del')[0].id = 'btn_del';
	
	document.getElementById(
		document.querySelectorAll('.'+'btn_gua')[0].id).classList.remove('oculto');
	document.getElementById(
		document.querySelectorAll('.'+'btn_alt')[0].id).classList.add('oculto');
	document.getElementById(
		document.querySelectorAll('.'+'btn_mod')[0].id).classList.add('oculto');
	document.getElementById(
		document.querySelectorAll('.'+'btn_del')[0].id).classList.add('oculto');
	
	document.getElementById('textarea').value = 'Escribe aquí tus apuntes...';
	
	document.querySelectorAll('.'+'btn_gua')[0].id = 'btn_gua'+apartadoActual;
}
/*
	Guardar nuevo aporte
*/
function saveAporte(id){
	id = id.substr(7, id.length);
	console.log(id);

	var notas = document.getElementById('textarea').value;
	
	fetch(urlHost+"/saveAporteIn/"+id+"/"+notas+"/"+cursoActual)
       .then((respuesta) => respuesta.json())
       .then(
           function (respuesta){    
           		if (respuesta.check){
           			document.querySelectorAll('.'+'btn_gua')[0].id = 'btn_gua';
					document.querySelectorAll('.'+'btn_alt')[0].id = 'btn_alt';
					document.querySelectorAll('.'+'btn_mod')[0].id = 'btn_mod';
					document.querySelectorAll('.'+'btn_del')[0].id = 'btn_del';
					document.getElementById(
						document.querySelectorAll('.'+'btn_gua')[0].id).classList.add('oculto');
					document.getElementById(
						document.querySelectorAll('.'+'btn_alt')[0].id).classList.remove('oculto');
					document.getElementById(
						document.querySelectorAll('.'+'btn_mod')[0].id).classList.remove('oculto');
					document.getElementById(
						document.querySelectorAll('.'+'btn_del')[0].id).classList.remove('oculto');						
					recuperarNotas(apartadoActual, posicionInicial);
           		}else{
           			console.log("FAILD Save Aporte");
           		}
            }

     ); 
	

}
/*
	Modificacion del aporte

*/

function modAporte(id){
	id = id.substr(7, id.length);
	var notas = document.getElementById('textarea').value;
	
	fetch(urlHost+"/modApunteIn/"+id+"/"+notas+"/"+cursoActual)
		.then((respuesta) => respuesta.json())
       .then(
           function (respuesta){   
           		if (respuesta.check){
           			console.log('Modificacion OK');
           		}else{
           			console.log('Modificacion KO');
           		}
				
				
            }

     ); 
	
}

/*
	Borrado del aporte actual

*/

function deleteAporte(id){

	id = id.substr(7, id.length);	
	
	fetch(urlHost+"/deleteapunte/"+id+"/"+cursoActual)
       .then((respuesta) => respuesta.json())
       .then(
           function (respuesta){    
				if (respuesta.check){
					recuperarNotas(document.querySelectorAll(
						'.'+'apartadoAporte')[0].id.substr(8, document.querySelectorAll('.'+'apartadoAporte')[0].id.length),0);
				}else{
					console.log(" FAILD Delete Apunte.");
				}
            }

     ); 
	
}

function nextStop(){
	console.log(pagApu);
	console.log(cantApu);
	console.log(apartadoActual);
	
	if (pagApu<cantApu){
		pagApu=pagApu+1;
		document.getElementById('pagApu').textContent = pagApu;
		var ajuste = pagApu-1;
		fetch(urlHost+"/apuntes/"+apartadoActual+"/"+ajuste+"/"+cursoActual)
       		.then((apunte) => apunte.json())
       		.then(
           		function (apunte){    
					document.getElementById('textarea').value = apunte[0].notas;
            	}

        	);
	}

}

function backStop(){
	
	if (pagApu>1){
		pagApu=pagApu-1;
		document.getElementById('pagApu').textContent = pagApu;
		var ajuste = pagApu-1;
		fetch(urlHost+"/apuntes/"+apartadoActual+"/"+ajuste+"/"+cursoActual)
       		.then((apunte) => apunte.json())
       		.then(
           		function (apunte){    
					document.getElementById('textarea').value = apunte[0].notas;
            	}

        	);		
		
		
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
	
	cargarRecurso(document.querySelectorAll('.'+'recurso')[pag-1].id);
	
	let id = document.querySelectorAll('.'+'apartado')[pag-1].id;
	apartadoActual = id.substr(10, id.length);
	recuperarNotas(apartadoActual,0);
	
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
	
	let id = document.querySelectorAll('.'+'apartado')[pag-1].id;
	apartadoActual = id.substr(10, id.length);
	recuperarNotas(apartadoActual,0);
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
function checking(){
	console.log(" checked "+document.getElementById('opt-in').checked);
	
	if (document.getElementById('opt-in').checked){
		document.getElementById('visto').innerText = '¡Visto!';
		console.log("http://localhost:8080/checkout/"+apartadoActual+"/true"+"/"+cursoActual);
		fetch(urlHost+"/checkout/"+apartadoActual+"/true"+"/"+cursoActual)
		.then((respuesta) => respuesta.json())
       .then(
           function (respuesta){    
				if(respuesta.check){
					console.log("Checking OK");
				}else{
					console.log("Checking KO");
				}
            }

     	); 
	
	}else{
		document.getElementById('visto').innerText = '¿Visto?';
		console.log("http://localhost:8080/checkout/"+apartadoActual+"/false"+"/"+cursoActual);
		fetch(urlHost+"/checkout/"+apartadoActual+"/false"+"/"+cursoActual)
		.then((respuesta) => respuesta.json())
       .then(
           function (respuesta){    
				if(respuesta.check){
					console.log("Checking OK");
				}else{
					console.log("Checking KO");
				}
            }

     	); 
		
	}
}

function isCheching(){
	console.log(urlHost+"/checkstatus/"+apartadoActual+"/"+cursoActual);
	fetch(urlHost+"/checkstatus/"+apartadoActual+"/"+cursoActual)
       .then((respuesta) => respuesta.json())
       .then(
           function (respuesta){    
				console.log(respuesta.check);
				if (respuesta.check){
					document.getElementById('opt-in').checked = respuesta.check;
					document.getElementById('visto').innerText = '¡Visto!';
				}else{
					document.getElementById('opt-in').checked = respuesta.check;
					document.getElementById('visto').innerText = '¿Visto?';				
				
				}
            }

     ); 



}

