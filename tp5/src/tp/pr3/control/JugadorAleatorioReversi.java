package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoReversi;
import tp.pr3.logica.Tablero;

public class JugadorAleatorioReversi implements Jugador {

	/**
	 * Devuelve el siguiente movimiento a efectuar por el jugador
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		MovimientoReversi mov = null;
		do {
			int columna = (int)(Math.random()*tab.getAncho());
			int fila = (int)(Math.random()*tab.getAlto());
			mov = new MovimientoReversi(columna+1, fila+1, color);
			if (mov.volteaFichas(tab, false)==0) {
				mov = null;
			}
		}while (mov == null);
		
		return mov;
	}

}
