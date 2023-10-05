const OK_ = true;
const KO_ = false;
let idCampo ="";
let container = "";
let classCampo = "";
let where = "";
let condicion = 'AND';
let sino = false;//quitar 
//query
let tipoQuery='';
let queryCampoWhere = 'queryCampoWhere';
let queryCampoWhereResult = 'queryCampoWhereResult';

let OR = 'OR'; let AND = 'AND'; let MenosIZQ = 'MenosIZQ'; let MasIZQ = 'MasIZQ'; let ParentIZQ ='ParentIZQ';
let RESULT = 'RESULT'; let ParentDER = 'ParentDER'; let MenosDER = 'MenosDER'; let MasDER ='MasDER';
let URL_SERVICIO_REST = "http://localhost:8080/campos/";



//var btnLanzarSelect = document.getElementById("btnLanzarSelect");

const selectCampos = document.querySelector("#idCampoSelect"); //cb select option
const selectCamposWhere = document.querySelector("#idCampoSelectWhere"); //cb select option where
/// default no es cambio de tabla.
var cambioTable=false;
let camposAndTipos;
let campo_tipo = 'campo_tipo';


// CLEAN_CODE_OK : unica tarea NO FUNCIONA PIERDO EL JSON CON LOS DATOS
async function getCamposAndTiposByTable(tabla){
    let response;
    try {
        response =  await
        fetch("http://localhost:8080/campos/"+tabla)
       .then((camposAndTipos) => camposAndTipos.json())
       .then(
           function (camposAndTipos){    
            if (Object.keys(camposAndTipos).length<1){
                return KO_
            }
            return camposAndTipos;
        }
        );
    } catch (error) {
        return error;
    }
    return console.log(response); // TODO: de Promise {<pending>} a jSon con console.log lo recupero.
}



const agregar = () => {// el btn esta comentado nada llama a este codigo solo esta a modo lectivo 

    //TODO: Temporal pruebas desde getTabla() //TODO: borrar
    for (let i = selectCampos.options.length; i >= 0; i--) {
        
        selectCampos.remove(i);
    }
    const option = document.createElement('option');
    const valor = new Date().getTime();
    option.value = valor;
    option.text = valor;
    selectCampos.appendChild(option);
  };


//document.addEventListener("DOMContentLoaded", () => {    document.querySelector("#btnAgregar").addEventListener("click", agregar);  });
/*
  Se activan unos componentes u otros en funcion de la opción del select que se elija "Select Update o Insert"
*/
function selectOption(valor){
    tipoQuery = valor;
    switch (valor) {
        case 'select':
          document.getElementById("select").style.display = "inline-block";
          document.getElementById("insert").style.display = "none";
          document.getElementById("update").style.display = "none";
          idCampo ="idCampoSelect";
          container = "containerSelect";
          classCampo = "campoSelect";
          break;
        case 'insert':
            document.getElementById("select").style.display = "none";
            document.getElementById("insert").style.display = "inline-block";
            document.getElementById("update").style.display = "none";
            idCampo ="idCampoInsert";
            container = "containerInsert";
            classCampo = "campoInsert";
          break;
        case 'update':
            document.getElementById("select").style.display = "none";
            document.getElementById("insert").style.display = "none";
            document.getElementById("update").style.display = "inline-block";
            idCampo ="idCampoUpdate";
            container = "containerUpdate";
            classCampo = "campoUpdate";
          break;
        default:
            console.log('Cagada: '+valor);
   
      }
}
//selectOption('select'); // TODO: SOLO EN FASE-DESARROLLO


