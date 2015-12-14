package tp.pr3.logica;

public class MovimientoConecta4 extends Movimiento {

	private int columna;
	
	/**
	 * Constructor del objeto
	 * @param donde columna del movimiento a ejecutar
	 * @param color del jugador
	 */
	public MovimientoConecta4(int donde, Ficha color) {
		super(color);
		columna = donde;
	}
	
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parametro
	 */
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {	
		if ((columna<1) || (columna>tab.getAncho())) {
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y 7.");
		}
		
		int i=getUltimaCasillaOcupada(tab, columna);
		
		if (i == 1)
		{
			throw new MovimientoInvalido("Columna llena.");
		}
		
		tab.setCasilla(columna, i-1, getJugador());
		
		return true;
	}

	/**
	 * Deshace el movimiento en el tablero recibido como parametro
	 */
	public void undo(Tablero tab) {
		quitaFicha(tab, columna);
	}

	/**
	 * Si desaparece no es vacia, es decir, la columna esta llena, eleva las fichas y coloca la ficha eliminada abajo(no se usa en esta clase)
	 */
	public void retornaFicha(Tablero tab) {
		// No hay nada que hacer		
	}
}
