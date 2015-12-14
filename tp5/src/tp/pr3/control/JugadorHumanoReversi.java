package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoReversi;
import tp.pr3.logica.Tablero;

public class JugadorHumanoReversi implements Jugador {
	Scanner in;
	
	/**
	 * Establece el escaner
	 * @param sc scanner para leer de teclado
	 */
	public JugadorHumanoReversi(Scanner sc) {
		in = sc;
	}

	/**
	 * Devuelve el siguiente movimiento a efectuar por el jugador
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		System.out.print("Introduce la columna: ");
		int columna = in.nextInt();
		in.nextLine();
		System.out.print("Introduce la fila: ");
		int fila = in.nextInt();
		in.nextLine();
		
		MovimientoReversi mov = new MovimientoReversi(columna, fila, color);
		return mov;
	}
}
