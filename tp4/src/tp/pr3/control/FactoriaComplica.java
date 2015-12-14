package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.ReglasComplica;
import tp.pr3.logica.ReglasJuego;

public class FactoriaComplica implements FactoriaTipoJuego {

	/**
	 * Construye el objeto Jugador capaz de jugar al juego concreto de forma aleatoria
	 */
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}

	/**
	 * Construye el objeto Jugador que se encarga de preguntar al usuario por consola el siguiente movimiento a realizar
	 */
	public Jugador creaJugadorHumanoConsola(Scanner sc) {
		return new JugadorHumanoComplica(sc);
	}

	/**
	 * Construye un movimiento para el juego concreto
	 */
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoComplica(col, color);
	}
	
	/**
	 * Construye las reglas del juego concreto
	 */
	public ReglasJuego creaReglas() {
		return new ReglasComplica();
	}

}
