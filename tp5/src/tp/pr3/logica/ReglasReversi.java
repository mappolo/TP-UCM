package tp.pr3.logica;

import java.util.Scanner;

import tp.pr3.control.Jugador;
import tp.pr3.control.JugadorAleatorioReversi;
import tp.pr3.control.JugadorHumanoReversi;

public class ReglasReversi implements ReglasJuego {

	private static final int LADO = 8;
	
	private Ficha ganador;
	private Ficha turno;
	
	/**
	 * Constructor de la clase
	 */
	public ReglasReversi() {
		turno = Ficha.NEGRA;
		ganador = Ficha.VACIA;
	}
	
	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no, devuelve el color del ganador (si lo hay)
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		if ((puedePoner(Ficha.BLANCA, t))||(puedePoner(Ficha.NEGRA, t)))
			return Ficha.VACIA;
		int blancas = casillasOcupadas(t, Ficha.BLANCA);
		int negras  = casillasOcupadas(t, Ficha.NEGRA);
		if ( blancas > negras )
			return Ficha.BLANCA;
		else 
			return Ficha.NEGRA;
	}

	/**
	 * Construye el tablero que hay que utilizar para la partida
	 */
	public Tablero iniciaTablero() {
		Tablero tab = new Tablero(LADO, LADO);
		tab.setCasilla(LADO/2, LADO/2, Ficha.BLANCA);
		tab.setCasilla((LADO/2)+1, (LADO/2)+1, Ficha.BLANCA);
		tab.setCasilla(LADO/2, (LADO/2)+1, Ficha.NEGRA);
		tab.setCasilla((LADO/2)+1, LADO/2, Ficha.NEGRA);
		return tab;
	}

	/**
	 * Devuelve el color del jugador que comienza la partida
	 */
	public Ficha jugadorInicial() {
		return Ficha.NEGRA;
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
		
		if (puedePoner(turno, t))
			return turno;
		if (puedePoner(ultimoEnPoner, t))
			return ultimoEnPoner;
		return Ficha.VACIA;
	}

	public boolean puedePoner(Ficha color, Tablero t) {
		for (int i=1; i<=t.getAncho(); i++) {
			for (int j=1; j<=t.getAlto(); j++) {
				MovimientoReversi mov = new MovimientoReversi(i, j, color);
				if (mov.volteaFichas(t, false)>0)
					return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		if ((puedePoner(Ficha.BLANCA, t))||(puedePoner(Ficha.NEGRA, t)))
			return false;
		int blancas = casillasOcupadas(t, Ficha.BLANCA);
		int negras  = casillasOcupadas(t, Ficha.NEGRA);
		// Hay tablas si no hay movimientos posibles y hay mitad de fichas de cada color
		return blancas == negras;
	}

	/**
	 * Devuelve el jugador aleatorio
	 */
	public Jugador jugadorAleatorio() {
		return new JugadorAleatorioReversi();
	}
	
	/**
	 * Devuelve el jugador humano
	 */
	public Jugador jugadorHumano(Scanner sc) {
		return new JugadorHumanoReversi(sc);
	}
	
	/**
	 * Devuelve el tipo de juego
	 */
	public Juego getJuego() {
		return Juego.REVERSI;
	}

	public int casillasOcupadas(Tablero tab, Ficha color) {
		int res = 0;
		for (int i=1; i<=tab.getAlto(); i++) {
			for (int j=1; j<=tab.getAncho(); j++) {
				if (tab.getCasilla(j, i) == color) {
					res++;
				}
			}
		}
		return res;
	}
}
