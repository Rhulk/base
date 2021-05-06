package com.alquiler.reservas.util;


import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class Tarea extends TimerTask {
    static private final Logger LOGGER = Logger.getLogger("com.alquiler.reservas.ReservasAplication");
    private Integer contador;    
    
    public Tarea() {
        contador = 0;
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Numero de ejecución {0}", contador);
        contador++;
        
        try {
            // Con esto hacemos que la funcion tarde *mas* en ejecutarse que
            // el periodo especificado
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "Error de interrupcion");
        }
    }
    
}

