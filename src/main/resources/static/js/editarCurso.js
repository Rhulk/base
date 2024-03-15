
function soloNumeros(e){
    var key = window.Event ? e.which : e.keyCode
    return (key >= 48 && key <= 57)
}

function goEditar(id){
	location.href ='cursolistEdit/'+document.getElementById(id).value;
}

function saveCapitulo(id){

	var capitulo = new Object();
	capitulo.id =
		document.getElementById('id_cap_').value;	
	capitulo.nombre =
		document.getElementById('id_cap_nombre').value;
	capitulo.descripcion =
		document.getElementById('id_cap_descripcion').value;
	capitulo.orden =
		document.getElementById('id_cap_orden').value;

	console.log(capitulo);
	
	fetch("http://localhost:8080"+"/editCapitulo/"
		+capitulo.id+"/"+capitulo.nombre.replaceAll("/", "%F7")+"/"
		+capitulo.descripcion.replaceAll("/", "%F7")
		+"/"+capitulo.orden.replaceAll("/", "%F7"))
    .then((respuesta) => respuesta.json())
    .then(
    	function (respuesta){    
			console.log(
				"Resultado respuesta: "+respuesta.check
			);

        }

     );
	
}

function addCapitulo(id){

	var capitulo = new Object();
	capitulo.id =
		document.getElementById('id_curso').value;	
	capitulo.nombre =
		document.getElementById('id_cap_nombre_new').value;
	capitulo.descripcion =
		document.getElementById('id_cap_descripcion_new').value;
	capitulo.orden =
		document.getElementById('id_cap_orden_new').value;

	console.log(capitulo);
	
	fetch("http://localhost:8080"+"/addCapitulo/"
		+capitulo.id+"/"+capitulo.nombre.replaceAll("/", "%F7")+"/"
		+capitulo.descripcion.replaceAll("/", "%F7")
		+"/"+capitulo.orden.replaceAll("/", "%F7"))
    .then((respuesta) => respuesta.json())
    .then(
    	function (respuesta){    
			console.log(
				"Resultado respuesta: "+respuesta.check
			);
			if (respuesta.check)
				location.href =''+document.getElementById('id_curso').value;
			// que hacer si falla?
        }

     );
	
}
function showFormCapitulo(){
	
	var isClassOculto = 
		document.getElementById('capitulo_new').classList.contains( 'oculto' );
	if (isClassOculto){
		document.getElementById('capitulo_new')
			.classList.remove('oculto');
		document.getElementById('btn_cap_new')
			.classList.remove('btn_green');	
		document.getElementById('btn_cap_new')
			.classList.add('btn_red');	
		document.getElementById('cursoEditBasic')
			.classList.remove('formCurso');
		document.getElementById('cursoEditBasic')
			.classList.add('oculto');	
		//document.getElementById('btn_cap_new').innerHTML = 'Cancelar alta';
	}else{
		document.getElementById('capitulo_new')
			.classList.add('oculto');
		document.getElementById('btn_cap_new')
			.classList.remove('btn_red');	
		document.getElementById('btn_cap_new')
			.classList.add('btn_green');
		document.getElementById('cursoEditBasic')
			.classList.remove('oculto');
		document.getElementById('cursoEditBasic')
			.classList.add('formCurso');		
		//document.getElementById('btn_cap_new').innerHTML = 'Add Capitulo';
	}


}


