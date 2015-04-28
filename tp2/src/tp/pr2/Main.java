package tp.pr2;
import java.util.Scanner;

import tp.pr2.control.Controlador;
import tp.pr2.logica.Partida;
import tp.pr2.logica.ReglasConecta4;
//Clase Principal
public class Main {

	public static void main(String args[]) {
		Partida p = new Partida(new ReglasConecta4()); //Crea objeto p de tipo partida
		Scanner in = new Scanner(System.in); //Crea objeto in de tipo Scanner
		Controlador c = new Controlador(p, in); //Crea objeto c de tipo controlador
		c.run(); //Ejecuta el objeto c
	}

}
