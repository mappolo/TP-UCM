package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.Tablero;

public class JugadorAleatorioConecta4 implements Jugador {

	/**
	 * Devuelve el siguiente movimiento a efectuar por el jugador
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		Movimiento mov = null;
		do {
			int columna = (int)(Math.random()*tab.getAncho());
			if (!tab.isColumnaLlena(columna)){
				mov = new MovimientoConecta4(columna+1, color);
			}
		} while (mov == null);
		return mov;
	}

}