/*
    FASE: TEST-FINAL
    OBJETIVO: Rellenar los cb's de campos según la tabla seleccionada
    PASOS:
        1. Borrammos el posible btn tabla.
        2. Creamos el nuevo btn de la nueva tabla
        3. Cambiamos el valor al cb select tabla.
        4. Borramos los valores de los cb's select
        5. rellenamos los cb's select consultando al servicio API REST
        6. Limpiamos todos los posibles btn field and where
*/
function rellenaFormByTable(tabla){
    
    limpiarBtnTabla('btnTable');
    insertElements(document.querySelector('#'+'containerSelectTable'),'button',
        'type','button',
        'class','btnTable',
        'value',tabla,
        'id',tabla,
        '',''
    );
	document.getElementById('QueryTable').value = 'Select Table';

	
    if (!cleanSelectsOption()){
        console.log("[Error] limpiando los datos de los select antes de recargar lo datos nuevos");
    }

    
    // Servicio Rest para recuperar los campos y tipos y rellenar los select option.
    fetch(URL_SERVICIO_REST+tabla)
     //fetch('./js/campos.json')
    .then((camposAndTipos) => camposAndTipos.json())
    .then(
    	function (camposAndTipos){
            campo_tipo = camposAndTipos;
            
			// Rellenamos el select con lo recupera del servicio REST
            if (!rellenarSelect(camposAndTipos)){
                console.log("[Error] rellenando el select option [STOP]. Ver como trartar esto");
                return KO_
            }
            if (!rellenarSelectWhere(camposAndTipos)){
                console.log("[Error] rellenando el select combo del where. Ver como trartar esto");
                return KO_
            }
    		
    	}
    )

    // Limpiamos los btn de los campos al cambiar de tabla.
    cambioTable=true;
    limpiarBtnAll('campoSelect');   //TODO: CLEAN CODE
    
    limpiarBtnAll('Where');         //TODO: CLEAN CODE
    // si es cambio de tabla no recuperamos los campos al cb select option
    cambioTable=false; // chapu
    ocultarTextarea();
}

// CLEAN CODE: Unica tarea limpiar los cb select Option
function cleanSelectsOption(){
    
    for (let i = selectCampos.options.length; i >= 0; i--) {
        selectCampos.remove(i);
    }

    //selectCamposWhere
    for (let i = selectCamposWhere.options.length; i >= 0; i--) {
        selectCamposWhere.remove(i);
    }

    return OK_
}


// IMP Clean Code unica tarea
// Se rellena el select option con los campos que se le pasan
// Return String status OK al finalizar
function rellenarSelect(camposAndTipos){
    const selectCampos = document.querySelector("#idCampoSelect"); //cb select option
    var option = document.createElement('option');
    option.value = 'Añadir Campo';
    option.text = 'Añadir Campo';
    option.id = 'add';
    selectCampos.appendChild(option);
    var option = document.createElement('option');
    option.value = '*';
    option.text = '*';
    option.id = '*';
    selectCampos.appendChild(option);

    //campo_tipo = camposAndTipos; Mas dificil de mantener
        // TODO: Recuperar el tipo de campo
    for (x=0; x<Object.keys(camposAndTipos).length;x++){
        var option = document.createElement('option');
        option.value = camposAndTipos[x].campo;
        option.text = camposAndTipos[x].campo;
        
        option.id = 'idCampoSelect'+camposAndTipos[x].campo;
        selectCampos.appendChild(option);
    }

    return OK_

}
/*
    CLEAN CODO: Una función con tarea unica
    Rellenar el select opción del form where
    1. Se añaden las opciones por defecto al selectWhere 'Añadir Campo'
    2. Recorremos el listado de campos y los vamos añadiendo al select
        2.1. Tenemos en cuenta que tipo de campo es
*/
function rellenarSelectWhere(camposAndTipos){
			// Rellenamos el select con lo recupera del servicio REST
    const selectCamposWhere = document.querySelector("#idCampoSelectWhere"); //cb select option where
    var option = document.createElement('option');
        option.value = 'Añadir Campo';
        option.text = 'Añadir Campo';
        option.id = 'add';
    selectCamposWhere.appendChild(option);

    selectCamposWhere.appendChild(option);

    for (x=0; x<Object.keys(camposAndTipos).length;x++){
        var option = document.createElement('option');
            //console.log(camposAndTipos[x].campo+' '+camposAndTipos[x].tipo)
            
    		option.value = camposAndTipos[x].campo;
    		option.text = camposAndTipos[x].tipo;
            //option.name = 'name';
            option.label =camposAndTipos[x].campo;
            //option.class = 'clas';
            option.id = 'idCampoSelect'+camposAndTipos[x].campo;
        selectCamposWhere.appendChild(option);
    }
    
    return OK_
}
function limpiarBtnTabla(filtro){
    var btns = document.querySelectorAll('.'+filtro);
    for (var i = 0; i < btns.length; i++) {
        document.querySelector('#'+btns[i].id).remove(); 
    }
}

