package tp.pr3.logica;

/**
 * Clase de la excepcion de movimineto invalido
 */
public class MovimientoInvalido extends Exception {
	
	/**
	 * Muestra un mensaje con la excepcion
	 * @param msg mensaje a mostrar
	 */
	public MovimientoInvalido(String msg) {
		super(msg);
	}
}
