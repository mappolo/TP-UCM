package tp.pr3.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ManejadorJuego implements ActionListener {

	private JLabel lblFilas;
	private JTextField txtFilas;
	private JLabel lblColumnas;
	private JTextField txtColumnas;
	
	/**
	 * Constructora
	 * @param lf
	 * @param tf
	 * @param lc
	 * @param tc
	 */
	public ManejadorJuego(JLabel lf, JTextField tf, JLabel lc, JTextField tc) {
		lblFilas = lf;
		txtFilas = tf;
		lblColumnas = lc;
		txtColumnas = tc;
	}

	/**
	 * Cambia el tipo de juego en la lista desplegable y si es gravity, muestra fila y columnas a establecer en el tablero
	 */
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();
		if (o instanceof JComboBox) {
			JComboBox combo = (JComboBox)o;
			String juego = (String)combo.getSelectedItem();
			if (juego.equalsIgnoreCase("gravity")) {
				lblFilas.setVisible(true);
				txtFilas.setVisible(true);
				lblColumnas.setVisible(true);
				txtColumnas.setVisible(true);
			} else {
				lblFilas.setVisible(false);
				txtFilas.setVisible(false);
				lblColumnas.setVisible(false);
				txtColumnas.setVisible(false);
			}
		}
	}

}
