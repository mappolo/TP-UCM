package tp.pr3.control;

import tp.pr3.logica.Movimiento;
import tp.pr3.logica.Partida;

public class ComandoPoner extends Comando {

	/**
	 * Ejecuta el comando para poner la siguiente ficha en el tablero
	 */
	public void ejecuta(Partida p, String[] args) throws Exception {
		if (p.isAutomatico(p.getTurno())){
			// Jugador automatico
			p.ejecutaMovimientoAutomatico();
		}
		else{
			Jugador jugador = p.getJugadorHumano();
			Movimiento mov = jugador.getMovimiento(p.getTablero(), p.getTurno());
			p.ejecutaMovimiento(mov);
		}
		
	}

}