// CLEAN CODE: Cambiar nombre mas descriptivo --> insertFieldAndRemoveOption Opción select || insertField opción Where 
// CLEAN CODE: 2 Metodos distintos.
/*  FASE: TEST-FINAL
    Objetivo: Crear los campos btn de los filtros [Paso 2] Diferenciar el tipo de field
    0. Recuperamos el value del opción con el id del select Where
    1. Creamos divHijo dentro del divPadre
    2. Creamos divcondicion dentro del divHijo
    3. Creamos el btn "where, AND u OR" en divcondicion segun el que corresponda
    4. Incrementamos id a los btn repetidos
    5. creamos los parentesis y btn (+) y (-)
        5.1. Recuperar el tipo_field ¿*? getTipoByCampo(campo);
        5.2. Select case para crear distintos tipos de bnt en funcion del tipo recuperado.

*/
function insertField(idSelect){
    var id=document.getElementById(idSelect).value;
    var addClass='Where';
    var isOR = false;
    const ORIid=id;
    where='Where';
    //localizar el select option para recuperar el tipo_field
    var tipo_field=getTipoByCampo(document.getElementById(idSelect).value);
    campo_tipo;//borrar



    
 
    
    const divPadre = document.querySelector('#'+container+where);
    insertElements(divPadre,'div',
        '','',
        'class','TEST-NoClass->classCampo',
        'value','',
        'id',idCampo+where+'-div-'+id,
        '',''
    );
    const divHijo = document.querySelector('#'+idCampo+where+'-div-'+id);

    var btns = document.querySelectorAll('.'+'ORI'+ORIid);
    var preId= id;
    var oldId=id; // genero el id anterior para borrar el parentesis cuando sea OR
    for (var i = 0; i < btns.length; i++) {
        preId=preId+''+btns[0].textContent;
        if (i < btns.length-1){
            oldId=oldId+''+btns[0].textContent;
        } 
    }

    
    insertElements(divHijo,'div',
        '','',
        'class',classCampo+'Where',
        'value','',
        'id',idCampo+where+'-condicion-'+preId,
        '',''
    );
    const divCondicion = document.querySelector('#'+idCampo+where+'-condicion-'+preId);

    // btn OR AND Where
    if(document.querySelectorAll('.'+'Where').length>0){
        if (document.getElementById( idCampo+where+AND+id )){
            insertElements(divCondicion,'button',
                'type','button',
                'class',classCampo+'Where'+' queryCondicion',
                'value',OR,
                'id',idCampo+where+condicion+preId,
                'onclick','AndOr(this.id)'
            );
            // delete parentesis de cierre anterior para OR
            getDereMenos(oldId);// test ok
            isOR=true;// para no crear el parentesis inicial en pag 213
        }else{
            insertElements(divCondicion,'button',
                'type','button',
                'class',classCampo+'Where'+' queryCondicion',
                'value',AND,
                'id',idCampo+where+AND+id,
                'onclick','AndOr(this.id)'
            );
        }
    }else{
        insertElements(divCondicion,'button',
            'type','button',
            'class',classCampo+'Where'+' queryCondicion',
            'value','Where',
            'id',idCampo+where+condicion+preId,
            '',''
        );
    } 
    // Incrementamos id a los btn repetidos
    if (document.getElementById( idCampo+where+condicion+id ) ){

        // for uno por cada btn esto solo funciona si el btn or es el primero.
        var btns = document.querySelectorAll('.'+'ORI'+ORIid);
        // Incrementamos el id a los campos repetidos.
        for (var i = 0; i < btns.length; i++) {
               
            id=id+''+btns[0].textContent;    
        }
    }

    // creamos los parentesis y btn (+)y (-) field 
    
    // btn MenosIZQ
    insertElements(divHijo,'button',
        'type','button',
        'class',classCampo+'Where',
        'value','-',
        'id',id,
        'onclick','getIzqMenos(this.id)'
    );
    
    // btn MasIZQ
    insertElements(divHijo,'button',
        'type','button',
        'class',classCampo+'Where',
        'value','+',
        'id',id,
        'onclick','getIzqMas(this.id)'
    );
    // btn ParentIZQ
    insertElements(divHijo,'button',
        'type','button',
        'class',classCampo+'Where'+' queryParentIzq',
        'value','(',
        'id',idCampo+where+ParentIZQ+id,
        '',''
    );
    // btn campo 'ORI'+ORIid
    insertElements(divHijo,'button',
        'type','button',
        'class',classCampo+'Where'+ ' btn '+addClass+' ORI'+ORIid +' '+queryCampoWhere, // id origen
        'value',ORIid,
        'id',idCampo+where+id,
        'onclick','quitarBtnAddOptionWhere(this.value)'
    );
    // cuando es OR decrementar el parentesis de apertura (
    if (isOR){
        getIzqMenos(preId);
    }
    // input RESULT TODO: FASE 2: Select case según el tipo de fiel.
    insertElements(divHijo,'input',
        'type',convertTypeSqlToTypeHtml(tipo_field),
        'class',classCampo+'Where'+' '+queryCampoWhereResult,
        'value',getValueByTypeSQL(tipo_field),
        'id',idCampo+where+RESULT+id,
        '',''
    );
    //btn ParentDERE
    insertElements(divHijo,'button',
        'type','button',
        'class',classCampo+'Where'+' queryParentDer',
        'value',')',
        'id',idCampo+where+ParentDER+id,
        '',''
    );
    //btn MenosDERE
    insertElements(divHijo,'button',
        'type','button',
        'class',classCampo+'Where',
        'value','-',
        'id',id,
        'onclick','getDereMenos(this.id)'
    );
    // btn MasDER
    insertElements(divHijo,'button',
        'type','button',
        'class',classCampo+'Where',
        'value','+',
        'id',id,
        'onclick','getDereMas(this.id)'
    );

    document.getElementById(idCampo+where).value = 'Añadir Campo';

}

