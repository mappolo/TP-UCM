package tp.pr2.logica;

public class MovimientoConecta4 extends Movimiento {

	private int columna;
	
	public MovimientoConecta4(int donde, Ficha color) { //Constructor del objeto
		super(color);
		columna = donde;
	}
	
	public boolean ejecutaMovimiento(Tablero tab) { //Ejecuta el movimiento sobre el tablero que se recibe como parámetro
		if ((columna<1) || (columna>tab.getAncho())) {
			return false;
		}
		
		int i=getUltimaCasillaOcupada(tab, columna);
		
		if (i == 1)
		{
			return false;
		}
		
		tab.setCasilla(columna, i-1, getJugador());
		
		return true;
	}

	public void undo(Tablero tab) { //Deshace el movimiento en el tablero recibido como parámetro
		quitaFicha(tab, columna);
	}

	public void retornaFicha(Tablero tab) {
		// No hay nada que hacer		
	}
}
