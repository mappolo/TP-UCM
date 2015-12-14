package tp.pr3.logica;

public interface Observer {
	// Gestión de la partida
	void onReset(Tablero estadoInicial, Ficha turno);
	void onPartidaTerminada(Tablero tableroFinal, Ficha ganador);
	
	// Movimientos
	void onMovimientoEnd(Tablero estadoTablero, Ficha turno, Ficha siguiente);
	void onMovimientoIncorrecto(String explicacion);
	
	// Deshacer
	void onUndo(Tablero estadoTablero, Ficha turno, boolean hayMas);
	void onUndoNotPossible();
}
