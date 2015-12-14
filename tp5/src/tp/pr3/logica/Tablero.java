package tp.pr3.logica;
import java.lang.StringBuilder;

public class Tablero {
	private Ficha[][] tablero;
	private int ancho;
	private int alto;
	
	/**
	 * Construye un tablero vacio
	 * @param tx ancho del tablero
	 * @param ty alto del tablero
	 */
	public Tablero(int tx, int ty) {
		if ((tx<=0)||(ty<=0)){
			tx = 1;
			ty = 1;
		}
		this.ancho = tx;
		this.alto = ty;
		tablero = new Ficha[ty][tx];
		reset();
	}
	
	/**
	 * Metodo para obtener el ancho del tablero
	 * @return ancho del tablero
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Permite cambiar el ancho del tablero
	 * @param ancho del tablero
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * Metodo para obtener el alto del tablero
	 * @return alto del tablero
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Permite cambiar el alto del tablero
	 * @param alto del tablero
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * Metodo para acceder a la informacion de una casilla o celda concreta
	 * @param x ancho
	 * @param y tablero
	 * @return casilla del tablero
	 */
	public Ficha getCasilla(int x, int y) {
		if ((x<1)||(x>ancho)) {
			return Ficha.VACIA;
		}
		if ((y<1)||(y>alto)) {
			return Ficha.VACIA;
		}
		return tablero[y-1][x-1];
	}
	
	/**
	 * Permite especificar el nuevo contenido de una casilla
	 * @param x ancho
	 * @param y alto
	 * @param color del jugador
	 */
	public void setCasilla(int x, int y, Ficha color) {
		if ((x>=1)&&(x<=ancho)&&(y>=1)&&(y<=alto)) {
			tablero[y-1][x-1] = color;
		}
	}
	
	/**
	 * Reinicia el tablero
	 */
	public void reset() {
		for (int i=0; i<alto; i++) {
			for (int j=0; j<ancho; j++) {
				tablero[i][j] = Ficha.VACIA;
			}
		}
	}
	
	/**
	 * Metodo toString
	 */
	public String toString() {
		StringBuilder Cadb = new StringBuilder();
		for (int i = 0; i < this.alto; i++){
			Cadb.append ("|");
			for (int j = 0; j < this.ancho; j++){
				Cadb.append(tablero[i][j]);
			}
			Cadb.append ("|");
			Cadb.append ("\n");
		}
		Cadb.append ("+");
		for (int i = 1; i <= this.ancho; i++){
			Cadb.append ("-");
		}
		Cadb.append ("+");
		Cadb.append ("\n");
		Cadb.append (" ");
		for (int i = 1; i <= this.ancho; i++){
			Cadb.append (i % 10);
		}
		Cadb.append ("\n");
		
		return Cadb.toString();
	}
	
	/**
	 * Comprueba si el tablero esta lleno
	 * @return si el tablero esta lleno
	 */
	public boolean isLleno() {
		for (int i=0; i<ancho; i++) {
			if (! isColumnaLlena(i)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Comprueba si una columna esta llena
	 * @param col columna a mirar
	 * @return si la columna esta llena
	 */
	public boolean isColumnaLlena(int col) {
		for (int i=0; i<alto; i++) {
			if (tablero[i][col]==Ficha.VACIA) {
				return false;
			}
		}
		return true;
	}
}