package tp.pr2.logica;

public class UndoStack {
	private Tablero[] undoTableros;
	private Movimiento[] undoMovimientos;
	private int numUndo;
	
	private static final int MAX_UNDO = 10;
	
	public UndoStack() { //Constructor de la clase
		undoTableros = new Tablero[MAX_UNDO];
		undoMovimientos = new Movimiento[MAX_UNDO];
		numUndo = 0;
	}

	public void agnadirUndo(Movimiento mov, Tablero tab) { //Añade un movimiento a la lista de movimientos a deshacer
		if (numUndo == MAX_UNDO) {
			for (int i=0; i<numUndo-1; i++) {
				undoMovimientos[i] = undoMovimientos[i+1];
			}
			numUndo--;
		}
		undoMovimientos[numUndo] = mov;
		undoTableros[numUndo] = tab;
		numUndo++;
	}
	
	public void reset() { //Resetea el contador de movimientos a deshacer
		numUndo = 0;
	}
	
	public boolean undo() { //Deshace el movimiento
		if (numUndo == 0) {
			return false;
		}
		numUndo--;
		
		undoMovimientos[numUndo].undo(undoTableros[numUndo]);
		return true;
	}
}
