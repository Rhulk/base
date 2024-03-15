package com.alquiler.reservas.entity;

public class Respuesta {
	
	public boolean check;
	
	

	public Respuesta() {
	}

	public Respuesta(boolean check) {
		this.check = check;
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
