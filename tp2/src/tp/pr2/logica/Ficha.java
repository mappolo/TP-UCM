package tp.pr2.logica;

public enum Ficha { //Tipo enumerado para las distintas fichas
	
	BLANCA, NEGRA, VACIA;

	public String toString(){ //En funcion del tipo de ficha devuelve O X o nada
		
		if (this == VACIA){
			return " ";
		}
		else if (this == BLANCA){
			return "O";
		}
		else 
			return "X";
	}
}
