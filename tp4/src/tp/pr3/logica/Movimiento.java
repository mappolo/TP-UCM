package tp.pr3.logica;

/**
 * Clase abstracta Movimiento
 */
public abstract class Movimiento {
	private Ficha jugador;
	
	/**
	 * Contructora de la clase movimiento
	 * @param j color del jugador
	 */
	public Movimiento(Ficha j) {
		jugador = j;
	}
	
	/**
	 * Devuelve el jugador
	 * @return jugador
	 */
	public Ficha getJugador() {
		return jugador;
	}
	
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parametro
	 * @param tab tablero del juego
	 * @return si se ha podido ejecutar el movimiento
	 * @throws MovimientoInvalido excepcion que se captura
	 */
	public abstract boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;
	
	/**
	 * Deshace el movimiento en el tablero recibido como parametro
	 * @param tab tablero del juego
	 */
	public abstract void undo(Tablero tab);
	
	/**
	* Si desaparece no es vacia, es decir, la columna esta llena, eleva las fichas y coloca la ficha eliminada abajo
	 * @param tab tablero del juego
	 */
	public abstract void retornaFicha(Tablero tab);
	
	/**
	 * Devuelve la ultima casilla ocupada desde 1 a n
	 * @param tab tablero del juego
	 * @param col columna a mirar
	 * @return ultima casilla ocupada o un valor fuera de rango
	 */
	protected int getUltimaCasillaOcupada(Tablero tab, int col) {
		for (int i=1; i<=tab.getAlto(); i++) {
			if (tab.getCasilla(col, i)!=Ficha.VACIA) {
				return i;
			}
		}
		// Ninguna casilla ocupada en la columna. Devolvemos un 
		// valor fuera de rango
		return tab.getAlto()+1;
	}
	
	/**
	 * Quita la ficha indicada del tablero
	 * @param tab tablero del juego
	 * @param col columna a mirar
	 */
	protected void quitaFicha(Tablero tab, int col) {
		int fila = getUltimaCasillaOcupada(tab, col);
		tab.setCasilla(col, fila, Ficha.VACIA);
	}
}
