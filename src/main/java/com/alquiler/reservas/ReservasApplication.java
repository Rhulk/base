package com.alquiler.reservas;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.DOMImplementation;

import com.alquiler.reservas.conf.SpringConfiguration;
import com.alquiler.reservas.entity.Cliente;
import com.alquiler.reservas.entity.Coche;
import com.alquiler.reservas.entity.Persona;
import com.alquiler.reservas.entity.Trabajador;
import com.alquiler.reservas.service.UserService;
import com.alquiler.reservas.util.EmailSenderService;
import com.alquiler.reservas.util.Tarea;

@SpringBootApplication
public class ReservasApplication {


	public static Document doc = null;
	
	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
		
		
				int numeroIf=0;
				
				if (numeroIf>0){
					System.out.print("Positivo");
				}else{
					if (numeroIf<0){
						System.out.print("Negativo");
					}else{
						System.out.print(" Es cero");
					}
				}
				
			    int numeroWhile  = 0;
			    
			    while(numeroWhile  < 3) {
			      System.out.println("WHILE :"+numeroWhile ); 
			      numeroWhile ++;
			    }
			    
			    do {
			        System.out.println("DO-WHILE: "+numeroWhile); 
			      } while(numeroWhile < 3);
			    
			   
			    
			    for (int numeroFor=0;numeroFor<=3;numeroFor++) {
			    	System.out.println("numeroFor: "+numeroFor);
			    }
			    
			    String estacion="Verano";
			    switch (estacion) { 
			    case "Invierno":
			     	System.out.println("Invierno");
			     break;
			    case "Primavera":
			    	System.out.println("Primavera");
			     break;

			    case "Verano" :
			    	System.out.println("Verano");
			     break;
			    case "Otoño" :
			    	System.out.println("Otoño");
			     break;
			    default:
			     		System.out.println("???");
			  }
		
		Coche miCoche = new Coche();
		miCoche.incrementaNumeroPuertas();
		System.out.println(miCoche.getNumeroPuertas());
		
		//start();
		//i18n();
		//EmailSenderService e = new EmailSenderService();
		//e.testMail();
	/*	
		if (abrir_XML_DOM(new File("src/main/resources/static/xml/file.xml")) == 0) {
			System.out.println("Read file.xml [OK]");
			System.out.println(recorrerDOMyMostrar(doc));
			if(annadirDOM( doc,  "titulo", "autor", "ano","tiempo") == 0) {
				System.out.println(" Añadido node New.");
				System.out.println(recorrerDOMyMostrar(doc));
			}else {
				System.out.println(" Fail add Node");
			}
		}else {
			System.out.println("Read file.xml [KO]");
		}
		guardarXML();
	*/
		if (abrir_XML_DOM(new File("src/main/resources/static/xml/strava.xml")) == 0) {
			System.out.println("Read strava.xml [OK]");
			System.out.println(recorrerDOMyMostrar(doc));
		}else {
			System.out.println("Read strava.xml [KO]");
		}
		
		Persona individuo = new Persona();
		individuo.setNombre("Juan");
		individuo.setEdad(25);
		individuo.setTelefono(65785432);
		
		System.out.println(" Creada la persona: "+individuo.getNombre()+" con "+individuo.getEdad()+" años y tlf: "+individuo.getTelefono());
		
		// Ejercicio 9
		
		// parte 1
		Cliente contacto = new Cliente();
		contacto.setNombre("Fran");
		contacto.setEdad(34);
		contacto.setTelefono(23476567);
		contacto.setCredito(2344);
		
		System.out.println("Cliente: "+contacto.getNombre()+" con credito: "+contacto.getCredito());
		
		//parte 2
		Trabajador currante = new Trabajador();
		currante.setNombre("Carlos");
		currante.setEdad(45);
		currante.setTelefono(345789234);
		currante.setSalario(323423);
		