/*
    FASE: INICIAL
    Recuperar el tipo del campo que se pasa

*/
function getTipoByCampo(campo){

    for (x=0; x<Object.keys(campo_tipo).length;x++){
        if(campo ===campo_tipo[x].campo){
            tipo = campo_tipo[x].tipo;
            x=Object.keys(campo_tipo).length;
        }
    }
    return tipo;
}
/*
    FASE: INICIAL
    Se le pasa un tipo de SQL y recupera el de html para el input

*/
function convertTypeSqlToTypeHtml(typeSQL){
    
    let typeHtml = 'FAIL';
    switch (typeSQL) {
    case 'varchar':
        console.log('varchar convert to text');
        typeHtml='text';
        break;
    case 'int':
        console.log('int convert to number');
        typeHtml='number';
        break;
    case 'bit':
        console.log('bit convert to number');
        typeHtml='number';
        break;
    case 'datetime2':
        console.log('datetime2 convert to date');
        typeHtml='date';
        break;

    case 'nvarchar':
        console.log('nvarchar convert to text');
        typeHtml='text';
        break;
    default:
        console.log(`Sorry, we are out of ${typeSQL}. Convert to text by default.`);
        typeHtml='text';
    }

    return typeHtml;
}

/*
    FASE: INICIAL
    Se le pasa un tipo de SQL y recupera el valor acorde para pintar el el input

*/
function getValueByTypeSQL(typeSQL){
    
    
    switch (typeSQL) {
    case 'varchar':
        console.log('Tipo varchar');
        return 'Escriba aquí'
    case 'int':
        console.log('Tipo int');
        return 0;
    case 'bit':
        console.log('Tipo bit');
        return 0;
    case 'datetime2':
        console.log('Tipo datetime2');
        return formatoFecha(new Date(),'dd/mm/yyyy');
    case 'nvarchar':
        console.log('Tipo nvarchar');
        return 'Escribe aquí'
    default:
        console.log(`Sorry, we are out of ${typeSQL}. Convert to text by default.`);

    }

    return "FAIL";
}
/*
    FASE: INICIAL
    Dada una fecha y el formato recuperas la fisma formateada
    Tipos parametros: new Date(), formato yy-dd-mm, yyyy-dd-mm, dd-mm-yy
*/
function formatoFecha(fecha, formato) {
    const map = {
        dd: fecha.getDate(),
        mm: fecha.getMonth() + 1,
        yy: fecha.getFullYear().toString().slice(-2),
        yyyy: fecha.getFullYear()
    }

    return formato.replace(/dd|mm|yy|yyy/gi, matched => map[matched])
}


