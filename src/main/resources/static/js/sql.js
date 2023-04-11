console.log("sql.js");
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

function getCampo(id){
    
    if (id== 'idCampoSelectWhere'){
        where='Where';
    }else{
        where='';
    }
    if(document.getElementById(id).value == '*'){
        limpiarCampoAll();
    }else{
        limpiarCampo(document.getElementById(id).value);  
    }
    
}
function getTabla(id){
    limpiarBtnTabla('table');
    insertElements(document.querySelector('#'+'containerSelectTable'),'button',
        'type','button',
        'class','table',
        'value',document.getElementById(id).value,
        'id',document.getElementById(id).value,
        '',''
    );
document.getElementById('QueryTable').value = 'Select Table';
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

    let addClass='';
    if (where == 'Where'){
        addClass='Where';
    }else{
        addClass='queryCampo';
    }
    // div padre del contenedor
    insertElements(divPadre,'div',
        '','',
        'class',classCampo,
        'value','',
        'id',idCampo+where+'-div-'+id,
        '',''
    );
    const divHijo = document.querySelector('#'+idCampo+where+'-div-'+id);
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
    insertElements(divHijo,'div',
        '','',
        'class',classCampo,
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
                'class',classCampo+' queryCondicion',
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
                'class',classCampo+' queryCondicion',
                'value',AND,
                'id',idCampo+where+AND+id,
                'onclick','AndOr(this.id)'
            );
        }
    }else{
        insertElements(divCondicion,'button',
            'type','button',
            'class',classCampo+' queryCondicion',
            'value','Where:',
            'id',idCampo+where+condicion+preId,
            '',''
        );
    }
    
    // AND - OR
    if (!document.getElementById( idCampo+where+condicion+id ) && where == 'Where'){

    }else{

        // for uno por cada btn esto solo funciona si el btn or es el primero.
        var btns = document.querySelectorAll('.'+'ORI'+ORIid);
        for (var i = 0; i < btns.length; i++) {
            id=id+''+btns[0].textContent;    
        }
    }
    }
    if (where == 'Where'){
        // btn MenosIZQ
        insertElements(divHijo,'button',
            'type','button',
            'class',classCampo,
            'value','-',
            'id',id,
            'onclick','getIzqMenos(this.id)'
        );

        // btn MasIZQ
        insertElements(divHijo,'button',
            'type','button',
            'class',classCampo,
            'value','+',
            'id',id,
            'onclick','getIzqMas(this.id)'
        );
        // btn ParentIZQ
        insertElements(divHijo,'button',
            'type','button',
            'class',classCampo+' queryParentIzq',
            'value','(',
            'id',idCampo+where+ParentIZQ+id,
            '',''
        );
        // btn campo 'ORI'+ORIid
        insertElements(divHijo,'button',
            'type','button',
            'class',classCampo+ ' btn '+addClass+' ORI'+ORIid +' '+queryCampoWhere, // id origen
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
        insertElements(divHijo,'button',
            'type','button',
            'class',classCampo+ ' btn '+addClass, // id origen solo en Where
            'value',id,
            'id',idCampo+where+id,
            'onclick','quitarBtnAddOptionSelect(this.value)'
        );
    }


    if (idCampo == "idCampoInsert" || where == 'Where' ){    
        // input RESULT
        insertElements(divHijo,'input',
            '','',
            'class',classCampo+' '+queryCampoWhereResult,
            'value','Escribe aquí...',
            'id',idCampo+where+RESULT+id,
            '',''
        );
    }
    if (where == 'Where'){
        //btn ParentDERE
        insertElements(divHijo,'button',
            'type','button',
            'class',classCampo+' queryParentDer',
            'value',')',
            'id',idCampo+where+ParentDER+id,
            '',''
        );
        //btn MenosDERE
        insertElements(divHijo,'button',
            'type','button',
            'class',classCampo,
            'value','-',
            'id',id,
            'onclick','getDereMenos(this.id)'
        );
        // btn MasDER
        insertElements(divHijo,'button',
            'type','button',
            'class',classCampo,
            'value','+',
            'id',id,
            'onclick','getDereMas(this.id)'
        );
    }
    if (where != 'Where'){
        document.querySelector('#'+idCampo+where+id).remove();
    }else{
        document.getElementById(idCampo+where).value = 'select';
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
    
    for (var option of document.getElementById(idCampo+where).options) {
        if (option.value != 'select' &&  option.value != '*'){
            insertElements(divPadre,'div',
            '','',
            'class',classCampo,
            'value','',
            'id',idCampo+where+'-div-'+option.value,
            '','');
            const divHijo = document.querySelector('#'+idCampo+where+'-div-'+option.value);

            // where
            if (where == 'Where'){
                // btn -
                insertElements(divHijo,'button',
                    'type','button',
                    'class',classCampo,
                    'value','-',
                    'id',idCampo+where+option.value,
                    'onclick','addParentesis(this.value)'
                );
                // btn +
                insertElements(divHijo,'button',
                    'type','button',
                    'class',classCampo,
                    'value','+',
                    'id',idCampo+where+option.value,
                    'onclick','addParentesis(this.value)'
                );
                // btn (
                insertElements(divHijo,'input',
                    '','',
                    'class',classCampo,
                    'value','(',
                    'id',idCampo+where+'-input'+option.value,
                    '',''
                ); 
                // btn campo where
                insertElements(divHijo,'button',
                    'type','button',
                    'class',classCampo+ ' btn '+addClass+' '+queryCampoWhere,
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
                    'class',classCampo,
                    'value','escribe aquí',
                    'id',idCampo+where+'-input'+option.value,
                    '',''
                );
            }
            if (where == 'Where'){
                // btn )
                insertElements(divHijo,'input',
                    '','',
                    'class',classCampo,
                    'value',')',
                    'id',idCampo+where+'-input'+option.value,
                    '',''
                );   
                // btn -
                insertElements(divHijo,'button',
                    'type','button',
                    'class',classCampo,
                    'value','-',
                    'id',idCampo+where+option.value,
                    'onclick','addParentesis(this.value)'
                );
                // btn +
                insertElements(divHijo,'button',
                    'type','button',
                    'class',classCampo,
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


function insertElements(container,elementName,tipoBtnName,tipoBtnValue,className,classValue,valueName,valueValue,idName,idValue,functionName,functionValue) { //recibe el div

    const btn = document.createElement(elementName);

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
    var all=true;
    for (var option of document.getElementById(idCampo+where).options) {
        if (option.value == '*'){
            all=false;
        }
    }
    if (all){
        addOption('*');
    }
    document.querySelector('#'+idCampo+where+id).remove();
    if (idCampo == "idCampoInsert"){
        document.querySelector('#'+idCampo+'-input'+id).remove();
    }
    document.querySelector('#'+idCampo+where+'-div-'+id).remove();
    addOption(id);
}
function limpiarBtnAll(filtro){

    if (filtro == 'Where'){
        where='Where';
    }else{
        where='';
    }

    var btns = document.querySelectorAll('.'+filtro);
    for (var i = 0; i < btns.length; i++) {
        quitarBtnAddOption(btns[i].textContent);    
    }
}
function addOption(valor){
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
btnLanzarSelect.addEventListener("click", () =>{


    
    console.log(tipoQuery);
    // campos
    var campos = document.querySelectorAll('.queryCampo');
    var primero=true;
    for ( var i = 0; i < campos.length; i++){
        if(!primero){
            console.log(',');
        }
        console.log(campos[i].textContent);
        primero=false;
    }
    console.log('from');
    console.log(document.querySelector('.table').value);
    // campos where 
    //console.log('Where');
    var camposWhere = document.querySelectorAll('.queryCampoWhere');
    var camposWhereResult = document.querySelectorAll('.queryCampoWhereResult');
    var queryCondicion = document.querySelectorAll('.queryCondicion');
    var queryParentIzq = document.querySelectorAll('.queryParentIzq');
    var queryParentDer = document.querySelectorAll('.queryParentDer');
    for ( var i=0; i < camposWhere.length; i++){
        console.log(queryCondicion[i].value);
        console.log(queryParentIzq[i].value +' ' + camposWhere[i].textContent+' = '+camposWhereResult[i].value+' ' +queryParentDer[i].value);
        
    }
    
    
});


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

