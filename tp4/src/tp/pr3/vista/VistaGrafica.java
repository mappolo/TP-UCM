package tp.pr3.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import tp.pr3.control.ControladorConsola;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.Partida;

/**
 * Vista grafica de la partida
 */
public class VistaGrafica extends JFrame implements Vista{
	
	private static final int HEIGHT = 600;
	private static final int WIDTH = 700;
	private static final String[] JUEGOS = {"Conecta 4", "Complica", "Gravity"};
	static final int FILAS_GRAVITY = 10;
	static final int COLUMNAS_GRAVITY = 10;
	private ControladorConsola controlador;
	private PanelTablero tablero;
	private JLabel lblJugador;
	private JButton botonAleatorio;
	private JButton botonSalir;
	private JButton botonDeshacer;
	private JButton botonReiniciar;
	private JButton botonCambiar;
	private JComboBox<String> comboJuego;
	private JLabel lblFilas;
	private JTextField textoFilas;
	private JLabel lblColumnas;
	private JTextField textoColumnas;
	private ManejadorCasilla manejador;
	
	/**
	 * Constructora
	 * @param p partida a ejecutar
	 */
	public VistaGrafica(Partida p) {
		super();
		controlador = new ControladorConsola(p);
		tablero = new PanelTablero(controlador.getAncho(), controlador.getAlto());
		manejador = new ManejadorCasilla(controlador, this);
		createGUI();
	}
	
	/**
	 * Crea la interfaz grafica
	 */
	public void createGUI() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		FlowLayout thisLayout = new FlowLayout();
		getContentPane().setLayout(thisLayout);
		this.setPreferredSize(new java.awt.Dimension(WIDTH,HEIGHT));
		this.setTitle("Practica 4 - TP");
		setBounds(10, 10, WIDTH, HEIGHT);
		