/*  FASE: TEST-FINAL
    Para insertar el campo elegido:
    0. Recuperamos el value del opción con el id del select
        0.1 - Si el field es * recuperamos igual pero con buble para todas las opciones del select
    1. Creamos divHijo dentro del divPadre
    2. Creamos btn en divHijo
    3. Borramos la opción del select elegida
*/
function  insertFieldAndRemoveOption(idSelect){

    var field=document.getElementById(idSelect).value;
    var addClass='queryCampo';
    var where = '';
    var veces;
    if(field == '*'){
        const divPadre = document.querySelector('#'+container+where);
        const $select = document.getElementById(idCampo+where);
        for (var option of document.getElementById(idCampo+where).options) {
            if (option.value != 'Añadir Campo' &&  option.value != '*'){
                insertElements(divPadre,'div',
                '','',
                'class','TEST-NoClass->classCampo',
                'value','',
                'id',idCampo+where+'-div-'+option.value,
                '','');
                const divHijo = document.querySelector('#'+idCampo+where+'-div-'+option.value);
    
                // btn selec
                insertElements(divHijo,'button',
                    'type','button',
                    'class',classCampo+ ' btn '+addClass,
                    'value',option.value,
                    'id',idCampo+where+option.value,
                    'onclick','quitarBtnAddOptionSelect(this.value)'
                );
            }
        }
    
        for (let i = $select.options.length; i >= 1; i--) {
            $select.remove(i);
        }
    }else{
        const divPadre = document.querySelector('#'+container+where);
        insertElements(divPadre,'div',
            '','',
            'class','TEST-NoClass->classCampo',
            'value','',
            'id',idCampo+where+'-div-'+field,
            '',''
        );
        const divHijo = document.querySelector('#'+idCampo+where+'-div-'+field);
        insertElements(divHijo,'button',
            'type','button',
            'class',classCampo+ ' btn '+addClass, // id origen solo en Where
            'value',field,
            'id',idCampo+where+field,
            'onclick','quitarBtnAddOptionSelect(this.value)'
        );
        document.querySelector('#'+idCampo+where+field).remove();
    }
}



/*Param 1 Contenedor donde se creara el elemento */
/*Param 2 Nombre del elemento a crear */
/*Param 3 - 4  Tipo de btn y el nombre del mismo*/
/*Param 5 - 6  Nombre de la class y su valor*/
/*Param 7 - 8  Campo value y su contenido*/
/*Param 9 - 10 Campo id y su contenido*/
/*Param 11- 12 Campo funcion y su contenido*/

