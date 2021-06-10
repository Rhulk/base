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
		properties.put("mail.smtp.port",465); // 465 ssl // 587 local tls
		properties.put("mail.smtp.mail.sender","activerhulk@gmail.com");
		properties.put("mail.smtp.user", "activerhulk@gmail.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("password", "08180818");
		properties.put("to", "todo.develop.gestion@gmail.com");

 
		session = Session.getDefaultInstance(properties);
	}
 
	public void run(){
 
		init();
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(properties.getProperty("to")));
			message.setSubject("Mantenimiento pendiente");
			message.setText("Correo prueba de mantenimiento");
			Transport t = session.getTransport("smtp");
			t.connect((String)properties.get("mail.smtp.user"), properties.getProperty("password"));
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			System.out.println("Mensaje enviado...");
		}catch (MessagingException me){
			me.printStackTrace();
			return;
		}
		
	}
	/*
	 * Metodo para mandar mail
	 * 
	 * Parametros: asunto, cuerpo y to
	 * 
	 * Opcional si se pone default en el parametro to recuperara el valor de la propiedad properties.put("to", "quique1904@gmail.com");
	 * 
	 */
	
	public void send(String asunto, String cuerpo, String to) {
		init();// Importante al inicio para recuperar datos de los properties.
		//ssl(); // test ssl
		if (to.equals("default")) {
			to = properties.getProperty("to");
			System.out.println(to);
		}
		
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport t = session.getTransport("smtp");
			t.connect((String)properties.get("mail.smtp.user"), properties.getProperty("password"));
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			System.out.println("Mensaje enviado...");
		}catch (MessagingException me){
			me.printStackTrace();
			return;
		}		
	}
	
	public void testMail() {
		
		init();
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(properties.getProperty("to")));
			message.setSubject("Mantenimiento pendiente");
			message.setText("Correo prueba de mantenimiento");
			
			Transport t = session.getTransport("smtp");
			
			// Create an SMTP transport from the session
			//SMTPTransport t = (SMTPTransport)session.getTransport("smtp");

			// Connect to the server using credentials
			t.connect(properties.getProperty("mail.smtp.host"), (String)properties.get("mail.smtp.user"), properties.getProperty("password"));		
			
			// Send the message
			t.sendMessage(message, message.getAllRecipients());
			
			
			session = Session.getDefaultInstance(properties);
			
			t.close();
			System.out.println("[ Test envio Mail ]");
		}catch (MessagingException me){
			me.printStackTrace();
			return;
		}	
		
	}
	public void ssl() {
		// Use the following if you need SSL
			//properties.put("mail.smtp.socketFactory.port", 587);
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.socketFactory.fallback", "false");
			
			
			// Set debug so we see the whole communication with the server
			properties.put("mail.debug", "true");


			// Enable STARTTLS
			properties.put("mail.smtp.starttls.enable", "true");

			// Accept only TLS 1.1 and 1.2
			properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.1 TLSv1.2");

			Session session = Session.getInstance(properties, null);
			session.setDebug(true);
	}

 
}
