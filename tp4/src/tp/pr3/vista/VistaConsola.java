package tp.pr3.vista;
import java.util.Scanner;
import java.util.StringTokenizer;

import tp.pr3.control.ControladorConsola;
import tp.pr3.control.FactoriaTipoJuego;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.Juego;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasComplica;
import tp.pr3.logica.ReglasConecta4;

/**
 * Vista por consola de la partida
 */
public class VistaConsola implements Vista{
	private ControladorConsola controlador;
	private Scanner in;
	
	/**
	 * Constructora de la clase VistaConsola
	 * @param f factoria para el tipo de juego
	 * @param p partida a ejecutar
	 * @param in scanner para leer de teclado
	 */
	public VistaConsola(Partida p, Scanner in) {
		this.controlador = new ControladorConsola(p);
		this.in = in;
		p.setIn(in);
	}
	
	/**
	 * Ejecuta el programa
	 */
	public void run() {
		while (!controlador.isPartidaTerminada()) {
			System.out.println(controlador.getTablero());
			if (controlador.getTurno() == Ficha.BLANCA){
				System.out.println("Juegan blancas");
			}
			else if (controlador.getTurno() == Ficha.NEGRA){
				System.out.println("Juegan negras");
			}
		
				System.out.print("Qué quieres hacer? ");
				String linea = in.nextLine();
				StringTokenizer st = new StringTokenizer(linea);
				String[] args = new String[st.countTokens()-1];
				String nombreComando = st.nextToken();
				int i=0;
				while (st.hasMoreTokens()){
					args[i] = st.nextToken();
					i++;
				}
				try {
					controlador.ejecuta(nombreComando, args);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			
		}
		
		System.out.println(controlador.getTablero());
		if (controlador.getGanador() == Ficha.VACIA) {
			System.out.println("Partida terminada en tablas.");
		} else {
			if (controlador.getGanador() == Ficha.BLANCA){
				System.out.println("Ganan las blancas");
			}
			else if (controlador.getGanador() == Ficha.NEGRA){
				System.out.println("Ganan las negras");
			}
		}
	}	
}