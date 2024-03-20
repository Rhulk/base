package com.alquiler.reservas.entity;

public class Cliente extends Persona {

	public int credito;

	public int getCredito() {
		return credito;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	@Override
	public String toString() {
		return "Cliente [credito=" + credito + "]";
	}
	
	
}
