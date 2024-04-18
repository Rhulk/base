

function cargarCapitulosByCurso(){

	var idCurso = document.getElementById("id_curso").value;
	
	console.log(idCurso);

    if(idCurso!=0){
    
    fetch("http://localhost:8080" + "/getCapitulosByCurso/" + idCurso)
    .then((respuesta) => respuesta.json())
    .then(function (respuesta) {
    	
    	const selectCampos = document.querySelector("#selectCap");
    	const $select = document.getElementById("selectCap");

	    for (let i = $select.options.length; i >= 1; i--) {
            $select.remove(i);
        }
      
		for (x=0; x<Object.keys(respuesta).length;x++){	
			var option = document.createElement('option');
			
        	option.value = respuesta[x].id;
        	option.text = respuesta[x].nombre;       
        	option.id = respuesta[x].id;
        	selectCampos.appendChild(option);
		}
    });
    
    }

}



function soloNumeros(e) {
  var key = window.Event ? e.which : e.keyCode;
  return key >= 48 && key <= 57;
}

function goEditar(id) {
  location.href = "cursolistEdit/" + document.getElementById(id).value;
}

function saveCapitulo(id) {
  var capitulo = new Object();
  capitulo.id = document.getElementById("id_cap_").value;
  capitulo.nombre = document.getElementById("id_cap_nombre").value;
  capitulo.descripcion = document.getElementById("id_cap_descripcion").value;
  capitulo.orden = document.getElementById("id_cap_orden").value;

  console.log(capitulo);

  fetch(
    "http://localhost:8080" +
      "/editCapitulo/" +
      capitulo.id +
      "/" +
      capitulo.nombre.replaceAll("/", "%F7") +
      "/" +
      capitulo.descripcion.replaceAll("/", "%F7") +
      "/" +
      capitulo.orden.replaceAll("/", "%F7")
  )
    .then((respuesta) => respuesta.json())
    .then(function (respuesta) {
      console.log("Resultado respuesta: " + respuesta.check);
    });
}
function saveApartado(){
  var apartado = new Object();
  apartado.id = document.getElementById("id_apar_").value;
  apartado.nombre = document.getElementById("id_apar_nombre").value;
  apartado.descripcion = document.getElementById("id_apar_descripcion").value;
  apartado.recurso = document.getElementById("id_apar_recurso").value;
  apartado.orden = document.getElementById("id_apar_orden").value;	
  
  console.log(apartado);
  console.log("http://localhost:8080"+"/editarApartado/"+apartado.id+
        "/" +
      apartado.nombre.replaceAll("/", "%F7") +
      "/" +
      apartado.descripcion.replaceAll("/", "%F7") +
      "/" +
      apartado.recurso.replaceAll("/", "%F7") +
      "/" +
      apartado.orden.replaceAll("/", "%F7")
    );
  
  fetch("http://localhost:8080"+"/editarApartado/"+apartado.id+
        "/" +
      apartado.nombre.replaceAll("/", "%F7") +
      "/" +
      apartado.descripcion.replaceAll("/", "%F7") +
      "/" +
      apartado.recurso.replaceAll("/", "%F7") +
      "/" +
      apartado.orden.replaceAll("/", "%F7")
      )
    .then((respuesta) => respuesta.json())
    .then(function (respuesta) {
      if (respuesta.check){
      	console.log("Modificado el apartado");
      	cancelarApartado();
      }else{
      	console.log("KO falla la modificación: "+respuesta.mensaje);
      }
    });
}

