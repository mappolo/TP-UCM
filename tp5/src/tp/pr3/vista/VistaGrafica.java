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
import tp.pr3.logica.Observer;
import tp.pr3.logica.Tablero;

/**
 * Vista grafica de la partida
 */
public class VistaGrafica extends JFrame implements Vista, Observer{
	
	private static final int HEIGHT = 600;
	private static final int WIDTH = 700;
	private static final String[] JUEGOS = {"Conecta 4", "Complica", "Gravity", "Reversi"};
	private static final String[] TIPOS_JUGADOR = {"Humano", "Automatico"};
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
	private JComboBox<String> comboJugadorBlancas;
	private JComboBox<String> comboJugadorNegras;
	private JComboBox<String> comboJuego;
	private JLabel lblFilas;
	private JTextField textoFilas;
	private JLabel lblColumnas;
	private JTextField textoColumnas;
	private ManejadorCasilla manejador;
	
	/**
	 * Constructora
	 * @param c controlador
	 */
	public VistaGrafica(ControladorConsola c) {
		super();
		controlador = c;
		tablero = new PanelTablero(controlador.getAncho(), controlador.getAlto());
		manejador = new ManejadorCasilla(controlador, this);
		createGUI();
		controlador.reset();
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
		panelDerecho.setLayout(new GridLayout(5,1));
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
		
		JPanel panelJugadores = new JPanel();
		panelJugadores.setLayout(new GridLayout(2,2));
		JLabel lblJugBlancas = new JLabel("Jugador Blancas");
		comboJugadorBlancas  = new JComboBox<String>(TIPOS_JUGADOR);
		JLabel lblJugNegras = new JLabel("Jugador Negras");
		comboJugadorNegras  = new JComboBox<String>(TIPOS_JUGADOR);
		panelJugadores.add(lblJugBlancas);
		panelJugadores.add(comboJugadorBlancas);
		panelJugadores.add(lblJugNegras);
		panelJugadores.add(comboJugadorNegras);
		panelDerecho.add(panelJugadores);
		
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
		
		comboJugadorBlancas.addActionListener(
				new ManejadorCambioTipoJugador(controlador, this));
		comboJugadorNegras.addActionListener(
				new ManejadorCambioTipoJugador(controlador, this));
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
	
	public JComboBox<String> getComboJugadorBlancas() {
		return comboJugadorBlancas;
	}
	
	/**
	 * Reinicia el tablero
	 */
	public void resetTablero(Tablero t) {
		tablero.reset(t.getAncho(), t.getAlto());
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
		//refresh();
		setVisible(true);
	}
	
	/**
	 * Refresca la interfaz
	 */
	public void refresh(Tablero t, Ficha turno) {
		Color[][] tab = getTableroColoreado(t);
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
		
		if (turno == Ficha.BLANCA) {
			lblJugador.setText("Juegan Blancas");
		} else {
			lblJugador.setText("Juegan Negras");
		}
	
		tablero.setEnabled(true);
		botonAleatorio.setEnabled(true);
		botonDeshacer.setEnabled(true);
		botonCambiar.setEnabled(true);
		comboJuego.setEnabled(true);
		textoFilas.setEnabled(true);
		textoColumnas.setEnabled(true);
	}

	/**
	 * Devuelve el tablero coloreado
	 * @return tablero coloreado
	 */
	public Color[][] getTableroColoreado(Tablero t) {
		Color[][] colores = new Color[t.getAlto()][t.getAncho()];
		
		for (int i=0; i<colores.length; i++) {
			for (int j=0; j<colores[i].length; j++) {
				Ficha color = t.getCasilla(j+1, i+1);
				switch (color) {
				case VACIA:
					colores[i][j] = Color.LIGHT_GRAY;
					break;
				case BLANCA:
					colores[i][j] = Color.WHITE;
					break;
				case NEGRA:
					colores[i][j] = Color.BLACK;
					break;
				}
			}
		}
		
		return colores;
	}
	
	public void onReset(Tablero estadoInicial, Ficha turno) {
		resetTablero(estadoInicial);
		refresh(estadoInicial, turno);		
	}

	public void onPartidaTerminada(Tablero tableroFinal, Ficha ganador) {
		tablero.setEnabled(false);
		botonAleatorio.setEnabled(false);
		botonDeshacer.setEnabled(false);
		botonCambiar.setEnabled(false);
		comboJuego.setEnabled(false);
		textoFilas.setEnabled(false);
		textoColumnas.setEnabled(false);
		String cadenaGanador = "Partida finalizada.";
		if (ganador == Ficha.VACIA){
			cadenaGanador += " Tablas";
		}
		if (ganador == Ficha.BLANCA) {
			cadenaGanador += " Han ganado Blancas";
		} else {
			cadenaGanador += " Han ganado Negras";
		}
		JOptionPane.showMessageDialog(null, cadenaGanador, "Partida terminada", JOptionPane.INFORMATION_MESSAGE);		
	}

	public void onMovimientoEnd(Tablero estadoTablero, Ficha turno,
			Ficha siguiente) {
		refresh(estadoTablero, turno);		
	}

	public void onMovimientoIncorrecto(String explicacion) {
		JOptionPane.showMessageDialog(null, explicacion, "Movimiento incorrecto", JOptionPane.INFORMATION_MESSAGE);
	}

	public void onUndo(Tablero estadoTablero, Ficha turno, boolean hayMas) {
		refresh(estadoTablero, turno);
		if (!hayMas) {
			onUndoNotPossible();
		}		
	}

	public void onUndoNotPossible() {
		botonDeshacer.setEnabled(false);
	}
}
