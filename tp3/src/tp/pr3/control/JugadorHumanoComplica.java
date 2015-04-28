package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.Tablero;

public class JugadorHumanoComplica implements Jugador{
	Scanner in;
	
	/**
	 * Establece el escaner
	 * @param sc scanner para leer de teclado
	 */
	public JugadorHumanoComplica(Scanner sc) {
		in = sc;
	}

	/**
	 * Devuelve el siguiente movimiento a efectuar por el jugador
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		System.out.print("Introduce la columna: ");
		int columna = in.nextInt();
		in.nextLine();
		MovimientoComplica mov = new MovimientoComplica(columna, color);
		return mov;
	}
}
