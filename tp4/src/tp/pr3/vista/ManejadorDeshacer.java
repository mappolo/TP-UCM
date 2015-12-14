package tp.pr3.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp.pr3.control.ControladorConsola;

public class ManejadorDeshacer implements ActionListener {

	ControladorConsola controlador;
	VistaGrafica vista;
	
	/**
	 * Constructora
	 * @param c Controlador de consola
	 * @param v Vista grafica
	 */
	public ManejadorDeshacer(ControladorConsola c, VistaGrafica v) {
		controlador = c;
		vista = v;
	}
	
	/**
	 * Deshace el movimineto
	 */
	public void actionPerformed(ActionEvent e) {
		controlador.undo();
		vista.refresh();
	}

}
