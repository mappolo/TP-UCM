package tp.pr2.logica;

public class MovimientoComplica extends Movimiento {
	private int columna;
	private Ficha desaparece;
	
	public MovimientoComplica(int donde, Ficha color) { //Constructor del objeto
		super(color);
		columna = donde;
		desaparece = Ficha.VACIA;
	}
	
	public Ficha getDesaparece() { //Devuelve desaparece
		return desaparece;
	}

	public boolean ejecutaMovimiento(Tablero tab) { //Ejecuta el movimiento sobre el tablero que se recibe como parámetro
		if ((columna<1)||(columna>tab.getAncho())) {
			return false;
		}
		
		int i=getUltimaCasillaOcupada(tab, columna);
		
		if (i==1) { // Columna llena. Eliminamos la ficha de abajo
			desaparece = tab.getCasilla(columna, tab.getAlto());
			for (int j=tab.getAlto(); j>1; j--){
				Ficha arriba = tab.getCasilla(columna, j-1);
				tab.setCasilla(columna, j, arriba);
			}
			// Colocamos la nueva ficha en la primera fila
			i = 2;
		}
		tab.setCasilla(columna, i-1, getJugador());
		
		return true;
	}

	public void undo(Tablero tab) { //Deshace el movimiento en el tablero recibido como parámetro
		quitaFicha(tab, columna);
		retornaFicha(tab);
	}

	public void retornaFicha(Tablero tab) { //Si desaparece no es vacia, es decir, la columna esta llena, eleva las fichas y coloca la ficha eliminada abajo
		if (desaparece != Ficha.VACIA) {
			for (int i=1; i<=tab.getAlto(); i++){
				Ficha abajo = tab.getCasilla(columna, i+1);
				tab.setCasilla(columna, i, abajo);
			}
			tab.setCasilla(columna, tab.getAlto(), desaparece);
		}
		
	}
}
