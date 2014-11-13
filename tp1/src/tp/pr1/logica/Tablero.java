package tp.pr1.logica;
import java.lang.StringBuilder;

public class Tablero {
	private Ficha[][] tablero;
	private int ancho;
	private int alto;
	
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
	
	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public Ficha getCasilla(int x, int y) {
		if ((x<1)||(x>ancho)) {
			return Ficha.VACIA;
		}
		if ((y<1)||(y>alto)) {
			return Ficha.VACIA;
		}
		return tablero[y-1][x-1];
	}
	
	public void setCasilla(int x, int y, Ficha color) {
		if ((x>=1)&&(x<=ancho)&&(y>=1)&&(y<=alto)) {
			tablero[y-1][x-1] = color;
		}
	}
		
	public void reset() {
		for (int i=0; i<alto; i++) {
			for (int j=0; j<ancho; j++) {
				tablero[i][j] = Ficha.VACIA;
			}
		}
	}
	
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
			Cadb.append (i);
		}
		Cadb.append ("\n");
		
		return Cadb.toString();
	}
	
	public boolean isLleno() {
		for (int i=0; i<ancho; i++) {
			if (! isColumnaLlena(i)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isColumnaLlena(int col) {
		for (int i=0; i<alto; i++) {
			if (tablero[i][col]==Ficha.VACIA) {
				return false;
			}
		}
		return true;
	}
}