package com.alquiler.reservas.entity;

public class Coche {
	
	public int numeroPuertas = 0;
	
	public int getNumeroPuertas() {
		return numeroPuertas;
	}



	public void setNumeroPuertas(int numeroPuertas) {
		this.numeroPuertas = numeroPuertas;
	}

	public void incrementaNumeroPuertas(){
		this.numeroPuertas ++;
	}



	@Override
	public String toString() {
		return "Coche [numeroPuertas=" + numeroPuertas + "]";
	}
	
	

}
