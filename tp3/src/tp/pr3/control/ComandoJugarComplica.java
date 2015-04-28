package tp.pr3.control;

import tp.pr3.logica.Juego;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasComplica;

public class ComandoJugarComplica extends Comando {
	
	/**
	 * Cambia el tipo de juego
	 */
	public void ejecuta(Partida p, String[] args) {
		ReglasComplica complica = new ReglasComplica();
		p.reset(complica);
		p.setJuego(Juego.COMPLICA);
		System.out.println("Partida reiniciada.");
	}

}
