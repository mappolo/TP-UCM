package tp.pr3.logica;

/**
 * Tipo enumerado para las distintas fichas
 */
public enum Ficha {
	
	BLANCA, NEGRA, VACIA;

	/**
	 * En funcion del tipo de ficha devuelve O X o nada
	 */
	public String toString(){
		
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
