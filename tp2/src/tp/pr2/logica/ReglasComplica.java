package tp.pr2.logica;

public class ReglasComplica implements ReglasJuego {

	private static final int ALTO = 7;
	private static final int ANCHO = 4;

	private Ficha ganador;
	private Ficha turno;
	private boolean cuatroEnRayaDoble;
	
	public ReglasComplica() { //Constructor de la clase
		turno = Ficha.BLANCA;
		ganador = Ficha.VACIA;
		cuatroEnRayaDoble = false;
	}
	
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) { //Permite averiguar si en la partida ya tenemos un ganador o no devuelve el color del ganador (si lo hay)
		ganador = Ficha.VACIA;
		if (hayCuatroEnRaya(t) && !cuatroEnRayaDoble)
		{
			return ganador;
		}
		else {
			return Ficha.VACIA;
		}
	}

	public Tablero iniciaTablero() { //Construye el tablero que hay que utilizar para la partida
		return new Tablero(ANCHO, ALTO);
	}

	public Ficha jugadorInicial() { //Devuelve el color del jugador que comienza la partida
		return Ficha.BLANCA;
	}

	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) { //Devuelve el color del jugador al que le toca poner
		switch (ultimoEnPoner) {
		case BLANCA: 
			turno = Ficha.NEGRA;
			break;
		case NEGRA:
			turno = Ficha.BLANCA;
			break;
		}
		return turno;
	}

	public boolean tablas(Ficha ultimoEnPoner, Tablero t) { //Devuelve true si, con el estado del tablero dado, la partida ha terminado en tablas
		return false;
	}

	private boolean hayCuatroEnRaya(Tablero tablero) { //Comprueba si hay cuatro en raya ya sea vertical, horizontal o diagonal
		cuatroEnRayaDoble = false;
		return hayCuatroEnRayaHorizontal(tablero) || 
				hayCuatroEnRayaVertical(tablero) ||
				hayCuatroEnRayaDiagonal(tablero);
	}
	private boolean hayCuatroEnRayaHorizontal(Tablero tablero) { //Comprueba si hay cuatro en raya horizontal
		boolean res = false;
		for (int i=1; i<=tablero.getAlto(); i++) {
			for (int j=1; j<=tablero.getAncho()-3; j++) {
				Ficha candidata = tablero.getCasilla(j, i);
				boolean aux = false;
				if (candidata!=Ficha.VACIA) {
					aux = true;
					for (int k=1; k<4; k++) {
						if (tablero.getCasilla(j+k, i)!=candidata) {
							aux = false;
							k = 4;
						}
					}
				}
				if (aux) {
					if ((ganador != Ficha.VACIA) && (ganador != candidata)) {
						cuatroEnRayaDoble = true;
					}
					else {
						ganador = candidata;
						res = true;
					}
				}
			}
		}
		return res;
	}
	
	private boolean hayCuatroEnRayaVertical(Tablero tablero) { //Comprueba si hay cuatro en raya vertical
		boolean res = false;
		for (int i=1; i<=tablero.getAncho(); i++) {
			for (int j=1; j<=tablero.getAlto()-3; j++) {
				Ficha candidata = tablero.getCasilla(i, j);
				boolean aux = false;
				if (candidata!=Ficha.VACIA) {
					aux = true;
					for (int k=1; k<4; k++) {
						if (tablero.getCasilla(i, j+k)!=candidata) {
							aux = false;
							k = 4;
						}
					}
				}
				if (aux) {
					if ((ganador != Ficha.VACIA) && (ganador != candidata)) {
						cuatroEnRayaDoble = true;
					}
					else {
						ganador = candidata;
						res = true;
					}
				}
			}
		}
		return res;
	}

	private boolean hayCuatroEnRayaDiagonal(Tablero tablero) { //Comprueba si hay cuatro en raya diagonal
		boolean res = false;
		for (int i=1; i<=tablero.getAncho()-3; i++) {
			for (int j=1; j<=tablero.getAlto()-3; j++) {
				Ficha candidata = tablero.getCasilla(i, j);
				boolean aux = false;
				if (candidata!=Ficha.VACIA) {
					aux = true;
					for (int k=1; k<4; k++) {
						if (tablero.getCasilla(i+k, j+k)!=candidata) {
							aux = false;
							k = 4;
						}
					}
				}
				if (aux) {
					if ((ganador != Ficha.VACIA) && (ganador != candidata)) {
						cuatroEnRayaDoble = true;
					}
					else {
						ganador = candidata;
						res = true;
					}
				}
			}
			for (int j=4; j<=tablero.getAlto(); j++) {
				Ficha candidata = tablero.getCasilla(i, j);
				boolean aux = false;
				if (candidata!=Ficha.VACIA) {
					aux = true;
					for (int k=1; k<4; k++) {
						if (tablero.getCasilla(i+k, j-k)!=candidata) {
							aux = false;
							k = 4;
						}
					}
				}
				if (aux) {
					if ((ganador != Ficha.VACIA) && (ganador != candidata)) {
						cuatroEnRayaDoble = true;
					}
					else {
						ganador = candidata;
						res = true;
					}
				}
			}
		}
		return res;
	}
}
