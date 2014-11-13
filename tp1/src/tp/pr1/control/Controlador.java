package tp.pr1.control;
import java.util.Scanner;

import tp.pr1.logica.Ficha;
import tp.pr1.logica.Partida;

public class Controlador {
	private Partida partida;
	private Scanner in;
	
	public Controlador(Partida p, Scanner in) {
		this.partida = p;
		this.in = in;
	}
	
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
			String comando = in.nextLine();
			
			if (comando.equalsIgnoreCase("poner")) {
				System.out.print("Introduce la columna: ");
				int columna = in.nextInt();
				in.nextLine();
				if (!partida.ejecutaMovimiento(partida.getTurno(), columna)) {
					System.err.println("Movimiento incorrecto");
				}
			} else if (comando.equalsIgnoreCase("deshacer")) {
				if (!partida.undo()) {
					System.err.println("Imposible deshacer.");
				}
			} else if (comando.equalsIgnoreCase("reiniciar")) {
				partida.reset();
				System.out.println("Partida reiniciada.");
			} else if (comando.equalsIgnoreCase("salir")) {
				System.exit(0);
			} else {
				System.err.println("No te entiendo.");
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