package tp.pr3.vista;

import javax.swing.JButton;

public class BotonCasilla extends JButton {
	private int fila;
	private int columna;
	
	/**
	 * Constructora
	 * @param f fila
	 * @param c columna
	 */
	public BotonCasilla(int f, int c) {
		super();
		setSize(20, 20);
		fila = f;
		columna = c;
	}

	/**
	 * Metodo para obtener la fila
	 * @return fila
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * Metodo para obtener la columna
	 * @return columna
	 */
	public int getColumna() {
		return columna;
	}
}
