package com.alquiler.reservas;

import java.util.Locale;
import java.util.Timer;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.alquiler.reservas.conf.SpringConfiguration;
import com.alquiler.reservas.util.EmailSenderService;
import com.alquiler.reservas.util.Tarea;

@SpringBootApplication
public class ReservasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
		//start();
		//i18n();

	}
	// up
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
