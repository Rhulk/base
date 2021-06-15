package com.alquiler.reservas.util;

public class SpringCoder {

	
	/*
	Implementación del algoritmo de cifrado César en Java
	@author parzibyte
	Visita: parzibyte.me/blog
*/

	public static void main(String[] args) {
		String mensajeOriginal = "origina";
		String mensajeCifrado = cifrar(mensajeOriginal);
		String mensajeDescifrado = descifrar(mensajeCifrado);
		System.out.printf("Original: %s\nCifrado: %s\nDescrifrado: %s\n", mensajeOriginal, mensajeCifrado, mensajeDescifrado);
	}

	public static String cifrar(String cadenaOriginal) {
		return rotar(cadenaOriginal, 3);
	}

	public static String descifrar(String cadenaCifrada) {
		return rotar(cadenaCifrada, -3);
	}

	public static String rotar(String cadenaOriginal, int rotaciones) {
		// En ASCII, la a es 97, b 98, A 65, B 66, etcétera
		final int LONGITUD_ALFABETO = 26, INICIO_MINUSCULAS = 97, INICIO_MAYUSCULAS = 65;
		String cadenaRotada = ""; // La cadena nueva, la que estará rotada
		for (int x = 0; x < cadenaOriginal.length(); x++) {
			char caracterActual = cadenaOriginal.charAt(x);
			// Si no es una letra del alfabeto entonces ponemos el char tal y como está
			// y pasamos a la siguiente iteración
			if (!Character.isLetter(caracterActual)) {
				cadenaRotada += caracterActual;
				continue;
			}

			int codigoAsciiDeCaracterActual = (int) caracterActual;
			boolean esMayuscula = Character.isUpperCase(caracterActual);

			// La posición (1 a 26) que ocupará la letra después de ser rotada
			// El % LONGITUD_ALFABETO se utiliza por si se pasa de 26. Por ejemplo,
			// la "z", al ser rotada una vez da el valor de 27, pero en realidad debería
			// regresar a la letra "a", y con mod hacemos eso ya que 27 % 26 == 1,
			// 28 % 26 == 2, etcétera ;)
			int nuevaPosicionEnAlfabeto = ((codigoAsciiDeCaracterActual
					- (esMayuscula ? INICIO_MAYUSCULAS : INICIO_MINUSCULAS)) + rotaciones) % LONGITUD_ALFABETO;
			// Arreglar rotaciones negativas
			if (nuevaPosicionEnAlfabeto < 0)
				nuevaPosicionEnAlfabeto += LONGITUD_ALFABETO;
			int nuevaPosicionAscii = (esMayuscula ? INICIO_MAYUSCULAS : INICIO_MINUSCULAS) + nuevaPosicionEnAlfabeto;
			// Convertir el código ASCII numérico a su representación como símbolo o letra y
			// concatenar
			cadenaRotada += Character.toString((char) nuevaPosicionAscii);
		}
		return cadenaRotada;
	}

}
	

