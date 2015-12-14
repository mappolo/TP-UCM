package tp.pr3.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp.pr3.control.ControladorConsola;

public class ManejadorSalir implements ActionListener {

	ControladorConsola controlador;
	/**
	 * Constructora
	 * @param c Controlador de consola
	 */
	public ManejadorSalir(ControladorConsola c) {
		controlador = c;
	}
	
	/**
	 * Sale del juego
	 */
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

}
