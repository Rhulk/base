console.log("sql.js");
let idCampo ="";
let container = "";
let classCampo = "";
let where = "";
let condicion = 'AND';
let sino = false;//quitar 
// control incrementos de crementos de los parentesis
let izqMas ='izqMas';
let izqMenos = 'izqMenos';
let derMas = 'derMas';
let derMenos = 'derMenos'; 
let izqParent = 'izqParent';
let derParent = 'derParent';
function selectOption(valor){
    
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
    //where=''; si pincho fuera del where qu se quite
}

function limpiarCampo(id){
    //console.log('Container: '+container+where+' | '+'id: '+id);
    const divPadre = document.querySelector('#'+container+where);
    //const condicion = document.querySelector('#'+container+where);
    //console.log('divPadre: '+divPadre+' - tamaño:'+divPadre.ariaSetSize);
    let addClass='';
    if (where == 'Where'){
        addClass='Where';
    }else{
        addClass='Select';
    }
 
    insertElements(divPadre,'div',
        '','',
        'class',classCampo,
        'value','',
        'id',idCampo+where+'-div-'+id,
        '','');
    const divHijo = document.querySelector('#'+idCampo+where+'-div-'+id);
    //console.log('- '+'#'+idCampo+'-div-'+id)
    //console.log('divHijo: '+divHijo);

    insertElements(divHijo,'div',
    '','',
    'class',classCampo,
    'value','',
    'id',idCampo+where+'-condicion-'+id,
    '','');      
    console.log('Condición:'+idCampo+where+condicion+id);
    if (!document.getElementById( idCampo+where+condicion+id ) && where == 'Where'){
        insertElements(divHijo,'button',
        'type','button',
        'class',classCampo,
        'value',condicion,
        'id',idCampo+where+condicion+id,
        'onclick','AndOr(this.id)'
        );
        console.log('Condición:'+idCampo+where+condicion+id+' Unico campo:'+id);
    }else{
        if(document.getElementById( idCampo+where+condicion+id ).value == 'AND'){
            AndOr(idCampo+where+condicion+id);
        }
        //console.log(' Campo repe para OR ': );
        console.log('Condición:'+idCampo+where+condicion+id+'  Repetido: '+id);


    }



    if (where == 'Where'){
        // btn -
        insertElements(divHijo,'button',
        'type','button',
        'class',classCampo,
        'value','-',
        'id',idCampo+where+id,
        'onclick','addParentesis(this.value)'
        );
        // btn +
        insertElements(divHijo,'button',
        'type','button',
        'class',classCampo,
        'value','+',
        'id',idCampo+where+id,
        'onclick','addParentesis(this.value)'
        );
        // input (
        insertElements(divHijo,'input',
        '','',
        'class',classCampo,
        'value','(',
        'id',idCampo+where+'-input'+id,
        '','');
    }
    // btn campo
    insertElements(divHijo,'button',
        'type','button',
        'class',classCampo+ ' btn '+addClass,
        'value',id,
        'id',idCampo+where+id,
        'onclick','quitarBtnAddOption(this.value)');

    if (idCampo == "idCampoInsert" || where == 'Where' ){    
        // input campo
        insertElements(divHijo,'input',
        '','',
        'class',classCampo,
        'value','',
        'id',idCampo+where+'-input'+id,
        '','');
    }
    if (where == 'Where'){
        //btn )
        insertElements(divHijo,'input',
        '','',
        'class',classCampo,
        'value',')',
        'id',idCampo+where+'-input'+id,
        '','');
        //btn -
        insertElements(divHijo,'button',
        'type','button',
        'class',classCampo,
        'value','-',
        'id',idCampo+where+id,
        'onclick','addParentesis(this.value)'
        );
        // btn +
        insertElements(divHijo,'button',
        'type','button',
        'class',classCampo,
        'value','+',
        'id',idCampo+where+id,
        'onclick','addParentesis(this.value)'
        );
    }

    if (where != 'Where'){
        document.querySelector('#'+idCampo+where+id).remove();
        //console.log('limpiarCampo -> id: '+'#'+idCampo+where+id);
    }else{
        //console.log('limpiarCampo -> id: NOO ');
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
        addClass='Select';
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
                '',''); 
                // btn campo where
                insertElements(divHijo,'button',
                'type','button',
                'class',classCampo+ ' btn '+addClass,
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
                'value','',
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
                '','');   
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
    //console.log(" container: "+container);
    //console.log(" elementName: "+elementName);
    //console.log()
    const btn = document.createElement(elementName);

    btn.setAttribute(className, classValue);
    btn.setAttribute(valueName,valueValue);
    btn.setAttribute(idName,idValue);
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
        //sino=false;
    }
    //console.log('id: '+id);
    //console.log('Where: '+where);
    //console.log('btn: '+idCampo+'-input'+id);
    document.querySelector('#'+idCampo+where+id).remove();
    if (idCampo == "idCampoInsert"){
        //console.log(" Remove Input Insert: -idCampoInsert-input2- id ="+idCampo+'-input'+id);
        
        document.querySelector('#'+idCampo+'-input'+id).remove();
    }
    //console.log('fail - '+idCampo+where+'-div-'+id);
    document.querySelector('#'+idCampo+where+'-div-'+id).remove();
    addOption(id);
    //console.log(" Done --> delete btn: id="+id+" Add Option: id="+idCampo+id);
}

function limpiarBtnAll(filtro){

    if (filtro == 'Where'){
        where='Where';
    }else{
        where='';
    }

    var btns = document.querySelectorAll('.'+filtro);
    //console.log("limpiarBtnAll: "+btns.length);
    for (var i = 0; i < btns.length; i++) {
        //console.log('valor: '+btns[i].textContent);
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

// incrementos decrementos de parentesis

function getIzqMas(valor){
    console.log('izqMas');
}
function getIzqMenos(valor){
    
}
function getDereMas(valor){
    
}
function getDereMenos(valor){

}

	    $(document).ready(function() {
	        //Asegurate que el id que le diste a la tabla sea igual al texto despues del simbolo #
	        console.log("DataTable Check- todoList");
	        $('#userList').DataTable();
	        $('#todoList').DataTable();
	    } );
	