package tp.pr3.control;

import java.awt.Color;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Juego;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.MovimientoGravity;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasComplica;
import tp.pr3.logica.ReglasConecta4;
import tp.pr3.logica.ReglasGravity;
import tp.pr3.logica.ReglasJuego;
import tp.pr3.logica.Tablero;

public class ControladorConsola {
	private Partida partida;
	private boolean salir;
	
	/**
	 * Constructora
	 * @param p partida
	 */
	public ControladorConsola(Partida p) {
		partida = p;
		salir = false;
	}
	
	/**
	 * Devuelve salir
	 * @return salir
	 */
	public boolean getSalir() {
		return salir;
	}

	/**
	 * Establece salir
	 * @param salir
	 */
	public void setSalir(boolean salir) {
		this.salir = salir;
	}

	/**
	 * Metodo para saber si la partida ha conluido ya o no
	 * @return si la partida ha terminado
	 */
	public boolean isPartidaTerminada() {
		return partida.isTerminada();
	}
	
	/**
	 * Metodo de acceso al tablero, devuelve el estado del tablero actual
	 * @return tablero de la partida
	 */
	public Tablero getTablero() {
		return partida.getTablero();
	}
	
	/**
	 * Metodo para obtener el alto del tablero
	 * @return alto del tablero
	 */
	public int getAlto() {
		return partida.getTablero().getAlto();
	}
	
	/**
	 * Metodo para obtener el ancho del tablero
	 * @return ancho del tablero
	 */
	public int getAncho() {
		return partida.getTablero().getAncho();
	}
	
	/**
	 * Devuelve el color del jugador al que le toca poner
	 * @return turno del jugador
	 */
	public Ficha getTurno() {
		return partida.getTurno();
	}
	
	/**
	 * Devuelve el color del ganador
	 * @return color del ganador
	 */
	public Ficha getGanador() {
		return partida.getGanador();
	}
	
	/**
	 * Ejecuta el movimiento indicado
	 * @param x columna del movimiento a ejecutar
	 * @param y fila del movimiento a ejecutar
	 */
	public void ejecutaMovimiento(int x, int y) {
		Juego juego = partida.getJuego();
		Ficha turno = partida.getTurno();
		Movimiento mov = null;
		
		switch (juego) {
		case CONECTA4:
			mov = new MovimientoConecta4(x, turno);
			break;
		case COMPLICA:
			mov = new MovimientoComplica(x, turno);
			break;
		case GRAVITY:
			mov = new MovimientoGravity(x, y, turno);
			break;
		default:
		}
		
		try {
			partida.ejecutaMovimiento(mov);
		} catch (MovimientoInvalido e) {
			
		}
	}
	
	/**
	 * Ejecuta un movimiento aleatorio
	 */
	public void ejecutaMovimientoAletorio() {
		partida.ejecutaMovimientoAutomatico();
	}
	
	/**
	 * 
	 * @param nombreComando nombre del comando introducido
	 * @param args argumentos de línea de comandos
	 * @throws Exception excepcion a capturar
	 */
	public void ejecuta(String nombreComando, String[] args) throws Exception {
		Comando comando = FactoriaComandos.parse(nombreComando);
		comando.ejecuta(partida, args);
	}

	/**
	 * Deshace el ultimo movimiento ejecutado
	 * @return si se ha deshecho el movimiento
	 */
	public boolean undo() {
		return partida.undo();
	}
	
	/**
	 * Reinicia la partida en curso
	 */
	public void reset() {
		ReglasJuego reglas = partida.getReglas();
		partida.reset(reglas);
	}

	/**
	 * Cambia el tipo de juego
	 * @param juego tipo de juego
	 * @param filas del tablero (gravity)
	 * @param columnas del tablero (gravity)
	 */
	public void cambiarJuego(String juego, int filas, int columnas) {
		ReglasJuego reglas = null;
		if (juego.equalsIgnoreCase("conecta 4")) {
			reglas = new ReglasConecta4();
		} else if (juego.equalsIgnoreCase("complica")) {
			reglas = new ReglasComplica();
		} else if (juego.equalsIgnoreCase("gravity")) {
			reglas = new ReglasGravity(columnas, filas);
		}
		
		partida.reset(reglas);
	}
	
	/**
	 * Devuelve el tablero coloreado
	 * @return tablero coloreado
	 */
	public Color[][] getTableroColoreado() {
		Tablero tablero = partida.getTablero();
		Color[][] colores = new Color[tablero.getAlto()][tablero.getAncho()];
		
		for (int i=0; i<colores.length; i++) {
			for (int j=0; j<colores[i].length; j++) {
				Ficha color = tablero.getCasilla(j+1, i+1);
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
	
	/**
	 * Informa si se puede deshacer el movimineto
	 * @return si se puede deshacer
	 */
	public boolean canUndo() {
		return partida.canUndo();
	}
}
