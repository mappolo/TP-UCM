package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoGravity;
import tp.pr3.logica.Tablero;

public class JugadorAleatorioGravity  implements Jugador {

	/**
	 * Devuelve el siguiente movimiento a efectuar por el jugador
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		Movimiento mov = null;
		do {
			int columna = (int)(Math.random()*tab.getAncho());
			int fila = (int)(Math.random()*tab.getAlto());
			if (tab.getCasilla(columna+1, fila+1) == Ficha.VACIA) {
				mov = new MovimientoGravity(columna+1, fila+1, color);
			}
		}while (mov == null);
		
		return mov;
	}
}
