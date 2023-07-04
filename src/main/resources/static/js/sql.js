console.log("sql.jss");
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

var btnLanzarSelect = document.getElementById("btnLanzarSelect");

const selectCampos = document.querySelector("#idCampoSelect"); //cb select option
const selectCamposWhere = document.querySelector("#idCampoSelectWhere"); //cb select option where
/// default no es cambio de tabla.
var cambioTable=false;


const agregar = () => {// el btn esta comentado nada llama a este codigo solo esta a modo lectivo 

    //TODO: Temporal pruebas desde getTabla() //TODO: borrar
    console.log("Limpiamos #miSelect Up");
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
selectOption('select');

function getCampo(id){
    console.log('Pag 77 -- Id:'+id);
    if (id== 'idCampoSelectWhere'){
        where='Where';
    }else{
        where='';
    }
    if(document.getElementById(id).value == '*'){
        limpiarCampoAll();
    }else{
    	console.log('Pag 86 '+document.getElementById(id).value);
        // limpia Campo y añade btn's
        limpiarCampo(document.getElementById(id).value);  
    }
    
}
function getTabla(tabla){
    
    limpiarBtnTabla('btnTable');
    insertElements(document.querySelector('#'+'containerSelectTable'),'button',
        'type','button',
        'class','btnTable',
        'value',tabla,
        'id',tabla,
        '',''
    );
	document.getElementById('QueryTable').value = 'Select Table';

	
    //console.log("Limpiamos campos #miSelect Up");
    for (let i = selectCampos.options.length; i >= 0; i--) {
        selectCampos.remove(i);
    }

    //selectCamposWhere
    for (let i = selectCamposWhere.options.length; i >= 0; i--) {
        selectCamposWhere.remove(i);
    }

    //TODO: Borrar los los botones si los hay
  	let cad = '{"results": [{"campo": "campo1"},{"campo": "campo2"}],"status": "OK"}';
    let json1 = JSON.parse(cad);
    
    // Recuperamos los campos de la $tabla y rellenamos los select option 
    fetch("http://localhost:8080/campos/"+tabla)
     //fetch('./js/campos.json')
    .then((data) => data.json())
    .then(
    	function (data){
			// Rellenamos el select con lo recupera del servicio REST
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

    		for (x=0; x<Object.keys(data).length;x++){
                var option = document.createElement('option');
    			option.value = data[x];
    			option.text = data[x];
                option.id = 'idCampoSelect'+data[x];
    			selectCampos.appendChild(option);
    		}
			// Rellenamos el select con lo recupera del servicio REST
            var option = document.createElement('option');
            option.value = 'Añadir Campo';
            option.text = 'Añadir Campo';
            option.id = 'add';
            selectCamposWhere.appendChild(option);
           /* var option = document.createElement('option');
            option.value = '*';
            option.text = '*';
            option.id = '*';*/
            selectCamposWhere.appendChild(option);

    		for (x=0; x<Object.keys(data).length;x++){
                var option = document.createElement('option');
    			option.value = data[x];
    			option.text = data[x];
                option.id = 'idCampoSelect'+data[x];
    			selectCamposWhere.appendChild(option);
    		}
    		
    	}
    )
    // Limpiamos los btn de los campos al cambiar de tabla.
    cambioTable=true;
    limpiarBtnAll('campoSelect');
    //TODO: Limpiar Campos selectWere
    limpiarBtnAll('Where'); // TEST
    // si es cambio de tabla no recuperamos los campos al cb select option
    cambioTable=false; // chapu
    ocultarTextarea();
}
function limpiarBtnTabla(filtro){
    var btns = document.querySelectorAll('.'+filtro);
    for (var i = 0; i < btns.length; i++) {
        document.querySelector('#'+btns[i].id).remove(); 
    }
}

function limpiarCampo(id){
    var isOR = false;
    const ORIid=id;
    const divPadre = document.querySelector('#'+container+where);
	
	//console.log('container:'+container);
	//console.log('where:'+where);
	//console.log('divPadre:'+divPadre);

    let addClass='';
    if (where == 'Where'){
        addClass='Where';
    }else{
        addClass='queryCampo';
    }
    // div padre del contenedor
    console.log('Pag 197 '+idCampo+where+'-div-'+id)
    insertElements(divPadre,'div',
        '','',
        'class','TEST-NoClass->classCampo',
        'value','',
        'id',idCampo+where+'-div-'+id,
        '',''
    );
    const divHijo = document.querySelector('#'+idCampo+where+'-div-'+id);
    //console.log(document.querySelector('#'+idCampo+where+'-div-'+id));
    //console.log('#'+idCampo+where+'-div-'+id);
    //console.log('divHijo '+divHijo.getAttributeNames);
    // posiblemente hay que borrarlo se queda solo
    // tengo que crear el id acorde con elcampo para los duplicados
    if (where == 'Where'){
    // div condición
    // incremento el id para el div
    var btns = document.querySelectorAll('.'+'ORI'+ORIid);
    var preId= id;
    var oldId=id; // genero el id anterior para borrar el parentesis cuando sea OR
    for (var i = 0; i < btns.length; i++) {
        preId=preId+''+btns[0].textContent;
        if (i < btns.length-1){
            oldId=oldId+''+btns[0].textContent;
        } 
    }
    console.log('pag 223 -classCampo: '+classCampo);
    insertElements(divHijo,'div',
        '','',
        'class',classCampo+'Where',
        'value','',
        'id',idCampo+where+'-condicion-'+preId,
        '',''
    ); 
    const divCondicion = document.querySelector('#'+idCampo+where+'-condicion-'+preId);

    //console.log('divCondicion '+divCondicion);

    // btn OR AND Where
    if(document.querySelectorAll('.'+'Where').length>0){
        if (document.getElementById( idCampo+where+AND+id )){
            console.log('pag 236 -classCampo: '+classCampo);
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
            console.log('pag 248 -classCampo: '+classCampo);
            insertElements(divCondicion,'button',
                'type','button',
                'class',classCampo+'Where'+' queryCondicion',
                'value',AND,
                'id',idCampo+where+AND+id,
                'onclick','AndOr(this.id)'
            );
        }
    }else{
        console.log('pag 258 -classCampo: '+classCampo);
        insertElements(divCondicion,'button',
            'type','button',
            'class',classCampo+'Where'+' queryCondicion',
            'value','Where',
            'id',idCampo+where+condicion+preId,
            '',''
        );
    }
    
    // AND - OR
    if (!document.getElementById( idCampo+where+condicion+id ) && where == 'Where'){

    }else{

        // for uno por cada btn esto solo funciona si el btn or es el primero.
        var btns = document.querySelectorAll('.'+'ORI'+ORIid);
        //console.log('btns:'+btns);
        for (var i = 0; i < btns.length; i++) {
            id=id+''+btns[0].textContent;    
        }
    }
    }
    if (where == 'Where'){
        // btn MenosIZQ
        console.log('pag 283 -classCampo: '+classCampo);
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
    }else {
            // btn campo Select
            //console.log('pag 266'+' id '+id);
            //console.log('divHijo:'+divHijo);
        insertElements(divHijo,'button',
            'type','button',
            'class',classCampo+ ' btn '+addClass, // id origen solo en Where
            'value',id,
            'id',idCampo+where+id,
            'onclick','quitarBtnAddOptionSelect(this.value)'
        );
        //console.log('idBtn:'+idCampo+where+id);
    }


    if (idCampo == "idCampoInsert" || where == 'Where' ){    
        // input RESULT
        insertElements(divHijo,'input',
            '','',
            'class',classCampo+'Where'+' '+queryCampoWhereResult,
            'value','Escribe aquí...',
            'id',idCampo+where+RESULT+id,
            '',''
        );
    }
    if (where == 'Where'){
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
    }
    if (where != 'Where'){
        document.querySelector('#'+idCampo+where+id).remove();
        console.log('pag375 Delete campo select'+document.querySelector('#'+idCampo+where+id));
        //console.log('#'+idCampo+where+id);
    }else{
        console.log('Pag 378 '+idCampo+where);
        console.log(document.getElementById(idCampo+where).value);
        console.log(document.getElementById(idCampo+where));
        document.getElementById(idCampo+where).value = 'Añadir Campo';
    } 
}
function limpiarCampoAll(){
    const divPadre = document.querySelector('#'+container+where);
    const $select = document.getElementById(idCampo+where);
 
    let addClass='';
    if (where == 'Where'){
        addClass='Where';
    }else{
        addClass='queryCampo';
    }
    //console.log('container:'+container+' '+'idCampo: '+idCampo);
    for (var option of document.getElementById(idCampo+where).options) {
        //console.log('In for-> Option.value: '+option.value);
        if (option.value != 'Añadir Campo' &&  option.value != '*'){
            insertElements(divPadre,'div',
            '','',
            'class','TEST-NoClass->classCampo',
            'value','',
            'id',idCampo+where+'-div-'+option.value,
            '','');
            const divHijo = document.querySelector('#'+idCampo+where+'-div-'+option.value);

            // where
            if (where == 'Where'){
                // btn -
                insertElements(divHijo,'button',
                    'type','button',
                    'class',classCampo+'Where',
                    'value','-',
                    'id',idCampo+where+option.value,
                    'onclick','addParentesis(this.value)'
                );
                // btn +
                insertElements(divHijo,'button',
                    'type','button',
                    'class',classCampo+'Where',
                    'value','+',
                    'id',idCampo+where+option.value,
                    'onclick','addParentesis(this.value)'
                );
                // btn (
                insertElements(divHijo,'input',
                    '','',
                    'class',classCampo+'Where',
                    'value','(',
                    'id',idCampo+where+'-input'+option.value,
                    '',''
                ); 
                // btn campo where
                insertElements(divHijo,'button',
                    'type','button',
                    'class',classCampo+'Where'+ ' btn '+addClass+' '+queryCampoWhere,
                    'value',option.value,
                    'id',idCampo+where+option.value,
                    'onclick','quitarBtnAddOptionWhere(this.value)'
                );               

            }else{
                // btn selec
                insertElements(divHijo,'button',
                    'type','button',
                    'class',classCampo+ ' btn '+addClass,
                    'value',option.value,
                    'id',idCampo+where+option.value,
                    'onclick','quitarBtnAddOptionSelect(this.value)'
                );
            }
  

            if (idCampo == "idCampoInsert"|| where == 'Where'){
                insertElements(divHijo,'input',
                    '','',
                    'class',classCampo+'Where',
                    'value','escribe aquí',
                    'id',idCampo+where+'-input'+option.value,
                    '',''
                );
            }
            if (where == 'Where'){
                // btn )
                insertElements(divHijo,'input',
                    '','',
                    'class',classCampo+'Where',
                    'value',')',
                    'id',idCampo+where+'-input'+option.value,
                    '',''
                );   
                // btn -
                insertElements(divHijo,'button',
                    'type','button',
                    'class',classCampo+'Where',
                    'value','-',
                    'id',idCampo+where+option.value,
                    'onclick','addParentesis(this.value)'
                );
                // btn +
                insertElements(divHijo,'button',
                    'type','button',
                    'class',classCampo+'Where',
                    'value','+',
                    'id',idCampo+where+option.value,
                    'onclick','addParentesis(this.value)'
                );                
            
            }
            
        }
    }

    for (let i = $select.options.length; i >= 1; i--) {
        $select.remove(i);
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
/*Param 11- 12 */       functionName,functionValue) { //recibe el div
	//console.log('pag 448 ' +'elementName '+ elementName+' in:'+container);
    const btn = document.createElement(elementName);

    //console.log('btn: '+btn);
    btn.setAttribute(valueName,valueValue);
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
    
    btn.innerHTML = valueValue;
    //console.log(btn);
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
    console.log('quitarBtnAddOption( '+id);
    var all=true;
    for (var option of document.getElementById(idCampo+where).options) {
        // buscamos el * en el cb select option si lo encontramos no hay que añadirlo.
        if (option.value == '*'){
            all=false;
        }
    }
    if (all && !cambioTable){
        //console.log('id '+id+' addOptioin(*) ');
        addOption('*');
    }
    console.log('Borrando btn--> '+'#'+idCampo+where+id+' - ');
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

    if (filtro == 'Where'){
        where='Where';
    }else{
        where='';
    }


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
	