package tp.pr2.logica;

public class ReglasConecta4 implements ReglasJuego {

	private static final int ALTO = 6;
	private static final int ANCHO = 7;

	private Ficha ganador;
	private Ficha turno;
	
	public ReglasConecta4() { //Constructor de la clase
		turno = Ficha.BLANCA;
		ganador = Ficha.VACIA;
	}
	
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) { //Permite averiguar si en la partida ya tenemos un ganador o no devuelve el color del ganador (si lo hay)
		if (hayCuatroEnRaya(t))
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
		return t.isLleno();
	}

	private boolean hayCuatroEnRaya(Tablero tablero) { //Comprueba si hay cuatro en raya ya sea vertical, horizontal o diagonal
		return hayCuatroEnRayaHorizontal(tablero) || 
				hayCuatroEnRayaVertical(tablero) ||
				hayCuatroEnRayaDiagonal(tablero);
	}
	private boolean hayCuatroEnRayaHorizontal(Tablero tablero) { //Comprueba si hay cuatro en raya horizontal
		boolean res = false;
		for (int i=1; i<=tablero.getAlto(); i++) {
			for (int j=1; j<=tablero.getAncho()-3; j++) {
				Ficha candidata = tablero.getCasilla(j, i);
				if (candidata!=Ficha.VACIA) {
					res = true;
					for (int k=1; k<4; k++) {
						if (tablero.getCasilla(j+k, i)!=candidata) {
							res = false;
							k = 4;
						}
					}
				}
				if (res) {
					ganador = candidata;
					return res;
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
				if (candidata!=Ficha.VACIA) {
					res = true;
					for (int k=1; k<4; k++) {
						if (tablero.getCasilla(i, j+k)!=candidata) {
							res = false;
							k = 4;
						}
					}
				}
				if (res) {
					ganador = candidata;
					return res;
				}
			}
		}
		return false;
	}

	private boolean hayCuatroEnRayaDiagonal(Tablero tablero) { //Comprueba si hay cuatro en raya diagonal	
		boolean res = false;
		for (int i=1; i<=tablero.getAncho()-3; i++) {
			for (int j=1; j<=tablero.getAlto()-3; j++) {
				Ficha candidata = tablero.getCasilla(i, j);
				if (candidata!=Ficha.VACIA) {
					res = true;
					for (int k=1; k<4; k++) {
						if (tablero.getCasilla(i+k, j+k)!=candidata) {
							res = false;
							k = 4;
						}
					}
				}
				if (res) {
					ganador = candidata;
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
							k = 4;
						}
					}
				}
				if (res) {
					ganador = candidata;
					return res;
				}
			}
		}
		return false;
	}
}
