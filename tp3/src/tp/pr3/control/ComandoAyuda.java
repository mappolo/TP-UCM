package tp.pr3.control;

import tp.pr3.logica.Partida;

public class ComandoAyuda extends Comando {

	/**
	 * Ejecuta el comando ayuda para mostrar la ayuda
	 */
	public void ejecuta(Partida p, String[] args) {
		System.out.println("Los comandos disponibles son: ");
		System.out.println("PONER: utilízalo para poner la siguiente ficha.");
		System.out.println("DESHACER: deshace el último movimiento hecho en la partida.");
		System.out.println("REINICIAR: reinicia la partida.");
		System.out.println("JUGAR [c4|co|gr] [tamX tamY]: cambia el tipo de juego.");
		System.out.println("JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.");
		System.out.println("SALIR: termina la aplicación.");
		System.out.println("AYUDA: muestra esta ayuda.");
	}
}