function insertElements(
/*Param 1 */            container,
/*Param 2 */            elementName,
/*Param 3 - 4  */       tipoBtnName,tipoBtnValue,
/*Param 5 - 6  */       className,classValue,
/*Param 7 - 8  */       valueName,valueValue,
/*Param 9 - 10 */       idName,idValue,
/*Param 11- 12 */       functionName,functionValue,
			/* Borrar */
/*Param 13- 14 */		rowsName, rowsValue,
/*Param 15- 16 */		colsName, colsValue,

/*Param 17- 18 */		hrefName, hrefValue,

/*Param 19- 20 */		xmlnsName, xmlnsValue,
/*Param 21- 22 */		fillName, fillValue,
/*Param 23- 24 */		viewBoxName, viewBoxValue,
/*Param 25- 26 */		strokeName, strokeValue,

/*Param 27- 28 */		stroke_linecapName, stroke_linecapValue,
/*Param 29- 30 */		stroke_linejoinName, stroke_linejoinValue,
/*Param 31- 32 */		stroke_widthName, stroke_widthValue,
/*Param 33- 34 */		dName, dValue,

/*Param 35- 36 */		styleName, styleValue

) { //recibe el div
	
    const btn = document.createElement(elementName);
    
    if(styleName != ''){
    	btn.setAttribute(styleName,styleValue);
    }

    if (hrefName != ''){
    	btn.setAttribute(hrefName,hrefValue);   
    }	
    
    if (xmlnsName != ''){
    	btn.setAttribute(xmlnsName,xmlnsValue); 
    	btn.setAttribute(fillName,fillValue);  
    	btn.setAttribute(viewBoxName,viewBoxValue);
    	btn.setAttribute(strokeName,strokeValue); 
    }
    if (stroke_linecapName != ''){
    	btn.setAttribute(stroke_linecapName,stroke_linecapValue); 
    	btn.setAttribute(stroke_linejoinName,stroke_linejoinValue); 
    	btn.setAttribute(stroke_widthName,stroke_widthValue); 
    	btn.setAttribute(dName,dValue); 
    	  
    }	

    //console.log('btn: '+btn);
    if (valueName != ''){
    	btn.setAttribute(valueName,valueValue);   
    }
    btn.setAttribute(idName,idValue); 

    if(className != ''){
        btn.setAttribute(className, classValue);
    }
    if (functionName != ''){
        btn.setAttribute(functionName,functionValue);
    }
    if (tipoBtnName != ''){
        btn.setAttribute(tipoBtnName,tipoBtnValue);
    }
    if (rowsName != ''){
    	btn.setAttribute(rowsName,rowsValue);
    }
    if (colsName != ''){
    	btn.setAttribute(colsName,colsValue);
    }  
    
    btn.innerHTML = valueValue;
    container.appendChild(btn); //añadido
}

function quitarBtnAddOptionWhere(id){
    where='Where';
    quitarBtnAddOption(id);
}
function quitarBtnAddOptionSelect(id){
    where='';
    quitarBtnAddOption(id);
}

  
function quitarBtnAddOption(id){
    //console.log('quitarBtnAddOption( '+id);
    var all=true;
    for (var option of document.getElementById(idCampo+where).options) {
        // buscamos el * en el cb select option si lo encontramos no hay que añadirlo.
        if (option.value == '*'){
            all=false;
        }
    }
    if (all && !cambioTable){
        addOption('*');
    }
    //console.log('Borrando btn--> '+'#'+idCampo+where+id+' - ');
    document.querySelector('#'+idCampo+where+id).remove();
    if (idCampo == "idCampoInsert"){
        document.querySelector('#'+idCampo+'-input'+id).remove();
    }
    //console.log(' Borrando Div: '+'#'+idCampo+where+'-div-'+id);
   document.querySelector('#'+idCampo+where+'-div-'+id).remove();
   
   // solo añadimos los campos al select si no cambiamos de tabla.
   //console.log('cambioTable:'+cambioTable)
   if(!cambioTable){
    //console.log('No cambiamos de tabla.')
    addOption(id);
   }
    
}
// opciones getTabla --> 'campoSelect' o 'Where'
function limpiarBtnAll(filtro){

    var btns = document.querySelectorAll('.'+filtro);
    console.log('filtro: '+filtro);
    console.log('btn: '+document.querySelectorAll('.'+filtro));
    console.log('length btns: '+btns.length);



    for (var i = 0; i < btns.length; i++) {
        //console.log(' quitarbtnAddOption '+btns[i].textContent);
        quitarBtnAddOption(btns[i].textContent);    
    }

}
function addOption(valor){
    console.log('Pag 596 Valor: '+valor)
  const $select = document.getElementById(idCampo+where);
  const option = document.createElement('option');
  option.value = valor;
  option.text = valor;
  option.id = idCampo+where+valor;

  $select.appendChild(option);
}
function AndOr(id){
    if ( document.getElementById(id).value == 'AND') {
        document.getElementById(id).value = 'OR';
        document.getElementById(id).innerText = 'OR';
    }else{
        document.getElementById(id).value = 'AND';
        document.getElementById(id).innerText = 'AND';
    }
}
function lanzarSelect(){
    console.log("Pendiente crear la consulta.");
    


}

