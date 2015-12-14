package tp.pr3.logica;

import java.util.Vector;

public class MovimientoReversi extends Movimiento {

	private int columna;
	private int fila;
	private Vector<Integer> volteadas;

	/**
	 * Constructor del objeto
	 * @param columna del tablero
	 * @param fila del tablero
	 * @param color del jugador
	 */
	public MovimientoReversi(int columna, int fila, Ficha color) {
		super(color);
		this.columna = columna;
		this.fila = fila;
		volteadas = new Vector<Integer>();
	}
	
	@Override
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		int cambiados = volteaFichas(tab, true);		
		
		if (cambiados == 0) {
			// No se da vuelta a ninguna ficha contraria. El movimiento es invalido
			throw new MovimientoInvalido("No se puede voltear ninguna ficha");
		}
		tab.setCasilla(columna, fila, getJugador());
		return true;
	}

	/**
	 * Deshace el movimiento en el tablero recibido como parametro
	 */
	public void undo(Tablero tab) {
		quitaFicha(tab);
		
		// Volteamos de nuevo las fichas que se voltearon en el movimiento
		Ficha otro = Ficha.VACIA;
		if (getJugador() == Ficha.BLANCA) {
			otro = Ficha.NEGRA;
		} else {
			otro = Ficha.BLANCA;
		}
		
		for (int i=0; i<volteadas.size(); i+=2) {
			tab.setCasilla(volteadas.get(i), volteadas.get(i+1), otro);
		}
	}

	/**
	 * Nada que hacer en este juego
	 */
	public void retornaFicha(Tablero tab) {
		// No hay nada que hacer
	}

	/**
	 * Quita la ficha indicada del tablero
	 * @param tab tablero del juego
	 */
	protected void quitaFicha(Tablero tab) {
		tab.setCasilla(columna, fila, Ficha.VACIA);
	}
	
	public int volteaFichas(Tablero tab, boolean voltea){
		int res = 0;
		
		// Comprobamos en las 8 direcciones posibles, a ver si se puede dar la vuelta
		// a alguna ficha
		for (int i=-1; i<=1; i++) {
			for (int j=-1; j<=1; j++) {
				if ( (i!=0) || (j!=0))
					res += volteaFichas(tab, i, j, voltea);
			}
		}
		
		return res;
	}
	
	private int volteaFichas(Tablero tab, int despHor, int despVert, boolean voltea) {
		int res = 0;
		Ficha otro = Ficha.VACIA;
		
		if (getJugador() == Ficha.BLANCA) {
			otro = Ficha.NEGRA;
		} else {
			otro = Ficha.BLANCA;
		}
		
		if ((tab.getCasilla(columna, fila).equals(Ficha.VACIA)) &&
			(tab.getCasilla(columna+despHor, fila+despVert).equals(otro))) {
			int filaDest = fila+despVert;
			int colDest = columna+despHor;
			while (tab.getCasilla(colDest+despHor, filaDest+despVert).equals(otro)) {
				filaDest += despVert;
				colDest += despHor;
			}
			
			if (tab.getCasilla(colDest+despHor, filaDest+despVert).equals(getJugador())) {
				while (tab.getCasilla(colDest, filaDest) == otro) {
					res++;
					if (voltea) {
						tab.setCasilla(colDest, filaDest, getJugador());
						// Guardamos las coordenadas de la ficha volteada por si hay 
						// que deshacer el movimiento
						volteadas.add(colDest);
						volteadas.add(filaDest);
					}
					filaDest -= despVert;
					colDest -= despHor;
				}
			}
		}
		return res;
	}
}