function saveCurso(id){

	document.getElementById('btn_gua').classList.remove('btn_green');
	document.getElementById('btn_gua').classList.add('btn_red');
	document.getElementById('btn_gua').innerHTML = 'Guardando ...';
	
	//document.getElementById('capitulo').classList.remove('oculto');
	console.log(
		document.getElementById('id_name_curso')
	);
	console.log(
		document.getElementById('id_desc_curso')
	);
	console.log(
		document.getElementById('id_source_curso')
	);
	console.log(
		document.getElementById('id_img_curso')
	);
	console.log(
		document.getElementById('id_icono_curso')
	);
	
	var curso = new Object();
	curso.id =
		document.getElementById('id_curso').value;
	curso.nombre = 
		document.getElementById('id_name_curso').value;
	curso.descripcion = 
		document.getElementById('id_desc_curso').value;
	curso.fuente = 
		document.getElementById('id_source_curso').value;
	curso.urlimagen =
		document.getElementById('id_img_curso').value;
	curso.urlicono = 
		document.getElementById('id_icono_curso').value;
	
	console.log(
		curso
	);
	var test = new Object();
	curso.urlicono = curso.urlicono.replaceAll("/", "%F7");
	curso.urlicono = curso.urlicono.replaceAll("?", "%24");
	
	curso.urlimagen = curso.urlimagen.replaceAll("/", "%F7");
	curso.urlimagen = curso.urlimagen.replaceAll("?", "%24");
	
	
	console.log(
		"http://localhost:8080"+"/editcurso/"
		+curso.id+"/"+curso.nombre.replaceAll("/", "%F7")+
		"/"+curso.descripcion.replaceAll("/", "%F7")+
		"/"+curso.fuente.replaceAll("/", "%F7")+"/"+curso.urlimagen+
		"/"+curso.urlicono
	);
	fetch("http://localhost:8080"+"/editcurso/"
		+curso.id+"/"+curso.nombre.replaceAll("/", "%F7")+"/"+curso.descripcion.replaceAll("/", "%F7")+
		"/"+curso.fuente.replaceAll("/", "%F7")+"/"+curso.urlicono+
		"/"+curso.urlimagen )
    .then((respuesta) => respuesta.json())
    .then(
    	function (respuesta){    
			console.log(
				"Resultado respuesta: "+respuesta.check
			);
			document.getElementById('btn_gua').classList.remove('btn_red');
			document.getElementById('btn_gua').classList.add('btn_green');
			document.getElementById('btn_gua').innerHTML = 'Guardar datos';
        }

     );	

}
function cancelarCapitulo(id){
	document.getElementById('apartado').classList.add('oculto');
	document.getElementById('capSelect').classList.add('oculto');
	
	document.getElementById('cursoEditBasic').classList.remove('oculto');
	document.getElementById('cursoEditBasic').classList.add('formCurso');
		
	document.getElementById('selectCap').selectedIndex = 0;
	
}

function selectEditarCapitulo(id){
	document.getElementById('apartado').classList.remove('oculto');
	document.getElementById('capSelect').classList.remove('oculto');
	document.getElementById('cursoEditBasic').classList.add('oculto');	
	document.getElementById('cursoEditBasic').classList.remove('formCurso');	
	console.log(id);
	console.log(document.getElementById('cursoEditBasic'));
	console.log("http://localhost:8080"+"/getCapitulo/"+id);
	
	fetch("http://localhost:8080"+"/getCapitulo/"+id)
    .then((capitulo) => capitulo.json())
    .then(
    	function (capitulo){ 
    		console.log('Respuesta Get Capitulo');   
			document.getElementById('id_cap_')
				.value = id;
			document.getElementById('id_cap_nombre')
				.value = capitulo.nombre;
			document.getElementById('id_cap_descripcion')
				.value = capitulo.descripcion;
			document.getElementById('id_cap_orden')
				.value = capitulo.orden;

        }

     );
}

function deleteCapitulo(){
	var id = document.getElementById('id_cap_').value;

	console.log("http://localhost:8080"+"/deleteCapitulo/"+id);
	
	fetch("http://localhost:8080"+"/deleteCapitulo/"+id)
    .then((respuesta) => respuesta.json())
    .then(
    	function (respuesta){    
			console.log(
				"Resultado respuesta: "+respuesta.check
			);
			if (respuesta.check)
				location.href =''+document.getElementById('id_curso').value;
        }

     );
	
}

function formatDivision(cadena){
	const nuevaStr = cadena.replaceAll("/", "%F7");
}