function addCapitulo() {
  var capitulo = new Object();
  capitulo.id = document.getElementById("id_curso").value;
  capitulo.nombre = document.getElementById("id_cap_nombre_new").value;
  capitulo.descripcion = document.getElementById("id_cap_descripcion_new").value;
  capitulo.orden = document.getElementById("id_cap_orden_new").value;

  console.log(capitulo);

  fetch(
    "http://localhost:8080" +"/addCapitulo/" +
      capitulo.id +"/" +
      capitulo.nombre.replaceAll("/", "%F7") +"/" +
      capitulo.descripcion.replaceAll("/", "%F7") +"/" +
      capitulo.orden.replaceAll("/", "%F7")
  	)
    .then((respuesta) => respuesta.json())
    .then(function (respuesta) {
      
      if (respuesta.check){
      	cargarCapitulosByCurso();
      	showFormCapitulo();
      	document.getElementById("id_cap_nombre_new").value = "";
  		document.getElementById("id_cap_descripcion_new").value = "";
  		document.getElementById("id_cap_orden_new").value ="";
      }else{
      	console.log(respuesta.mensaje);
      }
    });
}
function showFormApartado() {
  var isClassOculto = document.getElementById("apartado_new").classList.contains("oculto");
  if (isClassOculto) {
    document.getElementById("apartado_new").classList.remove("oculto");
  } else {
    document.getElementById("apartado_new").classList.add("oculto");
  }
}

function addApartado() {
  var apartado = new Object();
  apartado.id = document.getElementById("id_cap_").value;
  apartado.nombre = document.getElementById("id_apar_nombre_new").value;
  apartado.descripcion = document.getElementById("id_apar_descripcion_new").value;
  apartado.recurso = document.getElementById("id_apar_recurso_new").value;
  apartado.orden = document.getElementById("id_apar_orden_new").value;

  fetch(
    "http://localhost:8080" +"/addApartado/" +apartado.id +"/" +
      apartado.nombre.replaceAll("/", "%F7") +"/" +
      apartado.descripcion.replaceAll("/", "%F7") +"/" +
      apartado.recurso.replaceAll("/", "%F7") +"/" +
      apartado.orden.replaceAll("/", "%F7")
  	)
    .then((respuesta) => respuesta.json())
    .then(function (respuesta) {
      console.log("Resultado respuesta: " + respuesta.check);
      if (respuesta.check){
      	getApartadosByCap();
      	showFormApartado();
      	document.getElementById("id_apar_nombre_new").value="";
  		document.getElementById("id_apar_descripcion_new").value="";
  		document.getElementById("id_apar_recurso_new").value="";
  		document.getElementById("id_apar_orden_new").value="";
      }else{
      	console.log(respuesta.mensaje);
      	//TODO: Pintar el pantalla el problema
      }

    });
}
function showFormCapitulo() {
  var isClassOculto = document.getElementById("capitulo_new")
  	.classList.contains("oculto");
  if (isClassOculto) {
    document.getElementById("capitulo_new").classList.remove("oculto");
    document.getElementById("btn_cap_new").classList.remove("btn_green");
    document.getElementById("btn_cap_new").classList.add("btn_red");
    document.getElementById("cursoEditBasic").classList.remove("formCurso");
    document.getElementById("cursoEditBasic").classList.add("oculto");
    //document.getElementById('btn_cap_new').innerHTML = 'Cancelar alta';
  } else {
    document.getElementById("capitulo_new").classList.add("oculto");
    document.getElementById("btn_cap_new").classList.remove("btn_red");
    document.getElementById("btn_cap_new").classList.add("btn_green");
    document.getElementById("cursoEditBasic").classList.remove("oculto");
    document.getElementById("cursoEditBasic").classList.add("formCurso");
    //document.getElementById('btn_cap_new').innerHTML = 'Add Capitulo';
  }
}
function altCurso(){

  var curso = new Object();
  //curso.id = document.getElementById("id_curso").value;
  curso.nombre = document.getElementById("id_name_curso").value;
  curso.descripcion = document.getElementById("id_desc_curso").value;
  curso.fuente = document.getElementById("id_source_curso").value;
  curso.urlimagen = document.getElementById("id_img_curso").value;
  curso.urlicono = document.getElementById("id_icono_curso").value;
  
  fetch("http://localhost:8080" +"/altacurso/" +
      curso.nombre.replaceAll("/", "%F7") +"/" +
      curso.descripcion.replaceAll("/", "%F7") + "/" +
      curso.fuente.replaceAll("/", "%F7") +"/" +
      curso.urlicono +"/" +
      curso.urlimagen
  ).then((respuesta) => respuesta.json())
    .then(function (respuesta) {
    if (respuesta.check){
      console.log("Id Curso: " + respuesta.mensaje);
      document.getElementById("btn_alt").classList.add("oculto");
      document.getElementById("btn_gua").classList.remove("oculto");
      document.getElementById("btn_cap_new").classList.remove("oculto");
      document.getElementById("id_curso").value = respuesta.mensaje;
      
      document.getElementById("alta").classList.remove("active");
      document.getElementById("editar").classList.add("active");
      
      document.getElementById("fileUpload").classList.add("oculto");
      document.getElementById("labelInput").classList.add("oculto");
      document.getElementById("uploadExcel").classList.add("oculto");
      document.getElementById("download_plantilla").classList.add("oculto");    
    } else{
    	console.log("FAIL");
    }

      
  	}
  );

}