		// Panel principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		
		// Panel inferior
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new GridLayout(2, 1));
		
		lblJugador = new JLabel();
		panelInferior.add(lblJugador);
		
		JPanel panelInferior1 = new JPanel();
		botonAleatorio= new JButton("Poner Aleatorio");
		botonAleatorio.setIcon(new ImageIcon("iconos/random.png"));
		botonAleatorio.addActionListener(new ManejadorAleatorio(controlador, this));
		panelInferior1.add(botonAleatorio);
		
		botonSalir = new JButton("Salir");
		botonSalir.setIcon(new ImageIcon("iconos/exit.png"));
		botonSalir.addActionListener(new ManejadorSalir(controlador));
		panelInferior1.add(botonSalir);
		
		panelInferior.add(panelInferior1);
		
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
		
		// Panel central
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1,2));
		panelCentral.add(tablero);
		tablero.setManejadorCasillas(manejador);
		
		// Panel central derecho
		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new GridLayout(4,1));
		JPanel panelDerecho1 = new JPanel();
		panelDerecho1.setLayout(new GridLayout(1,2));
		botonDeshacer = new JButton("Deshacer");
		botonDeshacer.setIcon(new ImageIcon("iconos/undo.png"));
		botonDeshacer.addActionListener(new ManejadorDeshacer(controlador, this));
		panelDerecho1.add(botonDeshacer);
		botonReiniciar = new JButton("Reiniciar");
		botonReiniciar.setIcon(new ImageIcon("iconos/reiniciar.png"));
		botonReiniciar.addActionListener(new ManejadorReiniciar(controlador, this));
		panelDerecho1.add(botonReiniciar);
		panelDerecho.add(panelDerecho1);
		
		comboJuego = new JComboBox<String>(JUEGOS);
		
		panelDerecho.add(comboJuego);
		
		JPanel panelDerecho2 = new JPanel();
		panelDerecho2.setLayout(new GridLayout(1,4));
		lblFilas = new JLabel("Filas");
		panelDerecho2.add(lblFilas);
		textoFilas = new JTextField();
		panelDerecho2.add(textoFilas);
		lblColumnas = new JLabel("Columnas");
		panelDerecho2.add(lblColumnas);
		textoColumnas= new JTextField();
		panelDerecho2.add(textoColumnas);
		panelDerecho.add(panelDerecho2);
		
		comboJuego.addActionListener(new ManejadorJuego(lblFilas, textoFilas, 
				lblColumnas, textoColumnas));
		botonCambiar = new JButton("Cambiar");
		botonCambiar.setIcon(new ImageIcon("iconos/aceptar.png"));
		botonCambiar.addActionListener(new ManejadorCambiar(controlador, this));
		panelDerecho.add(botonCambiar);
		
		panelCentral.add(panelDerecho);
		
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		
		getContentPane().add(panelPrincipal);
		pack();
	}
	
	/**
	 * Devuelve el tipo de juego de la lista desplegable
	 * @return tipo de juego
	 */
	public String getJuego() {
		if (comboJuego != null) {
			return (String)comboJuego.getSelectedItem();
		}
		return "";
	}
	
	/**
	 * Devuelve el numero de filas del cajon de texto (solo en gravity)
	 * @return filas
	 */
	public int getFilas() {
		if (textoFilas != null) {
			try {
				return Integer.parseInt(textoFilas.getText());
			}catch (NumberFormatException e) {
			}
		}
		return 1;
	}
	
	/**
	 * Devuelve el numero de columnas del cajon de texto (solo en gravity)
	 * @return columnas
	 */
	public int getColumnas() {
		if (textoColumnas != null) {
			try {
				return Integer.parseInt(textoColumnas.getText());
			}catch (NumberFormatException e) {
			}
		}
		return 1;
	}
	
	/**
	 * Reinicia el tablero
	 */
	public void resetTablero() {
		tablero.reset(controlador.getAncho(), controlador.getAlto());
		tablero.setManejadorCasillas(manejador);
		boolean visible = lblFilas.isVisible();
		lblFilas.setVisible(!visible);
		lblFilas.setVisible(!visible);
		repaint();
	}
	
	/**
	 * Ejecuta el programa
	 */
	public void run() {
		refresh();
		setVisible(true);
		
		while (!controlador.getSalir()){
			
		}
		
		dispose();
		System.exit(0);
	}
	
	/**
	 * Refresca la interfaz
	 */
	public void refresh() {
		Color[][] tab = controlador.getTableroColoreado();
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length; j++) {
				tablero.pintaCasilla(j+1, i+1, tab[i][j]);
			}
		}
		textoFilas.setText(String.valueOf(FILAS_GRAVITY));
		textoColumnas.setText(String.valueOf(COLUMNAS_GRAVITY));
		String juego = (String)comboJuego.getSelectedItem();
		if (juego.equalsIgnoreCase("gravity")) {
			lblFilas.setVisible(true);
			textoFilas.setVisible(true);
			lblColumnas.setVisible(true);
			textoColumnas.setVisible(true);
		} else {
			lblFilas.setVisible(false);
			textoFilas.setVisible(false);
			lblColumnas.setVisible(false);
			textoColumnas.setVisible(false);
		}
		
		if (controlador.getTurno() == Ficha.BLANCA) {
			lblJugador.setText("Juegan Blancas");
		} else {
			lblJugador.setText("Juegan Negras");
		}
		
		if (controlador.isPartidaTerminada()) {
			tablero.setEnabled(false);
			botonAleatorio.setEnabled(false);
			botonDeshacer.setEnabled(false);
			botonCambiar.setEnabled(false);
			comboJuego.setEnabled(false);
			textoFilas.setEnabled(false);
			textoColumnas.setEnabled(false);
			String cadenaGanador = "Partida finalizada. Han ganado ";
			if (controlador.getTurno() == Ficha.BLANCA) {
				cadenaGanador += "Negras";
			} else {
				cadenaGanador += "Blancas";
			}
			JOptionPane.showMessageDialog(null, cadenaGanador, "Partida terminada", JOptionPane.INFORMATION_MESSAGE);
		} else {
			tablero.setEnabled(true);
			botonAleatorio.setEnabled(true);
			if (controlador.canUndo()) {
				botonDeshacer.setEnabled(true);
			} else {
				botonDeshacer.setEnabled(false);
			}
			botonCambiar.setEnabled(true);
			comboJuego.setEnabled(true);
			textoFilas.setEnabled(true);
			textoColumnas.setEnabled(true);
		}
	}
}
