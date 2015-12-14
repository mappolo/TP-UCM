package tp.pr3.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp.pr3.control.ControladorConsola;

public class ManejadorCasilla implements ActionListener {

	ControladorConsola controlador;
	VistaGrafica vista;
	
	/**
	 * Constructora
	 * @param c Controlador de consola
	 * @param v Vista grafica
	 */
	public ManejadorCasilla(ControladorConsola c, VistaGrafica v) {
		controlador = c;
		vista = v;
	}
	
	/**
	 * Ejecuta el movimiento en la casilla indicada
	 */
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();
		if (o instanceof BotonCasilla) {
			BotonCasilla boton = (BotonCasilla)o;
			controlador.ejecutaMovimiento(boton.getColumna(), boton.getFila());
			vista.refresh();
		}
	}
}
