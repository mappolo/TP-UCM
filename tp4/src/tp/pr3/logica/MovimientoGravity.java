package tp.pr3.logica;

public class MovimientoGravity extends Movimiento {

	private int columna;
	private int fila;

	/**
	 * Constructor del objeto
	 * @param columna del tablero
	 * @param fila del tablero
	 * @param color del jugador
	 */
	public MovimientoGravity(int columna, int fila, Ficha color) {
		super(color);
		this.columna = columna;
		this.fila = fila;
	}

	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parametro
	 */
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if ((columna<1) || (columna>tab.getAncho())) {
			throw new MovimientoInvalido("Posición incorrecta.");
		}

		if ((fila<1) || (fila>tab.getAlto())) {
			throw new MovimientoInvalido("Posición incorrecta.");
		}

		if (tab.getCasilla(columna, fila) != Ficha.VACIA) {
			throw new MovimientoInvalido("Casilla ocupada.");
		}

		// Calculamos el (los) borde(s) mas cercano(s)
		int incX = 0;
		int incY = 0;

		// Borde derecho mas cercano que el izquierdo
		if (columna>tab.getAncho()/2) {
			// Borde inferior mas cercano que borde superior
			if (fila>tab.getAlto()/2) {
				// Borde derecho mas cercano que borde inferior
				if ((tab.getAncho()-columna < tab.getAlto()-fila)||
				   ((tab.getAncho()-columna == tab.getAlto()-fila)&&(fila*2==tab.getAlto()+1))){
					if (columna*2>tab.getAncho()+1)
					{
						incX = 1;
					}
				}else{
					// Borde inferior mas cercano que borde derecho 
					if (tab.getAncho()-columna > tab.getAlto()-fila){
						if (fila*2>tab.getAlto()+1)
						{
							incY = 1;
						}
					}else{
						if (columna*2>tab.getAncho()+1)
						{
							// Borde derecho igual de cercano que borde inferior
							incX = 1;
							incY = 1;
						}
					}
				}
			}
			else{
				// Borde derecho mas cercano que borde superior
				if (tab.getAncho()-columna < fila-1){
					incX = 1;
				}else{
					// Borde superior mas cercano que borde derecho 
					if (tab.getAncho()-columna > fila-1){
						incY = -1;
					}else{
						// Borde derecho igual de cercano que borde superior
						incX = 1;
						incY = -1;
					}
				}
			}
		}
		else {// Borde izquierdo mas cercano que el derecho o igual de cercanos
			// Borde inferior mas cercano que borde superior
			if (fila>tab.getAlto()/2) {
				// Borde izquierdo mas cercano que borde inferior
				if ((columna < tab.getAlto()-fila + 1)||
					((columna == tab.getAlto()-fila + 1)&&(fila*2==tab.getAlto()+1))){
					incX = -1;
				}else{
					// Borde inferior mas cercano que borde izquierdo 
					if (columna > tab.getAlto()-fila + 1){
						incY = 1;
					}else{
						// Borde izquierdo igual de cercano que borde inferior
						incX = -1;
						incY = 1;
					}
				}
			}
			else {
				// Borde izquierdo mas cercano que borde superior
				if (columna < fila){
					incX = -1;
				}else{
					// Borde superior mas cercano que borde derecho 
					if (columna > fila){
						incY = -1;
					}else{
						// Borde derecho igual de cercano que borde superior
						incX = -1;
						incY = -1;
					}
				}
			}
		} 

		if ((incX!=0)||(incY!=0))
		{
			// La ficha gravita al borde adecuado
			while ((columna<tab.getAncho())&&(columna>1)&&
					(fila<tab.getAlto())&&(fila>1)&&
					(tab.getCasilla(columna + incX, fila + incY) == Ficha.VACIA)) {
				columna += incX;
				fila += incY;
			}
		}
		tab.setCasilla(columna, fila, getJugador());

		return true;
	}

	/**
	 * Deshace el movimiento en el tablero recibido como parametro
	 */
	public void undo(Tablero tab) {
		quitaFicha(tab);
	}

	/**
	 * Si desaparece no es vacia, es decir, la columna esta llena, eleva las fichas y coloca la ficha eliminada abajo(no se usa en esta clase)
	 */
	public void retornaFicha(Tablero tab) {
		// No hay nada que hacer
	}

	/**
	 * Quita la ficha indicada del tablero
	 * @param tab tablero del juego
	 */
	protected void quitaFicha(Tablero tab) {
		tab.setCasilla(columna, fila, Ficha.VACIA);
	}
}
