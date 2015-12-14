package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.Tablero;

/**
 * Interfaz que representa un jugador
 */
public interface Jugador {
	
	/**
	 * Devuelve el siguiente movimiento a efectuar por el jugador
	 * @param tab Estado del tablero donde poner
	 * @param color Color de la ficha que hay que colocar
	 * @return Movimiento que se desea ejecutar
	 */
	public abstract Movimiento getMovimiento(Tablero tab, Ficha color);
}
