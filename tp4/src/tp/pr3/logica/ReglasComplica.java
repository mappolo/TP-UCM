package tp.pr3.logica;

import java.util.Scanner;

import tp.pr3.control.Jugador;
import tp.pr3.control.JugadorAleatorioComplica;
import tp.pr3.control.JugadorHumanoComplica;

public class ReglasComplica implements ReglasJuego {

	private static final int ALTO = 7;
	private static final int ANCHO = 4;

	private Ficha ganador;
	private Ficha turno;
	private boolean cuatroEnRayaDoble;
	
	/**
	 * Constructor de la clase
	 */
	public ReglasComplica() {
		turno = Ficha.BLANCA;
		ganador = Ficha.VACIA;
		cuatroEnRayaDoble = false;
	}
	
	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no, devuelve el color del ganador (si lo hay)
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		ganador = Ficha.VACIA;
		if (hayCuatroEnRaya(t) && !cuatroEnRayaDoble)
		{
			return ganador;
		}
		else {
			return Ficha.VACIA;
		}
	}

	/**
	 * Construye el tablero que hay que utilizar para la partida
	 */
	public Tablero iniciaTablero() {
		return new Tablero(ANCHO, ALTO);
	}

	/**
	 * Devuelve el color del jugador que comienza la partida
	 */
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	/**
	 * Devuelve el color del jugador al que le toca poner
	 */
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
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

	/**
	 * Devuelve true si, con el estado del tablero dado, la partida ha terminado en tablas
	 */
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return false;
	}

	/**
	 *Comprueba si hay cuatro en raya ya sea vertical, horizontal o diagonal
	 * @param tablero de la partida
	 * @return si hay algun cuatro en raya
	 */
	private boolean hayCuatroEnRaya(Tablero tablero) {
		cuatroEnRayaDoble = false;
		boolean res = false;
		res = hayCuatroEnRayaHorizontal(tablero);
		res = hayCuatroEnRayaVertical(tablero) || res;
		res = hayCuatroEnRayaDiagonal(tablero) || res;
		return !cuatroEnRayaDoble && res;
	}
	
	/**
	 * Comprueba si hay cuatro en raya horizontal
	 * @param tablero de la partida
	 * @return si hay cuatro en raya horizontal
	 */
	private boolean hayCuatroEnRayaHorizontal(Tablero tablero) {
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
	
	/**
	 * Comprueba si hay cuatro en raya vertical
	 * @param tablero de la partida
	 * @return si hay cuatro en raya vertical
	 */
	private boolean hayCuatroEnRayaVertical(Tablero tablero) {
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

	/**
	 * Comprueba si hay cuatro en raya diagonal
	 * @param tablero de la partida
	 * @return si hay cuatro en raya diagonal
	 */
	private boolean hayCuatroEnRayaDiagonal(Tablero tablero) {	
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

	/**
	 * Devuelve el jugador aleatorio
	 */
	public Jugador jugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}
	
	/**
	 * Devuelve el jugador humano
	 */
	public Jugador jugadorHumano(Scanner sc) {
		return new JugadorHumanoComplica(sc);
	}

	/**
	 * Devuelve el tipo de juego
	 */
	@Override
	public Juego getJuego() {
		return Juego.COMPLICA;
	}
}
