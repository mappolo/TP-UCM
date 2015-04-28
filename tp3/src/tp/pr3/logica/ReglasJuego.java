package tp.pr3.logica;

import java.util.Scanner;

import tp.pr3.control.Jugador;

/**
 * interfaz de reglas
 */
public interface ReglasJuego {
	
	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no, devuelve el color del ganador (si lo hay)
	 * @param ultimoMovimiento ejecutado
	 * @param t tablero de la partida
	 * @return el color del ganador(en caso de no haber ganador, vacio)
	 */
	public abstract Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t);
	
	/**
	 * Construye el tablero que hay que utilizar para la partida
	 * @return el tablero
	 */
	public abstract Tablero iniciaTablero();
	
	/**
	 * Devuelve el color del jugador que comienza la partida
	 * @return el color del jugador
	 */
	public abstract Ficha jugadorInicial();
	
	/**
	 * Devuelve el color del jugador al que le toca poner
	 * @param ultimoEnPoner color del ultimo jugador en poner
	 * @param t tablero de la partida
	 * @return color del jugador
	 */
	public abstract Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t);
	
	/**
	 * Devuelve true si, con el estado del tablero dado, la partida ha terminado en tablas
	 * @param ultimoEnPoner color del ultimo jugador en poner
	 * @param t tablero de la partida
	 * @return si hay tablas
	 */
	public abstract boolean tablas(Ficha ultimoEnPoner, Tablero t);
	
	/**
	 * Devuelve el jugador aleatorio
	 * @return jugador aleatorio
	 */
	public abstract Jugador jugadorAleatorio();
	
	/**
	 * Devuelve el jugador humano
	 * @param sc escaner
	 * @return jugador humano
	 */
	public abstract Jugador jugadorHumano(Scanner sc);
	
	/**
	 * Devuelve el tipo de juego
	 * @return tipo de juego
	 */
	public abstract Juego getJuego();
}