function saveCurso() {
  document.getElementById("btn_gua").classList.remove("btn_green");
  document.getElementById("btn_gua").classList.add("btn_red");
  document.getElementById("btn_gua").innerHTML = "Guardando ...";
  setTimeout(
  	function(){ 
  	var curso = new Object();
  	curso.id = document.getElementById("id_curso").value;
  	curso.nombre = document.getElementById("id_name_curso").value;
  	curso.descripcion = document.getElementById("id_desc_curso").value;
  	curso.fuente = document.getElementById("id_source_curso").value;
  	curso.urlimagen = document.getElementById("id_img_curso").value;
  	curso.urlicono = document.getElementById("id_icono_curso").value;

  	curso.urlicono = curso.urlicono.replaceAll("/", "%F7");
  	curso.urlicono = curso.urlicono.replaceAll("?", "%24");
  	curso.urlimagen = curso.urlimagen.replaceAll("/", "%F7");
  	curso.urlimagen = curso.urlimagen.replaceAll("?", "%24");

  	fetch("http://localhost:8080" +"/editcurso/" + curso.id+ "/" +
      curso.nombre.replaceAll("/", "%F7") +"/" +
      curso.descripcion.replaceAll("/", "%F7") + "/" +
      curso.fuente.replaceAll("/", "%F7") +"/" +
      curso.urlicono +"/" +
      curso.urlimagen
  	).then((respuesta) => respuesta.json())
      .then(function (respuesta) {
      document.getElementById("btn_gua").classList.remove("btn_red");
      document.getElementById("btn_gua").classList.add("btn_green");
      document.getElementById("btn_gua").innerHTML = "Guardar datos";
      

   });
  	
  }, 2000);
}
function cancelarCapitulo() {
  
  document.getElementById("capSelect").classList.add("oculto");

  document.getElementById("cursoEditBasic").classList.remove("oculto");
  document.getElementById("cursoEditBasic").classList.add("formCurso");

  document.getElementById("selectCap").selectedIndex = 0;
  
  //apartado metodo?
  document.getElementById("aparSelect").classList.add("oculto");
  document.getElementById("selectApartado").selectedIndex = 0;
}
function selectEditarApartado(id){
	document.getElementById("aparSelect").classList.remove("oculto");
	var idCapSelect = document.getElementById('id_apar_').value;
	console.log(id);
	fetch("http://localhost:8080" + "/getApartado/" + id)
    .then((apartado) => apartado.json())
    .then(function (apartado) {
      console.log("Respuesta Get Apartado");
      document.getElementById("id_apar_").value = id;
      document.getElementById("id_apar_nombre").value = apartado.nombre;
      document.getElementById("id_apar_descripcion").value = apartado.descripcion;
      document.getElementById("id_apar_orden").value = apartado.orden;
      
    });
	
}
function cancelarApartado(){
	document.getElementById("aparSelect").classList.add("oculto");
  	document.getElementById("selectApartado").selectedIndex = 0;
}
function getApartadosByCap(){
	//recuperamos el value del cap seleccionado
  var idCapSelect = document.getElementById('id_cap_').value;

  fetch("http://localhost:8080" + "/getApartadosByCap/" + idCapSelect)
    .then((apartados) => apartados.json())
    .then(function (apartados) {
    	const selectCampos = document.querySelector("#selectApartado");
    	const $select = document.getElementById("selectApartado");

	    for (let i = $select.options.length; i >= 1; i--) {
            $select.remove(i);
        }
      
		for (x=0; x<Object.keys(apartados).length;x++){	
			var option = document.createElement('option');
			
        	option.value = apartados[x].id;
        	option.text = apartados[x].nombre;       
        	option.id = apartados[x].id;
        	selectCampos.appendChild(option);
		}
    });

}

