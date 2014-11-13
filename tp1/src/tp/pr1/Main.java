package tp.pr1;
import java.util.Scanner;
import tp.pr1.control.Controlador;
import tp.pr1.logica.Partida;

public class Main {

	public static void main(String args[]) {
		Partida p = new Partida();
		Scanner in = new Scanner(System.in);
		Controlador c = new Controlador(p, in);
		c.run();
	}

}
