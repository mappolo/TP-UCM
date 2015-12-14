package tp.pr3.logica;

public class MovimientoComplica extends Movimiento {
	private int columna;
	private Ficha desaparece;
	
	/**
	 * Constructor del objeto
	 * @param donde columna del movimiento a ejecutar
	 * @param color del jugador
	 */
	public MovimientoComplica(int donde, Ficha color) {
		super(color);
		columna = donde;
		desaparece = Ficha.VACIA;
	}
	
	/**
	 * Devuelve desaparece
	 * @return desaparece
	 */
	public Ficha getDesaparece() {
		return desaparece;
	}

	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parametro 
	 */
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if ((columna<1)||(columna>tab.getAncho())) {
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y 4.");
		}
		
		int i=getUltimaCasillaOcupada(tab, columna);
		
		if (i==1) { // Columna llena. Eliminamos la ficha de abajo
			desaparece = tab.getCasilla(columna, tab.getAlto());
			for (int j=tab.getAlto(); j>1; j--){
				Ficha arriba = tab.getCasilla(columna, j-1);
				tab.setCasilla(columna, j, arriba);
			}
			// Colocamos la nueva ficha en la primera fila
			i = 2;
		}
		tab.setCasilla(columna, i-1, getJugador());
		
		return true;
	}

	/**
	 * Deshace el movimiento en el tablero recibido como parametro
	 */
	public void undo(Tablero tab) {
		quitaFicha(tab, columna);
		retornaFicha(tab);
	}

	/**
	 * Si desaparece no es vacia, es decir, la columna esta llena, eleva las fichas y coloca la ficha eliminada abajo
	 */
	public void retornaFicha(Tablero tab) {
		if (desaparece != Ficha.VACIA) {
			for (int i=1; i<=tab.getAlto(); i++){
				Ficha abajo = tab.getCasilla(columna, i+1);
				tab.setCasilla(columna, i, abajo);
			}
			tab.setCasilla(columna, tab.getAlto(), desaparece);
		}
		
	}
}
