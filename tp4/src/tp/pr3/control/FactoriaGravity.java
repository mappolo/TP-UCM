package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoGravity;
import tp.pr3.logica.ReglasGravity;
import tp.pr3.logica.ReglasJuego;

public class FactoriaGravity implements FactoriaTipoJuego {

	private int numCols;
	private int numFilas;
	
	/**
	 * Establece el tamaño del tablero gravity
	 * @param numCols del tablero
	 * @param numFilas del tablero
	 */
	public FactoriaGravity(int numCols, int numFilas) {
		this.numCols = numCols;
		this.numFilas = numFilas;
	}

	/**
	 * Construye el objeto Jugador capaz de jugar al juego concreto de forma aleatoria
	 */
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}

	/**
	 * Construye el objeto Jugador que se encarga de preguntar al usuario por consola el siguiente movimiento a realizar
	 */
	public Jugador creaJugadorHumanoConsola(Scanner sc) {
		return new JugadorHumanoGravity(sc);
	}

	/**
	 * Construye un movimiento para el juego concreto
	 */
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col, fila, color);
	}

	/**
	 * Construye las reglas del juego concreto
	 */
	public ReglasJuego creaReglas() {
		return new ReglasGravity(numCols, numFilas);
	}

	/**
	 * Establece las columnas del tablero
	 * @param numCols del tablero
	 */
	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}

	/**
	 * Establece las filas del tablero
	 * @param numFilas del tablero
	 */
	public void setNumFilas(int numFilas) {
		this.numFilas = numFilas;
	}
}
