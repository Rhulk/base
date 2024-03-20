package com.alquiler.reservas.entity;

public class Respuesta {
	
	public boolean check;
	public String mensaje;
	

	public Respuesta() {
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Respuesta(boolean check) {
		this.check = check;
	}
	public Respuesta(boolean check, String mensaje) {
		this.check = check;
		this.mensaje = mensaje;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return "Respuesta [check=" + check + "]";
	}
	
	

}
