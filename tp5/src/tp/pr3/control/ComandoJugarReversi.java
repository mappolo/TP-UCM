package tp.pr3.control;

import tp.pr3.logica.Juego;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasReversi;

public class ComandoJugarReversi extends Comando {

	/**
	 * Cambia el tipo de juego
	 */
	public void ejecuta(Partida p, String[] args) {
		ReglasReversi reversi = new ReglasReversi();
		p.reset(reversi);
		p.setJuego(Juego.REVERSI);
		System.out.println("Partida reiniciada.");
	}

}