// Se amplia el tamaño con jQuery  --> $('input[type="text"]').on('keyup', resizeInput).each(resizeInput);
function resizeInput() {
  
    var valueLength = $(this).prop('value').length;
    
      // Para que no arroje error si el input se vacía
      if (valueLength > 0) {
        
        $(this).prop('size', valueLength);
      }
  }
  
  idCampoSelect.addEventListener("click", () =>{
    console.log(' Text')
  

 
  });

btnLanzarSelect.addEventListener("click", () =>{


    
    console.log(tipoQuery);
    // campos
    let resultQuery=tipoQuery+' ';
    let jCamposIncio = '{"ArrayCampos": [';
    let jCamposFin = '],"status": "OK"}';
    
    var campos = document.querySelectorAll('.queryCampo');

    var primero=true;
    for ( var i = 0; i < campos.length; i++){
        
        if(!primero){
            console.log(',');
            resultQuery=resultQuery+' , ';
            
            jCamposIncio=jCamposIncio+',{"campo": "'+campos[i].textContent+'"}'
        }else{
            jCamposIncio=jCamposIncio+'{"campo": "'+campos[i].textContent+'"}'
        }
        console.log(campos[i].textContent);
        resultQuery=resultQuery+campos[i].textContent+' ';
        
        primero=false;
    }
    
    console.log('from');
    resultQuery=resultQuery+' from ';
    console.log(document.querySelector('.btnTable').value);
    resultQuery=resultQuery+document.querySelector('.btnTable').value;
    let jsonTest = JSON.parse(jCamposIncio+jCamposFin);

    console.log(resultQuery);
    console.log(jsonTest);
    // campos where 
    //console.log('Where');
    var camposWhere = document.querySelectorAll('.queryCampoWhere');
    var camposWhereResult = document.querySelectorAll('.queryCampoWhereResult');
    var queryCondicion = document.querySelectorAll('.queryCondicion');
    var queryParentIzq = document.querySelectorAll('.queryParentIzq');
    var queryParentDer = document.querySelectorAll('.queryParentDer');
    for ( var i=0; i < camposWhere.length; i++){
        console.log(queryCondicion[i].value);
        resultQuery=resultQuery+' '+queryCondicion[i].value+' ';
        console.log(queryParentIzq[i].value +' ' + camposWhere[i].textContent+' = '+camposWhereResult[i].value+' ' +queryParentDer[i].value);
        resultQuery=resultQuery+queryParentIzq[i].value +' ' + camposWhere[i].textContent+' = '+camposWhereResult[i].value+' ' +queryParentDer[i].value;
        
    }
    // input donde mostrar la query div-result-query

    
    //document.querySelector('#'+'div-result-query').remove();
    const divResult = document.querySelector('#'+'div-result-contenido');
    const divResultCrud = document.querySelector('#'+'div-result-crud');

    if(document.querySelector('#'+'In-divResult')){
        console.log('Yes')
        //document.querySelector('#'+'In-divResult').textContent='Yes'
        document.getElementById('In-divResult').value = resultQuery
        document.getElementById('In-divResult').innerText = resultQuery
    }else{
        /*Param 1 Contenedor donde se creara el elemento */
        /*Param 2 Nombre del elemento a crear */
        /* Nombre atributo y su contenido */
        /*Param 3 - 4  Atributo style y su valor*/
        /*Param 5 - 6  Atributo class y su valor*/
        /*Param 7 - 8  Atributo value y su contenido*/
        /*Param 9 - 10 Atributo id y su contenido*/
        /*Param 11- 12 Atributo funcion y su contenido*/
        // Textarea para mostrar la consulta SQL
        insertElements(divResult,'textarea',
        'style',"height: 500px;width: 500px;",
        'class','textarea-sql',
        'text',resultQuery,
        'id','In-divResult',
        '',''
        );  
        // btn para ocultar el textarea de la consulta
        insertElements(divResultCrud,'button',
        'type','button',
        'class','btn-ocultar-sql',
        'value','Ocultar SQL',
        'id','btn-ocultar-sql',
        'onclick','ocultarTextarea()'
    );     
    }

    $('input[type="text"]').on('keyup', resizeInput).each(resizeInput);
    
});

