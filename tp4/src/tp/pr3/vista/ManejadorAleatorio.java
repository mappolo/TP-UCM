package tp.pr3.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp.pr3.control.ControladorConsola;

public class ManejadorAleatorio implements ActionListener {

	ControladorConsola controlador;
	VistaGrafica vista;
	
	/**
	 * Constructora
	 * @param c Controlador de consola
	 * @param v Vista grafica
	 */
	public ManejadorAleatorio(ControladorConsola c, VistaGrafica v) {
		controlador = c;
		vista = v;
	}
	
	/**
	 * Ejecuta el movimiento aleatorio
	 */
	public void actionPerformed(ActionEvent arg0) {
		controlador.ejecutaMovimientoAletorio();
		vista.refresh();
	}

}
