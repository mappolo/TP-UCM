package tp.pr3.control;

import tp.pr3.logica.Partida;

public class ComandoReiniciar extends Comando {
	
	/**
	 * Ejecuta el comando para reiniciar la partida
	 */
	public void ejecuta(Partida p, String[] args) {
		p.reset(p.getReglas());
		System.out.println("Partida reiniciada.");
	}

}
