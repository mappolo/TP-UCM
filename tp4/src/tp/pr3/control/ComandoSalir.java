package tp.pr3.control;

import tp.pr3.logica.Partida;

public class ComandoSalir extends Comando {
	
	/**
	 * Ejecuta el comando para salir de la partida y terminar la aplicacion
	 */
	public void ejecuta(Partida p, String[] args) {
		System.exit(0);
	}
}
