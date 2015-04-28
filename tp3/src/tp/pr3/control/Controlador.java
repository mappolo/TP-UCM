package tp.pr3.control;
import java.util.Scanner;
import java.util.StringTokenizer;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Juego;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasComplica;
import tp.pr3.logica.ReglasConecta4;

/**
 * Controlador de la partida
 */
public class Controlador {
	private Partida partida;
	private Scanner in;
	
	/**
	 * Constructora de la clase Controlador
	 * @param f factoria para el tipo de juego
	 * @param p partida a ejecutar
	 * @param in scanner para leer de teclado
	 */
	public Controlador(FactoriaTipoJuego f, Partida p, Scanner in) {
		this.partida = p;
		this.in = in;
		p.setIn(in);
	}
	
	/**
	 * Ejecuta el programa
	 */
	public void run() {
		while (!partida.isTerminada()) {
			System.out.println(partida.getTablero());
			if (partida.getTurno() == Ficha.BLANCA){
				System.out.println("Juegan blancas");
			}
			else if (partida.getTurno() == Ficha.NEGRA){
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
					Comando comando = FactoriaComandos.parse(nombreComando);
					comando.ejecuta(partida, args);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			
		}
		
		System.out.println(partida.getTablero());
		if (partida.getGanador() == Ficha.VACIA) {
			System.out.println("Partida terminada en tablas.");
		} else {
			if (partida.getGanador() == Ficha.BLANCA){
				System.out.println("Ganan las blancas");
			}
			else if (partida.getGanador() == Ficha.NEGRA){
				System.out.println("Ganan las negras");
			}
		}
	}	
}