package tp.pr3.logica;

import java.util.Scanner;

import tp.pr3.control.Jugador;
import tp.pr3.control.JugadorAleatorioConecta4;
import tp.pr3.control.JugadorHumanoConecta4;

public class ReglasConecta4 implements ReglasJuego {

	private static final int ALTO = 6;
	private static final int ANCHO = 7;

	private Ficha ganador;
	private Ficha turno;
	
	/**
	 * Constructor de la clase
	 */
	public ReglasConecta4() {
		turno = Ficha.BLANCA;
		ganador = Ficha.VACIA;
	}
	
	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no, devuelve el color del ganador (si lo hay)
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		if (hayCuatroEnRaya(t))
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
		return t.isLleno();
	}

	/**
	 * Comprueba si hay cuatro en raya ya sea vertical, horizontal o diagonal
	 * @param tablero de la partida
	 * @return si hay algun cuatro en raya
	 */
	private boolean hayCuatroEnRaya(Tablero tablero) {
		return hayCuatroEnRayaHorizontal(tablero) || 
				hayCuatroEnRayaVertical(tablero) ||
				hayCuatroEnRayaDiagonal(tablero);
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

	/**
	 * Devuelve el jugador aleatorio
	 */
	public Jugador jugadorAleatorio() {
		return new JugadorAleatorioConecta4();
	}
	
	/**
	 * Devuelve el jugador humano
	 */
	public Jugador jugadorHumano(Scanner sc) {
		return new JugadorHumanoConecta4(sc);
	}

	/**
	 * Devuelve el tipo de juego
	 */
	public Juego getJuego() {
		return Juego.CONECTA4;
	}
}
