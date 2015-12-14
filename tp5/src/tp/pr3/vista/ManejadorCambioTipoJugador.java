package tp.pr3.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import tp.pr3.control.ControladorConsola;

public class ManejadorCambioTipoJugador implements ActionListener {

	ControladorConsola controlador;
	VistaGrafica vista;
	
	/**
	 * Constructora
	 * @param c Controlador de consola
	 * @param v Vista grafica
	 */
	public ManejadorCambioTipoJugador(ControladorConsola c, VistaGrafica v) {
		controlador = c;
		vista = v;
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();
		String args[] = new String[2];
		
		if (o.equals(vista.getComboJugadorBlancas())) {
			args[0] = "blancas";
		} else {
			args[0] = "negras";
		}
		
		if (o instanceof JComboBox) {
			JComboBox<String> combo = (JComboBox<String>)o;
			if (combo.getSelectedItem().equals("Humano")) {
				args[1] = "humano";
			} else {
				args[1] = "aleatorio";
			}
		}
		
		try {
			controlador.ejecuta("JUGADOR", args);
			if (controlador.isTurnoAutomatico()) {
				controlador.ejecutaMovimientoAletorio();
			}
		} catch (Exception e) {
		}
	}

}
