package tp.pr2.logica;

public class Partida {
	private Tablero tablero;
	ReglasJuego reglas;
	Ficha turno;
	Movimiento ultimoMovimiento;
	UndoStack undoStack;
	
	public Partida(ReglasJuego reglas) { //Construye una partida nueva
		tablero = reglas.iniciaTablero();
		turno = reglas.jugadorInicial();
		ultimoMovimiento = null;
		undoStack = new UndoStack();
		reset(reglas);
	}
	
	public ReglasJuego getReglas(){ //Devuelve las reglas del juego en ejecucion
		return reglas;
	}
	
	public Tablero getTablero() { //Método de acceso al tablero, devuelve el estado del tablero actual
		return tablero;
	}

	public Ficha getTurno() { //Devuelve el color del jugador al que le toca poner
		return turno;
	}

	public Ficha getGanador() { //Devuelve el color del ganador
		if (reglas.tablas(turno, tablero))
			return Ficha.VACIA;
		else return reglas.hayGanador(ultimoMovimiento, tablero);
	}

	public boolean isTerminada() { //Método para saber si la partida ha conluído ya o no
		return reglas.hayGanador(ultimoMovimiento, tablero) != Ficha.VACIA ||
				reglas.tablas(turno, tablero);
	}

	public void reset(ReglasJuego reglas) { //Reinicia la partida en curso
		this.reglas = reglas;
		tablero.reset();
		undoStack.reset();
		turno = Ficha.BLANCA;
	}
	
	public boolean undo() { //Deshace el último movimiento ejecutado
		boolean res = undoStack.undo();
		if (res) {
			turno = reglas.siguienteTurno(turno, tablero);
		}
		return res;
	}
	
	public boolean ejecutaMovimiento(Movimiento mov) { //Ejecuta el movimiento indicado
		if (isTerminada()) {
			return false;
		}
		
		if (mov.getJugador()!=turno) {
			return false;
		}
		
		boolean res = mov.ejecutaMovimiento(tablero);
		
		if (res) {
			turno = reglas.siguienteTurno(turno, tablero);
			undoStack.agnadirUndo(mov, tablero);
		}
		return res;
	}
}
