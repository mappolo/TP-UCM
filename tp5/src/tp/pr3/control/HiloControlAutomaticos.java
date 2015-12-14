package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Observer;
import tp.pr3.logica.Tablero;

public class HiloControlAutomaticos extends Thread implements Observer {

	private ControladorConsola controlador;
	
	public HiloControlAutomaticos(ControladorConsola c) {
		controlador = c;
	}
	
	@Override
	public void onReset(Tablero estadoInicial, Ficha turno) {
		esTurnoAutomatico();
	}

	@Override
	public void onPartidaTerminada(Tablero tableroFinal, Ficha ganador) {
	}

	@Override
	public void onMovimientoEnd(Tablero estadoTablero, Ficha turno,
			Ficha siguiente) {
		esTurnoAutomatico();
	}

	@Override
	public void onMovimientoIncorrecto(String explicacion) {
	}

	@Override
	public void onUndo(Tablero estadoTablero, Ficha turno, boolean hayMas) {
		esTurnoAutomatico();
	}

	@Override
	public void onUndoNotPossible() {
	}

	private void esTurnoAutomatico() {
		if (controlador.isTurnoAutomatico())
			controlador.ejecutaMovimientoAletorio();
	}
}
