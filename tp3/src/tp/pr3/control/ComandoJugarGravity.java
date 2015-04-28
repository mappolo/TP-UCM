package tp.pr3.control;

import tp.pr3.logica.Juego;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasGravity;

public class ComandoJugarGravity extends Comando {
	
	/**
	 * Cambia el tipo de juego
	 */
	public void ejecuta(Partida p, String[] args) {
		int numCols = Integer.parseInt(args[1]);
		int numFilas = Integer.parseInt(args[2]);
		ReglasGravity gravity = new ReglasGravity(numCols, numFilas);
		p.reset(gravity);
		p.setJuego(Juego.GRAVITY);
		System.out.println("Partida reiniciada.");
	}
}
