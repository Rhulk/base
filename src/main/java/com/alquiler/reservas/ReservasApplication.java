package com.alquiler.reservas;

import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alquiler.reservas.util.Tarea;

@SpringBootApplication
public class ReservasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
		start();
		
	}
	
	public static void start() {
		System.out.println(" Start Timer ");
        Tarea tarea = new Tarea();
        Timer temporizador = new Timer();
        Integer segundos = 1;
        
        temporizador.scheduleAtFixedRate(tarea, 0, 1000*segundos);
		
	}
	


}
