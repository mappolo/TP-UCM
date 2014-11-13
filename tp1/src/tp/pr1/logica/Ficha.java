package tp.pr1.logica;

public enum Ficha {
	
	BLANCA, NEGRA, VACIA;

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
