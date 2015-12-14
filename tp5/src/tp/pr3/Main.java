package tp.pr3;
import java.util.Scanner;

import tp.pr3.control.ControladorConsola;
import tp.pr3.control.FactoriaComplica;
import tp.pr3.control.FactoriaConecta4;
import tp.pr3.control.FactoriaGravity;
import tp.pr3.control.FactoriaTipoJuego;
import tp.pr3.control.HiloControlAutomaticos;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasConecta4;
import tp.pr3.vista.Vista;
import tp.pr3.vista.VistaConsola;
import tp.pr3.vista.VistaGrafica;

/**
 * Clase Principal
 */
public class Main {
	private static final int COLUMNAS_GRAVITY = 10;
	private static final int FILAS_GRAVITY = 10;
	
	private static FactoriaTipoJuego factoriaJuegos;
	private static int numCols = COLUMNAS_GRAVITY;
	private static int numFilas = FILAS_GRAVITY;
	private static boolean vistaGrafica = false; 
	
	/**
	 * Metodo main
	 * @param args argumentos de l�nea de comandos
	 */
	public static void main(String args[]) {
		
		procesaParametros(args);
		Scanner in = new Scanner(System.in);
		Partida p;
		p = new Partida(factoriaJuegos.creaReglas());
		p.setIn(in);
		p.reset(factoriaJuegos.creaReglas());
		ControladorConsola c = new ControladorConsola(p);
		Vista v = null;
		if (vistaGrafica) {
			v = new VistaGrafica(c);
			p.addObserver((VistaGrafica)v);
			HiloControlAutomaticos hiloControl = new HiloControlAutomaticos(c);
			hiloControl.start();
			p.addObserver(hiloControl);
		} else {
			v = new VistaConsola(c, in);
		}
		v.run();
	}

	/**
	 * Metodo para procesar los parametros
	 * @param args argumentos de l�nea de comandos
	 */
	public static void procesaParametros(String args[]) {
		factoriaJuegos = new FactoriaConecta4();
		String noEntendidos = "";
		for (int i=0; i<args.length; i++) {
			if ((args[i].equals("-g"))||(args[i].equals("--game"))){
				i++;
				String juego = args[i];
				if (juego.equalsIgnoreCase("c4")){
					factoriaJuegos = new FactoriaConecta4();
				}else if (juego.equalsIgnoreCase("co")){
					factoriaJuegos = new FactoriaComplica();
				}else if (juego.equalsIgnoreCase("gr")){
					factoriaJuegos = new FactoriaGravity(COLUMNAS_GRAVITY, FILAS_GRAVITY);
				}else {
					mostrarErrorArgumentos("Uso incorrecto: Juego '" + juego + "' incorrecto.");
				}
			}else if ((args[i].equals("-h"))||(args[i].equals("--help"))){
				mostrarAyuda();
				
			}else if ((args[i].equals("-x"))||(args[i].equals("--tamX"))||(args[i].equals("-tamX"))){
				i++;
				try{
					numCols = Integer.parseInt(args[i]);
					((FactoriaGravity)factoriaJuegos).setNumCols(numCols);
				}catch (NumberFormatException e){
					mostrarErrorArgumentos("Uso incorrecto: " + args[i] + " no es un número entero.");
				}
			}else if ((args[i].equals("-y"))||(args[i].equals("--tamY"))||(args[i].equals("-tamY"))){
				i++;
				try{
					numFilas = Integer.parseInt(args[i]);
					((FactoriaGravity)factoriaJuegos).setNumFilas(numFilas);
				}catch (NumberFormatException e){
					mostrarErrorArgumentos("Uso incorrecto: " + args[i] + " no es un número entero.");
				}
			}else if ((args[i].equals("-u"))||(args[i].equals("--ui"))){
				i++;
				if (args[i].equals("window")){
					vistaGrafica = true;
				} else if (args[i].equals("console")) {
					vistaGrafica = false;
				} else {
					mostrarErrorArgumentos("Uso incorrecto: " + args[i] + " no es una vista correcta.");
				}
			}else{
				if (args[i].startsWith("-")) {
					mostrarErrorArgumentos("Uso incorrecto: Unrecognized option: " + args[i]);
				}else{
					noEntendidos += args[i] + " ";
				}
			}
		}
		if (!noEntendidos.equals("")) {
			mostrarErrorArgumentos("Uso incorrecto: Argumentos no entendidos: " + noEntendidos);
		}
		
	}
	
	/**
	 * Muestra la ayuda
	 */
	public static void mostrarAyuda() {
		System.out.println("usage: tp.pr3.Main [-g <game>] [-h] [-x <columnNumber>] [-y <rowNumber>]");
		System.out.println(" -g,--game <game>           Tipo de juego (c4, co, gr). Por defecto, c4.");
		System.out.println(" -h,--help                  Muestra esta ayuda.");
		System.out.println(" -x,--tamX <columnNumber>   Número de columnas del tablero (sólo para");
		System.out.println("                            Gravity). Por defecto, 10.");
		System.out.println(" -y,--tamY <rowNumber>      Número de filas del tablero (sólo para");
		System.out.println("                            Gravity). Por defecto, 10.");
		System.exit(0);
	}
	
	/**
	 * Muestra el mensaje de error
	 * @param msg mensaje de error
	 */
	public static void mostrarErrorArgumentos(String msg) {
		System.err.println(msg);
		System.err.println("Use -h|--help para más detalles.");
		System.exit(1);
	}
}
