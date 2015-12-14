package tp.pr3.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp.pr3.control.ControladorConsola;

public class ManejadorCambiar implements ActionListener {

	ControladorConsola controlador;
	VistaGrafica vista;
	
	/**
	 * Constructora
	 * @param c Controlador de consola
	 * @param v Vista grafica
	 */
	public ManejadorCambiar(ControladorConsola c, VistaGrafica v) {
		controlador = c;
		vista = v;
	}
	
	/**
	 * Cambia el juego
	 */
	public void actionPerformed(ActionEvent arg0) {
		controlador.cambiarJuego(vista.getJuego(), vista.getFilas(), vista.getColumnas());
		vista.resetTablero();
		vista.refresh();
	}

}
