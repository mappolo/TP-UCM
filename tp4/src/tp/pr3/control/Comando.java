package tp.pr3.control;

import tp.pr3.logica.Partida;

/**
 * Clase abstracta comando
 */
public abstract class Comando {
	
	/**
	 * Ejecuta el comando
	 * @param p partida
	 * @param args argumentos de línea de comandos
	 * @throws Exception excepcion a capturar
	 */
	public abstract void ejecuta(Partida p, String[] args) throws Exception;
}
