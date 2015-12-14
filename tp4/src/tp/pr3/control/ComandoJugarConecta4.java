package tp.pr3.control;

import tp.pr3.logica.Juego;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasConecta4;

public class ComandoJugarConecta4 extends Comando {
	
	/**
	 * Cambia el tipo de juego
	 */
	public void ejecuta(Partida p, String[] args) {
		ReglasConecta4 conecta4 = new ReglasConecta4();
		p.reset(conecta4);
		p.setJuego(Juego.CONECTA4);
		System.out.println("Partida reiniciada.");
	}

}
