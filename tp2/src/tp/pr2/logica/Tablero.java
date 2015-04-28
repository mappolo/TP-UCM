package tp.pr2.logica;
import java.lang.StringBuilder;

public class Tablero {
	private Ficha[][] tablero;
	private int ancho;
	private int alto;
	
	public Tablero(int tx, int ty) { //Construye un tablero vacío
		if ((tx<=0)||(ty<=0)){
			tx = 1;
			ty = 1;
		}
		this.ancho = tx;
		this.alto = ty;
		tablero = new Ficha[ty][tx];
		reset();
	}
	
	public int getAncho() { //Método para obtener el ancho del tablero
		return ancho;
	}

	public void setAncho(int ancho) { ////Permite cambiar el ancho del tablero
		this.ancho = ancho;
	}

	public int getAlto() { //Método para obtener el alto del tablero
		return alto;
	}

	public void setAlto(int alto) { //Permite cambiar el alto del tablero
		this.alto = alto;
	}

	public Ficha getCasilla(int x, int y) { //Método para acceder a la información de una casilla o celda concreta
		if ((x<1)||(x>ancho)) {
			return Ficha.VACIA;
		}
		if ((y<1)||(y>alto)) {
			return Ficha.VACIA;
		}
		return tablero[y-1][x-1];
	}
	
	public void setCasilla(int x, int y, Ficha color) { //Permite especificar el nuevo contenido de una casilla
		if ((x>=1)&&(x<=ancho)&&(y>=1)&&(y<=alto)) {
			tablero[y-1][x-1] = color;
		}
	}
		
	public void reset() { //Reinicia el tablero
		for (int i=0; i<alto; i++) {
			for (int j=0; j<ancho; j++) {
				tablero[i][j] = Ficha.VACIA;
			}
		}
	}
	
	public String toString() { //Metodo toString
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
			Cadb.append (i);
		}
		Cadb.append ("\n");
		
		return Cadb.toString();
	}
	
	public boolean isLleno() { //Comprueba si el tablero esta lleno
		for (int i=0; i<ancho; i++) {
			if (! isColumnaLlena(i)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isColumnaLlena(int col) { //Comprueba si una columna esta llena
		for (int i=0; i<alto; i++) {
			if (tablero[i][col]==Ficha.VACIA) {
				return false;
			}
		}
		return true;
	}
}