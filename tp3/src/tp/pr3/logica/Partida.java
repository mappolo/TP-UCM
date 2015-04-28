package tp.pr3.logica;

import java.util.Scanner;

import tp.pr3.control.Jugador;

public class Partida {
	private Tablero tablero;
	private Juego juego;
	private ReglasJuego reglas;
	private Ficha turno;
	private Movimiento ultimoMovimiento;
	private UndoStack undoStack;
	private Jugador jugadorAutomatico; 
	private Jugador jugadorHumano;
	private boolean[] jugadoresAutomaticos;
	private Scanner in;
	
	/**
	 * Construye una partida nueva
	 * @param reglas de la partida
	 */
	public Partida(ReglasJuego reglas) {
		ultimoMovimiento = null;
		undoStack = new UndoStack();
		jugadoresAutomaticos = new boolean[2];
		jugadoresAutomaticos[0] = false;
		jugadoresAutomaticos[1] = false;
		reset(reglas);
	}
	
	/**
	 * Devuelve las reglas del juego en ejecucion
	 * @return reglas de la partida
	 */
	public ReglasJuego getReglas(){
		return reglas;
	}
	
	/**
	 * Metodo de acceso al tablero, devuelve el estado del tablero actual
	 * @return tablero de la partida
	 */
	public Tablero getTablero() {
		return tablero;
	}

	/**
	 * Devuelve el color del jugador al que le toca poner
	 * @return turno del jugador
	 */
	public Ficha getTurno() {
		return turno;
	}

	/**
	 * Devuelve el color del ganador
	 * @return color del ganador
	 */
	public Ficha getGanador() {
		if (reglas.tablas(turno, tablero))
			return Ficha.VACIA;
		else return reglas.hayGanador(ultimoMovimiento, tablero);
	}

	/**
	 * Devuelve el tipo de juego
	 * @return tipo de juego
	 */
	public Juego getJuego() {
		return juego;
	}

	/**
	 * Establece el tipo de juego
	 * @param juego tipo de juego
	 */
	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	
	/**
	 * Metodo para saber si el jugador es o no automatico
	 * @param color del jugador
	 * @return si el jugador es o no automatico
	 */
	public boolean isAutomatico(Ficha color){
		return jugadoresAutomaticos[color.ordinal()];
	}
	
	/**
	 * Establece si el jugador es automatico o no
	 * @param color del jugador
	 * @param automatico 
	 */
	public void setAutomatico(Ficha color, boolean automatico){
		jugadoresAutomaticos[color.ordinal()] = automatico;
	}
	
	/**
	 * Metodo de acceso al escaner
	 * @return escaner
	 */
	public Scanner getIn() {
		return in;
	}

	/**
	 * Establece el escaner
	 * @param in escaner
	 */
	public void setIn(Scanner in) {
		this.in = in;
	}

	/**
	 * Devuelve el jugador humano
	 * @return tipo de jugador humano
	 */
	public Jugador getJugadorHumano() {
		return jugadorHumano;
	}
	
	/**
	 * Metodo para saber si la partida ha conluido ya o no
	 * @return si la partida ha terminado
	 */
	public boolean isTerminada() {
		return reglas.hayGanador(ultimoMovimiento, tablero) != Ficha.VACIA ||
				reglas.tablas(turno, tablero);
	}

	/**
	 * Reinicia la partida en curso
	 * @param reglas del juego
	 */
	public void reset(ReglasJuego reglas) {
		this.reglas = reglas;
		juego = reglas.getJuego();
		tablero = reglas.iniciaTablero();
		turno = reglas.jugadorInicial();
		tablero.reset();
		undoStack.reset();
		jugadorAutomatico = reglas.jugadorAleatorio(); 
		jugadorHumano = reglas.jugadorHumano(in);
		turno = Ficha.BLANCA;		
	}
	
	/**
	 * Deshace el ultimo movimiento ejecutado
	 * @return si se ha deshecho el movimiento
	 */
	public boolean undo() {
		boolean res = undoStack.undo();
		if (res) {
			turno = reglas.siguienteTurno(turno, tablero);
		}
		return res;
	}
	
	/**
	 * Ejecuta el movimiento indicado
	 * @param mov es el movimiento a ejecutar
	 * @return si la ejecución fue correcta
	 * @throws MovimientoInvalido excepcion a capturar
	 */
	public boolean ejecutaMovimiento(Movimiento mov) throws MovimientoInvalido{
		if (isTerminada()) {
			throw (new MovimientoInvalido("Partida finalizada."));
		}
		
		if (mov.getJugador()!=turno) {
			throw (new MovimientoInvalido("Turno incorrecto."));
		}
		
		boolean res = mov.ejecutaMovimiento(tablero);
		
		if (res) {
			turno = reglas.siguienteTurno(turno, tablero);
			undoStack.agnadirUndo(mov, tablero);
		}
		return res;
	}
	
	/**
	 * Ejecuta un movimiento aleatorio
	 * @return si se ha ejecutado correctamente
	 */
	public boolean ejecutaMovimientoAutomatico() {
		Movimiento mov = jugadorAutomatico.getMovimiento(tablero, turno);
		try {
			ejecutaMovimiento(mov);
		} catch (Exception e) {// Nunca debe fallar
			
		}
		return true;
	}
}