		System.out.println(" Trabajador: "+currante.getNombre()+" con un salario de "+currante.getSalario());
	}
	/*
	Crear clase Persona.

	Crear variables las privadas edad, nombre y telefono de la clase Persona.

	Crear gets y sets de cada propiedad.

	Crear un objeto persona en el main.

	Utiliza los gets y sets para darle valores a las propiedades edad, nombre y telefono, por último muéstralas por consola.


	*/


	public static int abrir_XML_DOM(File fichero){

		doc = null; // doc es de tipo Document y representa al árbol DOM
	    
	     try{
	          // Se crea un objeto DocumentBuilderFactory
	          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	         
	          // Indica que el modelo DOM no debe contemplar los comentarios que tenga el XML
	          factory.setIgnoringComments(true);
	         
	          // Ignora los espacios en blanco que tenga el documento
	          factory.setIgnoringElementContentWhitespace(true);
	         
	          // Se crea un objeto DocumentBuilder para cargar en él la estructura del árbol DOM a partir del XML seleccionado
	          DocumentBuilder builder = factory.newDocumentBuilder();
	         
	          // Interpreta (parsea) el documento XML (file) y genera el DOM equivalente
	          doc = builder.parse(fichero);
	         
	          // Ahora doc apunta al árbol DOM listo para ser recorrido
	         
	          return 0;
	     }
	     catch(Exception e){
	          e.printStackTrace();
	          return -1;
	     }

	}
	public static String recorrerDOMyMostrar(Document doc){

	     String datos_nodo[] = null;
	     String salida = "";
	     Node node;
	    
	     // Obtiene el primer nodo del DOM (primer hijo)
	     Node raiz = doc.getFirstChild();
	    
	     // Obtiene una lsita de nodos con todos los nodos hijo del raíz
	     NodeList nodeList = raiz.getChildNodes();
	    
	     // Procesa los nodos hijo
	     for (int i = 0; i < nodeList.getLength(); i++){
	          node = nodeList.item(i);
	          if (node.getNodeType() == Node.ELEMENT_NODE){
	                // Es un nodo libro
	                datos_nodo = procesarLibro(node);
	                salida = salida + "\n " + "Publicado en: " + datos_nodo[0];
	                salida = salida + "\n " + "El autor es: " + datos_nodo[2];
	                salida = salida + "\n " + "El título es: " + datos_nodo[1];
	                salida = salida + "\n --------------------";
	          }
	     }
	     return salida;
	}
	// Recorrer y pintar el contenido del notdo meta.
	public static String recorrerMeta(Document doc) {
		
		return "Pendiente";
	}
	
	// dadas las coordenadas de inicio y final decrementar el tiempo del nodo desde el punto final al inicial.
	// Restaremos el time_A_Reducir al ultimo timeNodo e progresivamente a los nodosTime hasta llegar al inicial que no se restara nada para reducir el tiempo blobal del segmento.
	public static int modTimeSegmento(long lat_inicial, long lon_inicial, long lat_final, long lon_fianl, Date time_A_Reducir) {
		
		return 0;
	}
	
	protected static String[] procesarLibro(Node n){

	     String datos[] = new String[4];
	     
	     
	     Node ntemp = null;
	     int contador = 1;
	    
	     // Obtiene el valor del primer atributo del nodo (solo uno en este ejemplo)
	     datos[0] = n.getAttributes().item(0).getNodeValue();
	    
	     // Obtiene los hijos del Libro (título y autor)
	     NodeList nodos = n.getChildNodes();
	     
	     for (int i = 0; i <nodos.getLength(); i++){
	          ntemp = nodos.item(i);
	          if(ntemp.getNodeType() == Node.ELEMENT_NODE){
	                /* Importante: para obtener el texto con el título y autor se accede al nodo TEXT hijo de ntemp y se saca su valor */
	                datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
	                contador++;
	          }
	     }
	     return datos;
	}
	
	// tratar de añadir time
	public static void addTime(Document doc, String time) {
		
	}
	public static int annadirDOM(Document doc, String titulo, String autor, String anno, String time){

	     try{
	          // Se crea un nodo tipo Element con nombre 'titulo' (<Titulo>)
	          Node ntitulo = doc.createElement("Titulo");
	         
	          // Se crea un nodo tipo texto con el títlo del libro
	          Node ntitulo_text = doc.createTextNode(titulo);

	          // Se añade el nodo de texto con el título como hijo del elemento Titulo
	          ntitulo.appendChild(ntitulo_text);
	          
	          // Se crean los nodos Time 
	          Node nTime = doc.createElement("Time");
	          Node nTime_text = doc.createTextNode(time);
	          nTime.appendChild(nTime_text);
	         

	         
	          // Se hace lo mismo que con titulo a autor (<Autor>)
	          Node nautor = doc.createElement("Autor");
	          Node nautor_text = doc.createTextNode(autor);
	          nautor.appendChild(nautor_text);
	         
	          // Se crea un nodo de tipo elemento (<libro>)
	          Node nlibro = doc.createElement("Libro");
	         
	          //  Al nuevo nodo libro se le añade un atributo publicado_en
	          ((Element)nlibro).setAttribute("publicado_en", anno);
	          ((Element)nlibro).setAttribute("publicado_en", "mod");
	         
	          // prueba mod Atributo
	          ((Element)nlibro).getAttribute("publicado_en");
	          
	          // Se añade a libro el nodo autor y titulo creados antes
	          nlibro.appendChild(ntitulo);
	          nlibro.appendChild(nautor);
	          nlibro.appendChild(nTime);
	         
	          /* Finalmente, se obtiene el primer nodo del documento y a él se le
	          añade como hijo el nodo libro que ya tiene colgando todos sus
	          hijos y atributos creados antes. */
	          Node raiz = doc.getChildNodes().item(0);
	          raiz.appendChild(nlibro);
	         
	          return 0;
	     }
	     catch(Exception e){
	          e.printStackTrace();
	          return -1;
	     }
	}
	

    public static void guardarXML(){
        
        
       
        try{
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			doc = implementation.createDocument(null, "Libros", null);
            doc.setXmlVersion("1.0");
            annadirDOM(doc,"Desarrollo de Interfaces", "Pablo Martinez", "2010",new Date().toString());
            annadirDOM(doc,"Acceso a datos", "Alberto Carrera", "2011", new Date().toGMTString());
            annadirDOM(doc,"Formación y orientación laboral", "Belén Carrera", "2012",new Date().toLocaleString());
             
              /* A partir de aquí cambio respecto a la versión anterior.
              Lo podría haber puesto como segundo método de guardar de ObjetoDOM y llamarlo */
            Source source = new DOMSource(doc);
            Result result = new StreamResult(new java.io.File("src/main/resources/static/xml/fileGuardado.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }
        catch(Exception e){
            e.printStackTrace();
        }
   }
	
	// Añadir nodo a xml
	
	public void addNodeXml(Document doc, String addNodo, String fileXml, String nodoPadre) {
		
	}

	
	public static void start() {
		System.out.println(" Start Timer ");
        Tarea tarea = new Tarea();
        Timer temporizador = new Timer();
        Integer segundos = 1;
        
        temporizador.scheduleAtFixedRate(tarea, 0, 1000*segundos);
        
	}
	
	public static void i18n() {
        AbstractApplicationContext ctx 
        = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		
		String msg_en = ctx.getMessage("title", null, Locale.ENGLISH);
		String msg_es = ctx.getMessage("title", null, new Locale("es"));
		
		System.out.println("English: " + msg_en);
		System.out.println("Español: " + msg_es);
		
		//SaludaService ss = ctx.getBean(SaludaService.class);
		//ss.saluda("carmelo", "marin");
		
		ctx.close();    
	}

}
