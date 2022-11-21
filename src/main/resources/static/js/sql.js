console.log("sql.js");

function getCampo(id){
	console.log(id+' getCampo');
    console.log(document.getElementById(id).textContent);
	

}
function getIdioma(id){
    console.log(id+' getIdioma');
    document.getElementById("mostrarIdioma").textContent = document.getElementById(id).textContent; // Mostramos el idioma seleccionado
    sessionStorage.setItem(idioma, document.getElementById("mostrarIdioma").textContent = document.getElementById(id).textContent );
    setByIdioma(sessionStorage.getItem(idioma));
}