function selectEditarCapitulo(id) {
 	
  //document.getElementById("apartadoList").classList.remove("oculto");
  document.getElementById("capSelect").classList.remove("oculto");
  document.getElementById("cursoEditBasic").classList.add("oculto");
  document.getElementById("cursoEditBasic").classList.remove("formCurso");
  console.log(id);
  console.log(document.getElementById("cursoEditBasic"));
  console.log("http://localhost:8080" + "/getCapitulo/" + id);

  fetch("http://localhost:8080" + "/getCapitulo/" + id)
    .then((capitulo) => capitulo.json())
    .then(function (capitulo) {
      console.log("Respuesta Get Capitulo");
      document.getElementById("id_cap_").value = id;
      document.getElementById("id_cap_nombre").value = capitulo.nombre;
      document.getElementById("id_cap_descripcion").value = capitulo.descripcion;
      document.getElementById("id_cap_orden").value = capitulo.orden;
      getApartadosByCap();// Actualizo los apartados by cap seleccionado
      cancelarApartado(); // Y quito el apartado seleccionado
      
    });
}

function deleteCapitulo() {
  var id = document.getElementById("id_cap_").value;

  console.log("http://localhost:8080" + "/deleteCapitulo/" + id);

  fetch("http://localhost:8080" + "/deleteCapitulo/" + id)
    .then((respuesta) => respuesta.json())
    .then(function (respuesta) {
      console.log("Resultado respuesta: " + respuesta.check);
      if (respuesta.check){
      	cargarCapitulosByCurso();
      	cancelarCapitulo();
      }else{
      	console.log(respuesta.mensaje);
      }
    });
}
function deleteApartado(){
  var id = document.getElementById("id_apar_").value;
  console.log("http://localhost:8080" + "/deleteApartado/" + id);

  fetch("http://localhost:8080" + "/deleteApartado/" + id)
    .then((respuesta) => respuesta.json())
    .then(function (respuesta) {
      if (respuesta.check){
      	getApartadosByCap();// Actualizo los apartados by cap seleccionado
      	cancelarApartado(); // Y quito el apartado seleccionado
      }else{
      	console.log(respuesta.mensaje);
      }
    });	
}

function formatDivision(cadena) {
  const nuevaStr = cadena.replaceAll("/", "%F7");
}
cargarCapitulosByCurso();

function importCurso(){
	location.href = "importCurso";
	
}

var inputs = document.querySelectorAll( '.inputfile' );
Array.prototype.forEach.call( inputs, function( input ) {
	var label	 = input.nextElementSibling,
		labelVal = label.innerHTML;
	console.log("Test");
	input.addEventListener( 'change', function( e )	{
	console.log("Test");
		var fileName = '';
		console.log(this.files.name);
		const fileList = e.target.files;
    	console.log(fileList);
    	
    	document.getElementById('labelInput').innerHTML =
    		document.getElementById('fileUpload').files[0].name;
    	document.getElementById('uploadExcel').removeAttribute("disabled");
		
    	
    	
    	var lector = new FileReader();
	  	lector.onload = function(e) {
	  		console.log("TEST");

	  	};
    	console.log(lector);
    	    var TmpPath = URL.createObjectURL(e.target.files[0]);
    // Mostramos la ruta temporal
    console.log(TmpPath);

	});
});
// test btn




// Test envio desde formulario

$(document).ready(function() {
    $("#form").submit(function(event) {
        event.preventDefault();

        // Crea un objeto FormData para enviar el archivo
        var formData = new FormData();
        formData.append("archivo", $("#archivo")[0].files[0]);
console.log(formData);
        // Realiza la petición AJAX
        $.ajax({
            url: "fichero.html" , // Cambia la URL al archivo PHP que procesará el archivo
            type: "POST",
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function(response) {
                var resultado = $.parseJSON(response);
                console.log(resultado);
            },
            error: function() {
                console.log("Error al enviar el archivo.");
            }
        });
    });
});
