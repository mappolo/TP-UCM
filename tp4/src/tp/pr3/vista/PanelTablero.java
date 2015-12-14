package tp.pr3.vista;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;


public class PanelTablero extends JPanel {
	private BotonCasilla[][] tablero;
	
	/**
	 * Constructora
	 * @param tx
	 * @param ty
	 */
	public PanelTablero(int tx, int ty) {
		super();
		reset(tx, ty);
	}
	
	/**
	 * Resetea el tablero
	 * @param tx columnas
	 * @param ty filas
	 */
	public void reset(int tx, int ty) {
		this.removeAll();
		if ((tx<=0)||(ty<=0)){
			tx = 1;
			ty = 1;
		}
		tablero = new BotonCasilla[ty][tx];
		setLayout(new GridLayout(ty, tx));
		
		for (int i=0; i<ty; i++) {
			for (int j=0; j<tx; j++) {
				tablero[i][j] = new BotonCasilla(i+1, j+1);
				add(tablero[i][j]);
			}
		}
	}
	
	/**
	 * Pinta la casilla con el color de la ficha
	 * @param x columna
	 * @param y fila
	 * @param color de la ficha
	 */
	public void pintaCasilla(int x, int y, Color color) {
		tablero[y-1][x-1].setBackground(color);
	}
	
	/**
	 * Establece el manejador en las casillas
	 * @param manejador
	 */
	public void setManejadorCasillas(ManejadorCasilla manejador) {
		for (int i=0; i<tablero.length; i++) {
			for (int j=0; j<tablero[i].length; j++) {
				tablero[i][j].addActionListener(manejador);
			}
		}
	}
	
	/**
	 * Activa o desactiva el boton de la casilla
	 */
	public void setEnabled(boolean enabled) {
		for (int i=0; i<tablero.length; i++) {
			for (int j=0; j<tablero[i].length; j++) {
				tablero[i][j].setEnabled(enabled);
			}
		}
	}
}
