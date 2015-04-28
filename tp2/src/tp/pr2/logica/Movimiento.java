package tp.pr2.logica;


public abstract class Movimiento { //Clase abstracta Movimiento
	private Ficha jugador;
	
	public Movimiento(Ficha j) { //Contructora de la clase movimineto
		jugador = j;
	}
	
	public Ficha getJugador() { //Devuelve el jugador
		return jugador;
	}
	
	public abstract boolean ejecutaMovimiento(Tablero tab); //Ejecuta el movimiento sobre el tablero que se recibe como parámetro
	public abstract void undo(Tablero tab); //Deshace el movimiento en el tablero recibido como parámetro
	public abstract void retornaFicha(Tablero tab); //Si desaparece no es vacia, es decir, la columna esta llena, eleva las fichas y coloca la ficha eliminada abajo
	
	protected int getUltimaCasillaOcupada(Tablero tab, int col) { //Devuelve la ultima casilla ocupada desde 1 a n
		for (int i=1; i<=tab.getAlto(); i++) {
			if (tab.getCasilla(col, i)!=Ficha.VACIA) {
				return i;
			}
		}
		// Ninguna casilla ocupada en la columna. Devolvemos un 
		// valor fuera de rango
		return tab.getAlto()+1;
	}
	
	protected void quitaFicha(Tablero tab, int col) { //Quita la ficha indicada del tablero
		int fila = getUltimaCasillaOcupada(tab, col);
		tab.setCasilla(col, fila, Ficha.VACIA);
	}
}
