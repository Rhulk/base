


function goEditar(id){
	location.href ='cursolistEdit/'+document.getElementById(id).value;
}

function saveCurso(id){
	
	document.getElementById('capitulo')
		.classList.remove('oculto');
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
	console.log(
		"http://localhost:8080"+"/editcurso/"
		+curso.id+"/"+curso.nombre.replaceAll("/", "%F7")+
		"/"+curso.descripcion.replaceAll("/", "%F7")+
		"/"+curso.fuente.replaceAll("/", "%F7")+"/"+curso.urlimagen.replaceAll("/", "%F7")+
		"/"+curso.urlicono.replaceAll("/", "%F7")
	);
	fetch("http://localhost:8080"+"/editcurso/"
		+curso.id+"/"+curso.nombre.replaceAll("/", "%F7")+"/"+curso.descripcion.replaceAll("/", "%F7")+
		"/"+curso.fuente.replaceAll("/", "%F7")+"/"+curso.urlicono.replaceAll("/", "%F7")+
		"/"+curso.urlimagen.replaceAll("/", "%F7") )
    .then((curso) => curso.json())
    .then(
    	function (curso){    
			console.log(
				curso.check
			);

        }

     );	

}
function selectEditarCapitulo(id){
	document.getElementById('apartado')
		.classList.remove('oculto');
	console.log(id);
	
	fetch("http://localhost:8080"+"/getCapitulo/"+id)
    .then((capitulo) => capitulo.json())
    .then(
    	function (capitulo){    
			document.getElementById('id_cap_')
				.vale = capitulo.id;
			document.getElementById('id_cap_nombre')
				.value = capitulo.nombre;
			document.getElementById('id_cap_descripcion')
				.value = capitulo.descripcion;
			document.getElementById('id_cap_orden')
				.value = capitulo.orden;

        }

     );
}

function formatDivision(cadena){
	const nuevaStr = cadena.replaceAll("/", "%F7");
}