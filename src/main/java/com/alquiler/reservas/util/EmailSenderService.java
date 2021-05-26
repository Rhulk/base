package com.alquiler.reservas.util;


import java.util.Properties;


import javax.mail.*;
import javax.mail.event.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;
 
public class EmailSenderService extends Thread{
	private final Properties properties = new Properties();
	
	
 
	private Session session;
 
	private void init() {
 
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port",587);
		properties.put("mail.smtp.mail.sender","todo.develop.gestion@gmail.com");
		properties.put("mail.smtp.user", "todo.develop.gestion");
		properties.put("mail.smtp.auth", "true");
		properties.put("password", "08180818");

 
		session = Session.getDefaultInstance(properties);
	}
 
	public void run(){
 
		init();
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("quique1904@gmail.com"));
			message.setSubject("Mantenimiento pendiente");
			message.setText("Correo prueba de mantenimiento");
			Transport t = session.getTransport("smtp");
			t.connect((String)properties.get("mail.smtp.user"), properties.getProperty("password"));
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			System.out.println("Mensaje enviado...");
		}catch (MessagingException me){
                        //Aqui se deberia o mostrar un mensaje de error o en lugar
                        //de no hacer nada con la excepcion, lanzarla para que el modulo
                        //superior la capture y avise al usuario con un popup, por ejemplo.
			me.printStackTrace();
			return;
		}
		
	}
 
}
