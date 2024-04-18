package com.alquiler.reservas.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alquiler.reservas.entity.Apartado;
import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.repository.ApartadoRepository;
import com.alquiler.reservas.repository.CapituloRepository;
import com.alquiler.reservas.repository.CursoRepository;
import com.alquiler.reservas.service.CursoService;
import com.alquiler.reservas.service.CursoServiceImp;


public class Excel {

	
	

	
	@Autowired
	CursoRepository cursoRepository;
	
	@Autowired
	CursoService cursoService;

	@Autowired
	CapituloRepository capituloRepository;
	
	@Autowired
	ApartadoRepository apartadoRepository;
	
	public String quitarComillas(String cadena) {

		StringBuilder sb = new StringBuilder();
		for (int n = 0; n <cadena.length (); n++) {
			char c = cadena.charAt(n);

			if (c != '"' && c != '{' && c !='}') {
				sb.append(c);
			}


		}
		//System.out.println(" test:"+ sb.toString() );
		return sb.toString();
	}
	public List<String> getCampos(String json){
		
		List<String> campos = new LinkedList<>();
		String[] cadenaSplit1 = json.split(":");

		for (String c : cadenaSplit1) {
			String[] cadenaSplit2 = c.split(",");
			for (String x : cadenaSplit2) {
				//System.out.println(x);
				campos.add(x);
			}
		}
		return campos;
	}
	   
   
    public void lecturaExcel() {
    	
    	String excelFilePath = "C:\\Users\\kike\\Documents\\ZK_oficina\\Tracking\\src\\main\\resources\\static\\doc\\Lectura.xls";
    	String tipo = "";
        String x2 = "";
        Random rd = new Random();
        Long idCap = new Long(0);
        Long idApar = new Long(0);
        
        Curso curso = new Curso("","","","","");
        Capitulo capitulo = new Capitulo("","",-1);
        Apartado apartado = new Apartado("","","",-1);
    	try
        {
            FileInputStream file = new FileInputStream(new File(excelFilePath));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet ws = wb.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = ws.iterator();
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                 
                while (cellIterator.hasNext()) 
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    

                    
                    switch (cell.getCellType()) {
                    
                        case NUMERIC:
                            
                            
                        case STRING:
                        	int num = cell.getCellType().getCode();//0 is double 1 String
                        	
                        	if (cell.getCellType().getCode()==1) {
                        		x2 = cell.getStringCellValue();
                        	}else {
                                x2 = ""+ cell.getNumericCellValue();
                        	}
                        	
                        	//System.out.println("x2:"+x2);
                            if (x2.equals("Curso")) {
                            	tipo = x2;	
                            }
                            if (x2.equals("Capitulo")) {
                            	tipo = x2;
                            	idCap = idCap + new Long(1);
                            	capitulo = new Capitulo(idCap,"","",-1); 
                            }
                            if (x2.equals("Apartado")) {
                            	tipo = x2;
                            	idApar = idApar + new Long(1);
                            	apartado = new Apartado(idApar,"","","",-1);
                            	
                            }
                            if (x2.equals("Leyenda")) {
                            	tipo = x2;
                            }
                        	//System.out.println(" Tipo:"+tipo);
                            switch (tipo) {
                        	
	                    		case "Curso":
	                    			
	                    			if(!x2.equals("Curso")) {
	                    				if(curso.getNombre().isBlank()) {
	                    					curso.setNombre(x2);
	                    				}else {
	                    					if(curso.getDescripcion().isEmpty()) {
	                    						curso.setDescripcion(x2);
	                    					}else {
	                    						if(curso.getFuente().isEmpty()) {
	                    							curso.setFuente(x2);
	                    						}else {
	                    							if (curso.getUrlimagen().isEmpty()) {
	                    								curso.setUrlimagen(x2);
	                    							}else {
	                    								if (curso.getUrlicono().isEmpty()) {
	                    									curso.setUrlimagen(x2);
	                    								}
	                    								if (curso.getId()==0) {//save curso
	                    									cursoRepository.count();
	                    									cursoRepository.save(curso);
	                    									//cursoService.altaCurso(curso);
	                    									System.out.println("Curso >> "+curso.toString());
	                    								}
	                    									
	                    							}
	                    						}
	                    					}
	                    				}
	                    				
	                    			}
	                    		break;
	                    		case "Capitulo":

	                    			if (!isCabeceraCapitulo(x2)) {
	                    				if(setNameWhenIsBlank(capitulo,x2)) {// si ya hay nombre pasa al siguiente
	                    					if(setDescripcionWhenIsBlank(capitulo,x2)) { //setDescripciónWhenIsBlank
	                    						if(setOrdenWhenIsBlank(capitulo,x2)) {
	                    							
	                    						}

	                    						System.out.println(" Capitulo >> "+capitulo.toString());
	                    					}
	                    				}
	                    				
	                    			}
	                    		break;
	                    		case "Apartado":
									
									                    			
	                    			if (!isCabeceraApartado(x2)) {
	                    				if(setNombreWhenIsBlank(apartado,x2)) {// si ya hay nombre pasa al siguiente
	                    					if(setDescripcionWhenIsBlank(apartado,x2)) { // setRecursoWhenIsBlank
	                    						if (setRecursoWhenIsBlank(apartado,x2)) {
		                    						if(setOrdenWhenIsBlank(apartado,x2)) {
		                    							
		                    						}

		                    						System.out.println(" Apartado >> "+apartado.toString());
	                    						}
	                    					}
	                    				}
	                    				
	                    			}                    			
	                    			
	                    			
	                    		break;
	                    		case "Leyenda":
	                    			//System.out.println(" Leyenda ");
	                    		break;
	                    		default :
	                    			//System.out.println(" << "+x2+" >>");
	                    		
	                        }

                            break;
                    
                    }
                }
            }
            file.close();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }

    
    }
    public boolean isCabeceraCapitulo(String campo) {
    		if (campo.equals("Capitulo")) {
    			return true;
    		}
    	return false;
    }

    /*
     *  Return false when is not blank
     */
    public boolean setNameWhenIsBlank(Capitulo capitulo, String nombre) {
    	if (capitulo.getNombre().isBlank()) {
    		capitulo.setNombre(nombre);
    		return false;
    	}
    	return true;
    }
    
    public boolean setDescripcionWhenIsBlank(Capitulo capitulo, String descripcion) {
    	
    	if (capitulo.getDescripcion().isBlank()) {
    		capitulo.setDescripcion(descripcion);
    		return false;
    	}
    	
    	return true;
    }
    public boolean setOrdenWhenIsBlank(Capitulo capitulo, String orden) {
    	
    	if (capitulo.getOrden()==-1) {
    		capitulo.setOrden(Integer.valueOf(orden.substring(0, 1)));
    		return false;
    	}
    	
    	return true;
    }
    
    public boolean isCabeceraApartado(String campo) {
    	
    	if (campo.equals("Apartado")) {
    		return true;
    	}
    	
    	return false;
    }
    
    public boolean setNombreWhenIsBlank(Apartado apartado, String nombre) {
    	
    	if (apartado.getNombre().isBlank()) {
    		apartado.setNombre(nombre);
    		return false;
    	}
    	return true;
    }
    
    public boolean setDescripcionWhenIsBlank(Apartado apartado, String descripcion) {
    	
    	if (apartado.getDescripcion().isBlank()) {
    		apartado.setDescripcion(descripcion);
    		return false;
    	}
    	
    	return true;
    }
    
    public boolean setRecursoWhenIsBlank(Apartado apartado, String recurso) {
    	
    	if (apartado.getRecurso().isBlank()) {
    		apartado.setRecurso(recurso);
    		return false;
    	}
    	
    	return true;
    }
    
    public boolean setOrdenWhenIsBlank(Apartado apartado, String orden) {
    	
    	if (apartado.getOrden()==-1) {
    		apartado.setOrden(Integer.valueOf(orden.substring(0, 1)));
    		return false;
    	}
    	
    	return true;
    }
}
