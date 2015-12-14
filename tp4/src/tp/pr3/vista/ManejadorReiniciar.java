package tp.pr3.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp.pr3.control.ControladorConsola;

public class ManejadorReiniciar implements ActionListener {

	ControladorConsola controlador;
	VistaGrafica vista;
	
	/**
	 * Constructora
	 * @param c Controlador de consola
	 * @param v Vista grafica
	 */
	public ManejadorReiniciar(ControladorConsola c, VistaGrafica v) {
		controlador = c;
		vista = v;
	}
	
	/**
	 * Reinicia el juego
	 */
	public void actionPerformed(ActionEvent e) {
		controlador.reset();
		vista.refresh();
	}

}
