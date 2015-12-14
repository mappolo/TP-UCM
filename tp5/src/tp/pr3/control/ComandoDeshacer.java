package tp.pr3.control;

import tp.pr3.logica.Partida;

public class ComandoDeshacer extends Comando {
	
	/**
	 * Ejecuta el comando para deshacer el ultimo movimiento hecho de la partida
	 */
	public void ejecuta(Partida p, String[] args) {
		if (!p.undo()) {
			System.err.println("Imposible deshacer.");
		}
	}
}
