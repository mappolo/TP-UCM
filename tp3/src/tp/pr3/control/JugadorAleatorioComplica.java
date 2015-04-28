package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.Tablero;

public class JugadorAleatorioComplica implements Jugador {

	/**
	 * Devuelve el siguiente movimiento a efectuar por el jugador
	 */
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		Movimiento mov = null;
		int columna = (int)(Math.random()*tab.getAncho());
		mov = new MovimientoComplica(columna+1, color);
		return mov;
	}

}
