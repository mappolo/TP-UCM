package tp.pr1.logica;

public class Partida {
	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;
	private int[] undoStack;
	private int numUndo;
	
	private static final int ALTO = 6;
	private static final int ANCHO = 7;
	
	private static final int MAX_UNDO = 10;
	
	public Partida() {
		tablero = new Tablero(ANCHO, ALTO);
		undoStack = new int[MAX_UNDO];
		
		reset();
	}
	
	public Tablero getTablero() {
		return tablero;
	}

	public Ficha getTurno() {
		return turno;
	}

	public Ficha getGanador() {
		return ganador;
	}

	public boolean isTerminada() {
		return terminada;
	}

	public void reset() {
		tablero.reset();
		turno = Ficha.BLANCA;
		terminada = false;
		ganador = Ficha.VACIA;
		numUndo = 0;
	}
	
	public boolean undo() {
		if (numUndo == 0) {
			return false;
		}
		numUndo--;
		int col = undoStack[numUndo];
		quitaFicha(col);
		cambiaTurno();
		return true;
	}
	
	private void quitaFicha(int col) {
		int fila = getUltimaCasillaOcupada(col);
		tablero.setCasilla(col, fila, Ficha.VACIA);
	}

	public boolean ejecutaMovimiento(Ficha color, int col) {
		if (color != turno || col > Partida.ANCHO || col <= 0){
			return false;
		}
		
		if (tablero.isColumnaLlena(col-1)) {
			return false;
		}
		
		if (terminada) {
			return false;
		}
		
		if ((col<1)||(col>tablero.getAncho())) {
			return false;
		}
		
		int i=getUltimaCasillaOcupada(col);
		
		tablero.setCasilla(col, i-1, color);
		
		compruebaPartidaTerminada();
		
		if (!terminada) {
			actualizaPilaUndo(col);
			cambiaTurno();
		}
		
		return true;
	}
	
	private void compruebaPartidaTerminada() {
		if ((hayCuatroEnRaya()) || (tablero.isLleno())) {
			terminada = true;	
		}
	}

	private boolean hayCuatroEnRaya() {
		return hayCuatroEnRayaHorizontal() || hayCuatroEnRayaVertical() ||
				hayCuatroEnRayaDiagonal();
	}

	private boolean hayCuatroEnRayaHorizontal() {
		boolean res = false;
		for (int i=1; i<=tablero.getAlto(); i++) {
			for (int j=1; j<=tablero.getAncho()-3; j++) {
				Ficha candidata = tablero.getCasilla(j, i);
				if (candidata!=Ficha.VACIA) {
					res = true;
					for (int k=1; k<4; k++) {
						if (tablero.getCasilla(j+k, i)!=candidata) {
							res = false;
						}
					}
				}
				if (res) {
					ganador = candidata;
					terminada = true;
					return res;
				}
			}
		}
		return res;
	}
	
	private boolean hayCuatroEnRayaVertical() {
		boolean res = false;
		for (int i=1; i<=tablero.getAncho(); i++) {
			for (int j=1; j<=tablero.getAlto()-3; j++) {
				Ficha candidata = tablero.getCasilla(i, j);
				if (candidata!=Ficha.VACIA) {
					res = true;
					for (int k=1; k<4; k++) {
						if (tablero.getCasilla(i, j+k)!=candidata) {
							res = false;
						}
					}
				}
				if (res) {
					ganador = candidata;
					terminada = true;
					return res;
				}
			}
		}
		return false;
	}

	private boolean hayCuatroEnRayaDiagonal() {	
		boolean res = false;
		for (int i=1; i<=tablero.getAncho()-3; i++) {
			for (int j=1; j<=tablero.getAlto()-3; j++) {
				Ficha candidata = tablero.getCasilla(i, j);
				if (candidata!=Ficha.VACIA) {
					res = true;
					for (int k=1; k<4; k++) {
						if (tablero.getCasilla(i+k, j+k)!=candidata) {
							res = false;
						}
					}
				}
				if (res) {
					ganador = candidata;
					terminada = true;
					return res;
				}
			}
			for (int j=4; j<=tablero.getAlto(); j++) {
				Ficha candidata = tablero.getCasilla(i, j);
				if (candidata!=Ficha.VACIA) {
					res = true;
					for (int k=1; k<4; k++) {
						if (tablero.getCasilla(i+k, j-k)!=candidata) {
							res = false;
						}
					}
				}
				if (res) {
					ganador = candidata;
					terminada = true;
					return res;
				}
			}
		}
		return false;
	}

	private void actualizaPilaUndo(int col) {
		// Si la pila de undos no esta llena, apilamos el movimiento
		if (numUndo < MAX_UNDO) {
			undoStack[numUndo] = col;
			numUndo++;
		} else {
			// Si no hay sitio, movemos los elementos un lugar a 
			// la izquierda, machacando el primero y apilamos 
			for (int i=1; i<MAX_UNDO; i++) {
				undoStack[i-1] = undoStack[i];
			}
			undoStack[MAX_UNDO-1] = col;
		}
	}
	
	private void cambiaTurno() {
		switch (turno) {
		case BLANCA: 
			turno = Ficha.NEGRA;
			break;
		case NEGRA:
			turno = Ficha.BLANCA;
			break;
		}
		
	}

	private int getUltimaCasillaOcupada(int col) {
		for (int i=1; i<=tablero.getAlto(); i++) {
			if (tablero.getCasilla(col, i)!=Ficha.VACIA) {
				return i;
			}
		}
		// Ninguna casilla ocupada en la columna. Devolvemos un 
		// valor fuera de rango
		return tablero.getAlto()+1;
	}
}
