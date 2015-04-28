package tp.pr2.control;
import java.util.Scanner;

import tp.pr2.logica.Ficha;
import tp.pr2.logica.Juego;
import tp.pr2.logica.Movimiento;
import tp.pr2.logica.MovimientoComplica;
import tp.pr2.logica.MovimientoConecta4;
import tp.pr2.logica.Partida;
import tp.pr2.logica.ReglasComplica;
import tp.pr2.logica.ReglasConecta4;

public class Controlador {
	private Partida partida;
	private Scanner in;
	private Juego juego; // 0 -> Conecta 4, 1 -> Complica
	
	public Controlador(Partida p, Scanner in) { //Constructora de la clase Controlador
		this.partida = p;
		this.in = in;
		juego = Juego.CONECTA4;
	}
	
	public void run() { // Ejecuta la practica
		while (!partida.isTerminada()) { //Comprueba si la partida esta terminada
			System.out.println(partida.getTablero());
			if (partida.getTurno() == Ficha.BLANCA){
				System.out.println("Juegan blancas");
			}
			else if (partida.getTurno() == Ficha.NEGRA){
				System.out.println("Juegan negras");
			}
			System.out.print("Qué quieres hacer? ");
			String comando = in.nextLine();
			
			if (comando.equalsIgnoreCase("poner")) { //Si el comando es Poner
				System.out.print("Introduce la columna: ");
				int columna = in.nextInt();
				in.nextLine();
				Movimiento mov = null;
				switch (juego) { //Ejecuta movivmiento en funcion del objeto
				case CONECTA4:
					mov = new MovimientoConecta4(columna, partida.getTurno());
					break;
				case COMPLICA:	
					mov = new MovimientoComplica(columna, partida.getTurno());
				}
				if (!partida.ejecutaMovimiento(mov)) {
					System.err.println("Movimiento incorrecto");
				}
			} else if (comando.equalsIgnoreCase("deshacer")) { //Si el comando es Deshacer
				if (!partida.undo()) { //Deshace el movimineto
					System.err.println("Imposible deshacer.");
				}
			} else if (comando.equalsIgnoreCase("reiniciar")) { //Si el comando es Reiniciar
				partida.reset(partida.getReglas()); //Reinicia la partida
				System.out.println("Partida reiniciada.");
			} else if (comando.toLowerCase().startsWith("jugar c")) {
				if (comando.length() == 8) {
					switch (comando.toLowerCase().charAt(7)) { //Cambia el tipo de juego
					case '4':
						ReglasConecta4 conecta4 = new ReglasConecta4();
						partida = new Partida(conecta4);
						juego = Juego.CONECTA4;
						System.out.println("Partida reiniciada.");
						break;
					case 'o':
						ReglasComplica complica = new ReglasComplica();
						partida = new Partida(complica);
						juego = Juego.COMPLICA;
						System.out.println("Partida reiniciada.");
						break;
					default:
						System.out.println("No te entiendo.");
					}
				} 
				else {
					System.out.println("No te entiendo.");
				}
			} else if (comando.equalsIgnoreCase("salir")) { //Si el comando es Salir
				System.exit(0);
			} else {
				System.err.println("No te entiendo."); //Si el comando no es valido
			}
		}
		
		// Muestra el ganador o la partida en tablas
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