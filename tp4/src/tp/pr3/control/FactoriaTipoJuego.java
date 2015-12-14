package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.ReglasJuego;

/**
 * Interfaz de los tipos de juego
 */
public interface FactoriaTipoJuego {
	
	/**
	 * Construye el objeto Jugador capaz de jugar al juego concreto de forma aleatoria
	 * @return Objeto jugador que juega de forma aleatoria
	 */
	public abstract Jugador creaJugadorAleatorio();
	
	/**
	 * Construye el objeto Jugador que se encarga de preguntar al usuario por consola el siguiente movimiento a realizar
	 * @param sc Scanner de la entrada que utilizará el objeto para preguntar al usuario
	 * @return Objeto jugador que utilizar para preguntar al usuario el siguiente movimiento
	 */
	public abstract Jugador creaJugadorHumanoConsola(Scanner sc);
	
	/**
	 * Construye un movimiento para el juego concreto
	 * @param col Columna donde se quiere colocar
	 * @param fila Fila donde se quiere colocar
	 * @param color Color de la ficha que se pondrá
	 * @return Objeto de tipo Movimiento capaz de ejecutar el movimiento para el juego concreto
	 */
	public abstract Movimiento creaMovimiento(int col, int fila, Ficha color);
	
	/**
	 * Construye las reglas del juego concreto
	 * @return El objeto que implementa las reglas del juego
	 */
	public abstract ReglasJuego creaReglas();
}