function ocultarTextarea(){
    console.log('pag 734 '+document.querySelectorAll('.'+'btn-ocultar-sql').length)
    if (document.querySelectorAll('.'+'btn-ocultar-sql').length>0){
        document.querySelector('#'+'btn-ocultar-sql').remove();
        document.querySelector('#'+'In-divResult').remove();
    }

}

// incrementos decrementos de parentesis
// Falta que los campos OR se indivizualicen los id's

function getIzqMas(id){
    document.getElementById(idCampo+where+ParentIZQ+id).style.display ='inline';
    document.getElementById(idCampo+where+ParentIZQ+id).value = document.getElementById(idCampo+where+ParentIZQ+id).value +'(';
    document.getElementById(idCampo+where+ParentIZQ+id).innerText = document.getElementById(idCampo+where+ParentIZQ+id).innerText+'(';
}
function getIzqMenos(id){
    if (document.getElementById(idCampo+where+ParentIZQ+id).innerText == '(' || document.getElementById(idCampo+where+ParentIZQ+id).innerText==''){
        document.getElementById(idCampo+where+ParentIZQ+id).style.display = 'none';
    }  
    document.getElementById(idCampo+where+ParentIZQ+id).innerText 
        = document.getElementById(idCampo+where+ParentIZQ+id).innerText.slice(0, -1);
    document.getElementById(idCampo+where+ParentIZQ+id).value 
        = document.getElementById(idCampo+where+ParentIZQ+id).value.slice(0, -1);
}
function getDereMas(id){
    document.getElementById(idCampo+where+ParentDER+id).style.display ='inline';
    document.getElementById(idCampo+where+ParentDER+id).value = document.getElementById(idCampo+where+ParentDER+id).value +')';
    document.getElementById(idCampo+where+ParentDER+id).innerText = document.getElementById(idCampo+where+ParentDER+id).innerText+')';
}
function getDereMenos(id){
    if (document.getElementById(idCampo+where+ParentDER+id).innerText == ')' || document.getElementById(idCampo+where+ParentDER+id).innerText==''){
        document.getElementById(idCampo+where+ParentDER+id).style.display = 'none';
    } 
    document.getElementById(idCampo+where+ParentDER+id).innerText 
        = document.getElementById(idCampo+where+ParentDER+id).innerText.slice(0, -1);;
    document.getElementById(idCampo+where+ParentDER+id).value 
        = document.getElementById(idCampo+where+ParentDER+id).value.slice(0, -1);      
}
function sowLog(value){
    if (value == 'Mostrar Log'){
        document.querySelector('#sowlog').textContent = 'Ocultar Log';
        document.querySelector('#sowlog').value = 'Ocultar Log';
        document.querySelector('#idlog').style.display = 'inline';
    }else{
        document.querySelector('#sowlog').textContent = 'Mostrar Log';
        document.querySelector('#sowlog').value = 'Mostrar Log';
        document.querySelector('#idlog').style.display = 'none';
    }
}



	    $(document).ready(function() {
	        //Asegurate que el id que le diste a la tabla sea igual al texto despues del simbolo #
	        console.log("DataTable Check- pag550");
	        $('#userList').DataTable();
	        $('#todoList').DataTable();
	    } );
	