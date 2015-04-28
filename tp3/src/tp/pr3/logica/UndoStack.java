package tp.pr3.logica;

public class UndoStack {
	private Tablero[] undoTableros;
	private Movimiento[] undoMovimientos;
	private int numUndo;
	
	private static final int MAX_UNDO = 10;
	
	/**
	 * Constructor de la clase
	 */
	public UndoStack() {
		undoTableros = new Tablero[MAX_UNDO];
		undoMovimientos = new Movimiento[MAX_UNDO];
		numUndo = 0;
	}

	/**
	 * Añade un movimiento a la lista de movimientos a deshacer (apila)
	 * @param mov es el movimiento a ejecutar
	 * @param tab de la partida
	 */
	public void agnadirUndo(Movimiento mov, Tablero tab) {
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
	
	/**
	 * Resetea el contador de movimientos a deshacer
	 */
	public void reset() {
		numUndo = 0;
	}
	
	/**
	 * Deshace el movimiento (desapila)
	 * @return si se ha podido deshacer
	 */
	public boolean undo() {
		if (numUndo == 0) {
			return false;
		}
		numUndo--;
		
		undoMovimientos[numUndo].undo(undoTableros[numUndo]);

		return true;
	}
}
